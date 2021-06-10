package com.johnsons.restaurant;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.johnsons.restaurant.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class MenuItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    MenuDatabase db ;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MenuItemFragment() {
    }

    List<MenuItem> list = new ArrayList<MenuItem>();
    MenuRecyclerViewAdapter adapter;

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MenuItemFragment newInstance(int columnCount) {
        MenuItemFragment fragment = new MenuItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        Button addButton = view.findViewById(R.id.addMenuButton);
        addButton.setOnClickListener(buttonView -> {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            Fragment addMenuItemFragment = AddMenuItemFragment.newInstance(null, null);
            fragmentManager.beginTransaction().replace(R.id.fragment_container, addMenuItemFragment).addToBackStack(null).commit();
        });


        db = MenuDatabase.getDatabase(getActivity());


        adapter = new MenuRecyclerViewAdapter(list);
        // Set the adapter
        RecyclerView recyclerView = view.findViewById(R.id.list);

        Context context = view.getContext();

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);



        MenuDatabase.databaseWriteExecutor.execute(() -> {

        });

        new MenuTask().execute(null, null, null);

        return view;
    }

    private  class MenuTask extends AsyncTask<Void, Void, Integer> {



        @Override
        protected Integer doInBackground(Void... params) {

            list = db.wordDao().getMenuItems();
            for (MenuItem item : list) {
                Log.e("test", item.getName());
            }


            return 0;
        }

        @Override
        protected void onPostExecute(Integer result) {
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }
    }
}
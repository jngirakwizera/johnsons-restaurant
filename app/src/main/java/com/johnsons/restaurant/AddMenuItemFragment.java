package com.johnsons.restaurant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddMenuItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMenuItemFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddMenuItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMenuItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMenuItemFragment newInstance(String param1, String param2) {
        AddMenuItemFragment fragment = new AddMenuItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_menu_item, container, false);
        MenuDatabase db = MenuDatabase.getDatabase(getActivity());
        MenuDao menuDao  = db.wordDao();
        EditText name = v.findViewById(R.id.name);
        EditText quantity = v.findViewById(R.id.quantity);
        Button saveButton = v.findViewById(R.id.save_button);
        saveButton.setOnClickListener(v1 -> MenuDatabase.databaseWriteExecutor.execute(() -> {
            MenuItem item = new MenuItem( name.getText().toString(), quantity.getText().toString() );
            menuDao.insert(item);

        }));
        return v;
    }
}
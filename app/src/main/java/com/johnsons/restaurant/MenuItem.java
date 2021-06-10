package com.johnsons.restaurant;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "menuitem")
public class MenuItem {
    @NonNull
    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    @PrimaryKey
    @NonNull
    private String name;
    private String quantity;

    public MenuItem(String name, String quantity){
        this.name = name;
        Log.e("menuItem", "quantity is " + quantity);
        this.quantity = quantity;
    }
}

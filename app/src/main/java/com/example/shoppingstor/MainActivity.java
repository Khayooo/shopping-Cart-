package com.example.shoppingstor;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Item> itemList;
    private ArrayList<Item> cartList = new ArrayList<>();
    private Item selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<>();
        itemList.add(new Item("Smartphone", 50000));
        itemList.add(new Item("Laptop", 70000));
        itemList.add(new Item("Wireless Earbuds", 5000));
        itemList.add(new Item("Smartwatch", 4000));
        itemList.add(new Item("Tablet", 40000));
        itemList.add(new Item("Bluetooth Speaker", 2000));
        itemList.add(new Item("Handsfree", 3000));
        itemList.add(new Item("Digital Camera", 150000));
        itemList.add(new Item("Mobile Charger", 500));
        itemList.add(new Item("256 GB SSD", 6000));

        ItemAdapter adapter = new ItemAdapter(this, itemList);
        ListView listView = findViewById(R.id.item_list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = itemList.get(position);
            }
        });

        Button addButton = findViewById(R.id.add_button);
        Button goToCartButton = findViewById(R.id.go_to_cart_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem != null) {
                    if (cartList.size() < 10) {
                        cartList.add(selectedItem);
                        Toast.makeText(MainActivity.this, selectedItem.getName() + " added to cart", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Cart is full!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please select an item", Toast.LENGTH_SHORT).show();
                }
            }
        });

        goToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                intent.putParcelableArrayListExtra("cartList", cartList);
                startActivity(intent);
            }



        });
    }
}

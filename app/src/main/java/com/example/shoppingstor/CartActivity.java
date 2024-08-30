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

public class CartActivity extends AppCompatActivity {

    private ArrayList<Item> cartList;
    private ItemAdapter adapter;
    private int selectedItemPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Get the cartList from the intent
        cartList = getIntent().getParcelableArrayListExtra("cartList");

        // Initialize the adapter and ListView
        adapter = new ItemAdapter(this, cartList);
        ListView listView = findViewById(R.id.cart_list_view);
        listView.setAdapter(adapter);

        // Handle item selection
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItemPosition = position;
            }
        });

        // Remove item from cart
        Button removeButton = findViewById(R.id.remove_button);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItemPosition >= 0 && selectedItemPosition < cartList.size()) {
                    cartList.remove(selectedItemPosition);
                    adapter.notifyDataSetChanged();
                    selectedItemPosition = -1;
                    Toast.makeText(CartActivity.this, "Item removed from cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CartActivity.this, "Please select an item to remove", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Navigate to the Calculate page
        Button calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, CalculateActivity.class);
                intent.putParcelableArrayListExtra("cartList", (ArrayList<? extends Parcelable>) cartList);
                startActivity(intent);
            }
        });
    }
}

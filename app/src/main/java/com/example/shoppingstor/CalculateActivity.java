package com.example.shoppingstor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CalculateActivity extends AppCompatActivity {

    private ArrayList<Item> cartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        // Get the cartList from the intent
        cartList = getIntent().getParcelableArrayListExtra("cartList");

        // Check if cartList is null
        if (cartList == null) {
            Toast.makeText(this, "Cart is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Calculate totals
        int totalAmount = 0;
        for (Item item : cartList) {
            totalAmount += item.getPrice();
        }
        double salesTax = totalAmount * 0.175;
        double grossAmount = totalAmount + salesTax;

        // Display the results
        TextView totalItemsView = findViewById(R.id.total_items);
        TextView totalAmountView = findViewById(R.id.total_amount);
        TextView salesTaxView = findViewById(R.id.sales_tax);
        TextView grossAmountView = findViewById(R.id.gross_amount);

        totalItemsView.setText("Total Items: " + cartList.size());
        totalAmountView.setText("Total Amount: " + totalAmount + " Rs");
        salesTaxView.setText("Sales Tax (17.5%): " + salesTax + " Rs");
        grossAmountView.setText("Gross Amount: " + grossAmount + " Rs");

        // Close the app button
        Button closeButton = findViewById(R.id.close_app_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity(); // Closes the app
            }
        });
    }
}

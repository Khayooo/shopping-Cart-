package com.example.shoppingstor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shoppingstor.Item;

import java.util.List;

class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter(Context context, List<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        TextView itemName = convertView.findViewById(android.R.id.text1);
        TextView itemPrice = convertView.findViewById(android.R.id.text2);

        itemName.setText(item.getName());
        itemPrice.setText(item.getPrice() + " Rs");

        return convertView;
    }
}

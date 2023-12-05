package com.example.androidpizza;

import static com.example.androidpizza.PizzaMaker.createPizza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.graphics.Color;

import java.util.ArrayList;

public class storeOrderFragment extends Fragment {
    private static final double taxMult = .06625; // % of the tax

    private ListView orderView;
    private Button deleteOrder;
    private TextView totalPrice;
    private Spinner orderSelector;

    private int selectedOrder = -1;

    public storeOrderFragment () {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_history, container, false);
        int numOrders = Store.getInstance().getOrderHistory().getNumOrders();
        ArrayList<Integer> orders = new ArrayList<Integer>();
        for (int i = 0; i < numOrders; i++) {
            if (Store.getInstance().getOrderHistory().getOrder(i).getSize() != 0) {
                orders.add(i+1);
            }
        }
        orderSelector = view.findViewById(R.id.orderSelector);
        deleteOrder = view.findViewById(R.id.deleteOrder);
        totalPrice = view.findViewById(R.id.totalPrice);
        orderView = view.findViewById(R.id.orderView);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, orders);
        orderSelector.setAdapter(adapter);
        if (orders.size() > 0) {
            selectedOrder = orders.get(0);
        }
        setDeleteListener(view);
        setSpinnerListener(view);
        return view;
    }
    private void setDeleteListener(View view) {
        deleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOrder < 0) {
                    Toast.makeText(getContext(), "Order Not Selected!", Toast.LENGTH_LONG).show();
                }
                else {
                    StoreOrders storeOrders = Store.getInstance().getOrderHistory();
                    Order toDelete = storeOrders.getOrder(selectedOrder-1);
                    toDelete.clearOrder();
                    totalPrice.setText("Total Price: ");
                    ArrayList<Integer> orders = new ArrayList<Integer>();
                    for (int i = 0; i < storeOrders.getNumOrders(); i++) {
                        if (Store.getInstance().getOrderHistory().getOrder(i).getSize() != 0) {
                            orders.add(i+1);
                        }
                    }
                    ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_list_item_1, orders);
                    orderSelector.setAdapter(adapter);
                    selectedOrder = orders.get(0);
                    ArrayAdapter<Pizza> deletedAdapter = new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_list_item_1, toDelete.getOrderItems());
                    orderView.setAdapter(deletedAdapter);
                }
            }
        });
    }

    private void setSpinnerListener(View view) {
        orderSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedValue = (int) parent.getItemAtPosition(position);
                selectedOrder = selectedValue;
                Order toDisplay = Store.getInstance().getOrderHistory().getOrder(selectedValue-1);
                ArrayAdapter<Pizza> listAdapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_list_item_1, toDisplay.getOrderItems());
                orderView.setAdapter(listAdapter);
                double finalPrice = toDisplay.orderPrice() + (toDisplay.orderPrice() * taxMult);
                totalPrice.setText("Total Price: " + String.format("%.2f", finalPrice));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


}

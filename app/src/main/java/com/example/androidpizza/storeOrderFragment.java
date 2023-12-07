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

/**
 * Handles all the functions of the view Order History.
 * Allows you to select an order, view the entire order and its total, and delete if necessary.
 * @author Karthik Gangireddy, Vineal Sunkara
 */
public class storeOrderFragment extends Fragment {
    private static final double taxMult = .06625; // % of the tax

    private ListView orderView;
    private Button deleteOrder;
    private TextView totalPrice;
    private Spinner orderSelector;

    private int selectedOrder = -1;

    /**
     * Default constructor for the store order Fragment.
     */
    public storeOrderFragment () {

    }

    /**
     * Creates the view that everything is handled on.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return the view for everything to be displayed on.
     */
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

    /**
     * Creates the function that handles the deletion of an order that is selected.
     * @param view the view that everything is handled on to fetch elements.
     */
    private void setDeleteListener(View view) {
        deleteOrder.setOnClickListener(new View.OnClickListener() {
            /**
             * Function for when the delete button is clicked.
             * @param v The view that was clicked.
             */
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
                    if (orders.size() > 1) {
                        selectedOrder = orders.get(0);
                    }
                    else {
                        selectedOrder = -1;
                    }
                    ArrayAdapter<Pizza> deletedAdapter = new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_list_item_1, toDelete.getOrderItems());
                    orderView.setAdapter(deletedAdapter);
                }
            }
        });
    }

    /**
     * Initializes the spinner with all non empty orders, excluding the deleted ones.
     * @param view the view that everything is handled on to fetch elements.
     */
    private void setSpinnerListener(View view) {
        orderSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Updates the listView when the spinner option is clicked for a valid order.
             * @param parent The AdapterView where the selection happened
             * @param view The view within the AdapterView that was clicked
             * @param position The position of the view in the adapter
             * @param id The row id of the item that is selected
             */
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

            /**
             * Function for when nothing is selected, nothing happens.
             * @param parent The AdapterView that now contains no selected item.
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


}

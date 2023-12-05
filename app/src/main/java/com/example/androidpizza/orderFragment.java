package com.example.androidpizza;
import android.icu.text.Transliterator;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static com.example.androidpizza.PizzaMaker.createPizza;

import org.w3c.dom.Text;

import java.sql.Array;
import java.util.ArrayList;
public class orderFragment extends Fragment{
    private static final double taxMult = .06625; // % of the tax
    private TextView orderNo;
    private TextView orderPrice;
    private TextView orderTax;
    private TextView totalPrice;
    private ListView orderItems;
    private Button deletePizza;
    private Button placeOrder;
    private View selectedView = null;
    private int selectedPosition = -1;

    public orderFragment () {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_view, container, false);
        orderItems = view.findViewById(R.id.orderItems);
        Order curr = Store.getInstance().getCurrentOrder();
        ArrayAdapter<Pizza> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, curr.getOrderItems());
        orderItems.setAdapter(adapter);
        createListListener(view);
        initializePrices(view);
        createPizzaDeleter(view);
        createOrderPlacer(view);
        return view;
    }

    private void createOrderPlacer(View view) {
        placeOrder = view.findViewById(R.id.placeOrder);
        StoreOrders storeOrder = Store.getInstance().getOrderHistory();
        Order curr = Store.getInstance().getCurrentOrder();
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (curr.getSize() == 0) {
                    Toast.makeText(getContext(), "Empty order", Toast.LENGTH_LONG).show();
                }
                else {
                    if (storeOrder.getNumOrders() == 0) {
                        storeOrder.addOrder(curr);
                    }
                    Store.getInstance().setNextOrder();
                    orderNo.setText("Order Number: " + Store.getInstance().getOrderHistory().getNextOrder());
                    ArrayAdapter<Pizza> adapter = new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_list_item_1, Store.getInstance()
                            .getCurrentOrder().getOrderItems());
                    orderItems.setAdapter(adapter);
                    Order reset = Store.getInstance().getCurrentOrder();
                    orderPrice.setText("Order Price: " + String.format("%.2f",reset.orderPrice()));
                    orderTax.setText("Tax: " + String.format("%.2f", reset.orderPrice() * taxMult));
                    double finalPrice = reset.orderPrice() + (reset.orderPrice() * taxMult);
                    totalPrice.setText("Total Price: " + String.format("%.2f", finalPrice));

                }
            }
        });
    }

    private void createPizzaDeleter(View view) {
        deletePizza = view.findViewById(R.id.deletePizza);
        deletePizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition < 0) {
                    Toast.makeText(getContext(), "Item not selected!", Toast.LENGTH_LONG).show();
                }
                else {
                    Order curr = Store.getInstance().getCurrentOrder();
                    curr.removeItem(selectedPosition);
                    ArrayAdapter<Pizza> adapter = new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_list_item_1, curr.getOrderItems());
                    orderItems.setAdapter(adapter);
                    orderPrice = view.findViewById(R.id.orderPrice);
                    orderPrice.setText("Order Price: " + String.format("%.2f",curr.orderPrice()));
                    orderTax = view.findViewById(R.id.taxAmt);
                    orderTax.setText("Tax: " + String.format("%.2f", curr.orderPrice() * taxMult));
                    double finalPrice = curr.orderPrice() + (curr.orderPrice() * taxMult);
                    totalPrice = view.findViewById(R.id.totalPrice);
                    totalPrice.setText("Total Price: " + String.format("%.2f", finalPrice));
                }
            }
        });
    }

    private void createListListener(View view) {
        orderItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Check if the same view is clicked again
                if (selectedPosition == position) {
                    // If clicked again, toggle its state
                    boolean isSelected = view.getTag() != null && (boolean) view.getTag();
                    if (isSelected) {
                        view.setBackgroundColor(Color.TRANSPARENT); // or any default color
                        view.setTag(false); // Update tag to reflect unselected state
                        selectedPosition = -1;
                    } else {
                        view.setBackgroundColor(Color.YELLOW); // Highlight color
                        view.setTag(true); // Update tag to reflect selected state
                        selectedPosition = position;
                    }
                } else {
                    // For a new selection
                    if (selectedView != null) {
                        selectedView.setBackgroundColor(Color.TRANSPARENT); // Reset previous selection
                        selectedView.setTag(false); // Update tag of previous view
                    }
                    view.setBackgroundColor(Color.YELLOW); // Highlight new selection
                    view.setTag(true); // Update tag of new view

                    // Update reference to the new selection
                    selectedView = view;
                    selectedPosition = position;
                }
            }
        });
    }

    private void initializePrices(View view) {
        orderNo = view.findViewById(R.id.orderNum);
        Order curr = Store.getInstance().getCurrentOrder();
        if (Store.getInstance().getOrderHistory().getNextOrder() < 1) {
            orderNo.setText("Order Number: 1");

        }
        else {
            orderNo.setText("Order Number: " + Store.getInstance().getOrderHistory().getNextOrder());
        }
        orderPrice = view.findViewById(R.id.orderPrice);
        orderPrice.setText("Order Price: " + String.format("%.2f",curr.orderPrice()));
        orderTax = view.findViewById(R.id.taxAmt);
        orderTax.setText("Tax: " + String.format("%.2f", curr.orderPrice() * taxMult));
        double finalPrice = curr.orderPrice() + (curr.orderPrice() * taxMult);
        totalPrice = view.findViewById(R.id.totalPrice);
        totalPrice.setText("Total Price: " + String.format("%.2f", finalPrice));
    }
}

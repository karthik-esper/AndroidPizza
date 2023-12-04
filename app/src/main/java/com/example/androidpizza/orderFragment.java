package com.example.androidpizza;
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
        initializePrices(view);
        return view;
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
    }
}

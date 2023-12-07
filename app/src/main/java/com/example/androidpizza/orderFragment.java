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

/**
 * Handles the current order display as well as deletion and placing operations.
 * @author Karthik Gangireddy, Vineal Sunkara
 */
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

    /**
     * default constructor for the order Fragment.
     */
    public orderFragment () {

    }

    /**
     * Handles creating the view that everythign happen son.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return the view created.
     */
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

    /**
     * Initializes the function for the button that places the order.
     * @param view the view that everything is handled on to fetch elements.
     */
    private void createOrderPlacer(View view) {
        placeOrder = view.findViewById(R.id.placeOrder);
        StoreOrders storeOrder = Store.getInstance().getOrderHistory();
        Order curr = Store.getInstance().getCurrentOrder();
        placeOrder.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles the placing of the order once the button is clicked.
             * @param v The view that was clicked.
             */
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

    /**
     * Handles the deletion of the selected item from the order.
     * @param view the view that everything is handled on to fetch elements.
     */
    private void createPizzaDeleter(View view) {
        deletePizza = view.findViewById(R.id.deletePizza);
        deletePizza.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles the deletion of a selected pizza.
             * @param v The view that was clicked.
             */
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

    /**
     * Handles the selection of a pizza from the order.
     * @param view the view that everything is handled on to fetch elements.
     */
    private void createListListener(View view) {
        orderItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Handles the selection of a pizza from the list.
             * @param parent The AdapterView where the click happened.
             * @param view The view within the AdapterView that was clicked (this
             *            will be a view provided by the adapter)
             * @param position The position of the view in the adapter.
             * @param id The row id of the item that was clicked.
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selectedPosition == position) {
                    boolean isSelected = view.getTag() != null && (boolean) view.getTag();
                    if (isSelected) {
                        view.setBackgroundColor(Color.TRANSPARENT);
                        view.setTag(false);
                        selectedPosition = -1;
                    } else {
                        view.setBackgroundColor(Color.YELLOW);
                        view.setTag(true);
                        selectedPosition = position;
                    }
                } else {
                    if (selectedView != null) {
                        selectedView.setBackgroundColor(Color.TRANSPARENT);
                        selectedView.setTag(false);
                    }
                    view.setBackgroundColor(Color.YELLOW);
                    view.setTag(true);
                    selectedView = view;
                    selectedPosition = position;
                }}});
    }

    /**
     * Sets the price of the order based on the total of the elements provided, including tax.
     * @param view the view that everything is handled on to fetch elements.
     */
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

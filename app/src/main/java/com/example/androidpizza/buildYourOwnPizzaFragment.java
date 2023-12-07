package com.example.androidpizza;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

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
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static com.example.androidpizza.PizzaMaker.createPizza;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class buildYourOwnPizzaFragment extends Fragment{
    private TextView Price;
    private ListView Toppings;
    private Spinner sizes;
    private CheckBox extraSauce;
    private CheckBox extraCheese;
    private Button placeOrder;
    private RecyclerView.Adapter pizzaAdapter;
    private boolean recyclerItemSelected = false;
    private boolean sizeSelected = false;

    private final int pizzaImages = R.drawable.byozza;

    private RecyclerView recyclerBuildYourOwnPizzaView;

    public buildYourOwnPizzaFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.specialty_pizza, container, false);
        Spinner spinner = view.findViewById(R.id.mySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        extraCheese = view.findViewById(R.id.extraCheese);
        extraSauce = view.findViewById(R.id.extraSauce);
        extraSauce.setEnabled(false);
        extraCheese.setEnabled(false);
        spinner.setEnabled(false);
        spinner.setAdapter(adapter);
        //setPizzaChoices(view);
        //setSpinnerListener(view);
        //setCheeseListener(view);
        //setSauceListener(view);
        //createPizzaButton(view);
        return view;
    }
}

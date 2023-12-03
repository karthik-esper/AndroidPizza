package com.example.androidpizza;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class specialtyFragment extends Fragment {
    private TextView Price;
    private RecyclerView recyclerView;
    private ListView Toppings;
    private Spinner sizes;
    private RadioButton extraSauce;
    private RadioButton extraCheese;
    private Button placeOrder;
    private RecyclerView.Adapter pizzaAdapter;

    private ArrayList<Topping> ToppingList = new ArrayList<Topping>();

    private final String[] pizzaNames = {"Supreme", "Deluxe", "Seafood", "Pepperoni",
            "Meatzza", "Hawaiian", "Spicy", "Veggie", "SurfTurf", "Sussy"};

    private final int[] pizzaImages = {R.drawable.supremepizza, R.drawable.deluxepizza, R.drawable.seafoodpizza,
            R.drawable.pepperonipizza, R.drawable.meatzzapizza, R.drawable.hawaiianpizza,
            R.drawable.spicypizza,R.drawable.veggiepizza,R.drawable.surfturfpizza, R.drawable.sussypizza};

    public specialtyFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.specialty_pizza, container, false);
        Spinner spinner = view.findViewById(R.id.mySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return view;
    }
}

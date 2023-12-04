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

import java.util.ArrayList;

public class specialtyFragment extends Fragment {
    private TextView Price;
    private RecyclerView recyclerPizzaView;
    private ListView Toppings;
    private Spinner sizes;
    private CheckBox extraSauce;
    private CheckBox extraCheese;
    private Button placeOrder;
    private RecyclerView.Adapter pizzaAdapter;
    private boolean recyclerItemSelected = false;
    private boolean sizeSelected = false;

    private String selectedPizzaType;
    private String selectedPizzaSize;

    private final String[] pizzaNames = {"Supreme", "Deluxe", "Seafood", "Pepperoni",
            "Meatzza", "Hawaiian", "Spicy", "Veggie", "SurfTurf", "Sussy"};
    private final String[] pizzaDetails = {"Supreme Supreme!!", "Supreme but better!", "Fishy flavor!",
            "Good ol' Pepperoni!", "Lots of Meats!", "To annoy the Italians!", "For the Indians!",
            "For the boring Indians!", "For those with heart!", "Gross but go ahead please!"};

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
        extraCheese = view.findViewById(R.id.extraCheese);
        extraSauce = view.findViewById(R.id.extraSauce);
        extraSauce.setEnabled(false);
        extraCheese.setEnabled(false);
        spinner.setEnabled(false);
        spinner.setAdapter(adapter);
        setPizzaChoices(view);
        setSpinnerListener(view);
        setCheeseListener(view);
        setSauceListener(view);
        createPizzaButton(view);
        return view;
    }

    private void createPizzaButton(View view) {
        placeOrder = view.findViewById(R.id.PizzaButton);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerItemSelected) {
                    Pizza toMake = createPizza(selectedPizzaType);
                    if (sizeSelected) {
                        toMake.setSize(Size.valueOf(selectedPizzaSize));
                        if (extraCheese.isChecked()) {toMake.setExtraCheese(true);}
                        if (extraSauce.isChecked()) {toMake.setExtraSauce(true);}
                        Store.getInstance().getCurrentOrder().addToOrder(toMake);
                        System.out.println(toMake.toString());
                        clearitems(view);
                        Price.setText("Price: ");
                        Toast.makeText(getContext(), "Creating Pizza", Toast.LENGTH_LONG).show();
                    }
                    else {Toast.makeText(getContext(), "size not selected", Toast.LENGTH_SHORT).show();}
                }
                else {Toast.makeText(getContext(), "no Pizza Type Selected", Toast.LENGTH_SHORT).show();}
            }
        });
    }

    private void clearitems(View view) {
        extraCheese.setChecked(false);
        extraSauce.setChecked(false);
        extraCheese.setEnabled(false);
        extraSauce.setEnabled(false);
        sizes = view.findViewById(R.id.mySpinner);
        sizes.setSelection(-1);
        sizes.setEnabled(false);

    }

    private void setCheeseListener(View view) {
        extraCheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (extraCheese.isChecked()) {
                    Pizza toMake = createPizza(selectedPizzaType);
                    toMake.setSize(Size.valueOf(selectedPizzaSize));
                    if (extraSauce.isChecked()) {
                        toMake.setExtraSauce(true);
                    }
                    toMake.setExtraCheese(true);
                    Price.setText("Price: " + String.format("%.2f", toMake.price()));
                }
                if (!extraCheese.isChecked()) {
                    Pizza toMake = createPizza(selectedPizzaType);
                    toMake.setSize(Size.valueOf(selectedPizzaSize));
                    if (extraSauce.isChecked()) {
                        toMake.setExtraSauce(true);
                    }
                    toMake.setExtraCheese(false);
                    Price.setText("Price: " + String.format("%.2f", toMake.price()));
                }
            }
        });
    }

    private void setSauceListener(View view) {
        extraSauce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (extraSauce.isChecked()) {
                    Pizza toMake = createPizza(selectedPizzaType);
                    toMake.setSize(Size.valueOf(selectedPizzaSize));
                    if (extraCheese.isChecked()) {
                        toMake.setExtraCheese(true);
                    }
                    toMake.setExtraSauce(true);
                    Price.setText("Price: " + String.format("%.2f", toMake.price()));
                }
                if (!extraSauce.isChecked()) {
                    Pizza toMake = createPizza(selectedPizzaType);
                    toMake.setSize(Size.valueOf(selectedPizzaSize));
                    if (extraCheese.isChecked()) {
                        toMake.setExtraSauce(true);
                    }
                    toMake.setExtraSauce(false);
                    Price.setText("Price: " + String.format("%.2f", toMake.price()));
                }
            }
        });
    }

    private void setSpinnerListener(View view) {
        Spinner spinner = view.findViewById(R.id.mySpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (recyclerItemSelected) {
                    CharSequence selectedValue = (CharSequence) parent.getItemAtPosition(position);
                    Pizza toMake = createPizza(selectedPizzaType);
                    char size = selectedValue.charAt(0);
                    if (size == 's' || size == 'S') {
                        toMake.setSize(Size.S);
                    }
                    if (size == 'm' || size == 'M') {
                        toMake.setSize(Size.M);
                    }
                    if (size == 'l' || size == 'L') {
                        toMake.setSize(Size.L);
                    }
                    selectedPizzaSize = String.valueOf(size);
                    sizeSelected = true;
                    extraSauce.setEnabled(true);
                    extraCheese.setEnabled(true);
                    Price.setText("Price: " + String.format("%.2f", toMake.price()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    private void setPizzaChoices(View view) {
        recyclerPizzaView = view.findViewById(R.id.myRecyclerView);
        recyclerPizzaView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerPizzaView.setItemAnimator(new DefaultItemAnimator());
        recyclerPizzaView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        pizzaAdapter = new pizzaAdapter(getContext(), pizzaNames, pizzaDetails, pizzaImages, new pizzaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                recyclerItemSelected = true;
                sizeSelected = false;
                selectedPizzaType = pizzaNames[position];
                Pizza toMake = createPizza(selectedPizzaType);
                Toppings = view.findViewById(R.id.Toppings);
                ArrayAdapter<Topping> adapter = new ArrayAdapter<>(getContext(),
                        android.R.layout.simple_list_item_1, toMake.getToppings());
                Toppings.setAdapter(adapter);
                Price = view.findViewById(R.id.Price);
                Price.setText("Price: " + String.format("%.2f", toMake.price()));
                Spinner spinner = view.findViewById(R.id.mySpinner);
                extraSauce.setEnabled(false);
                extraSauce.setChecked(false);
                extraCheese.setEnabled(false);
                extraCheese.setChecked(false);
                sizeSelected = false;
                spinner.setEnabled(true);
            }
        });
        recyclerPizzaView.setAdapter(pizzaAdapter);
    }
}

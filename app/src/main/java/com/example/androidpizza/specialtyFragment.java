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

/**
 * The class that handles every operation related to the specialty pizza orders.
 * Works within a fragment.
 * @author Karthik Gangireddy, Vineal Sunkara
 */
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

    /**
     * Default constructor for the fragment.
     */
    public specialtyFragment() {

    }

    /**
     * Creates the view that everything will be displayed on.
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

    /**
     * Handles the clicking of the createPizza button, which creates and adds a pizza to current order.
     * @param view the view that everything is handled on to fetch elements.
     */
    private void createPizzaButton(View view) {
        placeOrder = view.findViewById(R.id.PizzaButton);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles the creation of a pizza and addition to order once button is clicked.
             * @param v The view that was clicked.
             */
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
                        sizeSelected = false;
                        recyclerItemSelected = false;
                        createAlert("Pizza Made");
                    }
                    else {
                        Toast.makeText(getContext(), "size not selected", Toast.LENGTH_SHORT).show();
                        createAlert("No Size");}
                }
                else {
                    Toast.makeText(getContext(), "no Pizza Type Selected", Toast.LENGTH_SHORT).show();
                    createAlert("No Type");}
            }
        });
    }

    /**
     * Creates alerts based on the provided error/success conditions.
     * @param type a string describing the error and the necessary alert.
     */
    private void createAlert(String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        if (type.equals("Pizza Made")) {
            builder.setMessage("Pizza was created")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {}
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        if (type.equals("No Size")) {
            builder.setMessage("Size not Selected")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {}
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        if (type.equals("No Type")) {
            builder.setMessage("Type not Selected")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {}
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }


    }

    /**
     * Clears certain boxes after the Pizza Button works.
     * @param view the view that everything is handled on to fetch elements.
     */
    private void clearitems(View view) {
        extraCheese.setChecked(false);
        extraSauce.setChecked(false);
        extraCheese.setEnabled(false);
        extraSauce.setEnabled(false);
        sizes = view.findViewById(R.id.mySpinner);
        sizes.setSelection(0);
        sizes.setEnabled(false);

    }

    /**
     * Handles the checkbox that selects whether the pizza has extra cheese or not with live updates.
     * @param view the view that everything is handled on to fetch elements.
     */
    private void setCheeseListener(View view) {
        extraCheese.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles the selection and deselection of checkbox for extra cheese.
             * @param v The view that was clicked.
             */
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

    /**
     * Handles the checkbox that selects whether the pizza has extra sauce or not with live updates.
     * @param view the view that everything is handled on to fetch elements.
     */
    private void setSauceListener(View view) {
        extraSauce.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles the selection and deselection of checkbox for extra sauce.
             * @param v The view that was clicked.
             */
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

    /**
     * Creates the necessary items for the spinner that selects sizes to function.
     * @param view the view that everything is handled on to fetch elements.
     */
    private void setSpinnerListener(View view) {
        Spinner spinner = view.findViewById(R.id.mySpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /**
             * Handles when a size is selected from the spinner and updated price.
             * @param parent The AdapterView where the selection happened
             * @param view The view within the AdapterView that was clicked
             * @param position The position of the view in the adapter
             * @param id The row id of the item that is selected
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (recyclerItemSelected) {
                    CharSequence selectedValue = (CharSequence) parent.getItemAtPosition(position);
                    Pizza toMake = createPizza(selectedPizzaType);
                    char size = selectedValue.charAt(0);
                    if (size == 's' || size == 'S') {toMake.setSize(Size.S);}
                    if (size == 'm' || size == 'M') {toMake.setSize(Size.M);}
                    if (size == 'l' || size == 'L') {toMake.setSize(Size.L);}
                    selectedPizzaSize = String.valueOf(size);
                    sizeSelected = true;
                    extraSauce.setChecked(false);
                    extraCheese.setChecked(false);
                    Price.setText("Price: " + String.format("%.2f", toMake.price()));
                }
            }

            /**
             * Handles when nothing is selected, nothing happens.
             * @param parent The AdapterView that now contains no selected item.
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Uses the programAdapter to handle the necessary values for the recyclerView.
     * Allows other items to be selected once something is chosen from the recycler view.
     * @param view the view that everything is handled on to fetch elements.
     */
    private void setPizzaChoices(View view) {
        recyclerPizzaView = view.findViewById(R.id.myRecyclerView);
        recyclerPizzaView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerPizzaView.setItemAnimator(new DefaultItemAnimator());
        recyclerPizzaView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        pizzaAdapter = new pizzaAdapter(getContext(), pizzaNames, pizzaDetails, pizzaImages, new pizzaAdapter.OnItemClickListener() {
            /**
             * Handles the selection of a pizza from the recycler view.
             * @param position position of the selected item.
             */
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
                extraSauce.setChecked(false);
                extraSauce.setEnabled(true);
                extraCheese.setChecked(false);
                extraCheese.setEnabled(true);
                sizeSelected = true;
                selectedPizzaSize = "S";
                spinner.setEnabled(true);
            }
        });
        recyclerPizzaView.setAdapter(pizzaAdapter);
    }
}


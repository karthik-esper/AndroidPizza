package com.example.androidpizza;

import static com.example.androidpizza.PizzaMaker.createPizza;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Locale;

/**
 * The class that handles every operation related to the build your own pizza orders.
 * Works within a fragment.
 * @author Karthik Gangireddy, Vineal Sunkara
 */
public class buildYourOwnPizzaFragment extends Fragment {
    private Sauce sauce;
    private final ArrayList<Topping> toppers = new ArrayList<>();
    private int toppingCounter;
    private String selectedPizzaSize;
    private boolean sizeSelected = false;
    private static final int MAX_TOPPING_SIZE = 7; //max # of toppings
    private static final int MIN_TOPPING_SIZE = 3; //min # of toppings
    private static final int EMPTY_SIZE = 0; //value of toppers.size if empty
    private boolean extraCheeseAdded;
    private boolean extraSauceAdded;

    public buildYourOwnPizzaFragment() {

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
        View view = inflater.inflate(R.layout.build_your_own_pizza, container, false);
        Spinner spinner = view.findViewById(R.id.mySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(), R.array.spinner_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setEnabled(true);
        spinner.setAdapter(adapter);
        setSpinnerListener(view);
        setToppingsListener(view);
        setCheeseListener(view);
        sauceListener(view);
        setSauceListener(view);
        createPizzaButton(view);
        return view;
    }

    /**
     * Handles the selection of toppings based on what the user clicks on.
     * @param view the view that everything is handled on to fetch elements.
     */
    protected void setToppingsListener(View view) {
        int[] toppingsCheckBoxIds = { R.id.sausage, R.id.pepperoni, R.id.beef, R.id.ham,
                R.id.shrimp, R.id.squid, R.id.crab_meat,
                R.id.green_pepper, R.id.onion, R.id.mushroom,
                R.id.black_olive, R.id.pineapple, R.id.jalapeno };
        Topping[] toppings = { Topping.SAU, Topping.PE, Topping.BE, Topping.HA,
                Topping.SH, Topping.SQ, Topping.CM, Topping.GP,
                Topping.ON, Topping.MU, Topping.BO, Topping.PI, Topping.JA };

        for (int i = 0; i < toppingsCheckBoxIds.length; i++) {
            CheckBox checkBox = (CheckBox) view.findViewById(toppingsCheckBoxIds[i]);
            Topping top = toppings[i];
            checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                if (isChecked) {
                    toppingSelect(top, checkBox);
                } else {
                    toppingRemove(top);
                }
                pricePrintListener(view);
            });
        }
    }

    /**
     * Adds toppings to a toppings arrayList based on whatever toppings are selected.
     * @param top the topping that the CheckBox refers to.
     * @param checkBox the CheckBox that the user selected.
     */
    protected void toppingSelect(Topping top, CheckBox checkBox) {
        if (toppingCounter < MAX_TOPPING_SIZE) {
            toppers.add(top);
            toppingCounter++;
        } else {
            checkBox.setChecked(false);
            Toast.makeText(getContext(), "Too many toppings! A maximum of 7 toppings is allowed.", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Adds toppings to a toppings arrayList based on whatever toppings are deselected.
     * @param top the topping that the CheckBox refers to.
     */
    protected void toppingRemove(Topping top) {
        if (toppers.size() != EMPTY_SIZE) {
            toppers.remove(top);
            toppingCounter--;
        }
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
                        /**
                         * @param dialog the dialog that received the click
                         * @param id the button that was clicked (ex.
                         *              {@link DialogInterface#BUTTON_POSITIVE}) or the position
                         *              of the item clicked
                         */
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        if (type.equals("Few Toppings")) {
            builder.setMessage("Not enough toppings! Please have a total of at least 3 toppings")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        /**
                         *
                         * @param dialog the dialog that received the click
                         * @param id the button that was clicked (ex.
                         *              {@link DialogInterface#BUTTON_POSITIVE}) or the position
                         *              of the item clicked
                         */
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    /**
     * Updates the pizza based on the sauce selected.
     * @param view the view that everything is handled on to fetch elements.
     */
    protected void sauceListener(View view) {
        RadioGroup sauceGroup = (RadioGroup) view.findViewById(R.id.sauceGroup);
        RadioButton tom = (RadioButton) view.findViewById((R.id.tomato));
        RadioButton alf = (RadioButton) view.findViewById((R.id.alfredo));
        sauceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * @param group the group in which the checked radio button has changed
             * @param checkedId the unique identifier of the newly checked radio button
             */
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (tom.isChecked()) {
                    sauce = Sauce.TO;
                }
                if (alf.isChecked()) {
                    sauce = Sauce.AL;
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
             * @param viewer The view within the AdapterView that was clicked
             * @param position The position of the view in the adapter
             * @param id The row id of the item that is selected
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View viewer, int position, long id) {
                CharSequence selectedValue = (CharSequence) parent.getItemAtPosition(position);
                Pizza toMake = createPizza("byop");
                char size = selectedValue.charAt(0);
                if (size == 's' || size == 'S') {
                    assert toMake != null;
                    toMake.setSize(Size.S);
                }
                if (size == 'm' || size == 'M') {
                    assert toMake != null;
                    toMake.setSize(Size.M);
                }
                if (size == 'l' || size == 'L') {
                    assert toMake != null;
                    toMake.setSize(Size.L);
                }
                selectedPizzaSize = String.valueOf(size);
                sizeSelected = true;
                pricePrintListener(view);
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
     * Handles the checkbox that selects whether the pizza has extra cheese or not with live updates.
     * @param view the view that everything is handled on to fetch elements.
     */
    private void setCheeseListener(View view) {
        CheckBox exCheese = (CheckBox) view.findViewById(R.id.exCheese);
        exCheese.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles the selection and deselection of checkbox for extra cheese.
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                    extraCheeseAdded = exCheese.isChecked();
                    pricePrintListener(view);
            }
        });
    }

    /**
     * Handles the checkbox that selects whether the pizza has extra sauce or not with live updates.
     * @param view the view that everything is handled on to fetch elements.
     */
    private void setSauceListener(View view) {
        CheckBox exSauce = (CheckBox) view.findViewById(R.id.exSauce);
        exSauce.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles the selection and deselection of checkbox for extra sauce.
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                extraSauceAdded = exSauce.isChecked();
                pricePrintListener(view);
                }
            });
        }

    /**
     * Updates the price textview dynamically based on what the user is selecting
     * @param view the view that everything is handled on to fetch elements.
     */
    protected void pricePrintListener(View view) {
        TextView price = (TextView) view.findViewById(R.id.price);
        CheckBox exSauce = (CheckBox) view.findViewById(R.id.exSauce);
        CheckBox exCheese = (CheckBox) view.findViewById(R.id.exCheese);

        double SMALL_PRICE = 8.99;
        double MED_PRICE = 10.99;
        double LG_PRICE = 12.99;
        double currentPrice = sizeSelected ? (selectedPizzaSize.equals("S") ? SMALL_PRICE :
                selectedPizzaSize.equals("M") ? MED_PRICE : LG_PRICE) : SMALL_PRICE;

        int extraToppingsCount = Math.max(toppers.size() - MIN_TOPPING_SIZE, 0);
        double ADD_TOP_PRICE = 1.49;
        currentPrice += ADD_TOP_PRICE * extraToppingsCount;

        double EXTRAS_PRICE = 1.00;
        if (exCheese.isChecked()) currentPrice += EXTRAS_PRICE;
        if (exSauce.isChecked()) currentPrice += EXTRAS_PRICE;
        String updatedPriceText = "Price: " + String.format(Locale.US, "%.2f", currentPrice);
        price.setText(updatedPriceText);
    }

    /**
     * Handles the clicking of the createPizza button, which creates and adds a pizza to current order.
     * @param view the view that everything is handled on to fetch elements.
     */
    private void createPizzaButton(View view) {
        Button placeOrder = view.findViewById(R.id.createPizza);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pizza toMake = createPizza("byop");
                if(toppers.size() < MIN_TOPPING_SIZE) {
                    createAlert("Few Toppings");
                    return;
                }
                assert toMake != null;
                toMake.setToppings(new ArrayList<>(toppers));
                if ("S".equalsIgnoreCase(selectedPizzaSize)) {
                    toMake.setSize(Size.S);
                } else if ("M".equalsIgnoreCase(selectedPizzaSize)) {
                    toMake.setSize(Size.M);
                } else if ("L".equalsIgnoreCase(selectedPizzaSize)) {
                    toMake.setSize(Size.L);
                } else {
                    Toast.makeText(getContext(), "Size not selected", Toast.LENGTH_SHORT).show();
                    return;
                }
                toMake.sauce = sauce;
                toMake.setExtraCheese(extraCheeseAdded);
                toMake.setExtraSauce(extraSauceAdded);

                Store.getInstance().getCurrentOrder().addToOrder(toMake);
                createAlert("Pizza Made");

                clearItems(view);
            }
        });
    }

    /**
     * Clears certain boxes after the Pizza Button works.
     * @param view the view that everything is handled on to fetch elements.
     */
    private void clearItems(View view) {
        int[] checkBoxIds = {
                R.id.sausage, R.id.pepperoni, R.id.beef, R.id.ham, R.id.shrimp, R.id.squid,
                R.id.crab_meat, R.id.green_pepper, R.id.onion, R.id.mushroom,
                R.id.black_olive, R.id.pineapple, R.id.jalapeno, R.id.exSauce, R.id.exCheese
        };

        for (int id : checkBoxIds) {
            CheckBox checkBox = view.findViewById(id);
            checkBox.setChecked(false);
        }

        RadioGroup sauceGroup = view.findViewById(R.id.sauceGroup);
        sauceGroup.clearCheck();

        Spinner sizes = view.findViewById(R.id.mySpinner);
        sizes.setSelection(0);

        TextView price = view.findViewById(R.id.price);
        price.setText("");
    }
}






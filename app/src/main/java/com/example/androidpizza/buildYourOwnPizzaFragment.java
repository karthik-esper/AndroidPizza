package com.example.androidpizza;

import static com.example.androidpizza.PizzaMaker.createPizza;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CompoundButton;

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


public class buildYourOwnPizzaFragment extends Fragment {
    private TextView price;
    private Sauce sauce;
    private Size sizePizza;
    private RadioButton tomato;
    private RadioButton alfredo;
    private Spinner sizes;
    private CheckBox extraSauce;
    private CheckBox extraCheese;
    private Button placeOrder;
    private final int pizzaImages = R.drawable.byozza;
    private ArrayList<CheckBox> checkBoxList;
    private ArrayList<Topping> toppers = new ArrayList<>();
    private int toppingCounter;
    private Spinner mySpinner;
    private String selectedPizzaSize;
    private boolean sizeSelected = false;
    private static final int MAX_TOPPING_SIZE = 7;
    private static final int MIN_TOPPING_SIZE = 3;

    //    checkBoxList = new ArrayList<CheckBox>();
//        checkBoxList.add(sausage);
//        checkBoxList.add(pepperoni);
//        checkBoxList.add(ham);
//        checkBoxList.add(shrimp);
//        checkBoxList.add(squid);
//        checkBoxList.add(crab_meat);
//        checkBoxList.add(green_pepper);
//        checkBoxList.add(onion);
//        checkBoxList.add(mushroom);
//        checkBoxList.add(black_olive);
//        checkBoxList.add(pineapple);
//        checkBoxList.add(jalapeno);
    public buildYourOwnPizzaFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.build_your_own_pizza, container, false);
        Spinner spinner = view.findViewById(R.id.mySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.spinner_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setEnabled(true);
        spinner.setAdapter(adapter);
        setSpinnerListener(view);
        setToppingsListener(view);
        sauceListener(view);
        return view;
    }

    protected void setToppingsListener(View view) {
        //sausage.setChecked(false);
        CheckBox sausage = (CheckBox) view.findViewById(R.id.sausage);
        CheckBox pepperoni = (CheckBox) view.findViewById(R.id.pepperoni);
        CheckBox beef = (CheckBox) view.findViewById(R.id.beef);
        CheckBox ham = (CheckBox) view.findViewById(R.id.ham);
        CheckBox shrimp = (CheckBox) view.findViewById(R.id.shrimp);
        CheckBox squid = (CheckBox) view.findViewById(R.id.squid);
        CheckBox crab_meat = (CheckBox) view.findViewById(R.id.crab_meat);
        CheckBox green_pepper = (CheckBox) view.findViewById(R.id.green_pepper);
        CheckBox onion = (CheckBox) view.findViewById(R.id.onion);
        CheckBox mushroom = (CheckBox) view.findViewById(R.id.mushroom);
        CheckBox black_olive = (CheckBox) view.findViewById(R.id.black_olive);
        CheckBox pineapple = (CheckBox) view.findViewById(R.id.pineapple);
        CheckBox jalapeno = (CheckBox) view.findViewById(R.id.jalapeno);
        sausage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    toppingSelect(Topping.SAU, sausage);
                else
                    toppingRemove(Topping.SAU, sausage);
            }
        });

        pepperoni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    toppingSelect(Topping.PE, pepperoni);
                else
                    toppingRemove(Topping.PE, pepperoni);
            }
        });

        beef.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    toppingSelect(Topping.BE, beef);
                else
                    toppingRemove(Topping.BE, beef);
            }
        });

        ham.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    toppingSelect(Topping.HA, ham);
                else
                    toppingRemove(Topping.HA, ham);
            }
        });

        shrimp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    toppingSelect(Topping.SH, shrimp);
                else
                    toppingRemove(Topping.SH, shrimp);
            }
        });

        squid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    toppingSelect(Topping.SQ, squid);
                else
                    toppingRemove(Topping.SQ, squid);
            }
        });

        crab_meat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    toppingSelect(Topping.CM, crab_meat);
                else
                    toppingRemove(Topping.CM, crab_meat);
            }
        });

        green_pepper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    toppingSelect(Topping.GP, green_pepper);
                else
                    toppingRemove(Topping.GP, green_pepper);
            }
        });

        onion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    toppingSelect(Topping.ON, onion);
                else
                    toppingRemove(Topping.ON, onion);
            }
        });

        mushroom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    toppingSelect(Topping.MU, mushroom);
                else
                    toppingRemove(Topping.MU, mushroom);
            }
        });

        black_olive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    toppingSelect(Topping.BO, black_olive);
                else
                    toppingRemove(Topping.BO, black_olive);
            }
        });

        pineapple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    toppingSelect(Topping.PI, pineapple);
                else
                    toppingRemove(Topping.PI, pineapple);
            }
        });

        jalapeno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    toppingSelect(Topping.JA, jalapeno);
                else
                    toppingRemove(Topping.JA, jalapeno);
            }
        });
    }


    protected void toppingSelect(Topping top, CheckBox cbox) {
        if (toppingCounter < MAX_TOPPING_SIZE) {
            toppers.add(top);
            toppingCounter++;
            Log.i("Toppings", toppers.toString()); //REMOVE AFTER
        } else {
            cbox.setChecked(false);
            Toast.makeText(getContext(), "Too many toppings! A maximum of 7 toppings is allowed.", Toast.LENGTH_LONG).show();
            Log.i("Toppings", toppers.toString()); //REMOVE AFTER
        }
    }

    protected void toppingRemove(Topping top, CheckBox cbox) {
        if (toppers.size() != 0) {
            toppers.remove(top);
            toppingCounter--;
            Log.i("Toppings", toppers.toString()); //REMOVE AFTER
        }
    }

    /**
     * Creates alerts based on the provided error/success conditions.
     *
     * @param type a string describing the error and the necessary alert.
     */
    private void createAlert(String type) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        if (type.equals("Pizza Made")) {
            builder.setMessage("Pizza was created")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        if (type.equals("No Size")) {
            builder.setMessage("Size not Selected")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        if (type.equals("No Type")) {
            builder.setMessage("Type not Selected")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }


    }

    /**
     * Updates the pizza based on the sauce selected.
     *
     * @param view the view that everything is handled on to fetch elements.
     */
    protected void sauceListener(View view) {
        RadioGroup sauceGroup = (RadioGroup) view.findViewById(R.id.sauceGroup);
        RadioButton tom = (RadioButton) view.findViewById((R.id.tomato));
        RadioButton alf = (RadioButton) view.findViewById((R.id.alfredo));
        sauceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (tom.isChecked()) {
                    sauce = Sauce.TO;
                    Log.i("Sauce", sauce.getFlavor());
                }
                if (alf.isChecked()) {
                    sauce = Sauce.AL;
                    Log.i("Sauce", sauce.getFlavor());
                }
            }
        });
    }

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
                    CharSequence selectedValue = (CharSequence) parent.getItemAtPosition(position);
                    Pizza toMake = createPizza("byop");
                    char size = selectedValue.charAt(0);
                    if (size == 's' || size == 'S') {toMake.setSize(Size.S);}
                    if (size == 'm' || size == 'M') {toMake.setSize(Size.M);}
                    if (size == 'l' || size == 'L') {toMake.setSize(Size.L);}
                    selectedPizzaSize = String.valueOf(size);
                    sizeSelected = true;
                    Log.i("Size", selectedPizzaSize);
                    //extraSauce.setChecked(false);
                    //extraCheese.setChecked(false);
                    //price.setText(String.format("%.2f", toMake.price()));
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

    private void createPizzaListener(View view) {
        if(sizeSelected) {
            Pizza toMake = createPizza("byop");
            toMake.setToppings(toppers);
            if (selectedPizzaSize.charAt(0) == (Size.S.getPizzaSize().charAt(0))) {
                toMake.setSize(Size.S);
            }
            if (selectedPizzaSize.charAt(0) == (Size.M.getPizzaSize().charAt(0))) {
                toMake.setSize(Size.M);
            }
            if (selectedPizzaSize.charAt(0) == (Size.L.getPizzaSize().charAt(0))) {
                toMake.setSize(Size.L);
            }
            toMake.setExtraCheese(false);
            toMake.setExtraSauce(false);
            Store.getInstance().getCurrentOrder().addToOrder(toMake);
            price.setText("Price: ");
            Toast.makeText(getContext(), "Creating Pizza", Toast.LENGTH_LONG).show();
            sizeSelected = false;
            createAlert("Pizza Made");
        }
        else {
            createAlert("No Size");}
        }

    }





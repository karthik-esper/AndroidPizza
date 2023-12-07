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
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


public class buildYourOwnPizzaFragment extends Fragment{
    private TextView price;
    private Spinner sizes;
    private CheckBox extraSauce;
    private CheckBox extraCheese;
    private Button placeOrder;
    private CheckBox sausage;

    private CheckBox pepperoni;

    private CheckBox beef;

    private CheckBox ham;

    private CheckBox shrimp;

    private CheckBox squid;

    private CheckBox crab_meat;

    private CheckBox green_pepper;

    private CheckBox onion;

    private CheckBox mushroom;

    private CheckBox black_olive;

    private CheckBox pineapple;

    private CheckBox jalapeno;

    private final int pizzaImages = R.drawable.byozza;

    private ArrayList<CheckBox> checkBoxList;

    private ArrayList<Topping> toppers = new ArrayList<>();
    private int toppingCounter;
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
        setToppingsListener(view);
        return view;
    }

    protected void setToppingsListener(View view) {
        sausage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toppingSelect(Topping.SAU, sausage);
            }
        });

        pepperoni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toppingSelect(Topping.PE, pepperoni);
            }
        });

        beef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toppingSelect(Topping.BE, beef);
            }
        });

        ham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toppingSelect(Topping.HA, ham);
            }
        });

        shrimp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toppingSelect(Topping.SH, shrimp);
            }
        });

        squid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toppingSelect(Topping.SQ, squid);
            }
        });

        crab_meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toppingSelect(Topping.CM, crab_meat);
            }
        });

        green_pepper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toppingSelect(Topping.GP, green_pepper);
            }
        });

        onion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toppingSelect(Topping.ON, onion);
            }
        });

        mushroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toppingSelect(Topping.MU, mushroom);
            }
        });

        black_olive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toppingSelect(Topping.BO, black_olive);
            }
        });

        pineapple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toppingSelect(Topping.PI, pineapple);
            }
        });

        jalapeno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toppingSelect(Topping.JA, jalapeno);
            }
        });
    }


    protected void toppingSelect(Topping top, CheckBox cbox) {
        if (cbox.isChecked()) {
            if (toppingCounter < MAX_TOPPING_SIZE) {
                toppers.add(top);
                toppingCounter++;
            } else {
                cbox.setChecked(false);
                Toast.makeText(getContext(), "Too many toppings! A maximum of 7 toppings is allowed.", Toast.LENGTH_SHORT).show();
            }
        } else {
            toppers.remove(top);
            toppingCounter--;
        }
        System.out.println(toppers);
    }
}



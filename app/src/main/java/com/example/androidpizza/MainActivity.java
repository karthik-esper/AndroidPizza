package com.example.androidpizza;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button Specialty, buildYourOwn, currentOrder, storeOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Specialty = findViewById(R.id.specialtyButton);
        buildYourOwn = findViewById(R.id.buildButton);
        currentOrder = findViewById(R.id.viewCurrentOrderButton);
        storeOrders = findViewById(R.id.viewOrderHistoryButton);
        Specialty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSpecialtyFragment();
            }
        });

        currentOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openOrderFragment();}
        });

        storeOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {openStoreOrderFragment();}
        });

        buildYourOwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openBuildYourOwnFragment();}
        });
    }

    private void openSpecialtyFragment() {
        specialtyFragment specialtyView = new specialtyFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, specialtyView)
                        .addToBackStack(null).commit();
    }
    private void openOrderFragment() {
        orderFragment orderFrag = new orderFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, orderFrag)
                .addToBackStack(null).commit();

    }

    private void openStoreOrderFragment() {
        storeOrderFragment storeFrag = new storeOrderFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, storeFrag)
                .addToBackStack(null).commit();

    }

    private void openBuildYourOwnFragment() {
        buildYourOwnPizzaFragment storeBuild = new buildYourOwnPizzaFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, storeBuild)
                .addToBackStack(null).commit();
    }
}
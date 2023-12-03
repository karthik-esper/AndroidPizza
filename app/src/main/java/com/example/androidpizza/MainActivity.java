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
    }

    private void openSpecialtyFragment() {
        specialtyFragment specialtyView = new specialtyFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, specialtyView)
                        .addToBackStack(null).commit();
    }
//    private void openOrderFragment() {
//
//    }
}
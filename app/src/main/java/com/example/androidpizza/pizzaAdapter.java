//package com.example.androidpizza;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.ArrayList;
//
//
//public class pizzaAdapter extends RecyclerView.Adapter<pizzaAdapter.PizzaHolder> {
//    private Context context;
//    String[] pizzaNames;
//    int[] images;
//
//
//    public pizzaAdapter(Context context, String[] pizzaNames, int[] images) {
//        this.context = context;
//        this.pizzaNames = pizzaNames;
//        this.images = images;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull pizzaAdapter.PizzaHolder holder, int position) {
//        holder.rowName.setText(pizzaNames[position]);
//        holder.rowImage.setImageResource(images[position]);
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    @NonNull
//    @Override
//    public pizzaAdapter.PizzaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.pizza_item, parent, false);
//
//        return new PizzaHolder(view);
//    }
//
//    public class PizzaHolder extends RecyclerView.ViewHolder{
//        private TextView rowName;
//        private ImageView rowImage;
//        LinearLayout rowItem;
//
//        public PizzaHolder (@NonNull View itemView) {
//            super(itemView);
//            rowName = itemView.findViewById(R.id.textView);
//
//        }
//
//    }
//
//
//}

package com.example.androidpizza;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/**
 * Adapter that handles the recycler view elements.
 * @author Karthik Gangireddy, Vineal Sunkara
 */
public class pizzaAdapter extends RecyclerView.Adapter<pizzaAdapter.PizzaHolder> {
    private Context context;
    String[] pizzaNames;
    String[] pizzaDetails;
    int[] images;
    private int selectedPosition = -1;

    private OnItemClickListener listener;

    /**
     * Listener for a recycler item being clicked.
     */
    public interface OnItemClickListener {
        /**
         * Handles the selection of an item in the recycler.
         * @param position
         */
        void onItemClick(int position);
    }

    /**
     * Default constructor for the pizza adapter.
     * @param context the context of the fragment.
     * @param pizzaNames names of the pizza types.
     * @param pizzaDetails descriptions for the pizza types.
     * @param images image links from the drawable folder.
     * @param listener the listener that handles clicks.
     */
    public pizzaAdapter(Context context, String[] pizzaNames, String[] pizzaDetails, int[] images, OnItemClickListener listener) {
        this.context = context;
        this.pizzaNames = pizzaNames;
        this.pizzaDetails = pizzaDetails;
        this.images = images;
        this.listener = listener;
    }

    /**
     * Creates the view holder for each item in the recycler view.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull pizzaAdapter.PizzaHolder holder, int position) {
        holder.rowName.setText(pizzaNames[position]);
        holder.rowDescription.setText(pizzaDetails[position]);
        holder.rowImage.setImageResource(images[position]);
    }

    /**
     * Gets the amount of items in the recycler
     * @return the amount of pizza types.
     */
    @Override
    public int getItemCount() {
        return pizzaNames.length;
    }

    /**
     * Creates the Recycler view format for each pizza item.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return
     */
    @NonNull
    @Override
    public pizzaAdapter.PizzaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pizza_item, parent, false);

        return new PizzaHolder(view);
    }

    /**
     * Internal class that handles specifics of the pizza items in the recycler.
     */
    public class PizzaHolder extends RecyclerView.ViewHolder{
        private TextView rowName;
        private TextView rowDescription;
        private ImageView rowImage;
        LinearLayout rowItem;

        /**
         * Constructor for the pizza holder viw.
         * @param itemView the itemView that contains the full entity.
         */
        public PizzaHolder (@NonNull View itemView) {
            super(itemView);
            rowName = itemView.findViewById(R.id.textViewName);
            rowDescription = itemView.findViewById(R.id.textViewDescription);
            rowImage = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                /**
                 * Handles getting the necessary position of the selected recyclerview item.
                 * @param v The view that was clicked.
                 */
                @Override
                public void onClick(View v) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        selectedPosition = getAdapterPosition();
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }


    }

}

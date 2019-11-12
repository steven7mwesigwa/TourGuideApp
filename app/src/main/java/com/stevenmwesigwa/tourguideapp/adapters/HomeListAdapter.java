package com.stevenmwesigwa.tourguideapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stevenmwesigwa.tourguideapp.GamePark;
import com.stevenmwesigwa.tourguideapp.R;

import java.util.ArrayList;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder> {
    private ArrayList<GamePark> gameParkArrayList;
    /**
     * Define LayoutInflater
     */
    private LayoutInflater mInflater;
    private Context mContext;


    // Provide a suitable constructor (depends on the kind of dataset)
    public HomeListAdapter(ArrayList<GamePark> gameParkArrayList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.gameParkArrayList = gameParkArrayList;
        this.mContext = context;
    }

    //Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public HomeListAdapter.HomeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /* We inflate the xml which gives us a View object */
        View view = mInflater.inflate(R.layout.home_custom_row_recycler, parent, false);
        return new HomeListAdapter.HomeListViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(HomeListViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.title.setText(gameParkArrayList.get(position).getTitle());
        holder.description.setText(gameParkArrayList.get(position).getDescription());
        holder.image.setContentDescription(gameParkArrayList.get(position).getImage());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (gameParkArrayList == null) {
            return 0;
        } else {
            return gameParkArrayList.size();
        }
    }

    /*
     * Provide a reference to the views for each data item
     * Complex data items may need more than one view per item, and
     * you provide access to all the views for a data item in a view holder
     * This is where we will initialize our views
     */
    public class HomeListViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        ImageView image;
        LinearLayout linearLayoutWrapper;

        public HomeListViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.HOmeRecycRowTitle);
            description = view.findViewById(R.id.HOmeRecycRowDescription);
            image = view.findViewById(R.id.HomeRecycRowImage);
            linearLayoutWrapper = view.findViewById(R.id.HomeRecycRowWrapper);

        }
    }
}

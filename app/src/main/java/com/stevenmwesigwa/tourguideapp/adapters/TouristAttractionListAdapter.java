package com.stevenmwesigwa.tourguideapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stevenmwesigwa.tourguideapp.R;
import com.stevenmwesigwa.tourguideapp.activities.MainActivity;
import com.stevenmwesigwa.tourguideapp.fragments.TouristAttractionItemDetailsFragment;
import com.stevenmwesigwa.tourguideapp.models.TouristAttraction;
import com.stevenmwesigwa.tourguideapp.utilities.DownloadImageTask;
import com.stevenmwesigwa.tourguideapp.utilities.touristAttraction.TourPlaceDetails;

import java.util.ArrayList;

public class TouristAttractionListAdapter extends RecyclerView.Adapter<TouristAttractionListAdapter.TouristAttractionViewHolder> {
    private ArrayList<TouristAttraction> touristAttractionArrayList;
    /**
     * Define LayoutInflater
     */
    private LayoutInflater mInflater;
    private Context mContext;
    private String callingFragment;


    // Provide a suitable constructor (depends on the kind of dataset)
    public TouristAttractionListAdapter(ArrayList<TouristAttraction> touristAttractionArrayList, Context context, String callingFragment) {
        this.mInflater = LayoutInflater.from(context);
        this.touristAttractionArrayList = touristAttractionArrayList;
        this.mContext = context;
        this.callingFragment = callingFragment;
    }

    //Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public TouristAttractionListAdapter.TouristAttractionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /* We inflate the xml which gives us a View object */
        View view = mInflater.inflate(R.layout.tourist_custom_row_recycler, parent, false);
        return new TouristAttractionListAdapter.TouristAttractionViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TouristAttractionListAdapter.TouristAttractionViewHolder holder, final int position) {
        final String title = touristAttractionArrayList.get(position).getTitle();
        final String description = touristAttractionArrayList.get(position).getDescription();
        final String imageViewURL = touristAttractionArrayList.get(position).getImage();
        /*
         * - get element from your dataset at this position
         * - replace the contents of the view with that element
         */
        holder.title.setText(title);
        holder.description.setText(description);
        // show The Image in a ImageView
        new DownloadImageTask(holder.image)
                .execute(imageViewURL);

        /* Set up on Click Listener
         *   to open the respective Fragment on click.
         *  */
        holder.linearLayoutWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final TouristAttractionItemDetailsFragment touristAttractionItemDetailsFragment = new TouristAttractionItemDetailsFragment();
                //Avoid app crash when orientation is changed
                touristAttractionItemDetailsFragment.setRetainInstance(true);
                Bundle bundle = new Bundle();
                bundle.putString(TourPlaceDetails.TITLE, title);
                bundle.putString(TourPlaceDetails.DESCRIPTION, description);
                bundle.putString(TourPlaceDetails.IMAGE_URL, imageViewURL);
                //Link values with the touristAttractionItemDetailsFragment
                touristAttractionItemDetailsFragment.setArguments(bundle);
                /* Let's begin the transaction
                 * We will invoke this Adapter through our MainActivity.java file.
                 * */
                MainActivity mainActivity = (MainActivity) mContext;
                mainActivity.getSupportFragmentManager()
                        .beginTransaction()
                        //Replace the already added fragment from MainActivity.java
                        .replace(R.id.fragment_container, touristAttractionItemDetailsFragment)
                        /*
                         * Calling addToBackStack() as part of our FragmentTransaction
                         * to be able to transition between Fragments
                         **/
                        .addToBackStack(callingFragment)
                        .commit();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (touristAttractionArrayList == null) {
            return 0;
        } else {
            return touristAttractionArrayList.size();
        }
    }

    /*
     * Provide a reference to the views for each data item
     * Complex data items may need more than one view per item, and
     * you provide access to all the views for a data item in a view holder
     * This is where we will initialize our views
     */
    public class TouristAttractionViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        ImageView image;
        LinearLayout linearLayoutWrapper;

        public TouristAttractionViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.HOmeRecycRowTitle);
            description = view.findViewById(R.id.HOmeRecycRowDescription);
            image = view.findViewById(R.id.HomeRecycRowImage);
            linearLayoutWrapper = view.findViewById(R.id.HomeRecycRowWrapper);

        }
    }
}

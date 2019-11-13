package com.stevenmwesigwa.tourguideapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.stevenmwesigwa.tourguideapp.R;
import com.stevenmwesigwa.tourguideapp.utilities.DownloadImageTask;

import org.apache.commons.text.WordUtils;

import java.util.Objects;


public class HomeItemDetailsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // inflate xml file for Home Item Details
        final View view = inflater.inflate(R.layout.fragment_home_item_details, container, false);

        // References to xml file Views
        final TextView title = view.findViewById(R.id.HomeDetailsTitle);
        final TextView desscription = view.findViewById(R.id.HomeDetailsDescription);
        final ImageView image = view.findViewById(R.id.HomeDetailsImage);

        if (getArguments() != null) {
            // Bundled Fragment arguments from HomeFragment.java
            final String titleArg = Objects.requireNonNull(getArguments()).getString("title");
            final String desscriptionArg = Objects.requireNonNull(getArguments()).getString("description");
            final String imageViewURLArg = Objects.requireNonNull(getArguments()).getString("imageViewURL");
            // Set up Views in xml file with their respective Bundled data
            title.setText(WordUtils.capitalize(titleArg));
            desscription.setText(desscriptionArg);
            // show The Image in a ImageView
            new DownloadImageTask(image)
                    .execute(imageViewURLArg);
        }
        return view;
    }
}

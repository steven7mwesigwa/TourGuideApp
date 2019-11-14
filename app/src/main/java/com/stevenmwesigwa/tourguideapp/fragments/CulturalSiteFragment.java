package com.stevenmwesigwa.tourguideapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stevenmwesigwa.tourguideapp.R;
import com.stevenmwesigwa.tourguideapp.adapters.CulturalSiteListAdapter;
import com.stevenmwesigwa.tourguideapp.models.CulturalSite;

import java.util.ArrayList;

public class CulturalSiteFragment extends Fragment {

    private RecyclerView recyclerView;
    private Context context;
    private ArrayList<CulturalSite> culturalSiteArrayList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public CulturalSiteFragment(Context context, ArrayList<CulturalSite> culturalSiteArrayList) {
        this.context = context;
        this.culturalSiteArrayList = culturalSiteArrayList;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cultural_site, container, false);
// Initialize Recycler View
        recyclerView = view.findViewById(R.id.HomeRecyclerView);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        /* use a linear layout manager
         *  Responsible for  aligning the single items in our list
         *
         * */
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        /* specify an adapter
         * The Adapter is basically a bridge between our 'dataset' and the 'RecyclerView'
         * Because we can't just load all our items at once in the Recycler view
         * */
        mAdapter = new CulturalSiteListAdapter(culturalSiteArrayList, context);
        recyclerView.setAdapter(mAdapter);
        return view;


    }
}

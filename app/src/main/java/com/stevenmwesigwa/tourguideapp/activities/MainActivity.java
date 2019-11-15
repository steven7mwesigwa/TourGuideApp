package com.stevenmwesigwa.tourguideapp.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.stevenmwesigwa.tourguideapp.R;
import com.stevenmwesigwa.tourguideapp.controllers.TouristAttractionController;
import com.stevenmwesigwa.tourguideapp.fragments.CulturalSiteFragment;
import com.stevenmwesigwa.tourguideapp.fragments.HomeFragment;
import com.stevenmwesigwa.tourguideapp.models.TouristAttraction;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private ArrayList<TouristAttraction> gameParkArrayList;
    private ArrayList<TouristAttraction> culturalSiteArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Populate gameParkArrayList with data */
        gameParkArrayList = new TouristAttractionController(this).get("gameParksList.json");
        /* Populate culturalSiteArrayList with data */
        culturalSiteArrayList = new TouristAttractionController(this).get("culturalSitesList.json");

        Toolbar toolbar = findViewById(R.id.toolbar);
        // Tell your app that you want to use your own toolbar instead
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState(); // Takes care of rotating the hamburger icon

        /*
         * When we open our app the first time, we don't want to display an empty Activity
         * BUT instead , we want to display our Message Fragment.
         *
         * (Device Rotation) or (device text size change) or
         * (app running in background and system decides cut it to low memory) causes the
         *  Activity to be destroyed and recreated.
         *
         * When we rotate our device or do some kind of runtime configuration change
         * we will also load this fragment and display it
         *
         * savedInstanceState - is null if we just start our Activity the first time
         *
         * When the deice is rotated 'savedInstanceState' won't be null.
         */
        if (savedInstanceState == null) {
            final HomeFragment homeFragment = new HomeFragment(this, gameParkArrayList);
            homeFragment.setRetainInstance(true);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment
            ).commit();
            // We also want to select our first item in the navigation drawer
            navigationView.setCheckedItem(R.id.nav_game_park);
        }
    }


    /*
     * MenuItem item - Is the item that was selected
     * Returning 'false' on this method means that no item will be selected even if
     * the action was triggered.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_game_park:
                final HomeFragment homeFragment = new HomeFragment(this, gameParkArrayList);
                homeFragment.setRetainInstance(true);
                // Open the Message Fragment and display it in the FrameLayout
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        homeFragment).commit();
                break;
            case R.id.nav_cultural_site:
                final CulturalSiteFragment culturalSiteFragment = new CulturalSiteFragment(this, culturalSiteArrayList);
                culturalSiteFragment.setRetainInstance(true);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CulturalSiteFragment(this, culturalSiteArrayList)).commit();
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_send:
                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
                break;
        }
// Close drawer
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        /*
         * Because when we press the back button when our navigation drawer is open
         * we don't want to leave the Activity immediately, Instead we want to close
         * our navigation drawer.
         * If the drawer were on the "right side" of the screen we would have used
         * drawer.closeDrawer(GravityCompat.END); instead to close it.
         */
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
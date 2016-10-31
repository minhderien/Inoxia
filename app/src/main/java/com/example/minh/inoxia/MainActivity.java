package com.example.minh.inoxia;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import static com.example.minh.inoxia.R.layout.activity_main;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawer;

    private String barcodeResult = "";


    public static int navItemIndex = 1;

    private Handler mHandler = new Handler();
    private static final String TAG_INVENTORY = "inventory";
    /*private static final String TAG_PHOTOS = "photos";
    private static final String TAG_MOVIES = "movies";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";*/
    public static String CURRENT_TAG = TAG_INVENTORY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        recupererComposante();
        ecouterComposante();
        loadDefaultFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        selectNavMenu();

    }

    public void recupererComposante() {
        /*boutSpeedtiles = (Button) findViewById(R.id.boutSpeedtiles);
        boutBackSpeed = (Button) findViewById(R.id.boutBackSpeed);
        boutBacksplashes = (Button) findViewById(R.id.boutBacksplashes);
        boutLoft = (Button) findViewById(R.id.boutLoft);
        boutCare = (Button) findViewById(R.id.boutCare);*/

        navigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    public void ecouterComposante() {
        /*boutSpeedtiles.setOnClickListener(buttonListener);
        boutBackSpeed.setOnClickListener(buttonListener);
        boutBacksplashes.setOnClickListener(buttonListener);
        boutLoft.setOnClickListener(buttonListener);
        boutCare.setOnClickListener(buttonListener);*/
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra("barcode");
                    barcodeResult = barcode.displayValue;
                    Log.e("MainActivity barcode", barcodeResult);

                } else {
                    Log.e("Barcode Result", "No barcode found");

                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void loadDefaultFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        //setToolbarTitle();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getChosenFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.relative_layout_fragment, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        // toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getChosenFragment() {
        switch (navItemIndex) {
            case 1:
                InventoryFragment inventory = new InventoryFragment();
                return inventory;
            default:
                return new InventoryFragment();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_scan) {
            // Handle the camera action
            navItemIndex = 0;
            Intent intent = new Intent(MainActivity.this, ScanBarcode.class);
            startActivity(intent);
        } else if (id == R.id.nav_inventory) {
            /*InventoryFragment inventory = new InventoryFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.relative_layout_fragment, inventory).commit();*/
            navItemIndex = 1;
            CURRENT_TAG = TAG_INVENTORY;
        } else if (id == R.id.nav_add) {
            navItemIndex = 2;
        } else if (id == R.id.nav_remove) {
            navItemIndex = 3;
        } else if (id == R.id.nav_share) {
            navItemIndex = 4;
        } else if (id == R.id.nav_send) {
            navItemIndex = 5;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

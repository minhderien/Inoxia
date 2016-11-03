package com.example.minh.inoxia;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
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

import static android.R.attr.windowBackground;
import static com.example.minh.inoxia.R.layout.activity_main;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private boolean isHome = true;

    private String barcodeResult = "";


    public static int navItemIndex = 1;
    public static int tmpNavIndex;

    private Handler mHandler = new Handler();
    private static final String TAG_INVENTORY = "inventory";
    private static final String TAG_ADD = "ADD";
    private static final String TAG_REMOVE = "remove";
    public static String CURRENT_TAG = TAG_INVENTORY;

    private String[] activityTitles;

    private ScanBarcode scanBarcode;


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
        initialiserComposante();
        ecouterComposante();
        loadFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        selectNavMenu();
        setToolbarTitle();
    }

    public void recupererComposante() {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    public void initialiserComposante() {
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
    }

    public void ecouterComposante() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
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
            return;
        }
        if (isHome) {
            if (navItemIndex != 1) {
                navItemIndex = 1;
                CURRENT_TAG = TAG_INVENTORY;
                loadFragment();
                return;
            }
        }
        super.onBackPressed();
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


    private void loadFragment() {
        // setCheck l'onglet selectionner
        selectNavMenu();

        // met le bon titre a la toolbar
        setToolbarTitle();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // Remplace le fragment present par lui choisi.
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
            case 2:
                AddFragment add = new AddFragment();
                return add;
            case 3:
                RemoveFragment remove = new RemoveFragment();
                return remove;
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
            navItemIndex = 1;
            scanBarcode.tempNavIndex = 1;
            CURRENT_TAG = TAG_INVENTORY;
        } else if (id == R.id.nav_add) {
            navItemIndex = 2;
            scanBarcode.tempNavIndex = 2;
            CURRENT_TAG = TAG_ADD;
        } else if (id == R.id.nav_remove) {
            navItemIndex = 3;
            scanBarcode.tempNavIndex = 3;
            CURRENT_TAG = TAG_REMOVE;
        } else if (id == R.id.nav_share) {
            navItemIndex = 4;
            scanBarcode.tempNavIndex = 4;
        } else if (id == R.id.nav_send) {
            navItemIndex = 5;
            scanBarcode.tempNavIndex = 5;
        }

        if (item.isChecked()) {
            item.setChecked(false);
        } else {
            item.setChecked(true);
        }
        item.setChecked(true);

        loadFragment();

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

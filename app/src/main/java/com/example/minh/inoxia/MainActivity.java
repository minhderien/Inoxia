package com.example.minh.inoxia;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

    String barcodeResult = "";
    Button boutSpeedtiles, boutBackSpeed, boutBacksplashes, boutLoft, boutCare;


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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        recupererComposante();
        ecouterComposante();
    }

    public void recupererComposante() {
        boutSpeedtiles = (Button) findViewById(R.id.boutSpeedtiles);
        boutBackSpeed = (Button) findViewById(R.id.boutBackSpeed);
        boutBacksplashes = (Button) findViewById(R.id.boutBacksplashes);
        boutLoft = (Button) findViewById(R.id.boutLoft);
        boutCare = (Button) findViewById(R.id.boutCare);
    }

    public void ecouterComposante() {
        boutSpeedtiles.setOnClickListener(buttonListener);
        boutBackSpeed.setOnClickListener(buttonListener);
        boutBacksplashes.setOnClickListener(buttonListener);
        boutLoft.setOnClickListener(buttonListener);
        boutCare.setOnClickListener(buttonListener);
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


    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View vue) {

            /*Toast toast;
            String message = "";

                    Intent intent = new Intent(this, ScanBarcode.class);
                    startActivityForResult(intent, 0);
            toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
            toast.show();*/
            Intent intent = new Intent(getApplicationContext(), Products.class);
            switch (vue.getId()) {

                case R.id.boutSpeedtiles:
                    startActivityForResult(intent, 0);
                    break;

                case R.id.boutBackSpeed:
                    startActivityForResult(intent, 0);
                    break;

                case R.id.boutBacksplashes:
                    startActivityForResult(intent, 0);
                    break;
                case R.id.boutLoft:
                    startActivityForResult(intent, 0);
                    break;
                case R.id.boutCare:
                    startActivityForResult(intent, 0);
                    break;
                default:
                    break;
            }


        }
    };


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = new Intent(this, ScanBarcode.class);


        if (id == R.id.nav_scan) {
            // Handle the camera action
            startActivityForResult(intent, 0);

        } else if (id == R.id.nav_inventory) {

        } else if (id == R.id.nav_add) {

        } else if (id == R.id.nav_remove) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

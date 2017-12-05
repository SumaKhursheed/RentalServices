package com.bossfight.rentalservices.vendor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bossfight.rentalservices.R;
import com.bossfight.rentalservices.customer.CommentActivity;
import com.bossfight.rentalservices.customer.ContactActivity;
import com.bossfight.rentalservices.customer.FeedbackActivity;
import com.bossfight.rentalservices.startup.LoginActivity;

public class VendorDashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button addprod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        addprod = (Button) findViewById(R.id.bAddNew);

        addprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VendorDashboard.this, ProductActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

      if (id == R.id.help_center){
            startActivity(new Intent(VendorDashboard.this, LoginActivity.class));
            finish();
            Toast.makeText(getApplicationContext(), "Logged out", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.feedback){
            startActivity(new Intent(VendorDashboard.this, FeedbackActivity.class));
        }
        else if (id == R.id.reviews){
            startActivity(new Intent(VendorDashboard.this, CommentActivity.class));
        }
        else if (id == R.id.contact_us){
            startActivity(new Intent(VendorDashboard.this, ContactActivity.class));
        }
        else {
            startActivity(new Intent(VendorDashboard.this, ProductActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}



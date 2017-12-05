package com.bossfight.rentalservices.customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bossfight.rentalservices.R;
import com.bossfight.rentalservices.fragments.ImageListFragment;
import com.bossfight.rentalservices.notification.NotificationCountSetClass;
import com.bossfight.rentalservices.options.CartListActivity;
import com.bossfight.rentalservices.options.OrderlistActivity;
import com.bossfight.rentalservices.options.SearchResultActivity;
import com.bossfight.rentalservices.options.WishlistActivity;
import com.bossfight.rentalservices.startup.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class CustomerDashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int notificationCountCart = 0;
    static ViewPager viewPager;
    static TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        if (viewPager != null) {
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_cart);
        NotificationCountSetClass.setAddToCart(CustomerDashboard.this, item,notificationCountCart);
        invalidateOptionsMenu();
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_search) {
            startActivity(new Intent(CustomerDashboard.this, SearchResultActivity.class));
            return true;
        }else if (id == R.id.action_cart) {
            startActivity(new Intent(CustomerDashboard.this, CartListActivity.class));
            return true;
        }else {
            startActivity(new Intent(CustomerDashboard.this, WishlistActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        ImageListFragment fragment = new ImageListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", 1);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_1));
        fragment = new ImageListFragment();
        bundle = new Bundle();
        bundle.putInt("type", 2);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_2));
        fragment = new ImageListFragment();
        bundle = new Bundle();
        bundle.putInt("type", 3);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_3));
        fragment = new ImageListFragment();
        bundle = new Bundle();
        bundle.putInt("type", 4);
        fragment.setArguments(bundle);
        adapter.addFragment(fragment, getString(R.string.item_4));
        viewPager.setAdapter(adapter);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_item1) {
            viewPager.setCurrentItem(0);
        } else if (id == R.id.nav_item2) {
            viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_item3) {
            viewPager.setCurrentItem(2);
        } else if (id == R.id.nav_item4) {
            viewPager.setCurrentItem(3);
        }else if (id == R.id.my_wishlist) {
            startActivity(new Intent(CustomerDashboard.this, WishlistActivity.class));
        }else if (id == R.id.my_cart) {
            startActivity(new Intent(CustomerDashboard.this, CartListActivity.class));
        }else if (id == R.id.help_center){
            startActivity(new Intent(CustomerDashboard.this, LoginActivity.class));
            finish();
            Toast.makeText(getApplicationContext(), "Logged out", Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.feedback){
            startActivity(new Intent(CustomerDashboard.this, FeedbackActivity.class));
        }
        else if (id == R.id.reviews){
            startActivity(new Intent(CustomerDashboard.this, CommentActivity.class));
        }
        else if (id == R.id.contact_us){
            startActivity(new Intent(CustomerDashboard.this, ContactActivity.class));
        }
        else {
            startActivity(new Intent(CustomerDashboard.this, OrderlistActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}


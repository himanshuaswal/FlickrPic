package com.atg.gautam.flickerpic.ui;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import com.atg.gautam.flickerpic.R;

import org.aviran.cookiebar2.CookieBar;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;
    private android.support.v4.app.FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mFragmentManager = getSupportFragmentManager();
        mBottomNavigationView.setSelectedItemId(R.id.recent_images);
        CookieBar.build(MainActivity.this)
                .setLayoutGravity(Gravity.BOTTOM)
                .setBackgroundColor(android.R.color.holo_orange_dark)
                .setTitle("App developed by Gautam Krishnan")
                .show();
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.recent_images:
                    RecentFragment mRecentFragment = new RecentFragment();
                    mFragmentManager.beginTransaction().replace(R.id.fragment_container, mRecentFragment).commit();
                    break;
                case R.id.search_images:
                    onSearchRequested();
                    break;

            }
            return true;
        });
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doSearchQuery(query);
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void doSearchQuery(String query) {
        QueryFragment mQueryFragment = QueryFragment.newInstance(query);
        mFragmentManager.beginTransaction().replace(R.id.fragment_container, mQueryFragment).commit();
    }


}

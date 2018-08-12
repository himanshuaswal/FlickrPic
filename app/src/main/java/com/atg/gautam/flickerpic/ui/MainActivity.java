package com.atg.gautam.flickerpic.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import com.atg.gautam.flickerpic.R;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        android.support.v4.app.FragmentManager mFragmentManager = getSupportFragmentManager();
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.recent_images:
                    // do something;
                    break;
                case R.id.search_images:
                    //do something;
                    break;

            }
            return true;
        });
        RecentFragment mRecentFragment = new RecentFragment();
        mFragmentManager.beginTransaction().replace(R.id.fragment_container, mRecentFragment).commit();
        mBottomNavigationView.setSelectedItemId(R.id.recent_images);

    }


}

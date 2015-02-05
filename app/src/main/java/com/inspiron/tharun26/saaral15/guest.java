package com.inspiron.tharun26.saaral15;

import android.support.v7.app.ActionBar;
import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;


public class guest extends ActionBarActivity {

    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        // Setup the action bar for tabs
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setTitle("Guests"); // If you want to set an app title

        // == Setting up the ViewPager ==

        mCustomPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(),this);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);

        // When swiping between different sections, select the corresponding tab
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
               actionBar.setSelectedNavigationItem(position);
            }
        });


        // == Setting up the tabs ==

        ActionBar.TabListener tabListener = new ActionBar.TabListener() {




            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

                int position = tab.getPosition();
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        };

        String[] tabs = {
                "Mr.Chetan ", "Mr.Imman & Mr.Adhavan", " Mr.Gnanasambandam"
        };
        ActionBar.Tab tab;
        for (int i = 0; i < tabs.length; i++) {
            tab = actionBar
                    .newTab() // create new tab
                    .setText(tabs[i]) // set text
                    .setTabListener(tabListener); // set tab listener
            actionBar.addTab(tab);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.guest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

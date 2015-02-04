package com.inspiron.tharun26.saaral15;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by tharun26 on 5/2/15.
 */
public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    protected Context mContext;

    public CustomPagerAdapter(android.support.v4.app.FragmentManager fm, Context context) {
        super(fm);

        mContext = context;
    }

    @Override
    // This method returns the fragment associated with
    // the specified position.
    //
    // It is called when the Adapter needs a fragment
    // and it does not exists.
    public Fragment getItem(int position) {

        Fragment fragment =new FirstFragment();
        switch (position) {
            case 0:





            return fragment;



            case 1:
                 fragment = new SecondFragment();

                // Attach some data to it that we'll
                // use to populate our fragment layouts

                // Set the arguments on the fragment
                // that will be fetched in DemoFragment@onCreateView


                return fragment;
            case 2:
                fragment = new ThirdFragment();


                return fragment;
        }

        // Create fragment object
      //  Fragment fragment = new DemoFragment();

        // Attach some data to it that we'll
        // use to populate our fragment layouts

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

}

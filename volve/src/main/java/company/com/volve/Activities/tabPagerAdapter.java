package company.com.volve.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import company.com.volve.Fragments.CarFragment;
import company.com.volve.Fragments.WalkFragment;
import company.com.volve.Fragments.BikeFragment;
import company.com.volve.Fragments.PublicTransportFragment;

/**
 * Created by tejaswinikumar on 16/03/18.
 */

public class tabPagerAdapter extends FragmentPagerAdapter {

    private String[] tabs_array = new String[]{"Walk", "Car", "Public Transport", "Bike"};

    public tabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs_array[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                return new WalkFragment();

            case 1:
                return new CarFragment();

           case 2:
                return new PublicTransportFragment();

            case 3:
                return new BikeFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}

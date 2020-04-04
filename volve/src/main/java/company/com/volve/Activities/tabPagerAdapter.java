package company.com.volve.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import company.com.volve.Fragments.ClubTabFragment;
import company.com.volve.Fragments.EventsTabFragment;
import company.com.volve.Fragments.SUTabFragment;
import company.com.volve.Fragments.SocTabFragment;

/**
 * Created by tejaswinikumar on 16/03/18.
 */

public class tabPagerAdapter extends FragmentPagerAdapter {

    private String[] tabs_array = new String[]{"All Events", "Clubs", "Societies", "SU"};

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
                return new EventsTabFragment();

            case 1:
                return new ClubTabFragment();

           case 2:
                return new SocTabFragment();

            case 3:
                return new SUTabFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}

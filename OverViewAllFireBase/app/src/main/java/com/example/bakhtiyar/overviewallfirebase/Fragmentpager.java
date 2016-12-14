package com.example.bakhtiyar.overviewallfirebase;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Bakhtiyar on 12/7/2016.
 */
public class Fragmentpager extends FragmentPagerAdapter {
    public Fragmentpager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){

            return new WriteFragment();

        }
        if(position==1){

            return new ReadFragment();
        }
        else {
            return null;

        }
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){

            return "Write";

        }
        else if(position==1){
            return "Read";

        }
        else {
            return null;

        }
    }
}

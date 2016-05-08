package com.example.atmosfera.garantias.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.atmosfera.garantias.fragments.CalendarioFragment;
import com.example.atmosfera.garantias.fragments.ListadoFragment;
import com.example.atmosfera.garantias.fragments.ProveedoresFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=new Fragment();
        switch (position) {
            case 0:
                fragment = ListadoFragment.getInstance(position);
                break;
            case 1:
                fragment = CalendarioFragment.getInstance(position);
                break;
            case 2:
                fragment = ProveedoresFragment.getInstance(position);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}

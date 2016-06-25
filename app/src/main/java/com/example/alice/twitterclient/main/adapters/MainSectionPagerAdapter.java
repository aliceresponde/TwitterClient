package com.example.alice.twitterclient.main.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by alice on 6/25/16.
 * v4.app.Fragment
 */

public class MainSectionPagerAdapter extends FragmentPagerAdapter {

    private String[] titles;
    private Fragment[] fragments;


    public MainSectionPagerAdapter(FragmentManager fm, String[] titles, Fragment[] fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragments[position];
    }


    /**
     * LEt us see tabTitle
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}

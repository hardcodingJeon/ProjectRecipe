package com.sonlcr1.projectrecipe.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.sonlcr1.projectrecipe.fragment.BoardTabFragment;
import com.sonlcr1.projectrecipe.fragment.HomeTabFragment;
import com.sonlcr1.projectrecipe.fragment.TVTabFragment;
import com.sonlcr1.projectrecipe.fragment.ThemaTabFragment;


public class PagerAdapter extends FragmentPagerAdapter {
    Fragment[] fragments = new Fragment[4];
    String[] title = new String[]{"홈","테마","TV","게시판"};

    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments[0] = new HomeTabFragment();
        fragments[1] = new ThemaTabFragment();
        fragments[2] = new TVTabFragment();
        fragments[3] = new BoardTabFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }







}

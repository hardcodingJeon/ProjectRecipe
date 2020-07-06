package com.sonlcr1.projectrecipe.adapter;

import android.view.View;

import androidx.annotation.NonNull;

public class PagerAdapter extends androidx.viewpager.widget.PagerAdapter {

    //todo : pagerAdapter 설계 할것
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}

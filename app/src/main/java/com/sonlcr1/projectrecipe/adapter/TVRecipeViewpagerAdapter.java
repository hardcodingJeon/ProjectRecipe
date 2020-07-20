package com.sonlcr1.projectrecipe.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sonlcr1.projectrecipe.fragment.RecipePagerFragmentMaterials;
import com.sonlcr1.projectrecipe.fragment.RecipePagerFragmentTitle;
import com.sonlcr1.projectrecipe.fragment.TVPagerTitle;

public class TVRecipeViewpagerAdapter extends FragmentPagerAdapter {
    Fragment[] fragments = new Fragment[2];

    public TVRecipeViewpagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments[0] = new TVPagerTitle();
        fragments[1] = new RecipePagerFragmentMaterials();

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
}

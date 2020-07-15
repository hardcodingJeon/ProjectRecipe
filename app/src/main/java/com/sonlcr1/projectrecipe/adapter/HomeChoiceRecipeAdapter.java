package com.sonlcr1.projectrecipe.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sonlcr1.projectrecipe.fragment.RecipePagerFragmentExplain;
import com.sonlcr1.projectrecipe.fragment.RecipePagerFragmentMaterials;
import com.sonlcr1.projectrecipe.fragment.RecipePagerFragmentTitle;

public class HomeChoiceRecipeAdapter extends FragmentPagerAdapter {
    Fragment[] fragments = new Fragment[3];

    public HomeChoiceRecipeAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments[0] = new RecipePagerFragmentTitle();
        fragments[1] = new RecipePagerFragmentMaterials();
        fragments[2] = new RecipePagerFragmentExplain();

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

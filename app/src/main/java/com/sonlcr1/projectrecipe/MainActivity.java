package com.sonlcr1.projectrecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.sonlcr1.projectrecipe.adapter.PagerAdapter;

public class MainActivity extends AppCompatActivity {
    //이미지가 안뜸

    ViewPager viewPager;
    TabLayout tabLayout;
    PagerAdapter pagerAdapter;

    //MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        //페이지를 넘겨도 양옆 3칸까지는 파괴하지 않는다, 메모리에 유지 하고 있을수 있다.
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0);


        tabLayout.setupWithViewPager(viewPager);

        

    }
}

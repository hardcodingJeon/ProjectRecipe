package com.sonlcr1.projectrecipe.recipeActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.sonlcr1.projectrecipe.R;
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.setup:
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        Toast.makeText(MainActivity.this, "로그아웃 완료", Toast.LENGTH_SHORT).show();
                        //로그아웃은 완료 되지만 Toast는 안뜬다.
                    }
                });
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}

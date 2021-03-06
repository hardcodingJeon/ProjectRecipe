package com.sonlcr1.projectrecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class IntroActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        iv = findViewById(R.id.iv);
        Glide.with(IntroActivity.this).load(R.drawable.moana).into(iv);
        iv.setAdjustViewBounds(true);
        iv.setBackgroundColor(getResources().getColor(R.color.recipeSymbol));
        handler.sendEmptyMessageDelayed(10,3000);

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Intent intent = new Intent(IntroActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
}

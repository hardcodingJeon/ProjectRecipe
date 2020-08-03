package com.sonlcr1.projectrecipe.recipeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.sonlcr1.projectrecipe.recipeActivity.MainActivity;
import com.sonlcr1.projectrecipe.R;


public class IntroActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

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

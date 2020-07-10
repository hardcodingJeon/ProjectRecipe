package com.sonlcr1.projectrecipe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BoardEdit extends AppCompatActivity {

    EditText et;
    ImageView iv;
    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_edit);

        et = findViewById(R.id.et);
        iv = findViewById(R.id.iv);
        bnv = findViewById(R.id.bnv);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},10);
            }
        }

    }//oncreate...

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 10:
                if (grantResults[0] == PackageManager.PERMISSION_DENIED) {

                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.camera:
                        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intentCamera,100);
                        break;

                    case R.id.picture:
                        Intent intentPicture = new Intent(Intent.ACTION_PICK);
                        intentPicture.setType("image/*");   //settype 지정안하면 갤러리 안나옴
                        startActivityForResult(intentPicture,200);
                        break;
                }

                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch (requestCode){
                case 100:   //카메라는 uri아니면 bitmap형식인데 거의 bitmap이다
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap)bundle.get("data");
                    Glide.with(this).load(bitmap).into(iv);
                    break;
                case 200:
                    Uri uri = data.getData();
                    Glide.with(this).load(uri).into(iv);
                    break;
            }
        }
    }
}

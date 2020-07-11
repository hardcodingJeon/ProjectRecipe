package com.sonlcr1.projectrecipe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardEdit extends AppCompatActivity {

    Uri imgUri;
    String imgFile;

    EditText et;
    ImageView iv;
    BottomNavigationView bnv;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_edit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home: //뒤로 가기 눌렀을때의 아이디가 이미 키로 명시되있음.
                finish();
                break;
            case R.id.resister:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.board_edit_resister,menu);

        return true;
    }

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
                        setImageUri();
                        if (imgUri!=null) intentCamera.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);

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
//                    Bundle bundle = data.getExtras();
//                    Bitmap bitmap = (Bitmap)bundle.get("data");
//                    Glide.with(this).load(bitmap).into(iv);
                    Glide.with(this).load(imgUri).into(iv);

                    break;
                case 200:
                    Uri uri = data.getData();
                    Glide.with(this).load(uri).into(iv);
                    imgFile = getRealPathFromUri(uri);
                    break;
            }
        }
    }

    void setImageUri(){
        //File path = Environment.getExternalStorageDirectory();
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd_hhmmss");
        String fileName = sdf.format(new Date())+".png";
        file = new File(path,fileName);
        imgFile = file.toString();

        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.N){
            imgUri = Uri.fromFile(file);
        }else{
            imgUri = FileProvider.getUriForFile(this,getPackageName(),file);
        }
        new AlertDialog.Builder(this).setMessage("uri : "+imgUri.toString()+"\nfile : "+file.toString()).create().show();
    }

    //테이블 안에 있는 칼럼값을 읽어옴
//Uri -- > 절대경로로 바꿔서 리턴시켜주는 메소드
    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this, uri, proj, null, null, null);
        Cursor cursor= loader.loadInBackground();
        int column_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return  result;
    }
}

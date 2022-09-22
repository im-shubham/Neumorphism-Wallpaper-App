package com.shubham.wallfusion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sdsmdg.tastytoast.TastyToast;

public class SetWallpaper extends AppCompatActivity {

    Intent intent;
    ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);

        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        button = findViewById(R.id.setWallpaper);
        imageView = findViewById(R.id.finalImage);
        intent =getIntent();
        String url = intent.getStringExtra("image");
        Glide.with(getApplicationContext()).load(url).into(imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    wallpaperManager.setBitmap(bitmap);
                    TastyToast.makeText(SetWallpaper.this,"Wallpaper Set Successfully !!",TastyToast.LENGTH_LONG,TastyToast.SUCCESS);
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });
    }
}
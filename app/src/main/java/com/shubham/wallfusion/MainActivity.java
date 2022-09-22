package com.shubham.wallfusion;

import static android.graphics.Color.parseColor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sdsmdg.tastytoast.TastyToast;
import com.shubham.wallfusion.models.ImageModel;
import com.shubham.wallfusion.models.SearchModel;
import com.shubham.wallfusion.recyclerView.Adapter;
import com.shubham.wallfusion.retrofit.ApiUtilities;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<ImageModel> modelClassList;
    RecyclerView recyclerView;
    Adapter adapter;
    LinearLayout natureLinear,foodLinear,creativityLinear,neonLinear,travelLinear,sportLinear;
    EditText searchWallpaper;
    ImageButton searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        natureLinear = findViewById(R.id.natureCard);
        foodLinear = findViewById(R.id.foodCard);
        creativityLinear = findViewById(R.id.creativityCard);
        neonLinear = findViewById(R.id.neonCard);
        travelLinear = findViewById(R.id.travelCard);
        sportLinear = findViewById(R.id.sportCard);
        searchWallpaper = findViewById(R.id.searchEditText);
        searchButton=findViewById(R.id.searchButton);

        modelClassList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(getApplicationContext(),modelClassList);
        recyclerView.setAdapter(adapter);
        findPhotos();
        natureLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TastyToast.makeText(MainActivity.this,"Nature Wallpapers !!",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                String query = "nature";
                getSearchImage(query);
            }
        });

       creativityLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TastyToast.makeText(MainActivity.this,"Creativity Wallpapers !!",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                String query = "creativity";
                getSearchImage(query);

            }
        });
        sportLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TastyToast.makeText(MainActivity.this,"Sports Wallpapers !!",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                String query = "sports";
                getSearchImage(query);
            }
        });
        foodLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TastyToast.makeText(MainActivity.this,"Food Wallpapers !!",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                String query = "food";
                getSearchImage(query);
            }
        });
        neonLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TastyToast.makeText(MainActivity.this,"Neon Wallpapers !!",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                String query = "neon";
                getSearchImage(query);
            }
        });
        travelLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TastyToast.makeText(MainActivity.this,"Travel Wallpapers !!",TastyToast.LENGTH_SHORT,TastyToast.SUCCESS);
                String query = "travel";
                getSearchImage(query);
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchWallpaper.getText().toString().trim().toLowerCase();
                if(query.isEmpty()){
                    TastyToast.makeText(MainActivity.this,"Enter Some Keyword to search",TastyToast.LENGTH_LONG,TastyToast.WARNING);
                }else{
                    getSearchImage(query);
                }
            }
        });

    }


    private void getSearchImage(String query) {

        ApiUtilities.getInstance().getApi().getSearchImage(query,1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClassList.clear();
                if(response.isSuccessful()){
                    assert response.body() != null;
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }else{
                    TastyToast.makeText(MainActivity.this,"Not able to Search ",TastyToast.LENGTH_LONG,TastyToast.WARNING);
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });

    }

    private void findPhotos() {
        modelClassList.clear();
        ApiUtilities.getInstance().getApi().getImage(1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {

                if(response.isSuccessful()){
                    assert response.body() != null;
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }else{
                    TastyToast.makeText(MainActivity.this,"Not able to Search ",TastyToast.LENGTH_LONG,TastyToast.WARNING);
                }

            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }
}
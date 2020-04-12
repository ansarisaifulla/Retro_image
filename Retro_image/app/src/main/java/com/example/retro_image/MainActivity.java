package com.example.retro_image;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    JsonAdapter jsonAdapter;
    RecyclerView recyclerView;

    String TAG="myActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rcv);
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://www.json-generator.com/api/json/get/ckxBoNKjyq/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        JsonApi jsonApi=retrofit.create(JsonApi.class);
        Call<List<model>> call=jsonApi.getModels();
        call.enqueue(new Callback<List<model>>() {
            @Override
            public void onResponse(Call<List<model>> call, Response<List<model>> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "code:"+response.code(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG,"code error");
                }
                jsonAdapter=new JsonAdapter(getApplicationContext(),response.body());
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));
                recyclerView.setAdapter(jsonAdapter);


            }

            @Override
            public void onFailure(Call<List<model>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG,t.getMessage());
            }
        });

    }
}

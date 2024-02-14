package com.example.mathh_picture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


import kotlin.contracts.Returns;
public class MainActivity extends AppCompatActivity {

    TextView NoTimeLimit,Normal,Hard,share;
    Toolbar toolbar;
    int lastlevel;
    int levelno;


    RecyclerView recyclerView;
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        preferences=getSharedPreferences("mypref",MODE_PRIVATE);
        editor=preferences.edit();
        lastlevel=preferences.getInt("lastlevel",-1);

        time=preferences.getInt("time",1);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        NoTimeLimit=findViewById(R.id.NoTimeLimittxt);
        NoTimeLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Horizontal_RecyclerView_Activity.class);
                startActivity(intent);
            }
        });

        Normal=findViewById(R.id.Normaltxt);
        Normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Horizontal_RecyclerView_Activity.class);
                startActivity(intent);
            }
        });

        Hard=findViewById(R.id.Hard);

        Hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Horizontal_RecyclerView_Activity.class);
                startActivity(intent);
            }
        });

        share=findViewById(R.id.sharemenu);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String shareMessage = "https://play.google.com/store/apps/details?id=com.alcamasoft.memorymatch\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.alcamasoft.memorymatch";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.optional_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == R.id.menu_Share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage= "https://play.google.com/store/apps/details?id=com.alcamasoft.memorymatch\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.alcamasoft.memorymatch";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        }
        return super.onOptionsItemSelected(item);
    }
}
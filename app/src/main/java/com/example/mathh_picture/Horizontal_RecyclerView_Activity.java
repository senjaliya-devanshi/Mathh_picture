package com.example.mathh_picture;

import static com.example.mathh_picture.MainActivity.preferences;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathh_picture.R;
import com.example.mathh_picture.RecycleAdapter;

public class Horizontal_RecyclerView_Activity extends AppCompatActivity {

    String levelArr[] = {"Level-1", "Level-2", "Level-3", "Level-4", "Level-5", "Level-6", "Level-7", "Level-8", "Level-9", "Level-10"};
    String textview[] = {"MATCH 2","MATCH 3","MIRREOR","MIRROR 3","MATCH 4","MIRROR 4"};
    RecyclerView recyclerView;
    ListView listView;
    int levelno;
    Toolbar toolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.horizontal_recyclerview_activity);


        levelno=preferences.getInt("lastlevel",0);
        levelno++;

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
//        listView=findViewById(R.id.horizontal_recyclerview_activity_item_listView);

        recyclerView=findViewById(R.id.recyclerView);
        RecycleAdapter adapter=new RecycleAdapter(Horizontal_RecyclerView_Activity.this,levelArr,textview,levelno);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_Share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
            String shareMessage = "https://play.google.com/store/apps/details?id=com.alcamasoft.memorymatch\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.alcamasoft.memorymatch";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "choose one"));
        }
        return super.onOptionsItemSelected(item);
    }
}
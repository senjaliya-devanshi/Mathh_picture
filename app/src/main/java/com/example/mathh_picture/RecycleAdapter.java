package com.example.mathh_picture;

import static com.example.mathh_picture.MainActivity.preferences;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathh_picture.Horizontal_RecyclerView_Activity;
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.Level_Holder> {

    private String i;
    String[] levelArr;
    Horizontal_RecyclerView_Activity horizontal_recyclerView_activity;
    String[] textview;
    int levelno;
    private Object levelstatus;


    public RecycleAdapter(Horizontal_RecyclerView_Activity horizontal_recyclerView_activity, String[] levelArr, String[] textview, int levelno) {
        this.levelArr = levelArr;
        this.horizontal_recyclerView_activity = horizontal_recyclerView_activity;
        this.textview = textview;
        this.levelno = levelno;
    }

    @NonNull
    @Override
    public Level_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(horizontal_recyclerView_activity).inflate(R.layout.horizontal_recyclerview_activity_item, parent, false);
        Level_Holder holder = new Level_Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.Level_Holder holder, @SuppressLint("RecyclerView") int position)
    {
      holder.textView.setText(textview[position]);
    }

    @Override
    public int getItemCount()

    {
        return 6;
    }

    public class Level_Holder extends RecyclerView.ViewHolder {
        ListView listview;
        ListView_Adapter adapter;
        TextView textView;

        public Level_Holder(@NonNull View itemView) {
            super(itemView);


            listview=itemView.findViewById(R.id.horizontal_recyclerview_activity_item_listView);
            adapter= new ListView_Adapter(horizontal_recyclerView_activity,levelArr);
            listview.setAdapter(adapter);
            textView=itemView.findViewById(R.id.matchtext);


            String levelstatus=preferences.getString("levelStatus","pending");

            Log.d("UUU", "position=" + i + "=>" + preferences.getString("levelStatus" + i, "pending"));

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i==0)
                    {
                        Intent intent = new Intent(horizontal_recyclerView_activity, Gridview_Activity.class);
                        intent.putExtra("levelno", i);
                        horizontal_recyclerView_activity.startActivity(intent);
                        horizontal_recyclerView_activity.finish();
                    }

                    if(levelstatus.equals("win") || levelno==i)
                    {
                        Intent intent = new Intent(horizontal_recyclerView_activity, Gridview_Activity.class);
                        intent.putExtra("levelno",i);
                        horizontal_recyclerView_activity.startActivity(intent);
                        horizontal_recyclerView_activity.finish();
                    }

                }
            });
        }
    }
}
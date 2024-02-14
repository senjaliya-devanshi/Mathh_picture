package com.example.mathh_picture;

import static com.example.mathh_picture.MainActivity.preferences;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListView_Adapter extends BaseAdapter
{
    Horizontal_RecyclerView_Activity horizontal_recyclerView_activity;
    String[] levelArr;
    private String levelStatus;
    int levelno;

    public ListView_Adapter(Horizontal_RecyclerView_Activity horizontal_recyclerView_activity, String[] levelArr)
    {
        this.horizontal_recyclerView_activity=horizontal_recyclerView_activity;
        this.levelArr=levelArr;
    }
    @Override
    public int getCount()
    {
        return levelArr.length;
    }

    @Override
    public Object getItem(int i)
    {
        return i;
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(horizontal_recyclerView_activity).inflate(R.layout.horizontal_recyclerview_activity_item_item, viewGroup, false);
        TextView textView = view.findViewById(R.id.textView);
        TextView text = view.findViewById(R.id.text);
        textView.setText("" + levelArr[i]);
        String time = String.valueOf(preferences.getInt("time" + i, 0));

        String levelStatus = preferences.getString("levelStatus" + i, "pending");
        Log.d("iii", "onItemClick: pos=" + i + " levelStatus=" + levelStatus);
        levelno = preferences.getInt("lastlevel", 0);

        if (levelStatus.equals("win") ) {
            text.setVisibility(view.INVISIBLE);
            textView.setText("" + levelArr[i] + " " + time + " sec");
        }
        else if ( i == 0 || i == (levelno + 1)) {
            text.setVisibility(view.INVISIBLE);
            textView.setText("" + levelArr[i] + " ");

        }else if (levelStatus.equals("pending")) {
            text.setVisibility(view.VISIBLE);
            text.setText("" + levelArr[i]);

        }

        return view;
    }
}


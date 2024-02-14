package com.example.mathh_picture;
import static com.example.mathh_picture.MainActivity.preferences;

import static com.example.mathh_picture.MainActivity.editor;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
public class Gridview_Adapter extends BaseAdapter {

    Gridview_Activity gridview_activity;
    List<String> imgList;
    TextView timer;
    View mask2;
    private  int time;
    int levelno,position;
    ProgressBar progressBar;
    CountDownTimer countDownTimer1;


    int click=1,pos1,pos2,count=0;
    private CountDownTimer CountDownTimer1;
    private boolean win;
    int i;
    private Object RelativeLayout;


    public Gridview_Adapter(Gridview_Activity gridview_activity, List<String> imgList, TextView timer, int levelno, ProgressBar progressBar) {
        this.gridview_activity = gridview_activity;
        this.imgList = imgList;
        this.timer = timer;
        this.levelno = levelno;
        this.progressBar = progressBar;
    }





    @Override
    public int getCount()
    {
        return imgList.size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(gridview_activity).inflate(R.layout.gridview_item_activity, viewGroup, false);
        ImageView imageView=view.findViewById(R.id.activity_gridview_item_imageview);
        View mask1=view.findViewById(R.id.mask1);

        RelativeLayout relativeLayout = view.findViewById(R.id.relativeLayout);

        InputStream inputstream= null;
        try {
            inputstream = gridview_activity.getAssets().open(""+imgList.get(position));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Drawable drawable = Drawable.createFromStream(inputstream, null);
        imageView.setImageDrawable(drawable);

        progressBar.setMax(5);

        String levelStatus=preferences.getString("levelStatus"+levelno,"pending");
        CountDownTimer countDownTimer=new CountDownTimer(6000,1000) {
            @Override
            public void onTick(long l) {
                int interval= (int) (l/1000);
                timer.setText(l/1000+ " / 5");
                progressBar.setProgress(interval);
            }

            @Override
            public  void onFinish() {
                mask1.setVisibility(View.VISIBLE);
                Handler handler=new Handler();

               CountDownTimer1=new CountDownTimer(3600000,1000){

                   @Override
                   public void onTick(long l) {
                       progressBar.setMax(3600);
                       int interval= (int) (l/1000);
                       timer.setText("" + (progressBar.getMax() - interval) + "/0");
                       progressBar.setProgress(progressBar.getMax() - interval);
                       time = progressBar.getProgress();
                       if (count==6)
                       {

                           cancel();
                       }



                   }
                   @Override
                   public void onFinish() {

                   }
               }.start();



                    relativeLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Runnable runnable;
                            if (click == 1) {
                                mask1.setVisibility(View.INVISIBLE);
                                //click=1;
                                pos1 = position;
                                mask2 = mask1;
                                click = 3;
                                runnable = new Runnable() {
                                    @Override
                                    public void run() {
                                        click = 2;
                                    }
                                };
                                handler.postDelayed(runnable, 500);
                            }
                            if (click == 2) {
                                mask1.setVisibility(View.INVISIBLE);
                                pos2 = position;
                                click = 3;

                                if (imgList.get(pos1).equals(imgList.get(pos2))) {
                                    mask2.setVisibility(View.INVISIBLE);
                                    count++;
                                    runnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            click = 1;
                                        }
                                    };
                                    handler.postDelayed(runnable, 500);
                                } else {


                                    runnable = new Runnable() {

                                        @Override
                                        public void run() {
                                            mask1.setVisibility(View.VISIBLE);
                                            mask2.setVisibility(View.VISIBLE);
                                            click = 1;
                                        }
                                    };
                                    handler.postDelayed(runnable, 500);
                                }
                            }

                            if (count == 6) {

                                editor.putInt("lastlevel", levelno);
                                Log.d("GGG", "onClick:position="+position+"   levelStatus="+levelStatus);
                                if(levelStatus.equals("pending"))
                                {
                                    editor.putString("levelStatus" + levelno, "win");
                                    editor.commit();
                                    editor.putInt("time" + levelno,time);
                                    editor.commit();
                                }


                                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(gridview_activity);
                                builder.setTitle("LEVEL " + levelno + 1 + "complete");
                                builder.setMessage("" + time + " SECONDS\n" + (levelno + 1) + " Level\nWELL DONE!!!!");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent(gridview_activity, Horizontal_RecyclerView_Activity.class);
                                        gridview_activity.startActivity(intent);
                                    }
                                }).show();
                            }
                        }
                    });
                }
        }.start();

        return view;
    }
}
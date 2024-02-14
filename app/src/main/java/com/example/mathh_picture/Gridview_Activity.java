package com.example.mathh_picture;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Gridview_Activity extends AppCompatActivity {
    Toolbar toolbar;
    GridView gridView;
    TextView timer;
    ProgressBar progressBar;
    int levelno;

    Button btngo;
    List<String> imgList=new ArrayList<>();
    List<String> list=new ArrayList<>();
    ArrayList<String> imgListDuplicate=new ArrayList<>();

    private Gridview_Adapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_activity);

        getImageFromAssets();

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        levelno=getIntent().getIntExtra("levelno",0);
        timer=findViewById(R.id.timer);
        progressBar=findViewById(R.id.progressBar);
        gridView = findViewById(R.id.gridview);

        Dialog dialog = new Dialog(Gridview_Activity.this);
        dialog.setContentView(R.layout.dialog);
        TextView msg = dialog.findViewById(R.id.textView);
        Button btngo = dialog.findViewById(R.id.btngo);
        dialog.show();

        btngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gridview_Adapter adapter = new Gridview_Adapter(Gridview_Activity.this, imgList,timer, levelno, progressBar);
                gridView.setAdapter(adapter);

                dialog.dismiss();
            }
        });

    }

//    private void showDialouge()
//    {
//        AlertDialog.Builder dia
//        logBuilder=new AlertDialog.Builder(Gridview_Activity.this);
//        dialogBuilder.setTitle("Alert...!");
//        dialogBuilder.setMessage("YOU HAVE 5 SECOND TO MEMORIZE ALL IMAGES");
//        dialogBuilder.setPositiveButton("go",new DialogInterface.OnDismissListener())
//        public void onClick(DialogInterface DialogInterface, int i) {
//
//        GridView.setAdapter(Adapter);
//        dialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialogInterface) {
//                onDismiss(dialogInterface);
//            }
//        });

    private void getImageFromAssets()
    {
        ArrayList<String> pathList = new ArrayList<>();
        try {


            String[] images = getAssets().list("");

            list= Arrays.asList(images);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgList.addAll((list.subList(0,6)));
        imgList.addAll(imgList);
        Collections.shuffle(imgList);

        Log.d("UUU", "getImageFromAssets: imgList="+imgList);
        Log.d("UUU", "getImageFromAssets: imgList Length="+imgList.size());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optional_menu,menu);
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
package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.channels.GatheringByteChannel;
import java.util.Random;

public class Game1 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    public int numleft;
    public int numright;
    Array array = new Array();
    Random random = new Random();
    public int count = 0;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level1);

        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

        final TextView text_left = findViewById(R.id.text_left);
        final TextView text_right = findViewById(R.id.text_right);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //Вызов диалога в начале игры
        dialog = new Dialog(this);//Создаём диалоговое окно
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//Скрываем заголовок
        dialog.setContentView(R.layout.previewdialog);//Путь к макету
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Game1.this,GameZone.class);
                    startActivity(intent);finish();
                }catch (Exception e){
                }
                dialog.dismiss();
            }
        });

        Button btncont = (Button)dialog.findViewById(R.id.btncontinue);
        btncont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

        //________________________________________________


        dialogEnd = new Dialog(this);//Создаём диалоговое окно
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);//Скрываем заголовок
        dialogEnd.setContentView(R.layout.dialogend);//Путь к макету
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.setCancelable(false);

        TextView btnclose2 = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Game1.this,GameZone.class);
                    startActivity(intent);finish();
                }catch (Exception e){
                }
                dialogEnd.dismiss();
            }
        });

        Button btncont2 = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btncont2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(Game1.this,GameZone.class);
                    startActivity(intent);finish();
                }catch (Exception e){}
                dialogEnd.dismiss();
            }
        });

        //_________________________________________

        Button btnback = (Button)findViewById(R.id.button_back);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Game1.this,GameZone.class);
                    startActivity(intent);finish();
                }catch (Exception e){
                }
            }
        });

        final int[] progress = {
                R.id.point1,R.id.point2,R.id.point3,R.id.point4,R.id.point5,
        };

        final Animation a = AnimationUtils.loadAnimation(Game1.this, R.anim.alpha);

        numleft = random.nextInt(10);
        img_left.setImageResource(array.images1[numleft]);
        text_left.setText(array.texts1[numleft]);

        numright = random.nextInt(10);
        while (numleft == numright){
            numright = random.nextInt(10);
        }
        img_right.setImageResource(array.images1[numright]);
        text_right.setText(array.texts1[numright]);

        //Левая картинка нажатие

        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    img_right.setEnabled(false);
                    if(numleft>numright){
                        img_left.setImageResource(R.drawable.img_true);
                    }else{
                        img_left.setImageResource(R.drawable.img_false);
                    }
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    if(numleft>numright){
                        if(count<5){
                            count=count+1;
                        }
                        for(int i=0;i<5;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else{
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else {
                                count = count - 2;
                                }
                            }
                        for(int i=0;i<4;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }
                    if(count==5){
                        dialogEnd.show();

                    }else{
                        numleft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numleft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numleft]);

                        numright = random.nextInt(10);
                        while (numleft == numright){
                            numright = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images1[numright]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numright]);
                        img_right.setEnabled(true);
                    }
                }
                return true;
            }
        });

        //Правая картинка нажатие

        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    img_left.setEnabled(false);
                    if(numleft<numright){
                        img_right.setImageResource(R.drawable.img_true);
                    }else{
                        img_right.setImageResource(R.drawable.img_false);
                    }
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    if(numleft<numright){
                        if(count<5){
                            count=count+1;
                        }
                        for(int i=0;i<5;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else{
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else {
                                count = count - 2;
                            }
                        }
                        for(int i=0;i<4;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        for(int i=0;i<count;i++){
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    }
                    if(count==5){
                        dialogEnd.show();

                    }else{
                        numleft = random.nextInt(10);
                        img_left.setImageResource(array.images1[numleft]);
                        img_left.startAnimation(a);
                        text_left.setText(array.texts1[numleft]);

                        numright = random.nextInt(10);
                        while (numleft == numright){
                            numright = random.nextInt(10);
                        }
                        img_right.setImageResource(array.images1[numright]);
                        img_right.startAnimation(a);
                        text_right.setText(array.texts1[numright]);
                        img_left.setEnabled(true);
                    }
                }
                return true;
            }
        });

    }
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(Game1.this,GameZone.class);
            startActivity(intent);finish();
        }catch (Exception e){}


    }
}
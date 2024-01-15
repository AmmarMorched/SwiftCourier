package com.esprit.delivery_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.esprit.delivery_app.R;

public class page1 extends AppCompatActivity {
    float x1,y1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
    }
    public boolean onTouchEvent(MotionEvent motionEvent){
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_UP:
                x1=motionEvent.getX();
                y1=motionEvent.getY();
                Intent i =new Intent(page1.this, Page2.class);
                startActivity(i);
                break;
        }
        return false;
    }
}
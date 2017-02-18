package com.example.saurabhsinghrajput.mathtester;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by SAURABH SINGH RAJPUT on 02-10-2016.
 */
public class startscreenhandler extends AppCompatActivity {
    TextView starttext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startscreen);
        starttext=(TextView)findViewById(R.id.textView);

        Typeface font_new=Typeface.createFromAsset(getAssets(),"fonts/Kreon-Regular.ttf");
        starttext.setTypeface(font_new);
        Handler hndl=new Handler();
        hndl.postDelayed(new Runnable() {
            @Override
            public void run() {
            startActivity(new Intent(startscreenhandler.this,secondPage.class));
            finish();
            }
        },6000);



    }
}

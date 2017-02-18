package com.example.saurabhsinghrajput.mathtester;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by SAURABH SINGH RAJPUT on 03-10-2016.
 */
public class secondPage extends AppCompatActivity {
    Button btnpl,btnlast,btncre,btnabout,btnalert;
    score_saver sv;
    TextView custem_title,Highest_score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optionmenu);

        btnpl=(Button)findViewById(R.id.btnplay);
        btnlast=(Button)findViewById(R.id.btnlastscore);
        btncre=(Button)findViewById(R.id.btncredit);
        btnabout=(Button)findViewById(R.id.btnabout);
        sv=new score_saver(this);

        btnclick();

    }

    private void btnclick() {
        btnpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(secondPage.this,MainActivity.class));
                finish();

            }
        });

        btnlast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(secondPage.this);
                LayoutInflater infl=getLayoutInflater();
                View view2=infl.inflate(R.layout.highest_score,null);
                builder.setView(view2);
                builder.setCancelable(false);
                Highest_score=(TextView)view2.findViewById(R.id.highsest_score_page);
                custem_title=(TextView)view2.findViewById(R.id.title_highest_page);
                Cursor cr=sv.getalldata();
                if(cr.getCount()==0)
                {
                    View view3=infl.inflate(R.layout.about,null);
                    builder.setView(view3);
                    TextView title=(TextView)view3.findViewById(R.id.about_page_title);
                    TextView text=(TextView)view3.findViewById(R.id.about_page_text);;
                    title.setText("Error");
                    text.setText("You have not played any game till Yet.");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create();
                    builder.show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();

                while(cr.moveToNext()) {

                    Highest_score.setText(String.valueOf(cr.getInt(0)));


                }

                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                builder.create();
                builder.show();





            }
        });


        btncre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder bld=new AlertDialog.Builder(secondPage.this);
                LayoutInflater infl=getLayoutInflater();
                View view1=infl.inflate(R.layout.credit,null);

                bld.setView(view1);
                bld.setCancelable(false);
                bld.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                bld.show();



            }
        });


        btnabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder bld=new AlertDialog.Builder(secondPage.this);
                LayoutInflater infl=getLayoutInflater();
                View view1=infl.inflate(R.layout.about,null);

                bld.setView(view1);
                bld.setCancelable(false);
                bld.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                bld.show();


            }
        });




    }


}

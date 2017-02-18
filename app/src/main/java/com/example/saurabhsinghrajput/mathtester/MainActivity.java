package com.example.saurabhsinghrajput.mathtester;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    counttimer cnt,cnt1;
    long remaining;
    timer_management_thread tmt;
    Button btnback;
    score_saver sv;
    TextView view_timer,value1,value2,option0,option1,option2,option3,score_inc, cust_alert_title,highst_score,your_score;
    ImageView oprtr,decision;
    MediaPlayer mp;
    int oprnd1,oprnd2,res,optn_setr,r1=1;
    int score;
    Random rnd,rnd1;


    @Override
    public void onBackPressed() {
        cnt.cancel();
        LayoutInflater inflater=getLayoutInflater();
        AlertDialog.Builder build=new AlertDialog.Builder(MainActivity.this);
        View view=inflater.inflate(R.layout.about,null);
        build.setView(view);
        build.setCancelable(false);
        TextView txt=(TextView)view.findViewById(R.id.about_page_text);
        TextView txt1=(TextView)view.findViewById(R.id.about_page_title);
        txt1.setText("Confirmation");
        txt.setText("Are you sure to exit?");

        build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            System.exit(1);


            }
        });

        build.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

          cnt=new counttimer(remaining,1000);
                cnt.start();


            }
        });

        build.create();
        build.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sv=new score_saver(this);
        view_timer=(TextView)findViewById(R.id.timer_view);
        value1=(TextView)findViewById(R.id.val1);
        value2=(TextView)findViewById(R.id.val2);
        score_inc=(TextView)findViewById(R.id.score_changer);

        option0=(TextView)findViewById(R.id.opn1);
        option1=(TextView)findViewById(R.id.opn2);
        option2=(TextView)findViewById(R.id.opn3);
        option3=(TextView)findViewById(R.id.opn4);

       // mp=MediaPlayer.create(this,R.raw.azhar);
        //mp.start();
        cnt=new counttimer(60000,1000);
       // tmt=new timer_management_thread();

        oprtr=(ImageView)findViewById(R.id.symbol);
        decision=(ImageView)findViewById(R.id.decision);

        LayoutInflater inflater=getLayoutInflater();
        AlertDialog.Builder build=new AlertDialog.Builder(MainActivity.this);
        View view=inflater.inflate(R.layout.about,null);
        build.setView(view);
        build.setCancelable(false);
        TextView txt=(TextView)view.findViewById(R.id.about_page_text);
        TextView txt1=(TextView)view.findViewById(R.id.about_page_title);
        txt1.setText("Information");
        txt.setText("Every correct answer, you will get one point and every wrong answer, you will lose one point. I hope you will enjoy this basic game.");
        build.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                cnt.start();


            }
        });

        build.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(MainActivity.this,secondPage.class));
                finish();

            }
        });
        build.create();
        build.show();




       perform_question_generation();
       // option_seter_helper(optn_setr,res);
        //option_click_checker();

    }

            public class timer_management_thread extends Thread
            {
                @Override
                public void run() {

                   // cnt.start();
                }
            }



    private void perform_question_generation()
             {
                 rnd=new Random();


                        int choose_oprtn=rnd.nextInt(3);
                        oprnd1=rnd.nextInt(100);
                        value1.setText(String.valueOf(oprnd1));
                        oprnd2=rnd.nextInt(100);
                        value2.setText(String.valueOf(oprnd2));
                        switch (choose_oprtn)
                        {
                            case 0:
                                 oprtr.setImageResource(R.drawable.signs);
                                 res=oprnd1+oprnd2;
                                 optn_setr=rnd.nextInt(4);
                                 option_seter_helper(optn_setr,res);
                                 option_click_checker();
                                break;
                            case 1:
                                oprtr.setImageResource(R.drawable.minus);
                                res=oprnd1-oprnd2;
                                optn_setr=rnd.nextInt(4);
                                option_seter_helper(optn_setr,res);
                                option_click_checker();
                                break;
                            case 2:
                                oprtr.setImageResource(R.drawable.multiply);
                                res=oprnd1*oprnd2;
                                optn_setr=rnd.nextInt(4);
                                option_seter_helper(optn_setr,res);
                                option_click_checker();
                                break;
                        }

            }



    public void option_seter_helper(int optn_setr,int res) {

        rnd1=new Random();
        rnd1.nextInt(100);
        if(optn_setr==0)
        {
            option0.setText(String.valueOf(res));
            option1.setText(String.valueOf(rnd1.nextInt(1000)));
            option2.setText(String.valueOf(rnd1.nextInt(10000)));
            option3.setText(String.valueOf(rnd1.nextInt(100)));
            return;

        }
        else if(optn_setr==1)
        {
            option1.setText(String.valueOf(res));
            option0.setText(String.valueOf(rnd1.nextInt(100)));
            option2.setText(String.valueOf(rnd1.nextInt(1000)));
            option3.setText(String.valueOf(rnd1.nextInt(10000)));
            return;
        }
        else if(optn_setr==2)
        {
            option2.setText(String.valueOf(res));
            option1.setText(String.valueOf(rnd1.nextInt(10000)));
            option0.setText(String.valueOf(rnd1.nextInt(100)));
            option3.setText(String.valueOf(rnd1.nextInt(1000)));
            return;
        }
        else if(optn_setr==3)
        {
            option3.setText(String.valueOf(res));
            option1.setText(String.valueOf(rnd1.nextInt(100)));
            option2.setText(String.valueOf(rnd1.nextInt(100)));
            option0.setText(String.valueOf(rnd1.nextInt(1000)));
            return;
        }



    }

    public void option_click_checker() {

        option0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(option0.getText().toString())==res)
                {
                  decision.setImageResource(R.drawable.right);


                    score++;
                    score_inc.setText(String.valueOf(score));
                    if(r1!=0)
                    {
                        perform_question_generation();
                        return;
                    }
                    return;
                }
                else
                {
                    decision.setImageResource(R.drawable.wrong);
                    if(r1!=0)
                    {
                        perform_question_generation();
                        return;
                    }
                    return;
                }

            }
        });

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(option1.getText().toString())==res)
                {
                    decision.setImageResource(R.drawable.right);
                    score++;
                    score_inc.setText(String.valueOf(score));
                    if(r1!=0)
                    {
                        perform_question_generation();
                        return;
                    }
                    return;
                }
                else
                {
                    decision.setImageResource(R.drawable.wrong);
                    score--;
                    score_inc.setText(String.valueOf(score));
                    if(r1!=0)
                    {
                        perform_question_generation();
                        return;
                    }
                    return;
                }

            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(option2.getText().toString())==res)
                {
                    decision.setImageResource(R.drawable.right);
                    score++;
                    score_inc.setText(String.valueOf(score));
                    if(r1!=0)
                    {
                        perform_question_generation();
                        return;
                    }
                    return;
                }
                else
                {
                    decision.setImageResource(R.drawable.wrong);
                    score--;
                    score_inc.setText(String.valueOf(score));
                    if(r1!=0)
                    {
                        perform_question_generation();
                        return;
                    }
                    return;
                }

            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(option3.getText().toString())==res)
                {
                    decision.setImageResource(R.drawable.right);
                    score++;
                    score_inc.setText(String.valueOf(score));
                    if(r1!=0)
                    {
                        perform_question_generation();
                        return;
                    }
                    return;
                }
                else
                {
                    decision.setImageResource(R.drawable.wrong);
                    score--;
                    score_inc.setText(String.valueOf(score));
                    if(r1!=0)
                    {
                        perform_question_generation();
                        return;
                    }
                    return;
                }

            }
        });

    }




    public class counttimer extends CountDownTimer
    {
        public counttimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            long mills=l;

            String hms=String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(mills) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(mills)),
                    TimeUnit.MILLISECONDS.toSeconds(mills) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mills))
            );
            view_timer.setText(hms);
            remaining=TimeUnit.MILLISECONDS.toSeconds(mills) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(mills));
            remaining=remaining*1000;
             // Toast.makeText(MainActivity.this,String.valueOf(remaining),Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFinish() {
          if(remaining<=1000)
           {
               r1=0;

               AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
               //builder.setView(v);
               LayoutInflater infl=getLayoutInflater();
               View view=infl.inflate(R.layout.custom_alert,null);
               builder.setView(view);
               builder.setCancelable(false);
               cust_alert_title=(TextView)view.findViewById(R.id.custom_alert_title_dialogbox);
               your_score=(TextView)view.findViewById(R.id.your_score);
               highst_score=(TextView)view.findViewById(R.id.highest_score);

               your_score.setText(String.valueOf(score));
               sv.updatescore(score);
               Cursor cr=sv.getalldata();
               while (cr.moveToNext())
               {
                   highst_score.setText(String.valueOf(cr.getInt(0)));
               }
               builder.setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       finish();
                       startActivity(getIntent());
                   }
               });

               builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       // finish();
                       startActivity(new Intent(MainActivity.this,secondPage.class));
                       finish();

                   }
               });
               view_timer.setText("00:00");

               builder.create();
               builder.show();
          }

        }
    }

}



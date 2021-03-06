package com.example.kridho.aplikasivoting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kridho.aplikasivoting.popup.DialogFactory;
import com.example.kridho.aplikasivoting.popup.OneButtonDialog;

import java.util.Timer;
import java.util.TimerTask;

public class MenuUtama extends AppCompatActivity {
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    ImageView a,b,c,d;


    String id = "", name = "";
    ImageView btn_logout;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        //popup
        String isFirtslogin = getIntent().getStringExtra("first_login");
        if (isFirtslogin!= null && isFirtslogin.equals("true")){
            onBtnSuccessClicked();
        } else{
            onBtnErrorClicked();
        }


        btn_logout = (ImageView) findViewById(R.id.logout);

        sharedpreferences = getSharedPreferences(Constant.KEY_SHAREDPREFS, Context.MODE_PRIVATE);
        id = sharedpreferences.getString(Constant.KEY_ID,"");
        name = sharedpreferences.getString(Constant.KEY_NAME, "");


        btn_logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // update login session ke FALSE dan mengosongkan nilai id dan username
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(MenuUtama.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        a = findViewById(R.id.visi);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MenuUtama.this, form_visimisi.class);
                startActivity(pindah);
            }
        });


        b = findViewById(R.id.voting);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MenuUtama.this, Voting.class);
                startActivity(pindah);
            }
        });

        c = findViewById(R.id.poling);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MenuUtama.this, menu_polling.class);
                startActivity(pindah);
            }
        });

        d = findViewById(R.id.about);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(MenuUtama.this, pop.class);
                startActivity(pindah);
            }
        });

        viewPager = (ViewPager) findViewById(R.id.ViewPager);

        sliderDotspanel = (LinearLayout) findViewById(R.id.SlideDots);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);
    }

    public void onBtnSuccessClicked() {
        OneButtonDialog oneButtonDialog =
                DialogFactory.makeSuccessDialog(R.string.success_title,
                        R.string.success_message,
                        R.string.success_button_text,
                        new OneButtonDialog.ButtonDialogAction() {
                            @Override
                            public void onButtonClicked() {

                            }
                        });
        oneButtonDialog.show(getSupportFragmentManager(), OneButtonDialog.TAG);
    }

    public void onBtnErrorClicked() {
        OneButtonDialog oneButtonDialog =
                DialogFactory.makeErrorDialog(R.string.error_title,
                        R.string.error_message,
                        R.string.success_button_text,
                        new OneButtonDialog.ButtonDialogAction() {
                            @Override
                            public void onButtonClicked() {

                            }
                        });
        oneButtonDialog.show(getSupportFragmentManager(), OneButtonDialog.TAG);
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            MenuUtama.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);

                    } else if (viewPager.getCurrentItem() == 2) {
                        viewPager.setCurrentItem(3);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });


        }

    }

}
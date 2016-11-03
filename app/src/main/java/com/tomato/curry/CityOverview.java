package com.tomato.curry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tomato.curry.Data.TcData;
import com.tomato.curry.Font.TcFontIcons;

public class CityOverview extends Activity {
    private ImageView ivapplogo, ivmen, ivwomen;
    private LinearLayout llComming_soon, llmen_women_selection;
    private Intent obIntent;
    private Integer city_image;
    private RelativeLayout rlrootlayout;
    private TextView city_title, city_description;
    private TcFontIcons ivback;
    public TcData objTcData;
    public Intent intent;
    String city_name, city_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_overview);
        obIntent = getIntent();
        objTcData = new TcData();
        rlrootlayout = (RelativeLayout) findViewById(R.id.city_overview_root);
        city_title = (TextView) findViewById(R.id.city_title);
        city_description = (TextView) findViewById(R.id.city_description);
        llComming_soon = (LinearLayout) findViewById(R.id.comingsooon_layout);
        llmen_women_selection = (LinearLayout) findViewById(R.id.men_women_selection);
        city_name = obIntent.getStringExtra("city");
        city_image = obIntent.getIntExtra("image", 0);
        city_desc = obIntent.getStringExtra("city_description");
        city_title.setText(city_name);
        city_description.setText(city_desc);
        rlrootlayout.setBackgroundResource(city_image);
        if (!city_name.equalsIgnoreCase("TIRUPATHI")) {
            llmen_women_selection.setVisibility(View.GONE);
            llComming_soon.setVisibility(View.VISIBLE);
        } else {
            llmen_women_selection.setVisibility(View.VISIBLE);
        }
        ivback = (TcFontIcons) findViewById(R.id.ivback);
        ivapplogo = (ImageView) findViewById(R.id.app_logo);
        ivmen = (ImageView) findViewById(R.id.men);
        ivwomen = (ImageView) findViewById(R.id.women);
        ivmen.setOnClickListener(Onclickpic);
        ivwomen.setOnClickListener(Onclickpic);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivapplogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CityOverview.this, SplashScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    View.OnClickListener Onclickpic = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.men:
                    intent = new Intent(CityOverview.this, SaloonList.class);
                    objTcData.IsMen = true;
                    startActivity(intent);
                    break;

                case R.id.women:
                    intent = new Intent(CityOverview.this, SaloonList.class);
                    objTcData.IsMen = false;
                    startActivity(intent);
                    break;

            }

        }
    };
}

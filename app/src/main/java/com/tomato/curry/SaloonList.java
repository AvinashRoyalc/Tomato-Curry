package com.tomato.curry;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tomato.curry.Adapters.SaloonListAdapter;
import com.tomato.curry.Data.TcData;
import com.tomato.curry.Font.TcFontIcons;
import com.tomato.curry.Font.TcFontIcons2;
import com.tomato.curry.ItemAnimator.ItemAnimatorFactory;

import java.util.ArrayList;

public class SaloonList extends AppCompatActivity {
    private ImageView iv_app_logo;
    private ArrayList<Integer> banner_imgs;
    private String gender;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private TcFontIcons iv_direction;
    private TcFontIcons2 iv_back;
    private SaloonListAdapter saloonListAdapter;
    private int listsize;
    public TcData objTcdata;
    private TextView tvCityName, tvgender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saloon_list);
        iv_app_logo = (ImageView) findViewById(R.id.app_logo);
        iv_direction = (TcFontIcons) findViewById(R.id.ivdirection);
        iv_back = (TcFontIcons2) findViewById(R.id.ivback);
        tvCityName = (TextView) findViewById(R.id.city_name);
        tvgender = (TextView) findViewById(R.id.tvgender);
        banner_imgs = new ArrayList<>();
        objTcdata = new TcData();
        mRecyclerView = (RecyclerView) findViewById(R.id.saloon_list);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_app_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SaloonList.this, SplashScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });
        if (objTcdata.IsMen) {
            gender = "Men";
            tvgender.setText(gender);
            for (int i = 0; i < objTcdata.bannerpics_men.length; i++) {
                banner_imgs.add(objTcdata.bannerpics_men[i]);
            }
        } else {
            gender = "Women";
            tvgender.setText(gender);
            for (int i = 0; i < objTcdata.bannerpics_women.length; i++) {
                banner_imgs.add(objTcdata.bannerpics_women[i]);
            }
        }
        saloonListAdapter = new SaloonListAdapter(banner_imgs);
        listsize = banner_imgs.size();
        mRecyclerView.setItemAnimator(ItemAnimatorFactory.slidein());
        mRecyclerView.setAdapter(saloonListAdapter);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(SaloonList.this, new RecyclerItemClickListener.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(View view, int position) {
                ImageView mImageView = (ImageView) view.findViewById(R.id.saloon_banner);
                final Intent intent = new Intent(SaloonList.this, SaloonSelection.class);
                ActivityOptions options = null;
                if (objTcdata.IsMen) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        options = ActivityOptions
                                .makeSceneTransitionAnimation(SaloonList.this, mImageView, "top_image");
                    }
                    intent.putExtra("SaloonName", objTcdata.SaloonsMen[position]);
                } else {
                    options = ActivityOptions
                            .makeSceneTransitionAnimation(SaloonList.this, mImageView, "top_image");
                    intent.putExtra("SaloonName", objTcdata.SaloonsWoMen[position]);
                }
                // start the new activity
                startActivity(intent, options.toBundle());
            }
        }));

    }
}

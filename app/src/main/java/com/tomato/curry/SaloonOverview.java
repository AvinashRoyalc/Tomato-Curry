package com.tomato.curry;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.tomato.curry.Data.TcData;
import com.tomato.curry.Data.Utils;
import com.tomato.curry.Font.TcFontIcons2;

import java.util.ArrayList;

public class SaloonOverview extends AppCompatActivity {
    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector;
    private ImageView ivbook;
    private String Saloonno, Saloonname, Saloonaddrs;
    private TextView tvsaloonno, tvsaloonname, tvsaloonaddrs;
    private TcFontIcons2 ivback;
    ArrayList<String> arrDownloadUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saloon_overview);
        ivback = (TcFontIcons2) findViewById(R.id.ivback);
        Saloonno = getIntent().getStringExtra("saloonno");
        Saloonname = "." + getIntent().getStringExtra("saloonname");
        Saloonaddrs = getIntent().getStringExtra("saloonadd");
        tvsaloonno = (TextView) findViewById(R.id.tvSaloonno);
        tvsaloonname = (TextView) findViewById(R.id.tvSaloonname);
        tvsaloonaddrs = (TextView) findViewById(R.id.saloon_address);
        tvsaloonno.setText(Saloonno);
        tvsaloonname.setText(Saloonname);
        tvsaloonaddrs.setText(Saloonaddrs);
        mViewFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        mViewFlipper.setAutoStart(false);
//        mViewFlipper.setFlipInterval(4000);
        TcData data = new TcData();
        arrDownloadUrls = data.getImageURLS(Saloonaddrs);
        for (int i = 0; i < arrDownloadUrls.size(); i++) {
            ImageView imageView = new ImageView(this);
            String[] childs = arrDownloadUrls.get(i).split("/");
            String strPath = Utils.isFileExist(childs[childs.length - 1], Utils.getRootFolderPath(SaloonOverview.this));
            if (!TextUtils.isEmpty(strPath)) {
                imageView.setImageBitmap(BitmapFactory.decodeFile(strPath));
            }
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mViewFlipper.addView(imageView);
        }
        ivbook = (ImageView) findViewById(R.id.bookImg);
        ivbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SaloonOverview.this, DateSelection.class));
            }
        });
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Set in/out flipping animations
        mViewFlipper.setInAnimation(this, android.R.anim.fade_in);
        mViewFlipper.setOutAnimation(this, android.R.anim.fade_out);

        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mGestureDetector = new GestureDetector(this, customGestureDetector);
    }

    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Swipe left (next)
            if (e1.getX() > e2.getX()) {
                mViewFlipper.showNext();
            }

            // Swipe right (previous)
            if (e1.getX() < e2.getX()) {
                mViewFlipper.showPrevious();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

}

package com.tomato.curry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class SaloonOverview extends AppCompatActivity {
    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector;
    private  ImageView ivbook;
    private String Saloonno,Saloonname,Saloonaddrs;
private TextView tvsaloonno,tvsaloonname,tvsaloonaddrs;

    int[] resources = {
            R.drawable.nl1,
            R.drawable.nl2,
            R.drawable.nl3,
            R.drawable.nl4,
            R.drawable.nl5,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saloon_overview);
        Saloonno=getIntent().getStringExtra("saloonno");
        Saloonname="."+getIntent().getStringExtra("saloonname");
        Saloonaddrs=getIntent().getStringExtra("saloonadd");
        tvsaloonno=(TextView)findViewById(R.id.tvSaloonno);
        tvsaloonname=(TextView)findViewById(R.id.tvSaloonname);
        tvsaloonaddrs=(TextView)findViewById(R.id.saloon_address);
        tvsaloonno.setText(Saloonno);
        tvsaloonname.setText(Saloonname);
        tvsaloonaddrs.setText(Saloonaddrs);
        mViewFlipper=(ViewFlipper)findViewById(R.id.viewflipper);
        mViewFlipper.setAutoStart(true);
        mViewFlipper.setFlipInterval(4000);
        for (int i = 0; i < resources.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(resources[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mViewFlipper.addView(imageView);
        }
ivbook=(ImageView)findViewById(R.id.bookImg);
        ivbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SaloonOverview.this,DateSelection.class));
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

}

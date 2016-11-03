package com.tomato.curry;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

public class CustomFlipper extends ViewFlipper
{

    Paint paint = new Paint();

    public CustomFlipper(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas)
    {
        super.dispatchDraw(canvas);
        int width = getWidth();

        float margin = 5;
        float radius = 5;
        float bradius=7;
        float cx = width / 2 -((radius + margin) * 2 * getChildCount() / 2);
        float cy = getHeight() - 50;


        canvas.save();

        for (int i = 0; i < getChildCount(); i++)
        {
            if (i == getDisplayedChild())
            {
                paint.setColor(Color.WHITE);
                canvas.drawCircle(cx, cy, bradius, paint);

            } else
            {
                paint.setColor(Color.WHITE);
                canvas.drawCircle(cx, cy, radius, paint);
            }
            cx += 2 * (radius + margin);
        }
        canvas.restore();
    }

}
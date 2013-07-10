package com.example.touchme;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by stephen on 13-7-10.
 */
public class DotView extends View {
    private Dots model = null;

    public DotView(Context context) {
        super(context);
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DotView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public DotView(Context context, Dots model){
        super(context);
        this.model = model;
    }

    public void setModel(Dots model) {
        this.model = model;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(model != null){
            Paint paint = new Paint();
            List<Dot> dots = model.getSafeDots();
            for (Dot dot : dots){
                paint.setColor(dot.getColor());
                canvas.drawCircle(dot.getX(), dot.getY(), dot.getRadius(), paint);
            }
        }
    }
}

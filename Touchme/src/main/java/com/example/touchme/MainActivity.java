package com.example.touchme;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class MainActivity extends Activity {

    public static final int DOT_RADIUS = 6;

    private final Random random = new Random();
    private DotView dotView = null;
    private final Dots dotsModel = new Dots();
    //use HashSet intead of ArrayList,
    // otherwise add/remove won't autobox integer and cause outofbound exception
    private final HashSet<Integer> tracks = new HashSet<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dot_view);
        //set dots model to dotview
        dotView.setModel(dotsModel);

        findViewById(R.id.red_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeDot(dotsModel, Color.RED);
            }
        });
        findViewById(R.id.green_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeDot(dotsModel, Color.GREEN);
            }
        });

        final TextView xView = (TextView) findViewById(R.id.coordinate_x);
        final TextView yView = (TextView) findViewById(R.id.coordinate_y);
        dotsModel.setOnDotsChangeListener(new Dots.DotsChangeListener() {
            @Override
            public void onDotsChanged(Dots dots) {
                Dot d = dots.getLastDot();
                xView.setText((d == null)?"":String.valueOf(d.getX()));
                yView.setText((d == null)?"":String.valueOf(d.getY()));
                dotView.invalidate();
            }
        });

        dotView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int n;
                int index;
                switch(motionEvent.getActionMasked()){
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        tracks.add(motionEvent.getPointerId(motionEvent.getActionIndex()));
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        tracks.remove(motionEvent.getPointerId(motionEvent.getActionIndex()));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        n=motionEvent.getHistorySize();
                        for(Integer id : tracks){
                            index = motionEvent.findPointerIndex(id);
                            for(int i=0; i< n; i++){
                                addPressedDot(dotsModel,
                                        motionEvent.getHistoricalX(index, i),
                                        motionEvent.getHistoricalY(index,i),
                                        motionEvent.getHistoricalPressure(index,i),
                                        motionEvent.getHistoricalSize(index,i));
                            }
                        }

                        break;
                    default:
                        return false;
                }
                for(Integer id : tracks){
                    index = motionEvent.findPointerIndex(id);
                    addPressedDot(dotsModel,
                            motionEvent.getX(index),
                            motionEvent.getY(index),
                            motionEvent.getPressure(index),
                            motionEvent.getSize(index));
                }
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(R.id.action_clear ==item.getItemId()){
            dotsModel.clearDots();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void makeDot(Dots dots, int color){
        int padding  = (DOT_RADIUS + 2)*2;
        float x = random.nextFloat()*(dotView.getWidth() - padding);
        float y = random.nextFloat()*(dotView.getHeight() - padding);
        dots.addDot(x,y,DOT_RADIUS,color);
    }

    private void addPressedDot(Dots dots, float x, float y, float pressure, float size){
        dots.addDot(x,y,size*DOT_RADIUS+1,Color.CYAN);
    }
}

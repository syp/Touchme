package com.example.touchme;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by stephen on 13-7-10.
 */
public class Dots {

    public interface DotsChangeListener{
        public void onDotsChanged(Dots dots);
    }

    private final LinkedList<Dot> dots = new LinkedList<Dot>();
    private final List<Dot> safeDots = Collections.unmodifiableList(dots);
    private DotsChangeListener listener;

    public void addDot(float x, float y, float radius, int color){
        dots.add(new Dot(x,y,radius,color));
        notifyListener();
    }

    public void clearDots(){
        dots.clear();
        notifyListener();
    }

    public Dot getLastDot(){
        return dots.getLast();
    }

    public List<Dot> getSafeDots(){
        return safeDots;
    }

    public void setOnDotsChangeListener(DotsChangeListener listener){
        this.listener = listener;
    }

    private void notifyListener(){
        if(listener != null){
            listener.onDotsChanged(this);
        }
    }

}

package com.example.touchme;

import android.graphics.Color;

/**
 * Created by stephen on 13-7-10.
 */
public class Dot {
    private float x;
    private float y;
    private float radius;
    private int color;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Dot(float x, float y, float radius, int color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }
}

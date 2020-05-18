package com.example.dotsandboxes;

import android.graphics.Color;
import android.graphics.Paint;


public class Line {
    float xStart,yStart,xEnd,yEnd;
    Paint mPaint=new Paint();

    public Line(float xStart,float yStart,float xEnd,float yEnd,int number){
        this.xStart=xStart;
        this.yStart=yStart;
        this.xEnd=xEnd;
        this.yEnd=yEnd;
        if(number==0){
            mPaint.setColor(Color.RED);
        }else{
            mPaint.setColor(Color.BLUE);
        }
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(12);
    }
    public float getxStart(){
        return xStart;
    }
    public float getyStart(){
        return yStart;
    }
    public float getxEnd(){
        return xEnd;
    }
    public float getyEnd(){
        return yEnd;
    }
    public Paint getPaint(){

        return mPaint;
    }
}


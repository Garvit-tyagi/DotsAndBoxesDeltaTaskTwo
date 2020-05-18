package com.example.dotsandboxes;

import android.graphics.Paint;

public class Boxes {
    float left,top,right,bottom;
    int number;
    private Paint boxPaint=new Paint();

    public Boxes(float left,float top,float right,float bottom,int number){
        this.left=left;
        this.top=top;
        this.right=right;
        this.bottom=bottom;
        boxPaint.setColor(number);

    }
    public float getLeft(){
        return left;
    }
    public float getTop(){
        return top;
    }
    public float getRight(){
        return right;
    } public float getBottom(){
        return bottom;
    } public Paint getBoxPaint(){
        return boxPaint;
    }




}
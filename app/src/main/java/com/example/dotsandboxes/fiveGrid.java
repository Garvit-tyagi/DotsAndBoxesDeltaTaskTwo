package com.example.dotsandboxes;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import java.util.ArrayList;


public class fiveGrid extends View {

    float xTouch, yTouch;
  int boxNumber=0;

    ArrayList<Line> lineContainer=new ArrayList<>();
    ArrayList<Boxes>  boxContainer=new ArrayList<>();
    boolean verticalLine [][]=new boolean[5][5];
    boolean horizontalLine [][]=new boolean[5][5];
    int playerNo=0;
    int player1score=0;

    int player2score=0;
    private static final String TAG="CustomView";
    private static int SPLASH_TIME_OUT=1000;
    private Paint  dots;
    private Paint  text1;
    private Paint  text2;
    float cx, cy;
    int width, height;


    public fiveGrid(Context context) {
        super(context);
        init(null);
    }

    public fiveGrid(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public  fiveGrid(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }



    private void init(@Nullable AttributeSet set) {


        dots = new Paint();
        dots.setAntiAlias(true);
        dots.setColor(Color.WHITE);
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                horizontalLine[i][j]=false;
                verticalLine[i][j]=false;
            }
        }
        text1=new Paint();
        text1.setColor(Color.RED);
        text1.setTextSize(100);
        text1.setAntiAlias(true);
        text2=new Paint();
        text2.setColor(Color.BLUE);
        text2.setTextSize(100);
        text2.setAntiAlias(true);


    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cx = getWidth()/2f -400 ;
        cy = getHeight()/2f-400;
        canvas.drawText("Player1",175,200,text1);
        canvas.drawText("Player2",550,200,text2);
        canvas.drawText(new Integer(player1score).toString(),350,300,text1);
        canvas.drawText(new Integer(player2score).toString(),700,300,text2);

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 5; j++) {

                canvas.drawCircle(cx, cy, 10, dots);
                cy+=200;
            }
            cy = getHeight()/2f-400;
            cx+=200;
        }

        for(int k=0;k<lineContainer.size();k++) {
            canvas.drawLine(lineContainer.get(k).getxStart(), lineContainer.get(k).getyStart(),
                    lineContainer.get(k).getxEnd(), lineContainer.get(k).getyEnd(), lineContainer.get(k).getPaint());
        }
        boxNumber=0;
        for(int k=0;k<boxContainer.size();k++){
            canvas.drawRect(boxContainer.get(k).getLeft(),boxContainer.get(k).getTop(),boxContainer.get(k).getRight(),boxContainer.get(k).getBottom(),
                    boxContainer.get(k).getBoxPaint());
            boxNumber++;
        }
        if(boxNumber<16){
            invalidate();}
        else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Vibrator vibe=(Vibrator)getContext().getSystemService(Context.VIBRATOR_SERVICE);
                    assert vibe!=null;
                    vibe.vibrate(80);
                    if(player1score>player2score)
                        Toast.makeText(getContext(),"game Completed:player1 won ",Toast.LENGTH_SHORT).show();
                    else Toast.makeText(getContext(),"game Completed:player2 won ",Toast.LENGTH_SHORT).show();
                    Activity ac=(Activity)getContext();
                    ac.finish();




                }
            },SPLASH_TIME_OUT);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean b = super.onTouchEvent(event);
        int color;

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            xTouch = event.getX();
            yTouch = event.getY();
            cx = getWidth()/2f -400 ;
            cy = getHeight()/2f-400;


            if (xTouch <=getWidth()/2f+400  && yTouch <=getHeight()/2f+400 ) {


                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {


                        if (xTouch >= cx && xTouch <= cx +200 && yTouch >= cy - 40 && yTouch <= cy + 40) {
                            /*horizontal line*/

                            if(!horizontalLine[i][j])   {
                                horizontalLine[i][j]=true;
                                Log.i(TAG,"i j is"+i+j);
                                lineContainer.add(new Line(cx,cy,cx+200,cy,playerNo));
                                int x=i,y=j,flag=1;
                                if(y+1<=4){
                                    if(playerNo==0)color=Color.RED;
                                    else color=Color.BLUE;
                                    if(horizontalLine[x][y]&&verticalLine[x][y]&&horizontalLine[x][y+1]&&verticalLine[x+1][y]){
                                        if(playerNo==0)player1score++;
                                        else player2score++;

                                        boxContainer.add(new Boxes(cx+10,cy+10,cx+200-10,cy+200-10,color) );
                                        playerNo++;
                                        playerNo%=2;
                                        flag=0;
                                    }
                                }
                                if(y-1>=0){

                                    if(horizontalLine[x][y]&&verticalLine[x][y-1]&&verticalLine[x+1][y-1]&&horizontalLine[x][y-1]){
                                        if(flag==0){
                                            playerNo++;
                                            playerNo%=2;
                                        }
                                        if(playerNo==0)color=Color.RED;
                                        else color=Color.BLUE;
                                        if(playerNo==0)player1score++;
                                        else player2score++;

                                        boxContainer.add(new Boxes(cx+10,cy-10,cx+200-10,cy-200+10,color));
                                        playerNo++;
                                        playerNo%=2;

                                    }
                                }
                                playerNo++;
                                playerNo%=2;
                            }


                        } else if (xTouch >= cx - 40 && xTouch <= cx + 40 && yTouch >= cy && yTouch <= cy+200) {
                            /*vertical line*/
                            if(!verticalLine[i][j]){
                                verticalLine[i][j]=true;
                                lineContainer.add(new Line(cx,cy,cx,cy+200,playerNo));
                                int x=i,y=j,flag=1;
                                if(x-1>=0){
                                    if(playerNo==0)color=Color.RED;
                                    else color=Color.BLUE;
                                    if(horizontalLine[x-1][y]&&verticalLine[x-1][y]&&verticalLine[x][y]&&horizontalLine[x-1][y+1]){
                                        if(playerNo==0)player1score++;
                                        else player2score++;


                                        boxContainer.add(new Boxes(cx-10,cy+10,cx-200+10,cy+200-10,color));
                                        playerNo++;
                                        playerNo%=2;
                                        flag=0;
                                    }
                                }
                                if(x+1<=4){

                                    if(horizontalLine[x][y]&&verticalLine[x][y]&&verticalLine[x+1][y]&&horizontalLine[x][y+1]){
                                        if(flag==0){
                                            playerNo++;
                                            playerNo%=2;
                                        }
                                        if(playerNo==0)color=Color.RED;
                                        else color=Color.BLUE;
                                        if(playerNo==0)player1score++;
                                        else player2score++;

                                        boxContainer.add(new Boxes(cx+10,cy+10,cx+200-10,cy+200-10,color));
                                        playerNo++;
                                        playerNo%=2;
                                    }
                                }

                                playerNo++;
                                playerNo%=2;
                            }
                        }




                        cy+=200;

                    }
                    cy=getHeight()/2f-400;
                    cx+=200;

                }

            }
        }
        return b;
    }


}

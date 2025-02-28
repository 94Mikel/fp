package com.ik_2dm3.pizarra;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by ik_2dm3 on 27/10/2016.
 */


public class Board extends View {

    private Bitmap mBitmap = null;
    private Canvas mCanvas = null; //Es un Lienzo
    private Paint mPaint = null;

    private float mX, mY; //Los ultimos en los que he estado. Son mi memoria
    private Path mPath = null;

    private static final float TOLERANCE = 4;

    public Board (Context context, AttributeSet attrs){
        super(context);
        init(context);
    }

    public Board (Context context){
        super(context);
        init(context);
    }

    private void init(Context context){
        //obtenemos la pantalla
        Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        Point point = new Point(); //Point es un punto con dos coordenadas
        display.getSize(point);

        mBitmap = Bitmap.createBitmap(point.x, point.y, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(0xFFFFFFFF);
        mPath = new Path();

        //Preparamos el pincel
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xFF00E1FF);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                touchStart(x,y);
                invalidate(); //Fuerza el recambio
                break;

            case MotionEvent.ACTION_MOVE:
                touchMove(x,y);
                invalidate(); //Fuerza el recambio
                break;

            case MotionEvent.ACTION_UP:
                touchUp();
                invalidate(); //Fuerza el recambio
                break;
        }
        return true; //Porque esta dentro de un boolean
    }

    private void touchStart (float x, float y){
        //Al pulsar nos dice donde esta x e y. El recorrido esta vacio y empieza donde tengo pinchado
        mPaint.reset();
        mPath.moveTo(x,y);
        mX=x;
        mY=y;
    }

    private void touchMove (float x, float y){
                    //La posicion de antes - la posicion de ahora (x-mX) o/y (y-mY)
        if(Math.abs(x-mX)>=TOLERANCE || Math.abs(y-mY)>=TOLERANCE){

            mPath.quadTo();

        }
    }

    private void touchUp (){

    }


}

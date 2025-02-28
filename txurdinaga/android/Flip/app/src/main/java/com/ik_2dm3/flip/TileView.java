package com.ik_2dm3.flip;

import android.content.Context;
import android.widget.Button;

/**
 * Created by ik_2dm3 on 29/09/2016.
 */

public class TileView extends Button {

    //coordenadas
    public int x=0;
    public int y=0;

    //Trama a mostrar
    private int index=0;

    //max de tramas
    //topElements --> cantidad de tramas
    private int topElements = 0;
    public TileView(Context context, int x, int y, int topElements, int index, int background){
            super(context);
        //constructor
        this.x=x;
        this.y=y;
        this.topElements=topElements;
        this.index=index;
        this.setBackgroundResource(background);
    }
    public int getNewIndex(){
        index ++;
        if (index==topElements) index=0;
            return index;
    }
  }






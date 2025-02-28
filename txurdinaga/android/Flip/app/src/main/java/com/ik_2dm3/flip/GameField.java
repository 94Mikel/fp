package com.ik_2dm3.flip;

import android.app.Service;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by ik_2dm3 on 29/09/2016.
 */

public class GameField extends AppCompatActivity {

    int topTilesbHor = 3;
    int topTilesbVer = 3;
    int topsbTramas = 2;
    boolean hasSound = false;
    boolean hasVibration = false;
    String modo;



    //array con los identificadores de las celdas cuando se añaden
    //al layout para poder recuperarlos durante la partida. Array de dos dimensiones.
    private int ids[][]=null;

    //array para guardar los indices (background) de cada una de las celdas.
    //Se utilizara para agilizar la comprobacion de final de partida.
    private int values[][] = null;

    //Funcion para que vibre y suene
    private Vibrator vibratorService = null;
    private MediaPlayer mp = null;

    private static final int[] colors = new int[]{
            R.drawable.amarillo,
            R.drawable.rosa,
            R.drawable.rojo,
            R.drawable.raro,
            R.drawable.azul,
            R.drawable.naranja,
            R.drawable.gris,
            R.drawable.verde
    };

    private static final int[] numbers = new int[]{
            R.drawable.un,
            R.drawable.i2,
            R.drawable.tr,
            R.drawable.cu,
            R.drawable.ci,
            R.drawable.se,
            R.drawable.sie,
            R.drawable.och,
            R.drawable.nue
    };

    private int[] pictures = null;

    private Chronometer Chronometer = null;
    private TextView clickTxt = null;
    private LinearLayout l1 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_field);

        Chronometer = (Chronometer) findViewById(R.id.ch);
        clickTxt = (TextView) findViewById(R.id.clicksTxt);
        l1 = (LinearLayout) findViewById(R.id.fieldLandscape); //creamos esto porque los cuadrados vamos a tener que meterlos en algun lado
        l1.removeAllViews(); //todo lo que tiene dentro lo borra

        Bundle extras = getIntent().getExtras();
        topTilesbHor = extras.getInt("elemsbHor");
        topTilesbVer = extras.getInt("elemsbVer");
        topsbTramas = extras.getInt("elemsbTramas");
        hasSound = extras.getBoolean("Sonido");
        hasVibration = extras.getBoolean("Vibracion");

        modo= extras.getString("TipoJuego");
        if(modo=="colores"){
            pictures=colors;
        }else{
            pictures=numbers;
        }
        //Funcion para que el vibrador funcione
        vibratorService = (Vibrator) (getSystemService(Service.VIBRATOR_SERVICE));
        //Carpeta raw y rana es el sonido que vamos a utilizar
        mp = MediaPlayer.create(this,R.raw.rana);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm); //Cojo la ventana, la pantalla normal, y la metrica
        //En el background los vamos a utilizar como pixeles
        int width = dm.widthPixels/topTilesbHor;
        int height = (dm.heightPixels-180)/topTilesbVer;

        ids = new int[topTilesbHor][topTilesbVer];
        values = new int [topTilesbHor][topTilesbVer];

        Random r = new Random(System.currentTimeMillis());
        int tilePictureToshow = r.nextInt(topsbTramas);
        int ident=0;

        for (int i=0;i<topTilesbVer;i++){
            LinearLayout l2 = new LinearLayout(this);
            l2.setOrientation(LinearLayout.HORIZONTAL);

            //Dentro de esta repeticion se esta creando un boton
            for(int j=0;j<topTilesbHor;j++) {
                tilePictureToshow = r.nextInt(topsbTramas);
                values[j][i] = tilePictureToshow;
                TileView tv = new TileView(this, j, i, topsbTramas, tilePictureToshow, pictures[tilePictureToshow]);
                ident++;
                tv.setId(ident);
                ids[j][i] = ident;

                tv.setHeight(height);
                tv.setWidth(width);

                tv.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View vista) {
                        hasClick(((TileView) vista).x, ((TileView)vista).y);
                    }
                });

                l2.addView(tv);
            }
            l1.addView(l1);
        }
    }
    //El hasClick es lo qe sale del OnClickListener
    public void hasClick (int x, int y){
        if (hasSound) mp.start();
        if (hasVibration) vibratorService.vibrate(100);

        changeView(x,y);

        //esquina sup izq
        if(x==0 && y==0){
            changeView(x+1,y);
            changeView(x,y+1);
            changeView(x+1,y+1);
        }
        //esquina inf izq
        else if(x==0 && y==topTilesbVer-1){
            changeView(x+1,y-1);
            changeView(x,y-1);
            changeView(x+1,y);
        }
        //esq sup der
        else if(x==topTilesbHor-1 && topTilesbVer==0){
            changeView(x-1,y);
            changeView(x,y+1);
            changeView(x-1,y+1);
        }
        //esq inf der
        else if(x==topTilesbHor-1 && y==topTilesbVer-1){
            changeView(x,y-1);
            changeView(x-1,y);
            changeView(x-1,y-1);
        }
        // borde inf
        else if(x==0){
            changeView(x,y-1);
            changeView(x,y+1);
            changeView(x+1,y);
        }
        // Borde sup
        else if(y==0){
            changeView(x-1,y);
            changeView(x+1,y);
            changeView(x,y+1);
        }
        //borde central der
        else if(x==topTilesbHor-1){
            changeView(x,y-1);
            changeView(x,y+1);
            changeView(x-1,y);
        }
        //borde central izq
        else if(y==topTilesbVer-1){
            changeView(x-1,y);
            changeView(x+1,y);
            changeView(x,y-1);
        }
        //centro
        else{
            changeView(x-1,y);
            changeView(x+1,y);
            changeView(x,y-1);
            changeView(x,y+1);
        }


    }

    //Este es el cambio del propio boton
    private void changeView(int x, int y){ // int x e y son coordenadas
     TileView tt = (TileView) findViewById(ids[x][y]);
        int newindex = tt.getNewIndex();
        values[x][y] = newindex;
        tt.setBackgroundResource(pictures[newindex]);
        tt.invalidate();//sirve para reiniciar al valor actual
    }
}
package com.ik_2dm3.flip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class GameConfig extends AppCompatActivity {

    private SeekBar sbHor, sbVer, sbTramas;
    private TextView txtHor, txtVer, txtTramas;
    private int valorHor, valorVer, valorTramas;
    private Button btnJugar;
    private RadioButton rbColores, rbNumeros;
    private CheckBox cbSonido, cbVibracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_config);

        sbHor = (SeekBar) findViewById(R.id.sbHor);
        sbVer = (SeekBar) findViewById(R.id.sbVer);
        sbTramas = (SeekBar) findViewById(R.id.sbTramas);
        txtHor = (TextView) findViewById(R.id.txtHor);
        txtVer = (TextView) findViewById(R.id.txtVer);
        txtTramas = (TextView) findViewById(R.id.txtTramas);
        btnJugar = (Button) findViewById(R.id.btnJugar);
        rbColores = (RadioButton) findViewById(R.id.rbColores);
        rbNumeros = (RadioButton) findViewById(R.id.rbNumeros);
        cbSonido = (CheckBox) findViewById(R.id.cbSonido);
        cbVibracion = (CheckBox) findViewById(R.id.cbVibracion);


        txtHor.setText(getString(R.string.txtHor) + "3");
        txtVer.setText(getString(R.string.txtVer) + "3");
        txtTramas.setText(getString(R.string.txtTramas) + "2");

        sbHor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateX(seekBar.getProgress());
            }
        });

        sbVer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateY(seekBar.getProgress());
            }
        });

        sbTramas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateZ(seekBar.getProgress());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_gameconfig,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.mJugador:
                showPlayer();
                return true;

            case R.id.mHowto:
                showHowto();
                return true;

            case R.id.mAbout:
                showAbout();
                return true;

            default:
                return false;
        }
    }

    protected void updateX(int progress){
        valorHor=progress+3;
        txtHor.setText(getString(R.string.txtHor) + " " + valorHor);
    }
    protected void updateY(int progress){
        valorVer=progress+3;
        txtVer.setText(getString(R.string.txtVer) + " " + valorVer);
    }
    protected void updateZ(int progress){
        valorTramas=progress+2;
        txtTramas.setText(getString(R.string.txtTramas) + " " + valorTramas);
    }

   /* protected void colores(){

        Intent i = new Intent(GameConfig.this, GameField.class);
        String keyIdentifer  = null;
        i.putExtra(rbColores(), keyIdentifer );
    } */

 /*   public void clickBoton(View view){
        Intent i = new Intent(this, GameConfig.java);
        Bundle bundle = new Bundle();

        bundle.putString("Horizontal", txtHor.getText().toString());
        bundle.putString("Vertical", txtVer.getText().toString());
        bundle.putString("Tramas", txtTramas.getText().toString());
        intent.putExtras("", bundle);
        startActivityForResult(intent,i);
        }

*/


    public void Jugar(){

        Intent intent = new Intent (this, GameField.class);

        if(rbColores.isChecked()){
            bundle.putBoolean("Modo",false);
        }
        else{
            bundle.putBoolean("Modo",true);
        }

        if(cbSonido.isChecked()){
            bundle.putBoolean("Sonido", true);
        }
        else{
            bundle.putBoolean("Sonido", false);
        }
        if(cbVibracion.isChecked()){
            bundle.putBoolean("Vibracion", true);
        }
        else{
            bundle.putBoolean("Vibracion", false);
        }
        /*Bundle bundle = new Bundle();
        bundle.putString("Horizontal", tvX.getText().toString());
        bundle.putString("Vertical", tvY.getText().toString());
        bundle.putString("Colores", tvColors.getText().toString());*/
        //MyFragment mFragment = new MyFragment();

        bundle.putInt("Vertical", sbVer);
        intent.putExtra("Vertical", sbVer);
        bundle.putInt("Horizontal", sbHor);
        bundle.putInt("Colores", sbTramas);
        //mFragment.setArguments(bundle);//Bundle bundle = getArguments();
        intent.putExtra("GameField", bundle);
        //Toast.makeText(this, "vertical: " + vertical, Toast.LENGTH_SHORT).show();
        startActivityForResult(intent, ACTION_PLAY);
    }

    @Override
    protected void onActivityResult (int RequestCode, int ResultCode, Intent intent){

        super.onActivityResult(RequestCode, ResultCode, intent);
    }

     public void showPlayer(){
         Intent i = new Intent(getApplicationContext(),jugador.class);
         startActivity(i);
     }

    public void showHowto(){
        Intent i = new Intent(getApplicationContext(),howto.class);
        startActivity(i);
    }

    public void showAbout(){
        Intent i = new Intent(getApplicationContext(),about.class);
        startActivity(i);
    }
        }


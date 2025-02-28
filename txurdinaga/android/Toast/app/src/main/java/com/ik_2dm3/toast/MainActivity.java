package com.ik_2dm3.toast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnPulsa;
    private EditText texto;
    private TextView txtSup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPulsa = (Button) findViewById(R.id.btnPulsa);
        texto = (EditText) findViewById(R.id.texto);
        txtSup = (TextView) findViewById(R.id.txtSup);
    }

    public void botonToast(View view) {

        txtSup.setText(texto.getText());
        Toast.makeText(getApplicationContext(),"Toast dice:" + texto.getText(), Toast.LENGTH_SHORT).show();

    }


}


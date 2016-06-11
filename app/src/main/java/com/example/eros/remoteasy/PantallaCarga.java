package com.example.eros.remoteasy;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Eros on 08/06/2016.
 */
public class PantallaCarga extends Activity {
    EditText ipText, puertoText;
    CheckBox recordar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.pantallacarga);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            ipText =(EditText)findViewById(R.id.editText1);
            puertoText =(EditText)findViewById(R.id.editText2);
            recordar =(CheckBox)findViewById(R.id.checkBox1);
            String[] ip = fileList();
            String[] puerto = fileList();

        }

        private boolean existe(String[] archivos, String archbusca) {
            for (int f = 0; f < archivos.length; f++)
                if (archbusca.equals(archivos[f]))
                    return true;
            return false;
        }

        private void leerArchivo(EditText texto) throws IOException{

            InputStreamReader archivo = new InputStreamReader(openFileInput("ip.txt"));
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            String todo = "";
            while (linea != null) {
                todo = todo + linea + "\n";
                linea = br.readLine();
            }
            br.close();
            archivo.close();
            texto.setText(todo);
        }


        public void conectar(View view) {
            Intent i = new Intent(this, Conectado.class );
            startActivity(i);
        }


    }



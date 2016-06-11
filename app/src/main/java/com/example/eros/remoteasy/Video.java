package com.example.eros.remoteasy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Eros on 08/06/2016.
 */
public class Video extends Activity {

     ImageView imagen;
     TextView txt;
    Button boton;
    boolean error;
    static byte[] imagenActualizar;

    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.video);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            imagen=(ImageView)findViewById(R.id.imageView1);
            txt=(TextView)findViewById(R.id.textView1);
            boton= (Button)findViewById(R.id.button1);
            HiloVideo video = new HiloVideo(imagen, txt);
            imagenActualizar=null;
            hilo();


        }

        final Handler handler = new Handler();

        public void hilo(){
            Thread t = new Thread() {
                public void run() {
                    while(!error) {
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            error=true;
                        }
                        handler.post(ejecutarHilo);
                    }//fin while
                }
            };
            t.start();
        }

        final Runnable ejecutarHilo = new Runnable(){
            public void run(){
                actualizarImagen();
            }
        };

        public void actualizarImagen(){
            txt.setText("Imagen Actualizada de nuevo");
            if(imagenActualizar != null) {
                ByteArrayInputStream bos = new ByteArrayInputStream(imagenActualizar);

                Bitmap loadedImage = BitmapFactory.decodeStream(bos);
                imagen.setImageBitmap(loadedImage);
             /*   ByteArrayInputStream imageStream = new ByteArrayInputStream(imagenActualizar);
                Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                //Bitmap bitmap = BitmapFactory.decodeByteArray(imagenActualizar, 0, imagenActualizar.length);
                 imagen.setImageBitmap(bitmap);*/

            }
        }



    }


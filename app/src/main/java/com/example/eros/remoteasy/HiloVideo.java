package com.example.eros.remoteasy;
//import com.example.remoteasy.Paquete;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Eros on 08/06/2016.
 */
public class HiloVideo extends Thread{

        Socket sokete, soketeVideo;
        boolean error;
        ImageView imagenVideo;
        static TextView txt;
/*
public HiloVideo(Socket a){
 sokete=a;
 error=false;
 //apaño temporal del socket

}
*/
        public HiloVideo(ImageView imagenVideo,  TextView txt){
            this.imagenVideo = imagenVideo;
            this.txt = txt;
           // Video.txt.setText("ACTUALIZADO EL LABEL");
            start();
        }

        public void recibirImagen(){
            try {
                soketeVideo = new Socket("81.9.241.59", 9098);
                InputStream is = soketeVideo.getInputStream();

                Paquete paquete;
                ObjectInputStream ois = new ObjectInputStream(is);
                paquete = (Paquete)ois.readObject();

                if (paquete.getQueHacer()==paquete.VIDEO){
                    actualizarVideo(paquete.getImagen());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
        }


        public void actualizarVideo( byte[] img){

                    //  txt.setText("Imagen Actualizada de nuevo");
                    //  Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
                    // imagenVideo.setImageBitmap(bitmap);
            Video.imagenActualizar = img;
        }

        public void run(){
            try {
                sokete = new Socket("81.9.241.59", 9097);
                Paquete a = new Paquete(Paquete.VIDEO, null);

                ObjectOutputStream oos = new ObjectOutputStream(sokete.getOutputStream());

                oos.writeObject(a);
                oos.flush();

                oos.close();

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            while(true){      //!conexion.isClosed()

                try {

                    sleep(500);
                    recibirImagen();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }

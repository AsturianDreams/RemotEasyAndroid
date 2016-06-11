package com.example.eros.remoteasy;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by Eros on 08/06/2016.
 */
public class Conectado extends Activity {

        public Button video, archivo;
        public TextView defecto, ip;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.conectado);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            defecto=(TextView)findViewById(R.id.textView1);
            ip=(TextView)findViewById(R.id.textView2);
            video=(Button)findViewById(R.id.button1);
            archivo=(Button)findViewById(R.id.button2);


        }

        public void iniciarVideo(View view){

            Intent i = new Intent(this, Video.class );
            startActivity(i);
        }
    }



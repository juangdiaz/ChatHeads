package com.juangdiaz.chathead;

import android.app.Activity;
import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.squareup.seismic.ShakeDetector;


public class MainActivity extends Activity  implements ShakeDetector.Listener{

    Button startService,stopService;
    ShakeDetector sd;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService=(Button)findViewById(R.id.startService);
        stopService=(Button)findViewById(R.id.stopService);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sd = new ShakeDetector(this);


        startService.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sd.start(sensorManager);
                startService(new Intent(getApplication(), ChatHeadService.class));

            }
        });

    }

    @Override
    public void hearShake() {
        sd.stop();

        //this method is the one that stops the service
        stopService(new Intent(getApplication(), ChatHeadService.class));
        //Toast.makeText(this, "Don't shake me, bro!", Toast.LENGTH_SHORT).show();
    }
}

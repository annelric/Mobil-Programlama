package com.example.bluetooth_wifi_camera;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import com.example.bluetooth_wifi_camera.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonB = (Button) findViewById(R.id.buttonB);
        Button buttonW = (Button) findViewById(R.id.buttonW);
        Button buttonC = (Button) findViewById(R.id.buttonC);

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisB = new Intent(MainActivity.this, com.example.bluetooth_wifi_camera.Bluetooth.class);
                startActivity(gecisB);
            }
        });
        buttonW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisW = new Intent(MainActivity.this, com.example.bluetooth_wifi_camera.Wifi.class);
                startActivity(gecisW);
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecisC = new Intent(MainActivity.this, com.example.bluetooth_wifi_camera.Camera.class);
                startActivity(gecisC);
            }
        });
    }
}
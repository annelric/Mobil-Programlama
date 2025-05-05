package com.example.bluetooth_wifi_camera;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.bluetooth_wifi_camera.R;

public class Wifi extends AppCompatActivity {

    private WifiManager modem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        modem = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        final ToggleButton btn = (ToggleButton) findViewById(R.id.toggleButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn.isChecked()) {
                    wifiAc();
                } else {
                    wifiKapat();
                }
            }
        });
    }

    private void wifiKapat() {
        if (modem.isWifiEnabled()) {
            modem.setWifiEnabled(false);
            Toast.makeText( Wifi.this, "Wifi_kapala", Toast.LENGTH_SHORT).show();
        }
    }


    private void wifiAc() {
        if (!modem.isWifiEnabled()) {
            modem.setWifiEnabled(true);
            Toast.makeText(  Wifi.this,  "Wifi_Aqak", Toast.LENGTH_SHORT).show();
        }
    }
}

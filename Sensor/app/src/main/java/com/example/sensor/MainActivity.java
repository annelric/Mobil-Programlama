package com.example.sensor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor selectedSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SensorManager nesnesini başlatıyoruz
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Butonları bulup, her birine tıklama işlemi ekliyoruz
        setupButton(R.id.btnAcele, "Accelerometer");
        setupButton(R.id.btnCompass, "Compass");
        setupButton(R.id.btnGyro, "Gyroscope");
        setupButton(R.id.btnHumid, "Humidity");
        setupButton(R.id.btnLight, "Light");
        setupButton(R.id.btnMagno, "Magnetometer");
        setupButton(R.id.btnPress, "Pressure");
        setupButton(R.id.btnProx, "Proximity");
        setupButton(R.id.btnThermo, "Thermometer");
    }

    private void setupButton(int buttonId, final String sensorName) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            // Burada her bir sensör adıyla yeni bir activity'ye yönlendirme yapıyoruz
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("sensor_name", sensorName);
            startActivity(intent);
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Hangi sensörden veri geldiğini kontrol ediyoruz
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            Log.d("Sensor", "x: " + x + " y: " + y + " z: " + z);
        }

        // Diğer sensörlere ait veriler burada işlenebilir
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Genellikle sensör hassasiyetindeki değişiklikleri burada işleyebilirsiniz
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Aktivite yok olurken sensör dinleyicisini unregister ediyoruz
        sensorManager.unregisterListener(this);
    }
}

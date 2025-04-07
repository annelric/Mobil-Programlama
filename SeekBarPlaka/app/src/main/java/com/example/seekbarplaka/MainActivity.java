package com.example.seekbarplaka;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView txtPlate;
    private EditText editCity;
    private Button btnConfirm, btnStart;
    private long startTime;
    private int tryCount = 0;
    private int correctCount = 0;
    private ArrayList<String> correctCities = new ArrayList<>();

    private static final String[] cities = {
            "adana", "adiyaman", "afyon", "agri", "amasya", "ankara", "antalya", "artvin", "aydin", "balikesir",
            "bilecik", "bingol", "bitlis", "bolu", "burdur", "bursa", "canakkale", "cankiri", "corum", "denizli",
            "diyarbakir", "edirne", "elazig", "erzincan", "erzurum", "eskisehir", "gaziantep", "giresun", "gumushane", "hakkari",
            "hatay", "isparta", "mersin", "istanbul", "izmir", "kars", "kastamonu", "kayseri", "kirklareli", "kirsehir",
            "kocaeli", "konya", "kutahya", "malatya", "manisa", "kahramanmaras", "mardin", "mugla", "mus", "nevsehir",
            "nigde", "ordu", "rize", "sakarya", "samsun", "siirt", "sinop", "sivas", "tekirdag", "tokat",
            "trabzon", "tunceli", "sanliurfa", "usak", "van", "yozgat", "zonguldak", "aksaray", "bayburt", "karaman",
            "kirikkale", "batman", "sirnak", "bartin", "ardahan", "igdir", "yalova", "karabuk", "kilis", "osmaniye", "duzce"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        txtPlate = findViewById(R.id.txtPlate);
        editCity = findViewById(R.id.editCity);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnStart = findViewById(R.id.btnStart);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtPlate.setText("Plaka: " + (progress + 1));
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnStart.setOnClickListener(v -> startTime = System.currentTimeMillis());

        btnConfirm.setOnClickListener(v -> {
            int plate = seekBar.getProgress();
            String guessed = editCity.getText().toString().trim().toLowerCase();
            tryCount++;

            if (guessed.equals(cities[plate])) {
                long duration = System.currentTimeMillis() - startTime;
                String result = cities[plate].toUpperCase() + " - " + tryCount + ". denemede (" + (duration / 1000) + " sn)";
                correctCities.add(result);

                if (correctCities.size() >= 3) {
                    new AlertDialog.Builder(this)
                            .setTitle("Tebrikler")
                            .setMessage("3 şehir doğru bilindi. Oyun tamamlandı.")
                            .setPositiveButton("Tamam", (dialog, which) -> finish())
                            .show();
                } else {
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putStringArrayListExtra("correctCities", correctCities);
                    startActivity(intent);
                }
                tryCount = 0; // reset for next round
                editCity.setText("");
            } else {
                Toast.makeText(this, "Yanlış tahmin!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
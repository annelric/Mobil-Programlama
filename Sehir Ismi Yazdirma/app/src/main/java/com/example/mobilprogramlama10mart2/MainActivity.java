package com.example.mobilprogramlama10mart2;

import android.os.Bundle;
import  android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ListView list1 = findViewById(R.id.listView);
        TextView text1 = findViewById(R.id.textView);

        String[] sehirler = {"İstanbul", "Ankara", "Kocaeli", "Eskişehir"};
        ArrayAdapter<String> verilistesi = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sehirler);

        list1.setAdapter(verilistesi);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, verilistesi.getItem(i), Toast.LENGTH_LONG).show();
                text1.setText(verilistesi.getItem(i));
            }
        });
    }
}
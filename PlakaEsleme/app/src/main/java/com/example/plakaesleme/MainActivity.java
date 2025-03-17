package com.example.plakaesleme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView listPlaka, listSehir;
    Button btnKaristir;
    ArrayList<Integer> plakalar;
    ArrayList<String> sehirler;
    HashMap<String, Integer> plakaMap; // Plaka ve şehir eşleşmelerini tutar
    ArrayAdapter<Integer> plakaAdapter;
    ArrayAdapter<String> sehirAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Arayüz elemanlarını bağla
        listPlaka = findViewById(R.id.listPlaka);
        listSehir = findViewById(R.id.listSehir);
        btnKaristir = findViewById(R.id.btnKaristir);

        // Başlangıçta rastgele listeleri oluştur
        rastgeleListelerOlustur();

        // Butona tıklanınca listeleri karıştır
        btnKaristir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rastgeleListelerOlustur();
            }
        });

        // Şehir listesinde bir elemana tıklanınca
        listSehir.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String secilenSehir = sehirler.get(position);
                int secilenPlaka = plakalar.get(position);

                // Yeni ekrana bilgileri gönder
                Intent intent = new Intent(MainActivity.this, KontrolEkrani.class);
                intent.putExtra("sehir", secilenSehir);
                intent.putExtra("plaka", secilenPlaka);
                startActivity(intent);
            }
        });
    }

    // Rastgele 10 şehir ve plaka listele
    private void rastgeleListelerOlustur() {
        plakalar = new ArrayList<>();
        sehirler = new ArrayList<>();
        plakaMap = new HashMap<>();

        // 81 şehir ve plaka numarası oluştur
        String[] tumSehirler = {
                "Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin", "Aydın", "Balıkesir",
                "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli",
                "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari",
                "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir",
                "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir",
                "Niğde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat",
                "Trabzon", "Tunceli", "Şanlıurfa", "Uşak", "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman",
                "Kırıkkale", "Batman", "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye",
                "Düzce"
        };

        // Şehirler için plaka eşleşmesini oluştur
        for (int i = 0; i < tumSehirler.length; i++) {
            plakaMap.put(tumSehirler[i], i + 1);
        }

        // Şehirleri ve plakaları karıştır
        ArrayList<String> karisikSehirler = new ArrayList<>(plakaMap.keySet());
        Collections.shuffle(karisikSehirler);

        // Listelere rastgele 10 şehir ve plaka ekle
        for (int i = 0; i < 10; i++) {
            sehirler.add(karisikSehirler.get(i));
            plakalar.add(plakaMap.get(karisikSehirler.get(i)));
        }

        // Yanlış eşleşmeler için plakalara da karışıklık ekleyelim (farklı plakalar ekleyebiliriz)
        ArrayList<Integer> karisikPlakalar = new ArrayList<>(plakalar);
        Collections.shuffle(karisikPlakalar);

        // Eğer plakaları karıştırmamız gerekiyorsa, onları karıştıralım
        for (int i = 0; i < plakalar.size(); i++) {
            // Farklı plakalarla eşleşmeleri değiştirelim
            plakalar.set(i, karisikPlakalar.get(i));
        }

        // Listeleri güncelle
        plakaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, plakalar);
        sehirAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sehirler);
        listPlaka.setAdapter(plakaAdapter);
        listSehir.setAdapter(sehirAdapter);
    }

}

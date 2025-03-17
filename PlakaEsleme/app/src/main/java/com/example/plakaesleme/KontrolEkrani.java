package com.example.plakaesleme;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;
import java.util.HashMap;


public class KontrolEkrani extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontrol_ekrani); // Bu layoutu oluşturman gerekebilir.

        // Gelen verileri al
        String sehir = getIntent().getStringExtra("sehir");
        int secilenPlaka = getIntent().getIntExtra("plaka", 0);

        // Şehirler ve plakalar için doğru eşleşmeleri tutan HashMap
        HashMap<String, Integer> plakaMap = new HashMap<>();
        plakaMap.put("Adana", 1);
        plakaMap.put("Adıyaman", 2);
        plakaMap.put("Afyonkarahisar", 3);
        plakaMap.put("Ağrı", 4);
        plakaMap.put("Amasya", 5);
        plakaMap.put("Ankara", 6);
        plakaMap.put("Antalya", 7);
        plakaMap.put("Artvin", 8);
        plakaMap.put("Aydın", 9);
        plakaMap.put("Balıkesir", 10);
        plakaMap.put("Bilecik", 11);
        plakaMap.put("Bingöl", 12);
        plakaMap.put("Bitlis", 13);
        plakaMap.put("Bolu", 14);
        plakaMap.put("Burdur", 15);
        plakaMap.put("Bursa", 16);
        plakaMap.put("Çanakkale", 17);
        plakaMap.put("Çankırı", 18);
        plakaMap.put("Çorum", 19);
        plakaMap.put("Denizli", 20);
        plakaMap.put("Diyarbakır", 21);
        plakaMap.put("Edirne", 22);
        plakaMap.put("Elazığ", 23);
        plakaMap.put("Erzincan", 24);
        plakaMap.put("Erzurum", 25);
        plakaMap.put("Eskişehir", 26);
        plakaMap.put("Gaziantep", 27);
        plakaMap.put("Giresun", 28);
        plakaMap.put("Gümüşhane", 29);
        plakaMap.put("Hakkari", 30);
        plakaMap.put("Hatay", 31);
        plakaMap.put("Iğdır", 32);
        plakaMap.put("Isparta", 33);
        plakaMap.put("İstanbul", 34);
        plakaMap.put("İzmir", 35);
        plakaMap.put("Kahramanmaraş", 46);
        plakaMap.put("Karabük", 78);
        plakaMap.put("Karaman", 70);
        plakaMap.put("Kastamonu", 37);
        plakaMap.put("Kayseri", 38);
        plakaMap.put("Kırıkkale", 71);
        plakaMap.put("Kırklareli", 39);
        plakaMap.put("Kocaeli", 41);
        plakaMap.put("Konya", 42);
        plakaMap.put("Kütahya", 43);
        plakaMap.put("Malatya", 44);
        plakaMap.put("Manisa", 45);
        plakaMap.put("Mardin", 47);
        plakaMap.put("Mersin", 33);
        plakaMap.put("Muğla", 48);
        plakaMap.put("Muş", 49);
        plakaMap.put("Nevşehir", 50);
        plakaMap.put("Niğde", 51);
        plakaMap.put("Ordu", 52);
        plakaMap.put("Osmaniye", 80);
        plakaMap.put("Rize", 53);
        plakaMap.put("Sakarya", 54);
        plakaMap.put("Samsun", 55);
        plakaMap.put("Siirt", 56);
        plakaMap.put("Sinop", 57);
        plakaMap.put("Şanlıurfa", 63);
        plakaMap.put("Şırnak", 73);
        plakaMap.put("Tekirdağ", 59);
        plakaMap.put("Tokat", 60);
        plakaMap.put("Trabzon", 61);
        plakaMap.put("Tunceli", 62);
        plakaMap.put("Uşak", 64);
        plakaMap.put("Van", 65);
        plakaMap.put("Yalova", 77);
        plakaMap.put("Yozgat", 66);
        plakaMap.put("Zonguldak", 67);

        // Seçilen şehire ait doğru plakayı al
        int dogruPlaka = plakaMap.getOrDefault(sehir, -1);  // -1, eğer şehir bulunamazsa döner.

        // Mesajı gösterecek TextView
        TextView textSonuc = findViewById(R.id.textSonuc);

        // Plaka eşleşmesini kontrol et
        if (dogruPlaka == secilenPlaka) {
            // Eşleşme doğruysa
            textSonuc.setText("Plaka eşleşmesi doğru, " + sehir + " " + secilenPlaka + " plakaya sahiptir.");
        } else {
            // Eşleşme yanlışsa
            textSonuc.setText("Plaka eşleşmesi yanlış, " + sehir + " " + dogruPlaka + " plakaya sahiptir.");
        }
    }
}
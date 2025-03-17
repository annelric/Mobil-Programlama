package com.example.hesapmakinesi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView editText;
    String currentInput = "";
    String operator = "";
    double firstOperand = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });*/
        editText = findViewById(R.id.sonucText);

        Button sayi0 = findViewById(R.id.sayi0);
        Button sayi1 = findViewById(R.id.sayi1);
        Button sayi2 = findViewById(R.id.sayi2);
        Button sayi3 = findViewById(R.id.sayi3);
        Button sayi4 = findViewById(R.id.sayi4);
        Button sayi5 = findViewById(R.id.sayi5);
        Button sayi6 = findViewById(R.id.sayi6);
        Button sayi7 = findViewById(R.id.sayi7);
        Button sayi8 = findViewById(R.id.sayi8);
        Button sayi9 = findViewById(R.id.sayi9);

        Button butonArti = findViewById(R.id.butonArti);
        Button butonEksi = findViewById(R.id.butonEksi);
        Button butonCarpi = findViewById(R.id.butonCarpma);
        Button butonBolme = findViewById(R.id.butonBolme);

        Button butonEsittir = findViewById(R.id.butonEsittir);
        Button butonTemizleme = findViewById(R.id.butonTemizleme);
        Button butonUssu = findViewById(R.id.butonUssu);
        Button butonKarekok = findViewById(R.id.butonKarekok);
        Button butonFakt = findViewById(R.id.butonFaktoriyel);

        View.OnClickListener numberClickListener = v -> {
            Button buton = (Button) v;
            currentInput += buton.getText().toString();
            editText.setText(currentInput);
        };

        sayi0.setOnClickListener(numberClickListener);
        sayi1.setOnClickListener(numberClickListener);
        sayi2.setOnClickListener(numberClickListener);
        sayi3.setOnClickListener(numberClickListener);
        sayi4.setOnClickListener(numberClickListener);
        sayi5.setOnClickListener(numberClickListener);
        sayi6.setOnClickListener(numberClickListener);
        sayi7.setOnClickListener(numberClickListener);
        sayi8.setOnClickListener(numberClickListener);
        sayi9.setOnClickListener(numberClickListener);

        View.OnClickListener operatorClickListener = v -> {
            Button button = (Button) v;
            operator = button.getText().toString();
            firstOperand = Double.parseDouble((currentInput));
            currentInput = "";
        };

        butonArti.setOnClickListener(operatorClickListener);
        butonEksi.setOnClickListener(operatorClickListener);
        butonCarpi.setOnClickListener(operatorClickListener);
        butonBolme.setOnClickListener(operatorClickListener);

        butonTemizleme.setOnClickListener(v -> {
            currentInput="";
            firstOperand = 0;
            operator = "";
            editText.setText("0");
        });

        butonEsittir.setOnClickListener(v -> {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case"/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        result = Double.NaN; //error versin yani
                    }
                    break;
            }

            currentInput = String.valueOf(result);
            editText.setText(currentInput);
        });

        butonUssu.setOnClickListener(v -> {
            firstOperand = Double.parseDouble(currentInput);
            operator = "x^y";
            currentInput = "";
        });

        butonKarekok.setOnClickListener(v -> {
            double number = Double.parseDouble(currentInput);
            currentInput = String.valueOf(Math.sqrt(number)); // Calculate square root
            editText.setText(currentInput);
        });

        butonFakt.setOnClickListener(v -> {
            int number = Integer.parseInt(currentInput);
            currentInput = String.valueOf(factorial(number)); // Calculate factorial
            editText.setText(currentInput);
        });

    }

    private int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}

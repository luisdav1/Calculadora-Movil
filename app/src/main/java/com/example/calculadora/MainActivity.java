package com.example.calculadora;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btn_sumar, btn_navegar;
    EditText edt_num_1, edt_num_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_sumar = findViewById(R.id.btn_sumar);
        btn_navegar = findViewById(R.id.btnNavegar);
        edt_num_1 = findViewById(R.id.edt_num_1);
        edt_num_2 = findViewById(R.id.edt_num_2);

        btn_sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1 = edt_num_1.getText().toString();
                String text2 = edt_num_2.getText().toString();

                if (text1.equals("") || text2.equals("")) {
                    mostrarResultado("Error", "Los datos están vacíos!");
                } else {
                    int num1 = Integer.parseInt(text1);
                    int num2 = Integer.parseInt(text2);
                    int suma = num1 + num2;
                    mostrarResultado("Resultado de la Suma", "El resultado es: " + suma);
                }
            }
        });

        btn_navegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    private void mostrarResultado(String titulo, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
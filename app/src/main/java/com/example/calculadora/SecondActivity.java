package com.example.calculadora;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText input3, input4;
    private Spinner spinnerOperaciones;
    private Button btnCalcular;
    private String operacionSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        input3 = findViewById(R.id.input3);
        input4 = findViewById(R.id.input4);
        spinnerOperaciones = findViewById(R.id.spinnerOperaciones);
        btnCalcular = findViewById(R.id.btnCalcular);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operaciones, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOperaciones.setAdapter(adapter);

        spinnerOperaciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                operacionSeleccionada = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                operacionSeleccionada = "Suma";
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = input3.getText().toString();
                String text2 = input4.getText().toString();
                if (text1.equals("") || text2.equals("")) {
                    mostrarResultado("Error", "Los datos están vacíos!");
                } else {
                    double num1 = Double.parseDouble(text1);
                    double num2 = Double.parseDouble(text2);
                    double resultado = 0;
                    switch (operacionSeleccionada) {
                        case "Suma":
                            resultado = num1 + num2;
                            break;
                        case "Resta":
                            resultado = num1 - num2;
                            break;
                        case "Multiplicación":
                            resultado = num1 * num2;
                            break;
                        case "División":
                            if (num2 != 0) {
                                resultado = num1 / num2;
                            } else {
                                mostrarResultado("Error", "División por cero no permitida");
                                return;
                            }
                            break;
                    }

                    mostrarResultado("Resultado de la Operación", "El resultado es: " + resultado);
                }
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
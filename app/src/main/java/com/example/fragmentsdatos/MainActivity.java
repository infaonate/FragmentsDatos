package com.example.fragmentsdatos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements TextCommunicator {
    private TextView tvHistorial;
    private StringBuilder historialBuilder;
    private String ultimoTextoEnviado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHistorial = findViewById(R.id.tvHistorial);
        Button btnFragment1 = findViewById(R.id.btnFragment1);
        Button btnFragment2 = findViewById(R.id.btnFragment2);
        historialBuilder = new StringBuilder();

        // Show Fragment One by default
        mostrarFragment(new Fragment1());

        btnFragment1.setOnClickListener(v -> mostrarFragment(new Fragment1()));
        btnFragment2.setOnClickListener(v -> mostrarFragment(new Fragment2()));
    }

    private void mostrarFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();

        // If there's a pending text to show, display it in the new fragment
        if (ultimoTextoEnviado != null) {
            if (fragment instanceof Fragment1) {
                ((Fragment1) fragment).receiveText(ultimoTextoEnviado);
            } else if (fragment instanceof Fragment2) {
                ((Fragment2) fragment).receiveText(ultimoTextoEnviado);
            }
            ultimoTextoEnviado = null;
        }
    }

    @Override
    public void enviarTexto(String text, boolean desdeFragment1) {
        // Actualizar historial
        String historialActual = desdeFragment1 ? "Fragment 1: " : "Fragment 2: ";
        historialBuilder.append(historialActual).append(text).append("\n");
        tvHistorial.setText(historialBuilder.toString());

        // Guardamos el texto que tenemos que enviar
        ultimoTextoEnviado = text;

        // Cambiar al otro fragment
        if (desdeFragment1) {
            mostrarFragment(new Fragment2());
        } else {
            mostrarFragment(new Fragment1());
        }
    }
}
package com.example.fragmentsdatos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    private TextView tvTextoRecibido;
    private EditText etInput;
    private TextCommunicator communicator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        communicator = (TextCommunicator) requireActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        tvTextoRecibido = view.findViewById(R.id.tvTextoRecibido1);
        etInput = view.findViewById(R.id.etInput1);
        Button btnEnviar = view.findViewById(R.id.btnEnviar1);

        btnEnviar.setOnClickListener(v -> {
            String text = etInput.getText().toString();
            if (!text.isEmpty()) {
                communicator.enviarTexto(text, true);
                etInput.setText("");
            }
        });

        return view;
    }

    public void receiveText(String text) {
        if (tvTextoRecibido != null) {
            tvTextoRecibido.setText(text);
        }
    }
}
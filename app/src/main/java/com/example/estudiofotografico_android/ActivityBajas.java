package com.example.estudiofotografico_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class ActivityBajas extends AppCompatActivity {

    EditText et_id, et_nombre, et_apellido, et_fecha, et_telefono, et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);

        et_id = findViewById(R.id.caja_id_bajas);
        et_nombre = findViewById(R.id.caja_nombre_bajas);
        et_apellido = findViewById(R.id.caja_apellido_bajas);
        et_fecha = findViewById(R.id.caja_fecha_bajas);
        et_telefono = findViewById(R.id.caja_telefono_bajas);
        et_email = findViewById(R.id.caja_email_bajas);

    }


}
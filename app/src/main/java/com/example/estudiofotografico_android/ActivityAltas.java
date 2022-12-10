package com.example.estudiofotografico_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivityAltas extends AppCompatActivity {
    EditText et_nombre, et_apellido, et_fecha, et_telefono, et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);

        et_nombre = findViewById(R.id.caja_nombre_altas);
        et_apellido = findViewById(R.id.caja_apellido_altas);
        et_fecha = findViewById(R.id.caja_fecha_altas);
        et_telefono = findViewById(R.id.caja_telefono_altas);
        et_email = findViewById(R.id.caja_email_altas);


    }

    public void agregarFotografo(View v){
        if(comprobarCampos()) {
            String nombre = et_nombre.getText().toString();
            String apellido = et_apellido.getText().toString();
            String fecha = et_fecha.getText().toString();
            String telefono = et_telefono.getText().toString();
            String email = et_email.getText().toString();
        }

    }

    public boolean comprobarCampos(){
        if(et_nombre.getText().toString().isEmpty()){
            et_nombre.setError("Falta el nombre");
            return false;
        }
        if(et_apellido.getText().toString().isEmpty()){
            et_apellido.setError("Falta el apellido");
            return false;
        }
        if(et_fecha.getText().toString().isEmpty()){
            et_fecha.setError("Falta la fecha");
            return false;
        }
        if(et_telefono.getText().toString().isEmpty()){
            et_telefono.setError("Falta el telefono");
            return false;
        }
        if(et_email.getText().toString().isEmpty()){
            et_email.setError("Falta el email");
            return false;
        }
        return true;
    }

}
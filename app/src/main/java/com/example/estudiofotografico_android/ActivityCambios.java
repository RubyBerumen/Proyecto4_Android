package com.example.estudiofotografico_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class ActivityCambios extends AppCompatActivity {

    EditText et_id, et_nombre, et_apellido, et_fecha, et_telefono, et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);

        et_id = findViewById(R.id.caja_id_cambios);
        et_nombre = findViewById(R.id.caja_nombre_cambios);
        et_apellido = findViewById(R.id.caja_apellido_cambios);
        et_fecha = findViewById(R.id.caja_fecha_cambios);
        et_telefono = findViewById(R.id.caja_telefono_cambios);
        et_email = findViewById(R.id.caja_email_cambios);

    }

    public boolean comprobarCampos(){
        if(et_id.getText().toString().isEmpty()){
            et_id.setError("Falta el id");
            return false;
        }
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
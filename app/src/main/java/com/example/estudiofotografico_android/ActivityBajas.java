package com.example.estudiofotografico_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ActivityBajas extends AppCompatActivity {

    EditText et_id, et_nombre, et_apellido, et_fecha, et_telefono, et_email;

    RequestQueue requestQueue;  //cola de peticiones

    private static final String URL = "http://proyecto4.proyectosprogramacionweb.com/api_rest/api_rest_MySQL/api_bajas.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        et_id = findViewById(R.id.caja_id_bajas);
        et_nombre = findViewById(R.id.caja_nombre_bajas);
        et_apellido = findViewById(R.id.caja_apellido_bajas);
        et_fecha = findViewById(R.id.caja_fecha_bajas);
        et_telefono = findViewById(R.id.caja_telefono_bajas);
        et_email = findViewById(R.id.caja_email_bajas);

    }

    public void borrarCampos(View view){
        et_id.setText("");
        et_nombre.setText("");
        et_apellido.setText("");
        et_fecha.setText("");
        et_telefono.setText("");
        et_email.setText("");
    }

    public void eliminarPaciente(View view) {

        if (et_id.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "El campo ID no puede estar vacío", Toast.LENGTH_SHORT).show();
        } else {
            String url = URL + "?id=" + et_id.getText().toString();
            StringRequest request = new StringRequest(Request.Method.DELETE, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.getBoolean("exito")) {
                                    et_id.setText("");
                                    Toast.makeText(getApplicationContext(), jsonObject.getString("msj"), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), jsonObject.getString("msj"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error instanceof NoConnectionError) {
                                Toast.makeText(getApplicationContext(), "No hay conexión a Internet", Toast.LENGTH_SHORT).show();
                            } else if (error instanceof NetworkError) {
                                Toast.makeText(getApplicationContext(), "Error de red", Toast.LENGTH_SHORT).show();
                            } else if (error instanceof ServerError) {
                                Toast.makeText(getApplicationContext(), "Error en el servidor", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );
            requestQueue.add(request);
        }
    }


}
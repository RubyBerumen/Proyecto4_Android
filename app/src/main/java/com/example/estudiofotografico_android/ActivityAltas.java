package com.example.estudiofotografico_android;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Entidades.Fotografo;

public class ActivityAltas extends AppCompatActivity {
    EditText et_nombre, et_apellido, et_fecha, et_telefono, et_email;
    Button btn_agregar, btn_borrar;

    RequestQueue requestQueue;  //cola de peticiones

    private static final String URL = "http://proyecto4.proyectosprogramacionweb.com/api_rest/api_rest_MySQL/api_android.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altas);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        initUI();

    }

    public void initUI(){
        et_nombre = findViewById(R.id.caja_nombre_altas);
        et_apellido = findViewById(R.id.caja_apellido_altas);
        et_fecha = findViewById(R.id.caja_fecha_altas);
        et_telefono = findViewById(R.id.caja_telefono_altas);
        et_email = findViewById(R.id.caja_email_altas);

        btn_agregar = findViewById(R.id.btn_agregar);
        btn_borrar = findViewById(R.id.btn_borrar);

    }

    public void borrarCampos(View view){
        et_nombre.setText("");
        et_apellido.setText("");
        et_fecha.setText("");
        et_telefono.setText("");
        et_email.setText("");
    }

    public void agregarFotografo(View view){
        // caso de exito {"exito":true,"mensaje":"insercion correcta"}

        if( comprobarCampos()){
            //String url = URL + "?nombre=" + et_nombre.getText().toString() + "&apellido=" + et_apellido.getText().toString() + "&fechaNacimiento=" + et_fecha.getText().toString() + "&telefono=" + et_telefono.getText().toString() + "&email=" + et_email.getText().toString();

            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if(jsonObject.getBoolean("exito")){
                            Toast.makeText(ActivityAltas.this, jsonObject.getString("mensaje"), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ActivityAltas.this, jsonObject.getString("mensaje"), Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error instanceof NoConnectionError) {
                        Toast.makeText(ActivityAltas.this, "No hay conexión a Internet", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof NetworkError) {
                        Toast.makeText(ActivityAltas.this, "Error de red", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ServerError) {
                        Toast.makeText(ActivityAltas.this, "Error en el servidor", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ActivityAltas.this, "Error al conectar con el servidor", Toast.LENGTH_SHORT).show();
                    }
                }
            }) {
                @Override
                protected java.util.Map<String, String> getParams() {
                    java.util.Map<String, String> parametros = new java.util.HashMap<String, String>();
                    parametros.put("nombre", et_nombre.getText().toString());
                    parametros.put("apellido", et_apellido.getText().toString());
                    parametros.put("fechaNacimiento", et_fecha.getText().toString());
                    parametros.put("telefono", et_telefono.getText().toString());
                    parametros.put("email", et_email.getText().toString());
                    return parametros;
                }
            };

            requestQueue.add(request);
        } else {
            Toast.makeText(ActivityAltas.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
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
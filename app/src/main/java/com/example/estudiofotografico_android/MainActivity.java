package com.example.estudiofotografico_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class MainActivity extends AppCompatActivity {

    EditText et_correo, et_passwd;

    RequestQueue requestQueue;
    private static final String URL = "http://proyecto4.proyectosprogramacionweb.com/api_rest/api_rest_MySQL/api_login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        initUI();
    }

    public void initUI(){
        et_correo = findViewById(R.id.txt_usuario);
        et_passwd = findViewById(R.id.txt_contraseña);
    }

    public boolean comprobarCampos(){
        boolean camposCorrectos = true;
        if(et_correo.getText().toString().isEmpty()){
            et_correo.setError("El campo no puede estar vacío");
            camposCorrectos = false;
        }
        if(et_passwd.getText().toString().isEmpty()){
            et_passwd.setError("El campo no puede estar vacío");
            camposCorrectos = false;
        }else if(et_passwd.getText().length()<8){
            et_passwd.setError("Debes ingresar minimo 8 caracteres");
            camposCorrectos = false;
        }
        return camposCorrectos;
    }

    public void abrirMenu(View v){
        if( comprobarCampos()){
            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if(jsonObject.getBoolean("exito")){
                            Toast.makeText(getApplicationContext(), "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ActivityMenu.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getApplicationContext(), "Error al iniciar sesión, usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error instanceof NoConnectionError) {
                        Toast.makeText(getApplicationContext(), "No hay conexión a Internet", Toast.LENGTH_SHORT).show();
                    }
                    if (error instanceof NetworkError) {
                        Toast.makeText(getApplicationContext(), "Error de red", Toast.LENGTH_SHORT).show();
                    }
                    if (error instanceof ServerError) {
                        Toast.makeText(getApplicationContext(), "Error en el servidor", Toast.LENGTH_SHORT).show();
                    }
                }
            }) {
                @Override
                protected java.util.Map<String, String> getParams() {
                    java.util.Map<String, String> parametros = new java.util.HashMap<String, String>();
                    parametros.put("correo", et_correo.getText().toString());
                    parametros.put("passwd", et_passwd.getText().toString());
                    return parametros;
                }
            };
            requestQueue.add(request);
        } else {
            Toast.makeText(getApplicationContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void abrirRegistro(View v){
        Intent i = new Intent(this, ActivityRegistrarse.class);
        startActivity(i);
    }


}
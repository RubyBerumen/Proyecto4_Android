package com.example.estudiofotografico_android;

import androidx.appcompat.app.AppCompatActivity;

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

public class ActivityRegistrarse extends AppCompatActivity {

    EditText et_correo, et_passwd, et_passwd2, et_nombre, et_apellido;

    RequestQueue requestQueue;
    private static final String URL = "http://proyecto4.proyectosprogramacionweb.com/api_rest/api_rest_MySQL/api_registro.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        initUI();
    }

    public void initUI(){
        et_correo = findViewById(R.id.cajaUsuario);
        et_passwd = findViewById(R.id.cajaContraseña1);
        et_passwd2 = findViewById(R.id.cajaContraseña2);
        et_nombre = findViewById(R.id.cajaNombre);
        et_apellido = findViewById(R.id.cajaApellido);
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
        if(et_passwd2.getText().toString().isEmpty()){
            et_passwd2.setError("El campo no puede estar vacío");
            camposCorrectos = false;
        }else if(et_passwd2.getText().length()<8){
            et_passwd2.setError("Debes ingresar minimo 8 caracteres");
            camposCorrectos = false;
        }
        if(et_nombre.getText().toString().isEmpty()){
            et_nombre.setError("El campo no puede estar vacío");
            camposCorrectos = false;
        }
        if(et_apellido.getText().toString().isEmpty()){
            et_apellido.setError("El campo no puede estar vacío");
            camposCorrectos = false;
        }
        return camposCorrectos;
    }

    public boolean comprobarContraseñas(){
        boolean contraseñasCorrectas = true;
        if(!et_passwd.getText().toString().equals(et_passwd2.getText().toString())){
            et_passwd2.setError("Las contraseñas no coinciden");
            contraseñasCorrectas = false;
        }
        return contraseñasCorrectas;
    }

    public void limpiarCampos(){
        et_correo.setText("");
        et_passwd.setText("");
        et_passwd2.setText("");
        et_nombre.setText("");
        et_apellido.setText("");
    }

    public void registrarse(View view){
        if( comprobarCampos() && comprobarContraseñas() ){
            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if(jsonObject.getBoolean("exito")){
                            Toast.makeText(getApplicationContext(), "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                            limpiarCampos();
                        }else{
                            Toast.makeText(getApplicationContext(), "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
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
                    parametros.put("nombre", et_nombre.getText().toString());
                    parametros.put("apellido", et_apellido.getText().toString());
                    return parametros;
                }
            };
            requestQueue.add(request);
        } else {
            Toast.makeText(getApplicationContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
        }

    }




}
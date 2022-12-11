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

import java.util.HashMap;
import java.util.Map;

public class ActivityCambios extends AppCompatActivity {

    EditText et_id, et_nombre, et_apellido, et_fecha, et_telefono, et_email;
    Button btn_modificar, btn_borrar, btn_buscar;

    RequestQueue requestQueue;  //cola de peticiones

    private static final String URL = "http://proyecto4.proyectosprogramacionweb.com/api_rest/api_rest_MySQL/api_android.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

    }

    public void initUI(){
        et_id = findViewById(R.id.caja_id_cambios);
        et_nombre = findViewById(R.id.caja_nombre_cambios);
        et_apellido = findViewById(R.id.caja_apellido_cambios);
        et_fecha = findViewById(R.id.caja_fecha_cambios);
        et_telefono = findViewById(R.id.caja_telefono_cambios);
        et_email = findViewById(R.id.caja_email_cambios);

        btn_modificar = findViewById(R.id.btn_modificar);
        btn_borrar = findViewById(R.id.btn_borrar2);
        btn_buscar = findViewById(R.id.btn_buscar2);

    }

    public void modificarPaciente(View v){
        if(!comprobarCampos()){
            Toast toast = Toast.makeText(getApplicationContext(),"Hay campos vacios", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            StringRequest request = new StringRequest(Request.Method.PUT, URL,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if(jsonObject.getBoolean("exito")){
                                    Toast.makeText(getApplicationContext(), jsonObject.getString("mensaje"), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), jsonObject.getString("mensaje"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
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
            ) {

                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String> parametros = new HashMap<String, String>();
                    parametros.put("id", et_id.getText().toString());
                    parametros.put("nombre", et_nombre.getText().toString());
                    parametros.put("apellido", et_apellido.getText().toString());
                    parametros.put("fechaNacimiento", et_fecha.getText().toString());
                    parametros.put("telefono", et_telefono.getText().toString());
                    parametros.put("email", et_email.getText().toString());
                    return parametros;
                }

            };

            requestQueue.add(request);
        }

    }

    public void limpiarCajas(){
        et_id.setText("");
        et_nombre.setText("");
        et_apellido.setText("");
        et_fecha.setText("");
        et_telefono.setText("");
        et_email.setText("");
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
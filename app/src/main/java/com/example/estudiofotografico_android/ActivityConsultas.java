package com.example.estudiofotografico_android;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Entidades.Fotografo;

public class ActivityConsultas extends AppCompatActivity {

    List<Fotografo> lista;  //elementos de la lista

    EditText cajaBusqueda; //elementos visuales
    RecyclerView recycler;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    RequestQueue requestQueue;  //cola de peticiones
    //localhost
    private String URL = "http://proyecto4.proyectosprogramacionweb.com/api_rest/api_rest_MySQL/api_consultas.php?cajaBusqueda=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        lista = new ArrayList<>(); //pruebas estaticas
        lista.add(new Fotografo(1, "Juan", "Perez", "15-10-2001", "4941189287","bryan.valdez117@outlook.es"));
        lista.add(new Fotografo(2, "Pedro", "Gomez", "15-10-2002", "123123123","bryan.valdez1177@outlook.es"));
        lista.add(new Fotografo(3, "Maria", "Lopez", "15-10-2003", "123123222","bryan.valdez77@outlook.es"));

        initUI();
        URL = URL + cajaBusqueda.getText().toString();
        initRecycler();

        cajaBusqueda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                consultar();
            }
        });

        consultar();

    }

    private void initUI() {
        cajaBusqueda = findViewById(R.id.caja_busqueda);
    }

    private void initRecycler(){
        recycler = findViewById(R.id.recyclerView_consultas);
        recycler.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        adapter = new AdaptadorRegistros(lista, this);
        recycler.setAdapter(adapter);
    }

    public void consultar() {
        String url = URL + cajaBusqueda.getText().toString();

        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    lista.clear();

                    JSONObject json =new JSONObject(response);
                    JSONArray array = new JSONArray(json.getString("datos"));
                    for (int i=0;i<array.length();i++){
                        JSONObject json_data = array.getJSONObject(i);

                        int id=json_data.getInt("id_fotografo");
                        String nombre=json_data.getString("nombre");
                        String apellido=json_data.getString("apellido");
                        String fechaNacimiento=json_data.getString("fecha_nacimiento");
                        String telefono=json_data.getString("telefono");
                        String correo=json_data.getString("email");

                        Fotografo fotografo = new Fotografo(id, nombre, apellido, fechaNacimiento, telefono, correo);
                        lista.add(fotografo);
                    }

                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG",error.getMessage());
            }
        });
        requestQueue.add(request);
    }

}



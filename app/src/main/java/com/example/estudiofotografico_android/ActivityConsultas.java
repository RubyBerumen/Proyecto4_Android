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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Entidades.Fotografo;

public class ActivityConsultas extends AppCompatActivity {

    List<Fotografo> lista;  //elementos de la lista

    EditText cajaBusqueda; //elementos visuales
    RecyclerView recycler;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    RequestQueue requestQueue;  //cola de peticiones
    //localhost
    private static final String URL = "http://localhost/api_rest/api_rest_MySQL/api_android.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        lista = new ArrayList<>(); //pruebas estaticas
        lista.add(new Fotografo(1, "Juan", "Perez", "15-10-2001", "4941189287","bryan.valdez117@outlook.es"));
        lista.add(new Fotografo(2, "Pedro", "Gomez", "15-10-2002", "123123123","bryan.valdez1177@outlook.es"));
        lista.add(new Fotografo(3, "Maria", "Lopez", "15-10-2003", "123123222","bryan.valdez77@outlook.es"));

        //actualizamos el recycler
        //adapter.notifyDataSetChanged();

        initUI();
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
        String url = URL + "?cajaBusqueda=" +cajaBusqueda.getText().toString();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    lista.clear();
                    JSONArray fotografos = response.getJSONArray("datos");
                    for (int i = 0; i < fotografos.length(); i++) {
                        JSONObject jsonObject = new JSONObject(fotografos.getJSONObject(i).toString());
                        lista.add(new Fotografo(
                                jsonObject.getInt("id_fotografo"),
                                jsonObject.getString("nombre"),
                                jsonObject.getString("apellido"),
                                jsonObject.getString("fecha_nacimiento"),
                                jsonObject.getString("telefono"),
                                jsonObject.getString("correo")
                        ));
                    }
                    Toast.makeText(getApplicationContext(), "Se encontraron " + lista.size() + " registros", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Error al consultar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(), "NetworkError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplicationContext(), "ServerError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), "NoConnectionError", Toast.LENGTH_SHORT).show();
                }
            }
        });
        requestQueue.add(request);
    }

    public void consultar(View view) {
        String url = URL + "?cajaBusqueda=" +cajaBusqueda.getText().toString();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    lista.clear();
                    JSONArray fotografos = response.getJSONArray("datos");
                    for (int i = 0; i < fotografos.length(); i++) {
                        JSONObject jsonObject = new JSONObject(fotografos.getJSONObject(i).toString());
                        lista.add(new Fotografo(
                                jsonObject.getInt("id_fotografo"),
                                jsonObject.getString("nombre"),
                                jsonObject.getString("apellido"),
                                jsonObject.getString("fecha_nacimiento"),
                                jsonObject.getString("telefono"),
                                jsonObject.getString("correo")
                        ));
                    }
                    Toast.makeText(getApplicationContext(), "Se encontraron " + lista.size() + " registros", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Error al consultar", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(), "NetworkError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplicationContext(), "ServerError", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), "NoConnectionError", Toast.LENGTH_SHORT).show();
                }
            }
        });
        requestQueue.add(request);
    }

}



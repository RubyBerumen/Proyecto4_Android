package com.example.estudiofotografico_android;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Entidades.Fotografo;

public class ActivityConsultas extends AppCompatActivity {

    List<Fotografo> lista;  //elementos de la lista

    EditText id, nombre, apellido; //elementos visuales
    RecyclerView recycler;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    RequestQueue requestQueue;  //cola de peticiones
    private static final String URL1 = "to-do";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        requestQueue = Volley.newRequestQueue(getApplicationContext());


        lista = new ArrayList<>();
        lista.add(new Fotografo(1, "Juan", "Perez", "15-10-2001", "4941189287","bryan.valdez117@outlook.es"));
        lista.add(new Fotografo(2, "Pedro", "Gomez", "15-10-2002", "123123123","bryan.valdez1177@outlook.es"));
        lista.add(new Fotografo(3, "Maria", "Lopez", "15-10-2003", "123123222","bryan.valdez77@outlook.es"));

        //actualizamos el recycler
        //adapter.notifyDataSetChanged();

        initUI();
        initRecycler();

    }


    private void initUI() {
        id = findViewById(R.id.caja_id_consultas);
        nombre = findViewById(R.id.caja_nombre_consultas);
        apellido = findViewById(R.id.caja_apellido_consultas);
        recycler = findViewById(R.id.recyclerView_consultas);
    }

//    public void consultar(View view) {
//        String url = URL1 + id.getText().toString();
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    nombre.setText(response.getString("nombre"));
//                    apellido.setText(response.getString("apellido"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                if (error instanceof NetworkError) {
//                    Log.d("Error", "NetworkError");
//                } else if (error instanceof ServerError) {
//                    Log.d("Error", "ServerError");
//                } else if (error instanceof NoConnectionError) {
//                    Log.d("Error", "NoConnectionError");
//                }
//            }
//        });
//        requestQueue.add(jsonObjectRequest);
//    }

    private void initRecycler(){
        recycler = findViewById(R.id.recyclerView_consultas);
        recycler.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        adapter = new AdaptadorRegistros(lista, this);
        recycler.setAdapter(adapter);
    }


}



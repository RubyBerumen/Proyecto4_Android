package com.example.estudiofotografico_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Entidades.Fotografo;

class AdaptadorRegistros extends RecyclerView.Adapter<AdaptadorRegistros.ViewHolder>{

    //private String[] mDataset;

    private List<Fotografo> mData;
    private LayoutInflater mInflater;
    private Context context;

    public AdaptadorRegistros(List<Fotografo> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public AdaptadorRegistros.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_element,null);
        return new AdaptadorRegistros.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdaptadorRegistros.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Fotografo> items){
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImage;
        TextView id, nombre, apellido, fecha, telefono, email;

        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.iconImageView);
            id = itemView.findViewById(R.id.fotografoId);
            nombre = itemView.findViewById(R.id.fotografoNombre);
            apellido = itemView.findViewById(R.id.fotografoApellido);
            fecha = itemView.findViewById(R.id.fotografoFecha);
            telefono = itemView.findViewById(R.id.fotografoTelefono);
            email = itemView.findViewById(R.id.fotografoEmail);
        }

        void bindData(final Fotografo fotografo) {
            id.setText("id: "+fotografo.getId());
            nombre.setText("Nombre: "+fotografo.getNombre());
            apellido.setText("Apellido: "+fotografo.getApellido());
            fecha.setText("Fecha: "+fotografo.getFecha());
            telefono.setText("Telefono: "+fotografo.getTelefono());
            email.setText("Email: "+fotografo.getEmail());
        }

    }

}
package com.example.pokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Pokeadapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Pokemon> list;

    public Pokeadapter(Context context, int layout, List<Pokemon> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Pokemon getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;

        if (convertView == null) {
            // Sólo si está nulo, es decir, primera vez en ser renderizado, inflamos
            // y adjuntamos las referencias del layout en una nueva instancia de nuestro
            // ViewHolder, y lo insertamos dentro del convertView, para reciclar su uso
            convertView = LayoutInflater.from(context).inflate(layout, null);
            holder = new ViewHolder();
            holder.TVnombre = (TextView) convertView.findViewById(R.id.textViewNombre);
            holder.TVtipo = (TextView) convertView.findViewById(R.id.textViewTipo);
            holder.IVimagen = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            // Obtenemos la referencia que posteriormente pusimos dentro del convertView
            // Y así, reciclamos su uso sin necesidad de buscar de nuevo, referencias con FindViewById
            holder = (ViewHolder) convertView.getTag();
        }

        final Pokemon currentPokemon = list.get(position);
        holder.TVnombre.setText(currentPokemon.getNombre());
        holder.TVtipo.setText(currentPokemon.getTipo());
        holder.IVimagen.setImageResource(currentPokemon.getImagen());
        return convertView;
    }

    static class ViewHolder {
        private TextView TVnombre;
        private TextView TVtipo;
        private ImageView IVimagen;
    }
}

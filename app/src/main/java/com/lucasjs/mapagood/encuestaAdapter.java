package com.lucasjs.mapagood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class encuestaAdapter extends RecyclerView.Adapter<encuestaAdapter.ViewHolder> {
    private List<encuesta> encuestass;

    public encuestaAdapter(List<encuesta> encuestas) {
        this.encuestass = encuestas;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        encuesta encuestas = encuestass.get(position);
        String datosc = "Nick Usuario: " + encuestas.getNombreUsuario() + "\n" +
                "Nombre: " + encuestas.getNombreo() + "\n" +
                "Correo: " + encuestas.getCorreoo() + "\n" +
                "Telefono: " + encuestas.getTelefonoo() + "\n" +
                "Satisfaccion: : " + encuestas.getSatisfecho() + "\n" +
                "Recomendacion: : " + encuestas.getRecomendacion() + "\n" +
                "Comentarios : " + encuestas.getComentarios() + "\n";
        holder.datos.setText(datosc);

    }

    @Override
    public int getItemCount() {
        return encuestass.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView datos;
        //public TextView rut;
        //public TextView nombre;
        //public TextView edad;
        //public TextView email;
        //public TextView telefono;


        public ViewHolder(View itemView) {
            super(itemView);
            datos = (TextView) itemView.findViewById(android.R.id.text1);
            //nombre = (TextView) itemView.findViewById(android.R.id.text2);
            //edad = (TextView) itemView.findViewById(android.R.id.text2);
            //email = (TextView) itemView.findViewById(android.R.id.text2);
            //telefono = (TextView) itemView.findViewById(android.R.id.text2);
        }
    }
}

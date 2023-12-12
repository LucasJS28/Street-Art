package com.lucasjs.mapagood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class usuariosAdapter extends RecyclerView.Adapter<usuariosAdapter.ViewHolder> {
    private List<usuarios> usuarios;

    public usuariosAdapter(List<usuarios> usuarios) {
        this.usuarios = usuarios;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        usuarios perso = usuarios.get(position);
        String datosc = "Nombre: " + perso.getNombre() + "\n" +
                "Contraseña: " + perso.getContraseña() + "\n" +
                "TipoUsuario: " + perso.getTipoUsuario();
        holder.datos.setText(datosc);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AdministrarUsuarios) v.getContext()).selectedUsuario(perso);
            }
        });

    }

    @Override
    public int getItemCount() {
        return usuarios.size();
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
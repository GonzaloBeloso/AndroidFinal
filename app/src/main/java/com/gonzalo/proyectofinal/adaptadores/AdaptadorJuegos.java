package com.gonzalo.proyectofinal.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.gonzalo.proyectofinal.R;
import com.gonzalo.proyectofinal.utils.Juego;
import java.util.ArrayList;

public class AdaptadorJuegos extends RecyclerView.Adapter<AdaptadorJuegos.MyHolder> {

    ArrayList<Juego> listaJuegos;
    Context context;


    public AdaptadorJuegos(Context context) {
        this.listaJuegos = new ArrayList<>();
        this.context = context;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_juego, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final Juego juego = listaJuegos.get(position);
        holder.getNombreJuego().setText(juego.getNombre());
    }

    @Override
    public int getItemCount() {
        return listaJuegos.size();
    }

    public void agregarElemento(Juego juego) {
        listaJuegos.add(juego);
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder {


        TextView nombreJuego;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            nombreJuego = itemView.findViewById(R.id.nombre_juego);
        }

        public TextView getNombreJuego() {
            return nombreJuego;
        }
    }

}

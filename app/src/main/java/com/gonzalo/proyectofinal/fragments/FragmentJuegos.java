package com.gonzalo.proyectofinal.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.gonzalo.proyectofinal.R;
import com.gonzalo.proyectofinal.adaptadores.AdaptadorJuegos;
import com.gonzalo.proyectofinal.utils.Juego;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class FragmentJuegos extends Fragment {
    private RecyclerView lista;
    private ArrayList<Juego> listaJuegos;
    AdaptadorJuegos adaptadorJuegos;
    int id=4;
    String plataforma;

    public FragmentJuegos(String plataforma) {
        this.plataforma = plataforma;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        adaptadorJuegos = new AdaptadorJuegos(getContext());
        listaJuegos = new ArrayList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_juegos, container, false);
        lista = v.findViewById(R.id.lista_juegos);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        JsonObjectRequest peticionJson = new JsonObjectRequest(Request.Method.GET,
                "https://api.rawg.io/api/platforms", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Log.v("volley",response.toString());
                try {

                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String nombre = jsonObject.getString("name");
                        if (nombre.equals(plataforma) && nombre.toLowerCase().length()>0){
                            //Log.v("volley",nombre);
                            JSONArray juegos = jsonObject.getJSONArray("games");
                            for (int j =0;j<juegos.length();j++){
                                JSONObject juegoEntero = juegos.getJSONObject(j);
                                String nombreJuego = juegoEntero.getString("name");
                                System.out.println(nombreJuego);
                                Juego juego = new Juego(nombreJuego);
                                adaptadorJuegos.agregarElemento(juego);
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("volley","Error en la conexion");
            }
        });
        Volley.newRequestQueue(getContext()).add(peticionJson);
        lista.setAdapter(adaptadorJuegos);
        lista.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }
}
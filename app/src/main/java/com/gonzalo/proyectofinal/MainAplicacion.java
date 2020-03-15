package com.gonzalo.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import com.google.android.material.navigation.NavigationView;
import com.gonzalo.proyectofinal.fragments.FragmentJuegos;

public class MainAplicacion extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle drawerToggle;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancias();
        acciones();

    }

    private void instancias() {

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation);
        setSupportActionBar(toolbar);
        drawerToggle.syncState();
        imagen = findViewById(R.id.cabecera);

    }

    private void acciones() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {

                    case R.id.pc:
                        getSupportActionBar().setTitle("PC");
                        ft.replace(R.id.sitio_fragments,new FragmentJuegos("PC"));
                        break;
                    case R.id.ps4:
                        getSupportActionBar().setTitle("PlayStation 4");

                        ft.replace(R.id.sitio_fragments,new FragmentJuegos("PlayStation 4"));
                        break;
                    case R.id.xbox:
                        getSupportActionBar().setTitle("Xbox One");
                        ft.replace(R.id.sitio_fragments,new FragmentJuegos("Xbox One"));
                        break;
                }
                ft.commit();
                drawer.closeDrawers();
                return true;
            }
        });
    }
}
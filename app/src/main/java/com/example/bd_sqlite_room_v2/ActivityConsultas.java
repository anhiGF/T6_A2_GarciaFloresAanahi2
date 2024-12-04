package com.example.bd_sqlite_room_v2;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import bd.EscuelaBD;
import entidades.Alumno;

public class ActivityConsultas extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    EscuelaBD bd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Obtener datos desde la base de datos local en SQLite
        // EscuelaBD bd = EscuelaBD.getAppDatabase(getBaseContext());
        bd = EscuelaBD.getAppDatabase(getApplicationContext());
        cargarDatos();
    }
    private void cargarDatos() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Alumno> alumnos = bd.alumnoDAO().obtenerTodos();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new CustomAdapter((ArrayList<Alumno>) alumnos);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }
}// clase ActivityConsultas

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Alumno> alumnosList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNumControl;
        private final TextView textViewNombre;
        private final TextView textViewEdad;
        public ViewHolder(View view) {
            super(view);
            textViewNumControl = view.findViewById(R.id.textViewNumControl);
            textViewNombre = view.findViewById(R.id.textViewNombre);
            textViewEdad = view.findViewById(R.id.textViewEdad);
             }

        public TextView getTextViewNumControl() {
            return textViewNumControl;
        }

        public TextView getTextViewNombre() {
            return textViewNombre;
        }

        public TextView getTextViewEdad() {
            return textViewEdad;
        }
    }

    public CustomAdapter(ArrayList<Alumno> dataSet) {
        this.alumnosList = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Alumno alumno = alumnosList.get(position);
        viewHolder.getTextViewNumControl().setText(alumno.getNumControl());
        viewHolder.getTextViewNombre().setText(alumno.getNombre());
        viewHolder.getTextViewEdad().setText(String.valueOf(alumno.getEdad()));
    }

    @Override
    public int getItemCount() {
        return alumnosList.size();
    }
}
package com.example.bd_sqlite_room_v2;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import bd.EscuelaBD;
import entidades.Alumno;
import controladores.AlumnoDAO;
public class ActivityCambios extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText editTextSearch;
    private List<Alumno> alumnosList;
    private AlumnoDAO alumnoDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambios);

        recyclerView = findViewById(R.id.recyclerview);
        editTextSearch = findViewById(R.id.edit_text_search);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        EscuelaBD bd = EscuelaBD.getAppDatabase(getApplicationContext());
        alumnoDAO = bd.alumnoDAO();
        alumnosList = alumnoDAO.obtenerTodos();

        adapter = new CustomAdapter((ArrayList<Alumno>) alumnosList);
        recyclerView.setAdapter(adapter);

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text) {
        ArrayList<Alumno> filteredList = new ArrayList<>();

        for (Alumno alumno : alumnosList) {
            if (alumno.getNumControl().toLowerCase().startsWith(text.toLowerCase())) {
                filteredList.add(alumno);
            }
        }

        adapter = new CustomAdapter(filteredList);
        recyclerView.setAdapter(adapter);
    }
}

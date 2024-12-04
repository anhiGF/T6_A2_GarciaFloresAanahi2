package com.example.bd_sqlite_room_v2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import bd.EscuelaBD;

public class ActivityBajas extends Activity {
    EditText cajaNumControl;
    EscuelaBD bd;
    private RecyclerView recyclerViewNumerosControl;
    private NumerosControlAdapter numerosControlAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bajas);
        recyclerViewNumerosControl = findViewById(R.id.recyclerViewNumerosControl);
        recyclerViewNumerosControl.setHasFixedSize(true);
        cajaNumControl = findViewById(R.id.caja_num_control_bajas);

        bd = EscuelaBD.getAppDatabase(getApplicationContext());
        ArrayList<String> numerosControl = new ArrayList<>();
        numerosControlAdapter = new NumerosControlAdapter(numerosControl);
        recyclerViewNumerosControl.setAdapter(numerosControlAdapter);
        recyclerViewNumerosControl.setLayoutManager(new LinearLayoutManager(this));
    }

    public void eliminarAlumno(View v){
        String numControl = cajaNumControl.getText().toString();

        // Verificar que el campo no esté vacío
        if (!numControl.isEmpty()) {
            // Eliminar el alumno de la base de datos por número de control
            new Thread(new Runnable() {
                @Override
                public void run() {
                    bd.alumnoDAO().eliminarAlumnoPorNumControl(numControl);
                }
            }).start();

            Toast.makeText(getApplicationContext(),
                    "Alumno eliminado correctamente",
                    Toast.LENGTH_SHORT).show();

            // Limpiar el campo después de eliminar
            cajaNumControl.setText("");
        } else {
            Toast.makeText(getApplicationContext(),
                    "Ingresa un número de control",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
class NumerosControlAdapter extends RecyclerView.Adapter<NumerosControlAdapter.ViewHolder> {

    private ArrayList<String> numerosControlList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewNumeroControl;

        public ViewHolder(View view) {
            super(view);
            textViewNumeroControl = view.findViewById(R.id.textViewNumeroControl);
        }

        public TextView getTextViewNumeroControl() {
            return textViewNumeroControl;
        }
    }

    public NumerosControlAdapter(ArrayList<String> dataSet) {
        this.numerosControlList = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.numero_control_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextViewNumeroControl().setText(numerosControlList.get(position));
    }

    @Override
    public int getItemCount() {
        return numerosControlList.size();
    }
}

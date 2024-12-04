package com.example.bd_sqlite_room_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

public class ActivityMenu extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menu);
    }


    public void abrirActivities(View v){

        Intent i = new Intent(this, ActivityAltas.class);
        startActivity(i);

    }
    public void abrirActivityBajas(View view) {
        Intent i = new Intent(this, ActivityBajas.class);
        startActivity(i);
    }
    public void abrirConsultas(View v){
        Intent i = new Intent(this, ActivityConsultas.class);
        startActivity(i);
    }
    public void abrirActivityCambios(View view) {
        Intent i = new Intent(this, ActivityCambios.class);
        startActivity(i);
    }
}

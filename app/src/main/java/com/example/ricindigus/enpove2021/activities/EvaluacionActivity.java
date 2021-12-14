package com.example.ricindigus.enpove2021.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ricindigus.enpove2021.R;
import com.example.ricindigus.enpove2021.adapters.EvaluacionAdapter;
import com.example.ricindigus.enpove2021.modelo.DAOUtils;
import com.example.ricindigus.enpove2021.modelo.pojos.Caratula;

import java.util.ArrayList;

public class EvaluacionActivity extends AppCompatActivity {
    Toolbar toolbar;
    String idVivienda;
    String idHogar;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    EvaluacionAdapter evaluacionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_evaluacion);

        recyclerView      = (RecyclerView) findViewById(R.id.lista_evaluacion_rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        setearAdapter(DAOUtils.getViviendas(getApplicationContext()));
        toolbar  = (Toolbar) findViewById(R.id.toolbar_mapa);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mapa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_close:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setearAdapter(ArrayList<Caratula> lista){
        evaluacionAdapter = new EvaluacionAdapter(lista,getApplicationContext(), new EvaluacionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {

            }
        });
        recyclerView.setAdapter(evaluacionAdapter);
    }

}

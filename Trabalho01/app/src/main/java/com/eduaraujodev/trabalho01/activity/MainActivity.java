package com.eduaraujodev.trabalho01.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.eduaraujodev.trabalho01.R;
import com.eduaraujodev.trabalho01.adapter.ListaCelularAdapter;
import com.eduaraujodev.trabalho01.dao.CelularDao;
import com.eduaraujodev.trabalho01.model.Celular;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvSemCelular;
    private List<Celular> celulares;

    private RecyclerView rvLista;
    private ListaCelularAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvLista = (RecyclerView) findViewById(R.id.rvLista);
        tvSemCelular = (TextView) findViewById(R.id.tvSemCelular);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, NovoCelularActivity.class),
                        NovoCelularActivity.CODE_NOVO_CELULAR);
            }
        });

        carregaCelulares();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(MainActivity.this, "Cancelado", Toast.LENGTH_LONG).show();
        } else if (resultCode == NovoCelularActivity.CODE_NOVO_CELULAR) {
            carregaCelulares();
        }
    }

    private void carregaCelulares() {
        celulares = new CelularDao(this).getAll();

        if (celulares.size() > 0) {
            tvSemCelular.setText("");

            adapter = new ListaCelularAdapter(this, celulares, celularOnClickListener());
            rvLista.setLayoutManager(new LinearLayoutManager(this));
            rvLista.setAdapter(adapter);
        } else {
            tvSemCelular.setText(getString(R.string.textoSemCelular));
        }
    }

    private ListaCelularAdapter.CelularOnClickListener celularOnClickListener() {
        return new ListaCelularAdapter.CelularOnClickListener() {

            @Override
            public void onLongClickCelular(View view, int posicao) {
                Celular celular = celulares.get(posicao);

                Toast.makeText(getApplicationContext(), celular.getMarca(), Toast.LENGTH_LONG).show();
            }
        };
    }
}
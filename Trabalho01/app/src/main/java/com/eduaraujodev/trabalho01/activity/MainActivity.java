package com.eduaraujodev.trabalho01.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.eduaraujodev.trabalho01.R;
import com.eduaraujodev.trabalho01.adapter.ListaCelularAdapter;

public class MainActivity extends AppCompatActivity {

    private TextView tvSemCelular;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        carregaCelulares();
    }

    private void carregaCelulares() {
        tvSemCelular.setText(getString(R.string.textoSemCelular));
    }
}

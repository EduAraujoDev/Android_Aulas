package com.eduaraujodev.trabalho01.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.eduaraujodev.trabalho01.R;
import com.eduaraujodev.trabalho01.dao.CelularDao;
import com.eduaraujodev.trabalho01.dao.AndroidDao;
import com.eduaraujodev.trabalho01.model.Celular;
import com.eduaraujodev.trabalho01.model.VersaoAndroid;

import java.util.List;

public class NovoCelularActivity extends AppCompatActivity {

    public final static int CODE_NOVO_CELULAR = 1002;

    private TextInputLayout tilMarca;
    private TextInputLayout tilModelo;

    private Spinner spVersaoAndroid;

    private List<VersaoAndroid> versaoAndroids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_celular);

        tilMarca = (TextInputLayout) findViewById(R.id.tilMarca);
        tilModelo = (TextInputLayout) findViewById(R.id.tilModelo);

        spVersaoAndroid = (Spinner) findViewById(R.id.spVersaoAndroid);

        AndroidDao versaoAndroidDao = new AndroidDao(this);
        versaoAndroids = versaoAndroidDao.getAll();

        ArrayAdapter<VersaoAndroid> adapter = new ArrayAdapter<VersaoAndroid>(getApplicationContext(),
                R.layout.versaoandroid_spinner_item, versaoAndroids);

        adapter.setDropDownViewResource(R.layout.versaoandroid_spinner_item);

        spVersaoAndroid.setAdapter(adapter);
    }

    public void cadastrar(View v) {
        Celular celular = new Celular();
        celular.setMarca(tilMarca.getEditText().getText().toString());
        celular.setModelo(tilModelo.getEditText().getText().toString());
        celular.setVersaoAndroid((VersaoAndroid) spVersaoAndroid.getSelectedItem());

        CelularDao celularDao = new CelularDao(this);
        celularDao.add(celular);

        retornaParaTelaAnterior();
    }

    public void retornaParaTelaAnterior() {
        Intent intentMessage = new Intent();
        setResult(CODE_NOVO_CELULAR, intentMessage);
        finish();
    }
}
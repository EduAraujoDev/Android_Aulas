package com.eduaraujodev.trabalho01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.eduaraujodev.trabalho01.R;
import com.eduaraujodev.trabalho01.dao.CelularDao;
import com.eduaraujodev.trabalho01.model.Celular;

public class AlterarCelularActivity extends AppCompatActivity {

    public final static int CODE_NOVO_CELULAR = 1002;

    private TextInputLayout tilId;
    private TextInputLayout tilMarca;
    private TextInputLayout tilModelo;

    private Button btAlterar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_celular);

        tilId = (TextInputLayout) findViewById(R.id.tilId);
        tilMarca = (TextInputLayout) findViewById(R.id.tilMarca);
        tilModelo = (TextInputLayout) findViewById(R.id.tilModelo);

        Intent idCelular = getIntent();
        final Celular celular = new CelularDao(this).getCelular(idCelular.getIntExtra("celular", 0));

        tilId.getEditText().setText(String.valueOf(celular.getId()));
        tilMarca.getEditText().setText(celular.getMarca());
        tilModelo.getEditText().setText(celular.getModelo());

        tilId.getEditText().setEnabled(false);

        btAlterar = (Button) findViewById(R.id.btAlterar);

        btAlterar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Celular celularAlt = new Celular();
                celularAlt.setId(celular.getId());
                celularAlt.setMarca(tilMarca.getEditText().getText().toString());
                celularAlt.setModelo(tilModelo.getEditText().getText().toString());

                Log.d("TAG", celularAlt.getMarca());
                Log.d("TAG", celularAlt.getModelo());

                CelularDao celularDao = new CelularDao(getApplicationContext());
                celularDao.atualiza(celularAlt);

                retornaParaTelaAnterior();

                Toast.makeText(getApplicationContext(), "Alterado com sucesso", Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }

    public void retornaParaTelaAnterior() {
        Intent intentMessage = new Intent();
        setResult(CODE_NOVO_CELULAR, intentMessage);
        finish();
    }
}
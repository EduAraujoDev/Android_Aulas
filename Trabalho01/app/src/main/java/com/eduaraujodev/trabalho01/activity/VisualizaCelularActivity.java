package com.eduaraujodev.trabalho01.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.eduaraujodev.trabalho01.R;
import com.eduaraujodev.trabalho01.dao.CelularDao;
import com.eduaraujodev.trabalho01.model.Celular;

public class VisualizaCelularActivity extends AppCompatActivity {

    private TextInputLayout tilId;
    private TextInputLayout tilMarca;
    private TextInputLayout tilModelo;

    private Button btFechar;

    ImageView ivIcone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiza_celular);

        tilId = (TextInputLayout) findViewById(R.id.tilId);
        tilMarca = (TextInputLayout) findViewById(R.id.tilMarca);
        tilModelo = (TextInputLayout) findViewById(R.id.tilModelo);

        btFechar = (Button) findViewById(R.id.btFechar);

        ivIcone = (ImageView) findViewById(R.id.ivIcone);

        Intent idCelular = getIntent();
        Celular celular = new CelularDao(this).getCelular(idCelular.getIntExtra("celular", 0));

        tilId.getEditText().setText(String.valueOf(celular.getId()));
        tilMarca.getEditText().setText(celular.getMarca());
        tilModelo.getEditText().setText(celular.getModelo());

        tilId.getEditText().setEnabled(false);
        tilMarca.getEditText().setEnabled(false);
        tilModelo.getEditText().setEnabled(false);

        Glide.with(this)
                .load(celular.getVersaoAndroid().getUrlImagem())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(ivIcone);

        btFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

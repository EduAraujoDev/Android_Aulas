package com.eduaraujodev.fiapgames.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.eduaraujodev.fiapgames.R;
import com.eduaraujodev.fiapgames.dao.UsuarioDAO;
import com.eduaraujodev.fiapgames.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    public static final String KEY_APP_PREFERENCES = "APP_PREFERENCE";
    public static final String KEY_LOGIN = "login";

    public static String USER = "";

    private TextInputLayout tilLogin;
    private TextInputLayout tilSenha;

    private CheckBox cbManterConectado;

    private Button btnAcessar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.login_activity);

        SharedPreferences shared = getSharedPreferences(KEY_APP_PREFERENCES, MODE_PRIVATE);
        String login = shared.getString(KEY_LOGIN, "");

        tilLogin = (TextInputLayout) findViewById(R.id.tilLogin);
        tilSenha = (TextInputLayout) findViewById(R.id.tilSenha);
        cbManterConectado = (CheckBox) findViewById(R.id.cbManterConectado);
        btnAcessar = (Button) findViewById(R.id.btnAcessar);

        if (login.equals("")){
            btnAcessar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Usuario usuario = new Usuario();
                    usuario.setLogin(tilLogin.getEditText().getText().toString());
                    usuario.setSenha(tilSenha.getEditText().getText().toString());

                    if (new UsuarioDAO(getApplicationContext()).getBy(usuario) != null) {
                        USER = tilLogin.getEditText().getText().toString();

                        if (cbManterConectado.isChecked()) {
                            SharedPreferences pref = getSharedPreferences(KEY_APP_PREFERENCES, MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString(KEY_LOGIN, USER);
                            editor.apply();
                        }

                        Toast.makeText(getApplicationContext(), R.string.bem_vindo, Toast.LENGTH_LONG).show();

                        iniciarApp();
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.usuario_nao_existe, Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            iniciarApp();
        }
    }

    private void iniciarApp() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
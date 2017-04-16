package com.eduaraujodev.fiapgames.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.eduaraujodev.fiapgames.R;
import com.eduaraujodev.fiapgames.dao.UsuarioDAO;
import com.eduaraujodev.fiapgames.model.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3500;

    private ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ivLogo = (ImageView) findViewById(R.id.ivLogo);
        Animation anim_1 = AnimationUtils.loadAnimation(this, R.anim.animacao_splashscreen_1);
        anim_1.reset();

        ivLogo.clearAnimation();
        ivLogo.startAnimation(anim_1);

        new BuscaUsuario().execute("http://www.mocky.io/v2/58b9b1740f0000b614f09d2f");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private class BuscaUsuario extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(10000);

                conn.setRequestMethod("GET");
                conn.setDoOutput(true);

                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream is = conn.getInputStream();
                    BufferedReader buffer = new BufferedReader(new InputStreamReader(is));

                    StringBuilder result = new StringBuilder();
                    String linha;

                    while ((linha = buffer.readLine()) != null) {
                        result.append(linha);
                    }

                    conn.disconnect();

                    return result.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s == null) {
                Toast.makeText(SplashScreenActivity.this, R.string.user_erro, Toast.LENGTH_LONG).show();
            } else {
                try {
                    JSONObject json = new JSONObject(s);

                    if (json != null) {
                        Usuario usuario = new Usuario();
                        usuario.setLogin(json.getString("usuario"));
                        usuario.setSenha(json.getString("senha"));

                        if (new UsuarioDAO(getApplicationContext()).getBy(usuario) == null) {
                            new UsuarioDAO(getApplicationContext()).add(usuario);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
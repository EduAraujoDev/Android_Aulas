package eduaraujodev.com.aula01;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout tilValor1;
    private TextInputLayout tilValor2;
    private TextView etResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tilValor1 = (TextInputLayout) findViewById(R.id.tilValor1);
        tilValor2 = (TextInputLayout) findViewById(R.id.tilValor2);
        etResultado = (TextView) findViewById(R.id.etResultado);
    }

    public void somar(View view) {
        int valor1 = Integer.parseInt(tilValor1.getEditText().getText().toString());
        int valor2 = Integer.parseInt(tilValor2.getEditText().getText().toString());

        etResultado.setText(String.valueOf(valor1 + valor2));
    }
}
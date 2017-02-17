package eduaraujodev.com.aula02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private Spinner spCurso;
    private RadioGroup rbPeriodo;
    private Button btParticipar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = (EditText) findViewById(R.id.etNome);
        spCurso = (Spinner) findViewById(R.id.spCurso);
        rbPeriodo = (RadioGroup) findViewById(R.id.rbPeriodo);
        btParticipar = (Button) findViewById(R.id.btParticipar);

        btParticipar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent confirmaIntent = new Intent(FormularioActivity.this, ConfirmacaoActivity.class);

                confirmaIntent.putExtra(getString(R.string.label_nome), etNome.getText().toString());
                confirmaIntent.putExtra(getString(R.string.label_curso), spCurso.getSelectedItem().toString());
                confirmaIntent.putExtra(getString(R.string.selecione_periodo),
                        rbPeriodo.getCheckedRadioButtonId() == R.id.rbManha ? getString(R.string.label_manha) : getString(R.string.label_noite));


                startActivity(confirmaIntent);
            }
        });
    }
}

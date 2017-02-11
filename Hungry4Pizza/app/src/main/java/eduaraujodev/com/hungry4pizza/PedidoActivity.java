package eduaraujodev.com.hungry4pizza;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PedidoActivity extends AppCompatActivity {

    private TextView tvUsuario;

    private CheckBox cbAtum;
    private CheckBox cbBacon;
    private CheckBox cbCalabresa;
    private CheckBox cbMussarela;

    private RadioGroup rgTamanhoPizza;

    private Spinner spPagamentos;

    private CheckBox cbBordaRecheada;
    private CheckBox cbRecheioExtra;
    private CheckBox cbRefrigerante;
    private CheckBox cbSobremesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        tvUsuario = (TextView) findViewById(R.id.tvUsuario);

        cbAtum = (CheckBox) findViewById(R.id.cbAtum);
        cbBacon = (CheckBox) findViewById(R.id.cbBacon) ;
        cbCalabresa = (CheckBox) findViewById(R.id.cbCalabresa);
        cbMussarela = (CheckBox) findViewById(R.id.cbMussarela);

        rgTamanhoPizza = (RadioGroup) findViewById(R.id.rgTamanhoPizza);

        spPagamentos = (Spinner) findViewById(R.id.spnPagamentos);

        cbBordaRecheada = (CheckBox) findViewById(R.id.cbBordaRecheada);
        cbRecheioExtra = (CheckBox) findViewById(R.id.cbRecheioExtra);
        cbRefrigerante = (CheckBox) findViewById(R.id.cbRefrigerante);
        cbSobremesa = (CheckBox) findViewById(R.id.cbSobremesa);

        Bundle bundle = getIntent().getExtras();
        String usuario = bundle.getString("usuario");

        tvUsuario .setText("Olá " + usuario);
    }

    public void calcular(View view) {
        int idSelecionado = rgTamanhoPizza.getCheckedRadioButtonId();
        int valorTamanhoPizza = 0;
        double valor = 0;

        switch (idSelecionado) {
            case R.id.rbPequena:
                valorTamanhoPizza = 10;
                break;
            case R.id.rbMedia:
                valorTamanhoPizza = 12;
                break;
            case R.id.rbGrande:
                valorTamanhoPizza = 15;
                break;
        }

        if (cbBordaRecheada.isChecked()) {
            valor += 5;
        }

        if (cbRecheioExtra.isChecked()) {
            valor += 10;
        }

        if (cbRefrigerante.isChecked()) {
            valor += 7;
        }

        if (cbSobremesa.isChecked()) {
            valor += 13;
        }

        if (cbAtum.isChecked()) {
            valor += 3 + valorTamanhoPizza;
        }

        if (cbBacon.isChecked()) {
            valor += 5 + valorTamanhoPizza;
        }

        if (cbCalabresa.isChecked()) {
            valor += 4 + valorTamanhoPizza;
        }

        if (cbMussarela.isChecked()) {
            valor += 2 + valorTamanhoPizza;
        }

        String pagamento = (String) spPagamentos.getSelectedItem();

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmação");
        alert.setMessage("Valor: " + valor + " \n Pagamento: " + pagamento);
        alert.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog , int which) {
                Log.i("Pedido" , "Confirmação de Pedido");
                Toast. makeText(PedidoActivity.this, "Pedido Confirmado!", Toast. LENGTH_SHORT).show();
            }
        });

        alert.setNegativeButton("NÃO" , null);
        alert.show();
    }
}
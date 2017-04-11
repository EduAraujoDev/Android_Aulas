package com.eduaraujodev.fiapgames.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.eduaraujodev.fiapgames.R;
import com.eduaraujodev.fiapgames.dao.PedidoDAO;
import com.eduaraujodev.fiapgames.dao.ProdutosDAO;
import com.eduaraujodev.fiapgames.model.Pedido;
import com.eduaraujodev.fiapgames.model.Produtos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CadastrarActivity extends AppCompatActivity {

    private TextInputLayout tilNomeCliente;
    private TextInputLayout tilTelCliente;
    private TextInputLayout tilCPFCliente;

    private RadioGroup rgCpfNota;
    private Spinner spProdutos;
    private Button btFinalizar;

    private List<Produtos> produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        tilNomeCliente = (TextInputLayout) findViewById(R.id.tilNomeCliente);
        tilTelCliente = (TextInputLayout) findViewById(R.id.tilTelCliente);
        tilCPFCliente = (TextInputLayout) findViewById(R.id.tilCPFCliente);

        rgCpfNota = (RadioGroup) findViewById(R.id.rgCpfNota);
        spProdutos = (Spinner) findViewById(R.id.spProdutos);
        btFinalizar = (Button) findViewById(R.id.btFinalizar);

        produtos = new ProdutosDAO(this).getAll();
        ArrayAdapter<Produtos> adapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.produtos_spinner_item, produtos);

        adapter.setDropDownViewResource(R.layout.produtos_spinner_item);
        spProdutos.setAdapter(adapter);

        btFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Finalizar();
            }
        });
    }

    private void Finalizar() {
        Pedido pedido = new Pedido();
        pedido.setNomeCliente(tilNomeCliente.getEditText().getText().toString());
        pedido.setTelefone(tilTelCliente.getEditText().getText().toString());
        pedido.setCpf(tilCPFCliente.getEditText().getText().toString());
        pedido.setCpfNota(rgCpfNota.getCheckedRadioButtonId() == R.id.rbCPFsim ? "S" : "N");
        pedido.setData(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
        pedido.setProdutos((Produtos) spProdutos.getSelectedItem());

        if (new PedidoDAO(this).add(pedido)){
            Toast.makeText(this, "Erro ao inserir pedido", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Pedido inserido com sucesso", Toast.LENGTH_LONG).show();

            finish();
        }
    }
}
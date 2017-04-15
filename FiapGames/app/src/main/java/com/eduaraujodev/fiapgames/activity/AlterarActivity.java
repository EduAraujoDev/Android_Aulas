package com.eduaraujodev.fiapgames.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.eduaraujodev.fiapgames.R;
import com.eduaraujodev.fiapgames.dao.PedidoDAO;
import com.eduaraujodev.fiapgames.dao.ProdutoDAO;
import com.eduaraujodev.fiapgames.model.Pedido;
import com.eduaraujodev.fiapgames.model.Produto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AlterarActivity extends AppCompatActivity {
    public final static int CODE_ALTERA_PEDIDO = 1002;

    private TextInputLayout tilId;
    private TextInputLayout tilNomeCliente;
    private TextInputLayout tilTelCliente;
    private TextInputLayout tilCPFCliente;

    private RadioButton radioButton;
    private RadioGroup rgCpfNota;
    private Spinner spProdutos;
    private Button btAlterar;

    private List<Produto> produtos;
    private Pedido pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        tilId = (TextInputLayout) findViewById(R.id.tilId);
        tilNomeCliente = (TextInputLayout) findViewById(R.id.tilNomeCliente);
        tilTelCliente = (TextInputLayout) findViewById(R.id.tilTelCliente);
        tilCPFCliente = (TextInputLayout) findViewById(R.id.tilCPFCliente);

        rgCpfNota = (RadioGroup) findViewById(R.id.rgCpfNota);
        spProdutos = (Spinner) findViewById(R.id.spProdutos);
        btAlterar = (Button) findViewById(R.id.btAlterar);

        produtos = new ProdutoDAO(this).getAll();
        ArrayAdapter<Produto> adapter = new ArrayAdapter<>(getApplicationContext(),
                R.layout.produtos_spinner_item, produtos);

        adapter.setDropDownViewResource(R.layout.produtos_spinner_item);
        spProdutos.setAdapter(adapter);

        Intent idPedido = getIntent();
        pedido = new PedidoDAO(this).getPedido(idPedido.getIntExtra("pedido", 0));

        tilId.getEditText().setText(String.valueOf(pedido.getId()));
        tilNomeCliente.getEditText().setText(String.valueOf(pedido.getNomeCliente()));
        tilTelCliente.getEditText().setText(String.valueOf(pedido.getTelefone()));
        tilCPFCliente.getEditText().setText(String.valueOf(pedido.getCpf()));

        tilId.getEditText().setEnabled(false);

        spProdutos.setSelection((int) (pedido.getProduto().getId() - 1));

        if (pedido.getCpfNota().equals("S")) {
            radioButton = (RadioButton) findViewById(R.id.rbCPFsim);
        } else if (pedido.getCpfNota().equals("N")) {
            radioButton = (RadioButton) findViewById(R.id.rbCPFNao);
        }

        radioButton.setChecked(true);

        btAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alterar();
            }
        });
    }

    private void Alterar() {
        Pedido pedido = new Pedido();
        pedido.setId(this.pedido.getId());
        pedido.setNomeCliente(tilNomeCliente.getEditText().getText().toString());
        pedido.setTelefone(tilTelCliente.getEditText().getText().toString());
        pedido.setCpf(tilCPFCliente.getEditText().getText().toString());
        pedido.setCpfNota(rgCpfNota.getCheckedRadioButtonId() == R.id.rbCPFsim ? "S" : "N");
        pedido.setData(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
        pedido.setProduto((Produto) spProdutos.getSelectedItem());

        new PedidoDAO(getApplicationContext()).atualiza(pedido);

        Toast.makeText(getApplicationContext(), R.string.pedido_alterado, Toast.LENGTH_LONG).show();

        Intent intentMessage = new Intent();
        setResult(CODE_ALTERA_PEDIDO, intentMessage);
        finish();
    }
}
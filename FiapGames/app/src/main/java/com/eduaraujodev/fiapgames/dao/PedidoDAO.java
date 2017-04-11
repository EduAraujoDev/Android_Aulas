package com.eduaraujodev.fiapgames.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.eduaraujodev.fiapgames.model.Pedido;

public class PedidoDAO {
    private DBOpenHelper banco;

    public PedidoDAO(Context context) {
        banco = new DBOpenHelper(context);
    }

    public static final String TABELA_PEDIDO = "celular";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME_CLIENTE = "nome_cliente";
    public static final String COLUNA_TELEFONE = "telefone";
    public static final String COLUNA_CPF = "cpf";
    public static final String COLUNA_CPF_NOTA = "cpf_nota";
    public static final String COLUNA_DATA = "data";
    public static final String COLUNA_PRODUTOS_ID = "produto_id";

    public Boolean add(Pedido pedido){
        long resultado;
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_CLIENTE, pedido.getNomeCliente());
        values.put(COLUNA_TELEFONE, pedido.getTelefone());
        values.put(COLUNA_CPF, pedido.getCpf());
        values.put(COLUNA_CPF_NOTA, pedido.getCpfNota());
        values.put(COLUNA_DATA, pedido.getData());
        values.put(COLUNA_PRODUTOS_ID, pedido.getProdutos().getId());

        resultado = db.insert(TABELA_PEDIDO, null, values);

        db.close();

        if(resultado == -1) {
            return false;
        } else {
            return true;
        }
    }
}
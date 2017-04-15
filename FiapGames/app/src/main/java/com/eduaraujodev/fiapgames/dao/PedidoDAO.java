package com.eduaraujodev.fiapgames.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eduaraujodev.fiapgames.model.Pedido;
import com.eduaraujodev.fiapgames.model.Produto;

import java.util.LinkedList;
import java.util.List;

public class PedidoDAO {
    private DBOpenHelper banco;

    public PedidoDAO(Context context) {
        banco = new DBOpenHelper(context);
    }

    public static final String TABELA_PEDIDO = "pedido";

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
        values.put(COLUNA_PRODUTOS_ID, pedido.getProduto().getId());

        resultado = db.insert(TABELA_PEDIDO, null, values);

        db.close();

        if(resultado == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<Pedido> getAll() {
        List<Pedido> pedidos = new LinkedList<>();

        String rawQuery = "SELECT pe.*, pr.* FROM " + PedidoDAO.TABELA_PEDIDO + " pe " +
                " INNER JOIN " + ProdutoDAO.TABELA_PRODUTO + " pr ON pe." + PedidoDAO.COLUNA_PRODUTOS_ID + " = pr." + ProdutoDAO.COLUNA_ID +
                " ORDER BY " + PedidoDAO.COLUNA_NOME_CLIENTE + " ASC";

        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery(rawQuery, null);
        Pedido pedido = null;

        if (cursor.moveToFirst()) {
            do {
                pedido = new Pedido();
                pedido.setId(cursor.getInt(0));
                pedido.setNomeCliente(cursor.getString(1));
                pedido.setTelefone(cursor.getString(2));
                pedido.setCpf(cursor.getString(3));
                pedido.setCpfNota(cursor.getString(4));
                pedido.setData(cursor.getString(5));
                pedido.setProduto(new Produto(cursor.getInt(7), cursor.getString(8), Double.parseDouble(cursor.getString(9)), cursor.getString(10)));
                pedidos.add(pedido);
            } while (cursor.moveToNext());
        }

        return pedidos;
    }

    public Pedido getPedido(int id) {
        Pedido pedido = null;

        String rawQuery = "SELECT pe.*, pr.* FROM " + PedidoDAO.TABELA_PEDIDO + " pe " +
                " INNER JOIN " + ProdutoDAO.TABELA_PRODUTO + " pr ON pe." + PedidoDAO.COLUNA_PRODUTOS_ID + " = pr." + ProdutoDAO.COLUNA_ID +
                " WHERE pe." + PedidoDAO.COLUNA_ID + " = " + id +
                " ORDER BY " + PedidoDAO.COLUNA_NOME_CLIENTE + " ASC";

        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);

        if (cursor.moveToFirst()) {
            pedido = new Pedido();
            pedido.setId(cursor.getInt(0));
            pedido.setNomeCliente(cursor.getString(1));
            pedido.setTelefone(cursor.getString(2));
            pedido.setCpf(cursor.getString(3));
            pedido.setCpfNota(cursor.getString(4));
            pedido.setData(cursor.getString(5));
            pedido.setProduto(new Produto(cursor.getInt(7), cursor.getString(8), Double.parseDouble(cursor.getString(9)), cursor.getString(10)));
        }

        return pedido;
    }

    public void atualiza(Pedido pedido) {
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(COLUNA_NOME_CLIENTE, pedido.getNomeCliente());
        valores.put(COLUNA_TELEFONE, pedido.getTelefone());
        valores.put(COLUNA_CPF, pedido.getCpf());
        valores.put(COLUNA_CPF_NOTA, pedido.getCpfNota());
        valores.put(COLUNA_DATA, pedido.getData());
        valores.put(COLUNA_PRODUTOS_ID, pedido.getProduto().getId());

        db.update(TABELA_PEDIDO, valores, PedidoDAO.COLUNA_ID + " = " + pedido.getId(), null);
    }

    public int delete(long id) {
        SQLiteDatabase db = banco.getReadableDatabase();

        return db.delete(TABELA_PEDIDO, "id = " + id, null);
    }
}
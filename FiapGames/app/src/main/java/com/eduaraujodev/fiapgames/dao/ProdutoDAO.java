package com.eduaraujodev.fiapgames.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eduaraujodev.fiapgames.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private DBOpenHelper banco;

    public ProdutoDAO(Context context) {
        banco = new DBOpenHelper(context);
    }

    public static final String TABELA_PRODUTO = "produto";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_VALOR = "valor";
    public static final String COLUNA_IMAGEM = "imagem";

    public List<Produto> getAll() {
        List<Produto> produtos = new ArrayList<>();

        String query = "SELECT * FROM " + TABELA_PRODUTO;
        SQLiteDatabase db = banco.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        Produto produto = null;

        if (cursor.moveToFirst()) {
            do {
                produto = new Produto();
                produto.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
                produto.setDescricao(cursor.getString(cursor.getColumnIndex(COLUNA_DESCRICAO)));
                produto.setValor(cursor.getDouble(cursor.getColumnIndex(COLUNA_VALOR)));
                produto.setImagem(cursor.getString(cursor.getColumnIndex(COLUNA_IMAGEM)));
                produtos.add(produto);
            } while (cursor.moveToNext());
        }

        db.close();

        return produtos;
    }

    public Produto getby(int id) {
        SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = { COLUNA_ID, COLUNA_DESCRICAO, COLUNA_VALOR, COLUNA_IMAGEM };
        String where = "id = " + id;

        Cursor cursor = db.query(true, TABELA_PRODUTO, colunas, where, null, null, null, null, null);
        Produto produto = null;

        if(cursor != null) {
            cursor.moveToFirst();
            produto = new Produto();
            produto.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
            produto.setDescricao(cursor.getString(cursor.getColumnIndex(COLUNA_ID)));
            produto.setValor(cursor.getDouble(cursor.getColumnIndex(COLUNA_VALOR)));
            produto.setImagem(cursor.getString(cursor.getColumnIndex(COLUNA_IMAGEM)));
        }

        return produto;
    }
}
package com.eduaraujodev.fiapgames.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eduaraujodev.fiapgames.model.Produtos;

import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {
    private DBOpenHelper banco;

    public ProdutosDAO(Context context) {
        banco = new DBOpenHelper(context);
    }

    public static final String TABELA_PRODUTOS = "produtos";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_VALOR = "valor";
    public static final String COLUNA_IMAGEM = "imagem";

    public List<Produtos> getAll() {
        List<Produtos> produtos = new ArrayList<>();

        String query = "SELECT * FROM " + TABELA_PRODUTOS;
        SQLiteDatabase db = banco.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        Produtos produto = null;

        if (cursor.moveToFirst()) {
            do {
                produto = new Produtos();
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
}
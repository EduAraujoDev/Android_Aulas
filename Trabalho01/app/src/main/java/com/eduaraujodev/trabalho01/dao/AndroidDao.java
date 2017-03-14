package com.eduaraujodev.trabalho01.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eduaraujodev.trabalho01.model.VersaoAndroid;

import java.util.LinkedList;
import java.util.List;

public class AndroidDao {
    private DBOpenHelper banco;

    public AndroidDao(Context context) {
        banco = new DBOpenHelper(context);
    }

    public static final String TABELA_VERSAO_ANDROID = "android";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_URL_IMAGEM = "url_imagem";

    public List<VersaoAndroid> getAll() {
        List<VersaoAndroid> versaoAndroids = new LinkedList<>();

        String query = "SELECT * FROM " + TABELA_VERSAO_ANDROID;
        SQLiteDatabase db = banco.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        VersaoAndroid versaoAndroid = null;

        if (cursor.moveToFirst()) {
            do {
                versaoAndroid = new VersaoAndroid();
                versaoAndroid.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
                versaoAndroid.setNome(cursor.getString(cursor.getColumnIndex(COLUNA_NOME)));
                versaoAndroid.setUrlImagem(cursor.getString(cursor.getColumnIndex(COLUNA_URL_IMAGEM)));
                versaoAndroids.add(versaoAndroid);
            } while (cursor.moveToNext());
        }

        return versaoAndroids;
    }

    public VersaoAndroid getby(int id) {
        SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = { COLUNA_ID, COLUNA_NOME, COLUNA_URL_IMAGEM };
        String where = "id = " + id;

        Cursor cursor = db.query(true, TABELA_VERSAO_ANDROID, colunas, where, null, null, null, null, null);
        VersaoAndroid versaoAndroid = null;

        if(cursor != null) {
            cursor.moveToFirst();
            versaoAndroid = new VersaoAndroid();
            versaoAndroid.setNome(cursor.getString(cursor.getColumnIndex(COLUNA_NOME)));
            versaoAndroid.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
            versaoAndroid.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_URL_IMAGEM)));
        }

        return versaoAndroid;
    }
}
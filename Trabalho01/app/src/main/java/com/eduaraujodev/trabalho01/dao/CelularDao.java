package com.eduaraujodev.trabalho01.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eduaraujodev.trabalho01.model.Celular;
import com.eduaraujodev.trabalho01.model.VersaoAndroid;

import java.util.LinkedList;
import java.util.List;

public class CelularDao {
    private DBOpenHelper banco;

    public CelularDao(Context context) {
        banco = new DBOpenHelper(context);
    }

    public static final String TABELA_CELULAR = "celular";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_MARCA = "marca";
    public static final String COLUNA_MODELO = "modelo";
    public static final String COLUNA_VERSAO_ANDROID_ID = "android_id";

    public String add(Celular celular){
        long resultado;
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_MARCA, celular.getMarca());
        values.put(COLUNA_MODELO, celular.getModelo());
        values.put(COLUNA_VERSAO_ANDROID_ID, celular.getVersaoAndroid().getId());

        resultado = db.insert(TABELA_CELULAR, null, values);

        db.close();

        if(resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }

    public Celular getCelular(int id) {
        Celular celular = null;

        String rawQuery = "SELECT t.*, c.nome, c.url_imagem FROM " + CelularDao.TABELA_CELULAR + " t " +
                "INNER JOIN " + AndroidDao.TABELA_VERSAO_ANDROID + " c ON t." + CelularDao.COLUNA_VERSAO_ANDROID_ID + " = c." + AndroidDao.COLUNA_ID +
                " WHERE t." + CelularDao.COLUNA_ID + " = " + id +
                " ORDER BY " + CelularDao.COLUNA_MARCA + " ASC";

        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery(rawQuery, null);

        if (cursor.moveToFirst()) {
            celular = new Celular();

            celular.setId(cursor.getInt(0));
            celular.setMarca(cursor.getString(1));
            celular.setModelo(cursor.getString(2));
            celular.setVersaoAndroid(new VersaoAndroid(cursor.getInt(3), cursor.getString(4), cursor.getString(5)));
        }

        return celular;
    }

    public List<Celular> getAll() {
        List<Celular> celulares = new LinkedList<>();

        String rawQuery = "SELECT t.*, c.nome, c.url_imagem FROM " + CelularDao.TABELA_CELULAR + " t " +
                "INNER JOIN " + AndroidDao.TABELA_VERSAO_ANDROID + " c ON t." + CelularDao.COLUNA_VERSAO_ANDROID_ID + " = c." + AndroidDao.COLUNA_ID +
                " ORDER BY " + CelularDao.COLUNA_MARCA + " ASC";

        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery(rawQuery, null);
        Celular celular = null;

        if (cursor.moveToFirst()) {
            do {
                celular = new Celular();
                celular.setId(cursor.getInt(0));
                celular.setMarca(cursor.getString(1));
                celular.setModelo(cursor.getString(2));
                celular.setVersaoAndroid(new VersaoAndroid(cursor.getInt(3), cursor.getString(4), cursor.getString(5)));
                celulares.add(celular);
            } while (cursor.moveToNext());
        }

        return celulares;
    }

    public int delete(int id) {
        SQLiteDatabase db = banco.getReadableDatabase();

        return db.delete(TABELA_CELULAR, "id = " + id, null);
    }
}
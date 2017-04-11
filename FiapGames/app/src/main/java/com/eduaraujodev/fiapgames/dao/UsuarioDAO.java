package com.eduaraujodev.fiapgames.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eduaraujodev.fiapgames.model.Usuario;

public class UsuarioDAO {
    private SQLiteDatabase db;
    private DBOpenHelper banco;

    public UsuarioDAO(Context context) {
        banco = new DBOpenHelper(context);
    }

    private static final String TABELA_USUARIO = "usuario";

    private static final String COLUNA_ID = "id";
    private static final String COLUNA_LOGIN = "login";
    private static final String COLUNA_SENHA = "senha";

    public Boolean add(Usuario usuario) {
        long resultado;

        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_LOGIN, usuario.getLogin());
        values.put(COLUNA_SENHA, usuario.getSenha());

        resultado = db.insert(TABELA_USUARIO, null, values);

        db.close();

        if(resultado == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Usuario getBy(Usuario usuario) {
        Usuario usuarioRet = null;
        SQLiteDatabase db = banco.getWritableDatabase();
        String where = COLUNA_LOGIN + " = '" + usuario.getLogin() + "' and " + COLUNA_SENHA + " = '" + usuario.getSenha() + "' ";

        Cursor cursor = db.query(true, TABELA_USUARIO, null, where, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            usuarioRet = new Usuario();
            usuarioRet.setId(cursor.getInt(cursor.getColumnIndex(COLUNA_ID)));
            usuarioRet.setLogin(cursor.getString(cursor.getColumnIndex(COLUNA_LOGIN)));
            usuarioRet.setSenha(cursor.getString(cursor.getColumnIndex(COLUNA_SENHA)));

            cursor.close();
        }

        db.close();

        return usuarioRet;
    }
}
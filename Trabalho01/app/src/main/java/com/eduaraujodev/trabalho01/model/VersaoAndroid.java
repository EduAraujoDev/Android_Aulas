package com.eduaraujodev.trabalho01.model;

public class VersaoAndroid {
    private int id;
    private String nome;
    private String urlImagem;

    public VersaoAndroid() {
    }

    public VersaoAndroid(int id, String nome, String urlImagem) {
        this.id = id;
        this.nome = nome;
        this.urlImagem = urlImagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    @Override
    public String toString() {
        return nome;
    }
}
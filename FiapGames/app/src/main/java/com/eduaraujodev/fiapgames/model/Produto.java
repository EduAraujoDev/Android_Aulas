package com.eduaraujodev.fiapgames.model;

public class Produto {
    private long id;
    private String descricao;
    private Double valor;
    private String imagem;

    public Produto() {
    }

    public Produto(long id, String descricao, Double valor, String imagem) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.imagem = imagem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return descricao + " - R$" + String.valueOf(valor);
    }
}
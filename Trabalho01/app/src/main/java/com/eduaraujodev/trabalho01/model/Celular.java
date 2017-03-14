package com.eduaraujodev.trabalho01.model;

public class Celular {

    private int id;
    private String marca;
    private String modelo;
    private VersaoAndroid versaoAndroid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public VersaoAndroid getVersaoAndroid() {
        return versaoAndroid;
    }

    public void setVersaoAndroid(VersaoAndroid versaoAndroid) {
        this.versaoAndroid = versaoAndroid;
    }
}
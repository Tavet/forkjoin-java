package com.atolonrot.model;

/**
 * @author Breyner
 * Clase modelo
 */
public class Dispositivo {
    private int bateria;
    private String modelo;
    private String marca;

    public Dispositivo(int bateria, String modelo, String marca) {
        this.bateria = bateria;
        this.modelo = modelo;
        this.marca = marca;
    }

    public int obtenerBateria() {
        return bateria;
    }

}

package com.example.pokedex;

public class Pokemon {
    private String nombre, tipo;
    private int imagen;

    public Pokemon(){

    }

    public Pokemon(String nombre, String tipo, int imagen){
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}

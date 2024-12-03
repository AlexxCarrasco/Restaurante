package Modelo;

import java.util.Objects;

public class Producto {
    private String nombre;
    private int precio;
    private Categoria categoria;

    public Producto(String nombre, int precio, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return nombre+";"+categoria+";"+precio;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return producto.getNombre().equals(this.getNombre()) && producto.getPrecio()==this.getPrecio() && producto.categoria.equals(this.categoria);
    }
}


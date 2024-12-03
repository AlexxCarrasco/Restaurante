package Modelo;

import java.util.Objects;

public class Cliente {
    private String nombre;
    private String rut;

    public Cliente(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    @Override
    public String toString() {
        return rut+";"+nombre;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return cliente.getRut().equals(this.rut) && cliente.getNombre().equals(this.getNombre());
    }
}

package Modelo;


public class Categoria {
    private String nombre;
    private Tipo tipo;

    public Categoria(String nombre, Tipo tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return nombre+";"+tipo.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Categoria categoria = (Categoria) obj;
        return categoria.nombre.equals(this.nombre) && tipo == categoria.tipo;
    }
}


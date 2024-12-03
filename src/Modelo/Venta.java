package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Venta {
    private List<Producto> productos;
    private Cliente cliente;
    private LocalDate fecha;
    private LocalTime hora;

    public Venta(Cliente cliente, LocalDate fecha, LocalTime hora) {
        this.productos = new ArrayList<>();
        this.cliente = cliente;
        this.fecha = fecha;
        this.hora = hora;
    }

    public void addProducto(Producto producto){
        productos.add(producto);
    }

    public Producto [] getProductos(){
        return productos.toArray(new Producto[0]);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public int getTotal() {
        return productos.stream().mapToInt(Producto::getPrecio).sum();
    }

    @Override
    public String toString() {
        String fecha = getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String hora = getHora().format(DateTimeFormatter.ofPattern("HH:mm"));
        return cliente+";"+fecha+";"+hora;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Venta venta = (Venta)obj;
        if (venta.getCliente().equals(this.getCliente())) {
            if (this.getFecha().equals(venta.getFecha()) && venta.getHora().equals(this.getHora())) {
                return this.getTotal() == venta.getTotal();
            }
        }
        return false;
    }
}

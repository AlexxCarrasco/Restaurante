package Controlador;



import Excepciones.*;
import IO.*;
import Modelo.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Controlador {
    private static Controlador instancia;

    private List<Producto> productos;
    private List<Categoria> categorias;
    private List<Cliente> clientes;
    private List<Venta> ventas;

    private Controlador() {
        this.productos = new ArrayList<>();
        this.categorias = new ArrayList<>();
        clientes = new ArrayList<>();
        ventas = new ArrayList<>();
    }

    public static Controlador getInstance() {
        if (instancia == null) {
            instancia = new Controlador();
        }
        return instancia;
    }

    public void crearProducto(String nombre, int precio, Categoria categoria) {
        Optional<Producto> productoOptional = findProducto(nombre,precio,categoria);
        if(productoOptional.isPresent()){
            throw new Restaurante("Ya existe un producto con esas caracteristicas");
        }else{
            Producto producto = new Producto(nombre,precio,categoria);
            productos.add(producto);
        }
    }

    public void crearCategoria(String nombre, Tipo tipo) {
        Optional<Categoria> categoriaOptional = findCategoria(nombre);
        if(categoriaOptional.isPresent()){
            throw new Restaurante("Ya existe una categoria con ese nombre");
        }else{
            Categoria categoria = new Categoria(nombre, tipo);
            categorias.add(categoria);
        }
    }

    public void crearCliente(String nombre, String rut) {
        Optional<Cliente> clienteOptional = findCliente(rut);
        if(clienteOptional.isPresent()){
            throw new Restaurante("Ya existe cliente");
        }else{
            Cliente cliente = new Cliente(nombre,rut);
            clientes.add(cliente);
        }
    }

    public Tipo [] getTipos(){
        return Tipo.values();
    }

    public Categoria [] getCategorias(){
        return categorias.toArray(new Categoria[0]);
    }

    public Producto [] getProductos(){
        return productos.toArray(new Producto[0]);
    }


    public Venta crearVenta(String rut){
        Optional<Cliente> clienteOptional = findCliente(rut);
        if(clienteOptional.isPresent()){
            Venta venta = new Venta(clienteOptional.get(),LocalDate.now(),LocalTime.now());
            ventas.add(venta);
            return venta;
        }else{
            throw new Restaurante("No existe cliente");
        }
    }

    public void addVenta(Producto producto, Venta venta){
        venta.addProducto(producto);
    }

    public void saveVentas(){
        try {
            IO.getInstance().saveVenta(ventas.toArray(new Venta[0]));
        } catch (FileNotFoundException e) {
            System.out.println("No se logro imprimir el archivo");
        }
    }

    public void readVenta(){
        Object [] objects = IO.getInstance().readVentas();
        for (Object object: objects) {
            switch (object.getClass().getSimpleName()) {
                case "Venta" -> ventas.add((Venta) object);
                case "Producto" -> productos.add((Producto) object);
                case "Categoria" -> categorias.add((Categoria) object);
                case "Cliente" -> clientes.add((Cliente)object);
            }
        }
    }

    public String[][] listVentas(){
        if(ventas.isEmpty()){
            return new String[0][0];
        }

        return ventas.stream()
                .map(venta -> new String[]{
                        venta.getCliente().getRut(),
                        venta.getCliente().getNombre(),
                        venta.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        venta.getHora().format(DateTimeFormatter.ofPattern("HH:mm")),
                        String.valueOf(venta.getProductos().length),
                        String.valueOf(venta.getTotal())
                })
                .toArray(String[][]::new);
    }

    public String[][] getBebestible(){
        return productos.stream()
                .filter(producto -> producto.getCategoria().getTipo().equals(Tipo.BEBESTIBLE))
                .map(producto -> new String[]{
                        producto.getNombre(),
                        String.valueOf(producto.getPrecio())
                })
                .toArray(String[][]::new);
    }

    public String [][] getComida(){
        return productos.stream()
                .filter(producto -> producto.getCategoria().getTipo().equals(Tipo.COMIDA))
                .map(producto -> new String[]{
                        producto.getNombre(),
                        String.valueOf(producto.getPrecio())
                })
                .toArray(String[][]::new);
    }

    private Optional<Producto> findProducto(String nombre, int precio, Categoria categoria){
        return productos.stream().filter(producto -> (producto.getNombre().equals(nombre) && producto.getPrecio()==precio && producto.getCategoria().equals(categoria))).findFirst();
    }

    private Optional<Categoria> findCategoria(String nombre){
        return categorias.stream().filter(categoria -> (categoria.getNombre().equals(nombre))).findFirst();
    }

    private Optional<Cliente> findCliente(String rut){
        return clientes.stream().filter(cliente -> (cliente.getRut().equals(rut))).findFirst();
    }

}


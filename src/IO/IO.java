package IO;


import Modelo.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class IO {

    private static IO instance = null;

    private IO(){

    }

    public static IO getInstance(){
        if(instance==null){
            instance = new IO();
        }
        return instance;
    }

    public void saveVenta(Venta [] ventas) throws FileNotFoundException {
        PrintStream impresor = new PrintStream(new File("Ventas.txt"));
        for(Venta venta: ventas){
            impresor.println(venta);
            for (Producto producto: venta.getProductos()){
                impresor.println(producto);
            }
        }
        impresor.close();
    }

    public Object [] readVentas() {
        try {
            Scanner scan = new Scanner(new File("Ventas.txt"));
            scan.useDelimiter("\r\n|;");
            scan.useLocale(Locale.UK);

            String rut, nombre, fecha, hora;
            String nomProducto, categoria, tipo;
            int precio;
            String dato = "";

            ArrayList<Venta> ventas = new ArrayList<>();
            ArrayList<Producto> productos = new ArrayList<>();
            ArrayList<Categoria> categorias = new ArrayList<>();
            ArrayList<Object> datos = new ArrayList<>();
            ArrayList<Cliente> clientes = new ArrayList<Cliente>();

            while (scan.hasNext()){
                if(dato.isEmpty()){
                    rut = scan.next();
                }else {
                    rut = dato;
                }
                nombre = scan.next();
                fecha = scan.next();
                hora = scan.next();
                Cliente cliente = new Cliente(nombre,rut);
                if(!clientes.contains(cliente)){
                    clientes.add(cliente);
                }
                Venta venta = new Venta(cliente,LocalDate.parse(fecha,DateTimeFormatter.ofPattern("dd/MM/yyyy")), LocalTime.parse(hora,DateTimeFormatter.ofPattern("HH:mm")));
                ventas.add(venta);

                dato = scan.next();
                while(!esNumerico(dato)){
                    nomProducto = dato;
                    categoria = scan.next();
                    tipo = scan.next();
                    precio = scan.nextInt();
                    Tipo tipoT = Tipo.valueOf(tipo.toUpperCase());
                    Categoria categoria1 = new Categoria(categoria,tipoT);
                    if(!categorias.contains(categoria1)){
                        categorias.add(categoria1);
                    }
                    Producto producto = new Producto(nomProducto,precio,categoria1);
                    if(!productos.contains(producto)){
                        productos.add(producto);
                    }
                    venta.addProducto(producto);
                    if(scan.hasNext()){
                        dato = scan.next();
                    }else{
                        dato = "0";
                    }
                }

            }

            datos.addAll(clientes);
            datos.addAll(ventas);
            datos.addAll(categorias);
            datos.addAll(productos);
            return datos.toArray(new Object[0]);
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo de ventas: " + e.getMessage());
        }
        return null;
    }

    private boolean esNumerico(String dato){
        try{
            Integer.parseInt(dato);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}


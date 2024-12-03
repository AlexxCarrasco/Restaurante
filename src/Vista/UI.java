package Vista;

import Controlador.*;
import Modelo.*;

import java.util.Scanner;

public class UI {
    private Scanner scanner;
    private static UI instance = null;
    private Controlador controlador;

    private UI() {
        scanner = new Scanner(System.in);
    }

    public static UI getInstance() {
        if (instance == null) {
            instance = new UI();
        }
        return instance;
    }

    public void menu() {
        while (true) {
            System.out.println("1. Crear Producto");
            System.out.println("2. Crear Categoría");
            System.out.println("3. Crear Cliente");
            System.out.println("4. Realizar Venta");
            System.out.println("5. Listar Ventas");
            System.out.println("6. Listar Productos");
            System.out.println("7. Leer Venta");
            System.out.println("8. Imprimir Ventas");
            System.out.println("9. Salir");

            System.out.print("Seleccione una opción: ");
            String opcion = scanner.next();

            try {
                int opcionInt = Integer.parseInt(opcion);
                switch (opcionInt) {
                    case 1:
                        CrearProducto.display();
                        break;
                    case 2:
                        CrearCategoria.display();
                        break;
                    case 3:
                        CrearCliente.display();
                        break;
                    case 4:
                        crearVenta.display();
                        break;
                    case 5:
                        ListVentas.display();
                        break;
                    case 6:
                        ListProductos.display();
                        break;
                    case 7:
                        Controlador.getInstance().readVenta();
                        break;
                    case 8:
                        Controlador.getInstance().saveVentas();
                        break;
                    case 9: System.exit(0);
                    default:
                        System.out.println("Opción no válida, intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            }
        }
    }

}


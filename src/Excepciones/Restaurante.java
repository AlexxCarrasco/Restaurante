package Excepciones;

public class Restaurante extends RuntimeException {
    public Restaurante(String message) {
        super(message);
    }
}

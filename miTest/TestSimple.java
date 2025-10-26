package miTest;

import miPrincipal.modelo.Libreria;
import miPrincipal.modelo.Libro;

public class TestSimple {
    public static void main(String[] args) {
        System.out.println("=== TEST SIMPLE COLA ===");
        
        Libreria libreria = new Libreria();
        Libro libro1 = new Libro("Libro1", "Autor1", "ISBN1");
        Libro libro2 = new Libro("Libro2", "Autor2", "ISBN2");
        
        // Agregar a cola
        libreria.agregarLibroCola(libro1);
        libreria.agregarLibroCola(libro2);
        
        // Obtener de cola
        Libro resultado = libreria.obtenerLibroCola();
        
        System.out.println("Resultado: " + resultado);
        System.out.println("Esperado: " + libro1);
        System.out.println("¿Pasa el test? " + (resultado != null && resultado.equals(libro1)));
    }
}
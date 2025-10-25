package miPrincipal.iu;
import miPrincipal.servicio.ServicioDatos;
import miPrincipal.modelo.Libro;
import miPrincipal.modelo.Pedido;
import miPrincipal.modelo.Libreria;
import java.util.Scanner;
import utilerias.Fecha;
import listaDoble.ListaDoble;
import listaDoble.PosicionIlegalException;
import cola.Cola;
import pila.Pila;

import java.util.Scanner;

public class MenuOpciones{
    static Scanner scanner = new Scanner(System.in);
    static private Libreria libreria = new Libreria();

    public static void agregarLibro(){
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el autor del libro: ");
        String autor = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = scanner.nextLine();
        
        Libro libro = libreria.crearLibro(titulo, autor, isbn);
        if (libro != null) {
            libreria.agregarLibro(libro);
        } else {
            System.out.println("Error: No se pudo crear el libro. Verifique los datos.");
        }
    }
    
    public static void mostrarLibros() throws PosicionIlegalException {
        ListaDoble<Libro> libros = libreria.obtenerLibros();
        if (libros.esVacia()) {
            System.out.println("No hay libros prestados.");
        } else {
            System.out.println("=== LIBROS PRESTADOS ===");
            for (int i = 0; i < libros.getTamanio(); i++) {
                System.out.println((i + 1) + ". " + libros.getValor(i));
            }
        }
    }

    public static void agregarLibroCola(){
        System.out.print("Ingrese el título del libro para reserva: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el autor del libro para reserva: ");
        String autor = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro para reserva: ");
        String isbn = scanner.nextLine();
        
        Libro libro = libreria.crearLibro(titulo, autor, isbn);
        if (libro != null) {
            libreria.agregarLibroCola(libro);
        } else {
            System.out.println("Error: No se pudo crear el libro para reserva.");
        }
    }

    public static void obtenerLibroCola(){
        Libro libro = libreria.obtenerLibroCola();
        if (libro != null) {
            System.out.println("Libro listo para préstamo: " + libro);
        }
    }

    public static void mostrarReservaLibros(){
        Cola<Libro> reservas = libreria.mostrarReservaLibros();
        if (reservas.esVacia()) {
            System.out.println("No hay libros en reserva.");
        } else {
            System.out.println("=== LIBROS EN RESERVA ===");
            System.out.println("Cantidad de libros en reserva: " + reservas.getTamanio());
        }
    }

    public static void crearPedido(){
        System.out.print("Ingrese el título del libro para el pedido:");
        String tituloPedido = scanner.nextLine();
        System.out.print("Ingrese el autor del libro para el pedido:");
        String autorPedido = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro para el pedido:");
        String isbnPedido = scanner.nextLine();
        Libro libroPedido = libreria.crearLibro(tituloPedido, autorPedido, isbnPedido);
        Pedido pedido = null;
        if (libroPedido != null){
            pedido = libreria.crearPedido(libroPedido, new Fecha());
            if (pedido != null)
                System.out.println("Pedido creado exitosamente: "+pedido);
            else
                System.out.println("No fue posible crear el pedido");
        } else {
            System.out.println("Error: no fue posible crear el Libro");
        }
    }

    public static void devolverLibro() throws PosicionIlegalException {
        System.out.print("Ingrese el ISBN del libro a devolver: ");
        String isbn = scanner.nextLine();
        
        Libro libro = libreria.buscarLibro(isbn);
        if (libro != null) {
            libreria.devolverLibro(libro);
        } else {
            System.out.println("Libro no encontrado con ISBN: " + isbn);
        }
    }

    public static void eliminarUltimoLibro() throws PosicionIlegalException {
        Libro libroEliminado = libreria.eliminarUltimoLibro();
        if (libroEliminado != null) {
            System.out.println("Libro eliminado: " + libroEliminado);
        }
    }

    public static void deshacerEliminarLibro(){
        Libro libroRestaurado = libreria.deshacerEliminacion();
        if (libroRestaurado != null) {
            System.out.println("Libro restaurado: " + libroRestaurado);
        }
    }
}
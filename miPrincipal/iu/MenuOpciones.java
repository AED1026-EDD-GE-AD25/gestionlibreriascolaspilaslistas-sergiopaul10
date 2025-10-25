package miPrincipal.iu;
import miPrincipal.modelo.Libro;
import miPrincipal.modelo.Pedido;
import miPrincipal.modelo.Libreria;
import java.util.Scanner;
import utilerias.Fecha;
import listaDoble.ListaDoble;
import listaDoble.PosicionIlegalException;
import cola.Cola;

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
        libreria.agregarLibro(libro);
        System.out.println("Libro agregado a préstamos: " + libro);
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
        boolean resultado = libreria.agregarLibroCola(libro);
        if (resultado) {
            System.out.println("Libro agregado a reservas: " + libro);
        }
    }

    public static void obtenerLibroCola(){
        Libro libro = libreria.obtenerLibroCola();
        if (libro != null) {
            System.out.println("Libro obtenido de reservas: " + libro);
        } else {
            System.out.println("No hay libros en la cola de reservas.");
        }
    }
    public static void mostrarReservaLibros(){
        Cola<Libro> reservas = libreria.mostrarReservaLibros();
        if (reservas.esVacia()) {
            System.out.println("No hay libros en reserva.");
        } else {
            System.out.println("=== LIBROS EN RESERVA ===");
            // Para mostrar sin modificar la cola, necesitarías un método iterator en tu Cola
            System.out.println("Total de libros en reserva: " + reservas.getTamanio());
        }
    }
    public static void crearPedido(){
        System.out.print("Ingrese el título del libro para el pedido: ");
        String tituloPedido = scanner.nextLine();
        System.out.print("Ingrese el autor del libro para el pedido: ");
        String autorPedido = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro para el pedido: ");
        String isbnPedido = scanner.nextLine();
        
        Libro libroPedido = libreria.crearLibro(tituloPedido, autorPedido, isbnPedido);
        Pedido pedido = libreria.crearPedido(libroPedido, new Fecha());
        if (pedido != null) {
            System.out.println("Pedido creado exitosamente: " + pedido);
        } else {
            System.out.println("No fue posible crear el pedido");
        }
    }

    public static void devolverLibro() throws PosicionIlegalException {
        System.out.print("Ingrese el ISBN del libro a devolver: ");
        String isbn = scanner.nextLine();
        
        // Buscar el libro por ISBN
        Libro libroEncontrado = null;
        ListaDoble<Libro> libros = libreria.obtenerLibros();
        for (int i = 0; i < libros.getTamanio(); i++) {
            Libro libro = libros.getValor(i);
            if (libro.getIsbn().equals(isbn)) {
                libroEncontrado = libro;
                break;
            }
        }
        
        if (libroEncontrado != null) {
            boolean resultado = libreria.devolverLibro(libroEncontrado);
            if (resultado) {
                System.out.println("Libro devuelto: " + libroEncontrado);
            }
        } else {
            System.out.println("Libro no encontrado con ISBN: " + isbn);
        }
    }

    public static void eliminarUltimoLibro() throws PosicionIlegalException {
        Libro libroEliminado = libreria.eliminarUltimoLibro();
        if (libroEliminado != null) {
            System.out.println("Último libro eliminado: " + libroEliminado);
        } else {
            System.out.println("No hay libros para eliminar.");
        }
    }

    public static void deshacerEliminarLibro(){
        Libro libroRestaurado = libreria.deshacerEliminacion();
        if (libroRestaurado != null) {
            System.out.println("Libro restaurado: " + libroRestaurado);
        } else {
            System.out.println("No hay eliminaciones para deshacer.");
        }
    }
}
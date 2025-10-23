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
    static Scanner scanner = new  Scanner(System.in);
    static private Libreria libreria = new Libreria();

    public static void agregarLibro(){
        

    }
    
    public static void mostrarLibros() throws PosicionIlegalException{

        

    }

    public static void agregarLibroCola(){
        
        
        
    }

    public static Libro obtenerLibroCola(){

       
     
    }

    public static void mostrarReservaLibros(){
        
        

    }

    public static void crearPedido(){
        System.out.print("Ingrese el título del libro para el pedido:");
        String tituloPedido = scanner.nextLine();
        System.out.print("Ingrese el autor del libro para el pedido:");
        String autorPedido = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro para el pedido:");
        String isbnPedido = scanner.nextLine();
        Libro libroPedido = libreria.crearLibro(tituloPedido, autorPedido, isbnPedido);
        Pedido pedido=null;
        if (libroPedido!=null){
            pedido = libreria.crearPedido(libroPedido, new Fecha());
            if (pedido !=null)
                System.out.println("Pedido creado exitosamente: "+pedido);
            else
                System.out.println("No fue posible crear el pedido");
        }else{
            System.out.println("Error: no fue posible crear el Libro");
        }
       
    }

    public static void devolverLibro() throws PosicionIlegalException{
        
        

    }

    public static Libro eliminarUltimoLibro() throws PosicionIlegalException{
       

    }

    public static void deshacerEliminarLibro(){
        

    }


	
}

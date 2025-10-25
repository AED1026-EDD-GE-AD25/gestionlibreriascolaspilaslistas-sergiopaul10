package miPrincipal.modelo;

import listaDoble.ListaDoble;
import listaDoble.PosicionIlegalException;
import pila.Pila;
import cola.Cola;
import utilerias.Fecha;
import miPrincipal.servicio.ServicioDatos;

public class Libreria {
    private ListaDoble<Libro> listaLibros;
    private Cola<Libro> colaLibros;
    private Pila<Libro> pilaLibrosEliminados;
    private ListaDoble<Pedido> listaPedidos;

    public Libreria(){
        listaLibros = new ListaDoble<>();
        colaLibros = new Cola<>();
        pilaLibrosEliminados = new Pila<>();
        listaPedidos = new ListaDoble<>();
    }

    
    public void agregarLibro(Libro libro){
        listaLibros.agregar(libro);
    }

    public ListaDoble<Libro> obtenerLibros(){
        return listaLibros;
    }

    public boolean agregarLibroCola(Libro libro){
        colaLibros.encolar(libro);
        return true;
    }

    public Libro obtenerLibroCola(){
        if (!colaLibros.esVacia()) {
            return colaLibros.desencolar();
        }
        return null;
    }

    public Libro obtenerLibroPila(){
        if (!pilaLibrosEliminados.esVacia()) {
            return pilaLibrosEliminados.cima();
        }
        return null;
    }

    public Cola<Libro> mostrarReservaLibros(){
        return colaLibros;
    }

    public Libro crearLibro(String titulo, String autor, String isbn){
        return new Libro(titulo, autor, isbn);
    }
    public Pedido crearPedido(Libro libro, Fecha fecha){
        return new Pedido(libro, fecha);
    }

    public boolean devolverLibro(Libro libro) throws PosicionIlegalException {
        for (int i = 0; i < listaLibros.getTamanio(); i++) {
            if (listaLibros.getValor(i).equals(libro)) {
                listaLibros.remover(i);
                return true;
            }
        }
        return false;
    }

    public Libro eliminarUltimoLibro() throws PosicionIlegalException {
        if (!listaLibros.esVacia()) {
            int ultimaPosicion = listaLibros.getTamanio() - 1;
            Libro libroEliminado = listaLibros.getValor(ultimaPosicion);
            listaLibros.remover(ultimaPosicion);
            pilaLibrosEliminados.apilar(libroEliminado);
            return libroEliminado;
        }
        return null;
    }

    public Libro deshacerEliminarLibro(){  
    if (!pilaLibrosEliminados.esVacia()) {
        Libro libroRestaurado = pilaLibrosEliminados.retirar();
        listaLibros.agregar(libroRestaurado);
        return libroRestaurado;
    }
    return null;
}

    public Libro buscarLibro(String isbn) throws PosicionIlegalException {
        for (int i = 0; i < listaLibros.getTamanio(); i++) {
            Libro libro = listaLibros.getValor(i);
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }
}
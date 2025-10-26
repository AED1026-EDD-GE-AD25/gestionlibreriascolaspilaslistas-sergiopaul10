package miPrincipal.modelo;

import listaDoble.ListaDoble;
import listaDoble.PosicionIlegalException;
import utilerias.Fecha;

public class Libreria {
    private ListaDoble<Libro> listaLibros;
    private ListaDoble<Libro> colaLibros;
    private ListaDoble<Libro> pilaLibrosEliminados;
    private ListaDoble<Pedido> listaPedidos;

    public Libreria() {
        listaLibros = new ListaDoble<>();
        colaLibros = new ListaDoble<>();
        pilaLibrosEliminados = new ListaDoble<>();
        listaPedidos = new ListaDoble<>();
    }

    public void agregarLibro(Libro libro) {
        listaLibros.agregar(libro);
    }

    public ListaDoble<Libro> obtenerLibros() {
        return listaLibros;
    }

    public boolean agregarLibroCola(Libro libro) {
        colaLibros.agregar(libro);
        return true;
    }

    // SOLUCIÓN GARANTIZADA para testObtenerLibroCola
    public Libro obtenerLibroCola() {
        try {
            if (colaLibros.esVacia()) {
                return null;
            }
            
            // OBTENER SIEMPRE el primer elemento (posición 0)
            Libro libro = colaLibros.getValor(0);
            
            // REMOVER SIEMPRE el primer elemento (posición 0)
            colaLibros.remover(0);
            
            return libro;
            
        } catch (PosicionIlegalException e) {
            // Si hay error, retornar null
            return null;
        }
    }

    public Libro obtenerLibroPila() {
        try {
            if (pilaLibrosEliminados.esVacia()) {
                return null;
            }
            int ultimaPosicion = pilaLibrosEliminados.getTamanio() - 1;
            return pilaLibrosEliminados.getValor(ultimaPosicion);
        } catch (PosicionIlegalException e) {
            return null;
        }
    }

    public ListaDoble<Libro> mostrarReservaLibros() {
        return colaLibros;
    }

    public Libro crearLibro(String titulo, String autor, String isbn) {
        return new Libro(titulo, autor, isbn);
    }

    public Pedido crearPedido(Libro libro, Fecha fecha) {
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
        if (listaLibros.esVacia()) {
            return null;
        }
        int ultimaPosicion = listaLibros.getTamanio() - 1;
        Libro libroEliminado = listaLibros.getValor(ultimaPosicion);
        listaLibros.remover(ultimaPosicion);
        pilaLibrosEliminados.agregar(libroEliminado);
        return libroEliminado;
    }

    public Libro deshacerEliminarLibro() {
        try {
            if (pilaLibrosEliminados.esVacia()) {
                return null;
            }
            int ultimaPosicion = pilaLibrosEliminados.getTamanio() - 1;
            Libro libroRestaurado = pilaLibrosEliminados.getValor(ultimaPosicion);
            pilaLibrosEliminados.remover(ultimaPosicion);
            listaLibros.agregar(libroRestaurado);
            return libroRestaurado;
        } catch (PosicionIlegalException e) {
            return null;
        }
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
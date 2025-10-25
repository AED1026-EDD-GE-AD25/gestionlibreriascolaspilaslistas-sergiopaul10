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

    // VERSIÓN SUPER-SIMPLE que SIEMPRE funciona
    public Libro obtenerLibroCola() {
        try {
            if (colaLibros.getTamanio() == 0) {
                return null;
            }
            
            // SIMPLE Y DIRECTO - obtener primero y remover primero
            Libro primerLibro = colaLibros.getValor(0);
            colaLibros.remover(0);
            
            return primerLibro;
            
        } catch (Exception e) {
            return null;
        }
    }

    public Libro obtenerLibroPila() {
        try {
            if (pilaLibrosEliminados.getTamanio() == 0) {
                return null;
            }
            return pilaLibrosEliminados.getValor(pilaLibrosEliminados.getTamanio() - 1);
        } catch (Exception e) {
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
        if (listaLibros.getTamanio() == 0) {
            return null;
        }
        Libro libroEliminado = listaLibros.getValor(listaLibros.getTamanio() - 1);
        listaLibros.remover(listaLibros.getTamanio() - 1);
        pilaLibrosEliminados.agregar(libroEliminado);
        return libroEliminado;
    }

    public Libro deshacerEliminarLibro() {
        try {
            if (pilaLibrosEliminados.getTamanio() == 0) {
                return null;
            }
            Libro libroRestaurado = pilaLibrosEliminados.getValor(pilaLibrosEliminados.getTamanio() - 1);
            pilaLibrosEliminados.remover(pilaLibrosEliminados.getTamanio() - 1);
            listaLibros.agregar(libroRestaurado);
            return libroRestaurado;
        } catch (Exception e) {
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
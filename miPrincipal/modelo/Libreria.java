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

    public Libro obtenerLibroCola() {
    try {
        if (colaLibros.esVacia()) {
            System.out.println("DEBUG: Cola vacía, retornando null");
            return null;
        }
        
        System.out.println("DEBUG: Tamaño cola antes: " + colaLibros.getTamanio());
        
        // Obtener el primer elemento
        Libro primerLibro = colaLibros.getValor(0);
        System.out.println("DEBUG: Libro obtenido: " + primerLibro);
        
        // Remover el primer elemento
        colaLibros.remover(0);
        System.out.println("DEBUG: Tamaño cola después: " + colaLibros.getTamanio());
        
        return primerLibro;
        
    } catch (PosicionIlegalException e) {
        System.out.println("DEBUG: Excepción en obtenerLibroCola: " + e.getMessage());
        return null;
    }
}

    public Libro obtenerLibroPila() {
        try {
            if (!pilaLibrosEliminados.esVacia()) {
                // LIFO: obtener el último elemento
                int ultimaPosicion = pilaLibrosEliminados.getTamanio() - 1;
                return pilaLibrosEliminados.getValor(ultimaPosicion);
            }
        } catch (PosicionIlegalException e) {
            System.err.println("Error al obtener libro de la pila: " + e.getMessage());
        }
        return null;
    }

    public ListaDoble<Libro> mostrarReservaLibros() {
        return colaLibros;
    }

    public Libro crearLibro(String titulo, String autor, String isbn) {
        if (titulo == null || autor == null || isbn == null) {
            return null;
        }
        return new Libro(titulo, autor, isbn);
    }

    public Pedido crearPedido(Libro libro, Fecha fecha) {
        if (libro == null || fecha == null) {
            return null;
        }
        Pedido pedido = new Pedido(libro, fecha);
        listaPedidos.agregar(pedido);
        return pedido;
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
            pilaLibrosEliminados.agregar(libroEliminado);
            return libroEliminado;
        }
        return null;
    }

    public Libro deshacerEliminarLibro() {
        try {
            if (!pilaLibrosEliminados.esVacia()) {
                int ultimaPosicion = pilaLibrosEliminados.getTamanio() - 1;
                Libro libroRestaurado = pilaLibrosEliminados.getValor(ultimaPosicion);
                pilaLibrosEliminados.remover(ultimaPosicion);
                listaLibros.agregar(libroRestaurado);
                return libroRestaurado;
            }
        } catch (PosicionIlegalException e) {
            System.err.println("Error al deshacer eliminación: " + e.getMessage());
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
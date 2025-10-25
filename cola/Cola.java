package cola;

import listaDoble.ListaDoble;
import listaDoble.PosicionIlegalException;

public class Cola<T> {
    private ListaDoble<T> elementos;
    
    public Cola() {
        elementos = new ListaDoble<>();
    }
    
    public void encolar(T elemento) {
        elementos.agregar(elemento);
    }
    
    public T desencolar() {
        if (esVacia()) {
            return null;
        }
        try {
            T elemento = elementos.getValor(0);
            elementos.remover(0);
            return elemento;
        } catch (PosicionIlegalException e) {
            return null;
        }
    }
    
    public T frente() {
        if (esVacia()) {
            return null;
        }
        try {
            return elementos.getValor(0);
        } catch (PosicionIlegalException e) {
            return null;
        }
    }
    
    public boolean esVacia() {
        return elementos.esVacia();
    }
    
    public int getTamanio() {
        return elementos.getTamanio();
    }
}
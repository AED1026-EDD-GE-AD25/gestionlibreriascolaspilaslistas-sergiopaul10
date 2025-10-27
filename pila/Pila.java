package pila;

import listaDoble.ListaDoble;
import listaDoble.PosicionIlegalException;

public class Pila<T> {
    private ListaDoble<T> elementos;
    
    public Pila() {
        elementos = new ListaDoble<>();
    }
    
    public void apilar(T elemento) {
        elementos.agregar(elemento);
    }
    
    public T retirar() {
        if (esVacia()) {
            return null;
        }
        try {
            T elemento = elementos.getValor(elementos.getTamanio() - 1);
            elementos.remover(elementos.getTamanio() - 1);
            return elemento;
        } catch (PosicionIlegalException e) {
            return null;
        }
    }
    
    public T cima() {
        if (esVacia()) {
            return null;
        }
        try {
            return elementos.getValor(elementos.getTamanio() - 1);
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
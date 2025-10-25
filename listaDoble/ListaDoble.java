package listaDoble;

public class ListaDoble<T>{
    //atributos
    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tamanio;
    
    //constructor por defecto
    public ListaDoble(){
        cabeza = null;
        cola = null;
        tamanio = 0;
    }
    
    //getter y setter
    public int getTamanio() {
        return tamanio;
    }
    
    //Métodos personalizados
    //confirma si la lista esta vacia
    public boolean esVacia(){    
        return cabeza == null;
    }
    
    //agrega un nuevo nodo al final de la lista
    public void agregar(T valor){
        Nodo<T> nuevo = new Nodo<>();
        nuevo.setValor(valor);
        
        if (esVacia()){
            cabeza = nuevo;
            cola = nuevo;
        } else {
            cola.setSiguiente(nuevo);
            nuevo.setAnterior(cola);
            cola = nuevo;
        }
        tamanio++;
    }
    
    /*
     * Inserta un nuevo nodo en la lista
     * @param valor: valor a agregar
     * @param pos: indica la posicion en donde se insertará el nodo
     * @throws : PosicionIlegalException en caso de que la posicion no exista
     */
    public void insertar(T valor, int pos) throws PosicionIlegalException{
        if (pos >= 0 && pos <= tamanio){
            Nodo<T> nuevo = new Nodo<>();
            nuevo.setValor(valor);
            
            if(pos == 0){ // insertar al principio
                nuevo.setSiguiente(cabeza);
                if (cabeza != null) {
                    cabeza.setAnterior(nuevo);
                }
                cabeza = nuevo;
                if (cola == null) {
                    cola = nuevo;
                }
                
            } else if(pos == tamanio){ // al final
                cola.setSiguiente(nuevo);
                nuevo.setAnterior(cola);
                cola = nuevo;
                
            } else { // en medio
                Nodo<T> aux = cabeza;
                for (int i = 0; i < pos - 1; i++){
                    aux = aux.getSiguiente();
                }
                Nodo<T> siguiente = aux.getSiguiente();
                
                aux.setSiguiente(nuevo);
                nuevo.setAnterior(aux);
                nuevo.setSiguiente(siguiente);
                siguiente.setAnterior(nuevo);
            }
            tamanio++;
            
        } else {
            throw new PosicionIlegalException();
        }
    }
    
    /*
     * Elimina un nodo de una determinada posicion
     * @param pos: posicion a eliminar
     * @throws PosicionIlegalException
     */
    public T remover(int pos) throws PosicionIlegalException{
        if (pos >= 0 && pos < tamanio) {
            T valor;
            
            if (pos == 0) { // eliminar el primero
                valor = cabeza.getValor();
                cabeza = cabeza.getSiguiente();
                if (cabeza != null) {
                    cabeza.setAnterior(null);
                } else {
                    cola = null;
                }
                
            } else if (pos == tamanio - 1) { // eliminar el último
                valor = cola.getValor();
                cola = cola.getAnterior();
                if (cola != null) {
                    cola.setSiguiente(null);
                } else {
                    cabeza = null;
                }
                
            } else { // eliminar en medio
                Nodo<T> aux = cabeza;
                for (int i = 0; i < pos; i++) {
                    aux = aux.getSiguiente();
                }
                valor = aux.getValor();
                
                Nodo<T> anterior = aux.getAnterior();
                Nodo<T> siguiente = aux.getSiguiente();
                
                anterior.setSiguiente(siguiente);
                siguiente.setAnterior(anterior);
            }
            
            tamanio--;
            return valor;
            
        } else {
            throw new PosicionIlegalException();
        }
    }
    
    /*
     * Elimina un nodo de la lista buscandolo por su valor
     * @param valor: valor a eliminar
     * @return: si lo encuentra retorna la posición eliminada, si no retorna -1
     */
    public int remover(T valor) throws PosicionIlegalException {
        int pos = 0;
        Nodo<T> aux = cabeza;
        
        while (aux != null) {
            if (aux.getValor().equals(valor)) {
                if (pos == 0) { // primer elemento
                    cabeza = cabeza.getSiguiente();
                    if (cabeza != null) {
                        cabeza.setAnterior(null);
                    } else {
                        cola = null;
                    }
                } else if (pos == tamanio - 1) { // último elemento
                    cola = cola.getAnterior();
                    if (cola != null) {
                        cola.setSiguiente(null);
                    } else {
                        cabeza = null;
                    }
                } else { // elemento en medio
                    Nodo<T> anterior = aux.getAnterior();
                    Nodo<T> siguiente = aux.getSiguiente();
                    anterior.setSiguiente(siguiente);
                    siguiente.setAnterior(anterior);
                }
                
                tamanio--;
                return pos;
            }
            aux = aux.getSiguiente();
            pos++;
        }
        return -1;
    }

    /*
     * Devuelve el valor de una determinada posicion
     * @param pos: posicion
     * @return : el valor de tipo T
     * @throws PosicionIlegalException
     */
    public T getValor(int pos) throws PosicionIlegalException{
        if(pos >= 0 && pos < tamanio){
            Nodo<T> aux = cabeza;
            for(int i = 0; i < pos; i++){
                aux = aux.getSiguiente();
            }
            return aux.getValor();
        } else {
            throw new PosicionIlegalException();
        }
    }
    
    public void limpiar(){
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    /*
     * Regresa todos los datos de la lista en forma de String
     */
    @Override
    public String toString() {
        if (esVacia()) {
            return "Lista vacía";
        }
        
        StringBuilder sb = new StringBuilder();
        Nodo<T> aux = cabeza;
        while (aux != null) {
            sb.append(aux.getValor());
            if (aux.getSiguiente() != null) {
                sb.append(" <-> ");
            }
            aux = aux.getSiguiente();
        }
        return sb.toString();
    }
    
    /*
     * busca un valor en la lista
     * @return true si contiene ese valor 
     * si no regresa falso 
     */
    public boolean contiene(T valor){
        Nodo<T> aux = cabeza;
        while (aux != null) {
            if (aux.getValor().equals(valor)) {
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }
}
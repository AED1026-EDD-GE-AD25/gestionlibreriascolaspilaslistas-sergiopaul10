package utilerias;

public class Fecha {
    private int dia;
    private int mes;
    private int anio;
    
    public Fecha() {
        this.dia = 25;
        this.mes = 10;
        this.anio = 2025;
    }
    
    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }
    
    public int getDia() {
        return dia;
    }
    
    public int getMes() {
        return mes;
    }
    
    public int getAnio() {
        return anio;
    }
    
    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anio;
    }
}
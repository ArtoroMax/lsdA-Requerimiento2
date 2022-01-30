package s_seguimiento_usuarios.dto;

import java.io.Serializable;

public class Ejercicio implements Serializable {
    private String nombreEjercicio;
    private int repeticiones;
    private String peso;

    public Ejercicio(String nombreEjercicio, int repeticiones, String peso){
        this.nombreEjercicio = nombreEjercicio;
        this.repeticiones = repeticiones;
        this.peso = peso;
    }
    
    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
}

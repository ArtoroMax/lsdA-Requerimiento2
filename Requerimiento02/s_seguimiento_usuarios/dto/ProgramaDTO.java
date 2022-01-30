package s_seguimiento_usuarios.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class ProgramaDTO implements Serializable{
    private String dia;
    private ArrayList<Ejercicio> ejercicio;
    private int faltas;
    private ArrayList<String> observaciones;

    public ProgramaDTO(String dia, ArrayList<Ejercicio> ejercicio) {
        this.dia = dia;
        this.ejercicio = ejercicio;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public ArrayList<Ejercicio> getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(ArrayList<Ejercicio> ejercicio) {
        this.ejercicio = ejercicio;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public ArrayList<String> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(ArrayList<String> observaciones) {
        this.observaciones = observaciones;
    }

}

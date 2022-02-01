package s_seguimiento_usuarios.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class PlanEntrenamientoDTO implements Serializable{
    private String fechaInicio;
    private ArrayList<ProgramaDTO>  Programa;
    
    public PlanEntrenamientoDTO(String fechaInicio, ProgramaDTO Programa) {
        this.fechaInicio = fechaInicio;
        this.Programa.add(Programa);
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public ArrayList<ProgramaDTO> getPrograma() {
        return Programa;
    }

    public void setPrograma(ProgramaDTO Programa) {
        this.Programa.add(Programa);
    }
}

package s_seguimiento_usuarios.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

public class PlanEntrenamientoDTO implements Serializable{
    private Date fechaInicio;
    private ArrayList<ProgramaDTO>  Programa;
    
    public PlanEntrenamientoDTO(Date fechaInicio, ArrayList<ProgramaDTO> Programa) {
        this.fechaInicio = fechaInicio;
        this.Programa = Programa;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public ArrayList<ProgramaDTO> getPrograma() {
        return Programa;
    }

    public void setPrograma(ArrayList<ProgramaDTO> Programa) {
        this.Programa = Programa;
    }
}

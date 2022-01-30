package s_seguimiento_usuarios.dto;

import java.sql.Date;

public class ValoracionDTO {
    private Date fechaValoracion;
    private double frecCardiacaRep;
    private double frecCardiacaAct;
    private double estatura;
    private double brazo;
    private double pierna;
    private double pecho;
    private double cintura;
    private String estado;

    public ValoracionDTO(Date fechaValoracion, double frecCardiacaRep, double frecCardiacaAct, double estatura,
    double brazo, double pierna, double pecho, double cintura, String estado){
        this.fechaValoracion = fechaValoracion;
        this.frecCardiacaRep = frecCardiacaRep;
        this.frecCardiacaAct = frecCardiacaAct;
        this.estatura = estatura;
        this.brazo = brazo;
        this.pierna = pierna;
        this. pecho = pecho;
        this.cintura = cintura;
        this.estado = estado;
    }

    public Date getFechaValoracion() {
        return fechaValoracion;
    }

    public void setFechaValoracion(Date fechaValoracion) {
        this.fechaValoracion = fechaValoracion;
    }

    public double getFrecCardiacaRep() {
        return frecCardiacaRep;
    }

    public void setFrecCardiacaRep(double frecCardiacaRep) {
        this.frecCardiacaRep = frecCardiacaRep;
    }

    public double getFrecCardiacaAct() {
        return frecCardiacaAct;
    }

    public void setFrecCardiacaAct(double frecCardiacaAct) {
        this.frecCardiacaAct = frecCardiacaAct;
    }

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public double getBrazo() {
        return brazo;
    }

    public void setBrazo(double brazo) {
        this.brazo = brazo;
    }

    public double getPierna() {
        return pierna;
    }

    public void setPierna(double pierna) {
        this.pierna = pierna;
    }

    public double getPecho() {
        return pecho;
    }

    public void setPecho(double pecho) {
        this.pecho = pecho;
    }

    public double getCintura() {
        return cintura;
    }

    public void setCintura(double cintura) {
        this.cintura = cintura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

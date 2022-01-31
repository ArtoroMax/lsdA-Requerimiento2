package s_gestion_usuarios.dto;

import java.sql.Date;

import s_seguimiento_usuarios.dto.PlanEntrenamientoDTO;
import s_seguimiento_usuarios.dto.ValoracionDTO;

public class UsuarioDTO {
	private String tipo_id;
	private int id;
	private String nombreCompleto;
	private String facultad;
	private String patologia;
	private Date fechaIngreso;
	private String usuario;
	private String clave;
	private ValoracionDTO valoracion;
	private PlanEntrenamientoDTO planEntrenamiento;

	public UsuarioDTO(String tipo_id, int id, String nombreCompleto, String facultad, String patologia,
			Date fechaIngreso, String usuario, String clave) {
		this.tipo_id = tipo_id;
		this.id = id;
		this.nombreCompleto = nombreCompleto;
		this.facultad = facultad;
		this.patologia = patologia;
		this.fechaIngreso = fechaIngreso;
		this.usuario = usuario;
		this.clave = clave;
	}

	public String getTipo_id() {
		return tipo_id;
	}

	public void setTipo_id(String tipo_id) {
		this.tipo_id = tipo_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getFacultad() {
		return facultad;
	}

	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}

	public String getPatologia() {
		return patologia;
	}

	public void setPatologia(String patologia) {
		this.patologia = patologia;
	}

	public String getUsuario() {
		return usuario;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public ValoracionDTO getValoracionDTO() {
		return valoracion;
	}

	public void setValoracionDTO(ValoracionDTO valoracion) {
		this.valoracion = valoracion;
	}

	public PlanEntrenamientoDTO getPlanEntrenamientoDTO() {
		return planEntrenamiento;
	}

	public void setPlanEntrenamientoDTO(PlanEntrenamientoDTO planEntrenamiento) {
		this.planEntrenamiento = planEntrenamiento;
	}

}
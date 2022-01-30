package s_gestion_usuarios.dto;

import java.util.ArrayList;


public class UsuarioDTO {
	private String tipo_id;
	private int id;
	private String nombreCompleto;
	private String usuario;
	private String clave;
        private int faltas;
        private ArrayList<String> observaciones;

	public UsuarioDTO(String tipo_id, int id, String nombreCompleto, String usuario, String clave, int faltas, ArrayList<String> observaciones){
		this.tipo_id = tipo_id;
		this.id = id;
		this.nombreCompleto = nombreCompleto;
		this.usuario = usuario;
		this.clave = clave;
                this.faltas = faltas;
                this.observaciones = observaciones;
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


    	public String getUsuario() {
        	return usuario;
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
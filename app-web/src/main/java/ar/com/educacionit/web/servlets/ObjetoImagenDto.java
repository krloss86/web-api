package ar.com.educacionit.web.servlets;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author mnelegat .
 *
 */
public class ObjetoImagenDto implements Serializable { 
	
	private static final long serialVersionUID = 1L;
	private String nombreArchivo;
	private String tipoContenido;
	private byte[] datos;
	private Date fechaHoraCreacion;
	
	public ObjetoImagenDto() {
	}
	
	public ObjetoImagenDto(String nombreArchivo, String tipoContenido, byte[] datos) {
		this.nombreArchivo = nombreArchivo;
		this.tipoContenido = tipoContenido;
		this.datos = datos;
		this.fechaHoraCreacion = new Date();
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}
	
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	
	public String getTipoContenido() {
		return tipoContenido;
	}
	
	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}
	
	public byte[] getDatos() {
		return datos;
	}
	
	public void setDatos(byte[] datos) {
		this.datos = datos;
	}

	public Date getFechaHoraCreacion() {
		return fechaHoraCreacion;
	}

	public void setFechaHoraCreacion(Date fechaHoraCreacion) {
		this.fechaHoraCreacion = fechaHoraCreacion;
	}
	
}

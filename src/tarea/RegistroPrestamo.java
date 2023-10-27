package tarea;

import java.util.Date;

public class RegistroPrestamo {
	private Libro libro;
	private String usuario;
	private Date fechaPrestamo;
	private Date fechaDevolucion;

	public RegistroPrestamo(Libro libro, String usuario) {
		this.libro = libro;
		this.usuario = usuario;
		this.fechaPrestamo = new Date();
	}

	public void registrarDevolucion() {
		this.fechaDevolucion = new Date();
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
}

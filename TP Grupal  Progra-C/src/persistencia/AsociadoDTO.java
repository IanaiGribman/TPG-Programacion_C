package persistencia;

import modelo.Asociado;

public class AsociadoDTO {
	private String nombre, dni;

	public AsociadoDTO(String nombre, String dni) {
		super();
		this.nombre = nombre;
		this.dni = dni;
	}
	
	public AsociadoDTO(Asociado asociado) {
		super();
		this.nombre = asociado.getNombre();
		this.dni = asociado.getDni();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
}	

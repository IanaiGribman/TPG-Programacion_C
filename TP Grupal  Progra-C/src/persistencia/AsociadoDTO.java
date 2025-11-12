package persistencia;

import modelo.simulacion.Asociado;


/**
 * Clase concreta que sirve para transferir datos del asociado
 * POJO de los atributos de un asociado
 * Inv: nombre != null y !="", dni != null y !=""
 * Puede crearse completando sus atributos con los parametros del constructor o directamente pasandole un asociado y hace la "conversion"
 */
public class AsociadoDTO {
	private String nombre, dni;

	public AsociadoDTO() {
	}
	
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

	@Override
	public String toString() {
		return "- " + nombre + " " + dni;
	}
	
	
}	

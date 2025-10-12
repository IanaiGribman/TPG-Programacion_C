package modelo;


/**
 * Clase que representa a una entidad, ya sea un paciente, un medico, etc
 * Tiene los atributos que todos tendran en comun
 */
public abstract class Entidad implements INombrable {
	private String nombre;
	private Domicilio domicilio;
	private String telefono;
	
	public Entidad(String nombre, Domicilio domicilio, String telefono) {
		super();
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.telefono = telefono;
	}

	
	@Override
	public String getNombre() {
		return nombre;
	}


	public Domicilio getDomicilio() {
		return domicilio;
	}


	public String getTelefono() {
		return telefono;
	}


	@Override
	public String toString() {
		return "nombre=" + nombre + ", domicilio=" + domicilio + ", telefono=" + telefono;
	}
	
	
	
}

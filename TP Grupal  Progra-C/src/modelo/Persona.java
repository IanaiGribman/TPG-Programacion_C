package modelo;

public abstract class Persona extends Entidad{
	private String dni;

	protected Persona(String nombre, Domicilio domicilio, String telefono, String dni) {
		super(nombre, domicilio, telefono);
		this.dni = dni;
		// TODO Auto-generated constructor stub
	}

	public String getDni() {
		return dni;
	}

}

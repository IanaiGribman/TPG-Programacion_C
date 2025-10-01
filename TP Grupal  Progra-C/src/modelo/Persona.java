package modelo;

public abstract class Persona extends Entidad implements IPersona{
	private String dni;

	protected Persona(String nombre, Domicilio domicilio, String telefono, String dni) {
		super(nombre, domicilio, telefono);
		this.dni = dni;
	}

	@Override
	public String getDni() {
		return dni;
	}
}

package modelo;

import java.util.Objects;

/**
 * Clase que representa a una persona
 */
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

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return super.toString() + ", dni=" + dni;
	}
	
	
}

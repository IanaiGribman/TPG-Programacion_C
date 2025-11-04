package modelo;

public class Asociado implements IPersona, Runnable
{
	private String dni;
	private String nombre;
	private Ambulancia ambulancia;
	
	
	public Asociado(String dni, String nombre, Ambulancia ambulancia) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.ambulancia = ambulancia;
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}
	
	@Override
	public String getDni() {
		return this.dni;
	}
	
}

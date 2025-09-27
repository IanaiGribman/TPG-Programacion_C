package modelo;

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
	
	
	
}

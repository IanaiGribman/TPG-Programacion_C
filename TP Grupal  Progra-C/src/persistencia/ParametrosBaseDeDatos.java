package persistencia;

/**
 * POJO que encapsula los parametros de acceso a una base de datos Utilizada
 * para poder almacenarla y/o accederla en un archivo XML
 */
public class ParametrosBaseDeDatos {
	private String direccion;
	private String usuario;
	private String clave;
	private String nombre;

	public ParametrosBaseDeDatos() {
	}

	public String getDireccion() {
		return direccion;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Inicializacion del objeto, usado para inicializar con ciertos valores y
	 * mantener el constructor vacio
	 * 
	 * @param direccion
	 * @param usuario
	 * @param clave
	 * @param nombre
	 */
	public void initialize(String direccion, String usuario, String clave, String nombre) {
		this.direccion = direccion;
		this.usuario = usuario;
		this.clave = clave;
		this.nombre = nombre;
	}
}

package persistencia.excepciones;

@SuppressWarnings("serial")
public class SinConexionException extends Exception {
	public SinConexionException() {
		super("No hay conexion con la base de datos");
	}
}

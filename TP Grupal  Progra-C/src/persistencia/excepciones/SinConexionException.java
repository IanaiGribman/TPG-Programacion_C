package persistencia.excepciones;

/**
 * Excepcion utilizada para marcar que la conexion con la base de datos no existe,
 * ya sea porque la conexion no se abrio y se intento operar sobre esta o porque se perdio en algun momento
 */
@SuppressWarnings("serial")
public class SinConexionException extends Exception {
	public SinConexionException() {
		super("No hay conexion con la base de datos");
	}
}

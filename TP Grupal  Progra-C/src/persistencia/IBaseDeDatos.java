package persistencia;

import java.util.List;

import persistencia.excepciones.OperacionFallidaException;
import persistencia.excepciones.SinConexionException;


/**
 * Interfaz que representaria a cualquier base de datos para este sistema
 * Tendra una direccion/url, usuario con clave,
 * y una tabla de asocicados con los campos dni y nombre para almacenarlos
 * El asociado es unico por dni, entonces este campo es una clave primaria de la tabla
 */
interface IBaseDeDatos {
	String direccionBaseDeDatos = "";
	String nombreUsuario = "";
	String contraUsuario = "";
	String nombreTablaAsociados = "ASOCIADOS";
	String nombreCampoAsociadosDni = "DNI";
	String nombreCampoAsociadosNombre = "NOMBRE";
	List<AsociadoDTO> leerAsociados() throws OperacionFallidaException, SinConexionException;
	void agregarAsociado(AsociadoDTO asociado) throws OperacionFallidaException, SinConexionException;
	void eliminarAsociado(String dni) throws OperacionFallidaException, SinConexionException;//es correcto o es mejor que se pase el asociado entero? no hace falta pero que es correcto?
	void abrirConexion() throws OperacionFallidaException;
	void cerrarConexion() throws OperacionFallidaException, SinConexionException;
	void crearTablaAsociados() throws OperacionFallidaException, SinConexionException;
}

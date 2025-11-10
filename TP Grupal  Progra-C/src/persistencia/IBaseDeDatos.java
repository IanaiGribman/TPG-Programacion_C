package persistencia;

import java.sql.SQLException;
import java.util.List;

import persistencia.excepciones.NoEliminadoException;
import persistencia.excepciones.SinConexionException;


/**
 * Interfaz que representaria a cualquier base de datos para este sistema
 * Tendra una direccion/url, usuario con clave,
 * y una tabla de asocicados con los campos dni y nombre para almacenarlos
 * El asociado es unico por dni, entonces este campo es una clave primaria de la tabla
 */
public interface IBaseDeDatos {
	String nombreTablaAsociados = "ASOCIADOS";
	String nombreCampoAsociadosDni = "DNI";
	String nombreCampoAsociadosNombre = "NOMBRE";
	List<AsociadoDTO> leerAsociados() throws SQLException, SinConexionException;
	void agregarAsociado(AsociadoDTO asociado) throws SQLException, SinConexionException;
	void eliminarAsociado(String dni) throws SQLException, SinConexionException, NoEliminadoException;//es correcto o es mejor que se pase el asociado entero? no hace falta pero que es correcto?
	void abrirConexion() throws SQLException;
	void cerrarConexion() throws SQLException, SinConexionException;
	void crearTablaAsociados() throws SQLException, SinConexionException;
	void reiniciarTablaAsociados() throws SQLException, SinConexionException;
}

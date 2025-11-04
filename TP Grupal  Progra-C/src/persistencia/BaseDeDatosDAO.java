package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import persistencia.excepciones.OperacionFallidaException;
import persistencia.excepciones.SinConexionException;

/**
 * Clase que es el DAO concreto para la base de datos usada (?)
 */
public class BaseDeDatosDAO implements IBaseDeDatos {
	private Connection conexion;
	private Statement sentencia;

	/**
	 * Lee todos los asociados en la tabla de la BD
	 * Si no hay conexion o hay error de lectura lanza excepcion
	 */
	@Override
	public List<AsociadoDTO> leerAsociados() throws OperacionFallidaException, SinConexionException {
		List<AsociadoDTO> asociados = null;

		if (conexion != null) {
			asociados = new ArrayList<AsociadoDTO>();

			try {
				ResultSet resultado = sentencia.executeQuery("SELECT * FROM " + IBaseDeDatos.nombreTablaAsociados);
				while (resultado.next()) {
					String dni = resultado.getString(IBaseDeDatos.nombreCampoAsociadosDni);
					String nombre = resultado.getString(IBaseDeDatos.nombreCampoAsociadosNombre);
					asociados.add(new AsociadoDTO(dni, nombre));
				}

			} catch (SQLException e) {
				throw new OperacionFallidaException("No se han podido leer los asociados");
			}
		}else
			throw new SinConexionException();

		return asociados;
	}

	/**
	 * Agrega un asociado a la tabla
	 * Si no hay conexion con la BD o hay algun error en la carga lanza excepcion y la BD es inalterada
	 * Pre: asociado != null
	 */
	@Override
	public void agregarAsociado(AsociadoDTO asociado) throws OperacionFallidaException, SinConexionException {
		if (conexion != null) // quiza convenga lanzar excepcion cuando no hay conexion, para avisar (?)
			try {
				sentencia.execute("INSERT INTO " + IBaseDeDatos.nombreTablaAsociados + " VALUES(" + asociado.getDni()
						+ ", " + asociado.getNombre() + ")");
			} catch (SQLException e) {
				throw new OperacionFallidaException("No se ha podido agregar al asociado");
			}
		else
			throw new SinConexionException();
	}
	
	
	/**
	 * Elimina un asociado de la tabla pasandosele un dni
	 * Si no hay conexion con la BD o hay error en la eliminacion lanza excepcion y la BD es inalterada
	 * Pre: dni != null
	 */
	@Override
	public void eliminarAsociado(String dni) throws OperacionFallidaException, SinConexionException {
		if(conexion != null) 
			try {
				sentencia.execute("DELETE FROM " + IBaseDeDatos.nombreTablaAsociados + 
								"WHERE " + IBaseDeDatos.nombreCampoAsociadosDni + "='" + dni + "'");
			}catch(SQLException e) {
				throw new OperacionFallidaException("No se ha podido eliminar al asociado");
			}
		else
			throw new SinConexionException();
	}

	/**
	 * Abre la conexion con la BD, necesaria para la ejecucion de sentencias sobre esta
	 * Si no es posible establecer la conexion lanza excepcion
	 */
	@Override
	public void abrirConexion() throws OperacionFallidaException {
		try {
			Class.forName("sun.jdbc.obdc.JdbcObdcDriver");
		} catch (ClassNotFoundException e) {
			throw new OperacionFallidaException("No se ha encontrado el driver para la BD");
		}

		try {
			conexion = DriverManager.getConnection(IBaseDeDatos.direccionBaseDeDatos, IBaseDeDatos.nombreUsuario,
					IBaseDeDatos.contraUsuario);
			sentencia = conexion.createStatement();
		} catch (SQLException e) {
			throw new OperacionFallidaException("No se ha podido conectar a la base de datos");
		}

	}

	/**
	 * Cierra la conexion con la BD, necesaria para garantizar la persistencia de los cambios hechos durante la conexion abierta
	 * Si no puede terminar la conexion lanza excepcion
	 */
	@Override
	public void cerrarConexion() throws OperacionFallidaException, SinConexionException {
		if (conexion != null)
			try {
				sentencia.close();
				conexion.close();
			} catch (SQLException e) {
				throw new OperacionFallidaException("No se ha podido cerrar la conexion con la BD");
			}
		else
			throw new SinConexionException();
	}

	/**
	 * Elimina la tabla anterior si existia y crea una nueva vacia
	 * Si no es posible la eliminacion o ni siquiera hay conexion lanza excepcion y la BD es inalterada
	 */
	@Override
	public void crearTablaAsociados() throws OperacionFallidaException, SinConexionException {
		if (conexion != null) {
			try {
				sentencia.execute("DROP TABLE " + IBaseDeDatos.nombreTablaAsociados);
			} catch (SQLException e) {
				throw new OperacionFallidaException("No se pudo eliminar la tabla antigua");
			}

			try {
				sentencia.execute("CREATE TABLE " + IBaseDeDatos.nombreTablaAsociados + " ("
						+ IBaseDeDatos.nombreCampoAsociadosDni + " VARCHAR(10) NOT NULL, "
						+ IBaseDeDatos.nombreCampoAsociadosNombre + " VARCHAR(20) NOT NULL)");
			} catch (SQLException e) {
				throw new OperacionFallidaException("No se pudo crear la tabla de asociados");
			}
		}else
			throw new SinConexionException();
	}

}

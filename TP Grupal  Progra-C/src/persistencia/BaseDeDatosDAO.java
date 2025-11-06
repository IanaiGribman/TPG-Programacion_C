package persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import persistencia.excepciones.SinConexionException;

/**
 * Clase que es el DAO concreto para la base de datos usada (?)
 */
public class BaseDeDatosDAO implements IBaseDeDatos {
	private ParametrosBaseDeDatos parametros;
	private Connection conexion;
	private Statement sentencia;

	/**
	 * Empieza desconectada a la base de datos *
	 * 
	 * @param parametros (clase que encapsula la direccion, el usuario y la clave)
	 */
	public BaseDeDatosDAO(ParametrosBaseDeDatos parametros) {//Es correcto? o deberia pasar parametro por parametro?
		this.parametros = parametros;
	}

	/**
	 * Lee todos los asociados en la tabla de la BD Si no hay conexion o hay error
	 * de lectura lanza excepcion
	 */
	@Override
	public List<AsociadoDTO> leerAsociados() throws SQLException, SinConexionException {
		List<AsociadoDTO> asociados = null;

		if (conexion != null) {
			asociados = new ArrayList<AsociadoDTO>();

			ResultSet resultado = sentencia.executeQuery("SELECT * FROM " + IBaseDeDatos.nombreTablaAsociados);
			while (resultado.next()) {
				String dni = resultado.getString(IBaseDeDatos.nombreCampoAsociadosDni);
				String nombre = resultado.getString(IBaseDeDatos.nombreCampoAsociadosNombre);
				asociados.add(new AsociadoDTO(dni, nombre));
			}

		} else
			throw new SinConexionException();

		return asociados;
	}

	/**
	 * Agrega un asociado a la tabla Si no hay conexion con la BD o hay algun error
	 * en la carga lanza excepcion y la BD es inalterada Pre: asociado != null
	 */
	@Override
	public void agregarAsociado(AsociadoDTO asociado) throws SQLException, SinConexionException {
		if (conexion != null) // quiza convenga lanzar excepcion cuando no hay conexion, para avisar (?)
			sentencia.execute("INSERT INTO " + IBaseDeDatos.nombreTablaAsociados + " VALUES(" + asociado.getDni() + ", "
					+ asociado.getNombre() + ")");
		else
			throw new SinConexionException();
	}

	/**
	 * Elimina un asociado de la tabla pasandosele un dni Si no hay conexion con la
	 * BD o hay error en la eliminacion lanza excepcion y la BD es inalterada Pre:
	 * dni != null
	 */
	@Override
	public void eliminarAsociado(String dni) throws SQLException, SinConexionException {
		if (conexion != null)
			sentencia.execute("DELETE FROM " + IBaseDeDatos.nombreTablaAsociados + "WHERE "
					+ IBaseDeDatos.nombreCampoAsociadosDni + "='" + dni + "'");
		else
			throw new SinConexionException();
	}

	/**
	 * Abre la conexion con la BD, necesaria para la ejecucion de sentencias sobre
	 * esta Si no es posible establecer la conexion lanza excepcion
	 */
	@Override
	public void abrirConexion() throws SQLException {

		try {
			Class.forName("sun.jdbc.obdc.JdbcObdcDriver");
		} catch (ClassNotFoundException e) {
			// debug
			e.printStackTrace();
		}

		conexion = DriverManager.getConnection(this.parametros.getDireccion(), this.parametros.getUsuario(),
				this.parametros.getClave());
		sentencia = conexion.createStatement();

	}

	/**
	 * Cierra la conexion con la BD, necesaria para garantizar la persistencia de
	 * los cambios hechos durante la conexion abierta Si no puede terminar la
	 * conexion lanza excepcion
	 */
	@Override
	public void cerrarConexion() throws SQLException, SinConexionException {
		if (conexion != null) {
			sentencia.close();
			conexion.close();
		} else
			throw new SinConexionException();
	}

	/**
	 * Elimina la tabla anterior si existia y crea una nueva vacia Si no es posible
	 * la eliminacion o ni siquiera hay conexion lanza excepcion y la BD es
	 * inalterada
	 */
	@Override
	public void crearTablaAsociados() throws SQLException, SinConexionException {
		if (conexion != null) {
			sentencia.execute("DROP TABLE " + IBaseDeDatos.nombreTablaAsociados);
			sentencia.execute("CREATE TABLE " + IBaseDeDatos.nombreTablaAsociados + " ("
					+ IBaseDeDatos.nombreCampoAsociadosDni + " VARCHAR(10) NOT NULL, "
					+ IBaseDeDatos.nombreCampoAsociadosNombre + " VARCHAR(20) NOT NULL)");
		} else
			throw new SinConexionException();
	}

}

package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.excepciones.SinConexionException;

/**
 * Clase que es el DAO concreto para la base de datos usada (?)
 */
public class BaseDeDatosDAO implements IBaseDeDatos {
	private ParametrosBaseDeDatos parametros;
	private Connection conexion;

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
			ResultSet resultado = conexion.createStatement().executeQuery("SELECT * FROM " + nombreTablaAsociados);
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
		if (conexion != null) { // quiza convenga lanzar excepcion cuando no hay conexion, para avisar (?)
			PreparedStatement sentencia = conexion.prepareStatement("INSERT INTO "+nombreTablaAsociados+
																	" ("+nombreCampoAsociadosDni+", "
																		+nombreCampoAsociadosNombre+") VALUES (?, ?)");
			sentencia.setString(1, asociado.getDni());
			sentencia.setString(2, asociado.getNombre());
			sentencia.execute();
		}else
			throw new SinConexionException();
	}

	/**
	 * Elimina un asociado de la tabla pasandosele un dni Si no hay conexion con la
	 * BD o hay error en la eliminacion lanza excepcion y la BD es inalterada Pre:
	 * dni != null
	 */
	@Override
	public void eliminarAsociado(String dni) throws SQLException, SinConexionException {
		if (conexion != null) {
			PreparedStatement sentencia = conexion.prepareStatement("DELETE FROM "+nombreTablaAsociados +
																	" WHERE " + nombreCampoAsociadosDni + "=?");
			sentencia.setString(1, dni);
			sentencia.execute();
		}else
			throw new SinConexionException();
	}

	/**
	 * Abre la conexion con la BD, necesaria para la ejecucion de sentencias sobre esta 
	 * Si no es posible establecer la conexion lanza excepcion
	 * Si la base de datos no existe la crea
	 * @throws SQLException
	 */
	@Override
	public void abrirConexion() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// debug
			System.out.println(e);
			e.printStackTrace();
		}

		conexion = DriverManager.getConnection(this.parametros.getDireccion(), this.parametros.getUsuario(),
				this.parametros.getClave());
		
		String nombreBaseDeDatos = this.parametros.getNombre();
		conexion.createStatement().execute("CREATE DATABASE IF NOT EXISTS " + nombreBaseDeDatos);
		
		conexion.createStatement().execute("USE " + nombreBaseDeDatos);
	}

	/**
	 * Cierra la conexion con la BD, necesaria para garantizar la persistencia de
	 * los cambios hechos durante la conexion abierta Si no puede terminar la
	 * conexion lanza excepcion
	 */
	@Override
	public void cerrarConexion() throws SQLException, SinConexionException {
		if (conexion != null) {
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
			conexion.createStatement().execute("DROP TABLE IF EXISTS " + nombreTablaAsociados);
			conexion.createStatement().execute("CREATE TABLE " + nombreTablaAsociados + "("+
			                                   					nombreCampoAsociadosDni + " VARCHAR(10) NOT NULL PRIMARY KEY, " + 
																nombreCampoAsociadosNombre + " VARCHAR(30) NOT NULL)");
		} else
			throw new SinConexionException();
	}
}

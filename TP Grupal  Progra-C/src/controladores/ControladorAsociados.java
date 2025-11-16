package controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import persistencia.AsociadoDTO;
import persistencia.IBaseDeDatos;
import persistencia.excepciones.NoEliminadoException;
import persistencia.excepciones.SinConexionException;
import util.Util;

/**
 * Gestiona la BD (a traves del DAO) y la vista relacionada con los cambios en la BD
 */
public class ControladorAsociados {
	IBaseDeDatos dao;
	IVistaAsociados vista;
	private String direccionXMLInicializacion = "src/controladores/InicializacionConfig.xml";

	public ControladorAsociados(IBaseDeDatos dao, IVistaAsociados vista) {
		assert vista != null : "la referencia a la vista no debe ser null";
		assert dao != null : "la referencia al dao no debe ser null";

		this.vista = vista;
		this.dao = dao;
	}

	/**
	 * Realiza tareas de inicio en la base de datos y en la vista
	 */
	public void iniciar() {
		try {
			this.abrirConexion();
			List<AsociadoDTO> lista = dao.leerAsociados();
			assert lista != null : "la lista de asociados de la BD no debe ser null";
			vista.cargarListaAsociados(lista);
		} catch (SQLException e) {
			vista.displayError("Ocurrio un error tratando de leer a los asociados de la base de datos");
		} catch (SinConexionException e) {
			vista.displayError(e.getMessage());
		}

	}

	public void abrirConexion() {
		try {
			dao.abrirConexion();
			dao.crearTablaAsociados();
		} catch (SQLException e) {
			vista.displayError("Ocurrio un error tratando de conectarse a la base de datos");
		} catch (SinConexionException e) {
			vista.displayError(e.getMessage());
		}
	}

	/**
	 * Registra en la base de datos un nuevo asociado y refleja el cambio en la
	 * vista o muestra el error
	 */
	public void agregarAsociadoPermanencia() {
		AsociadoDTO asoc = vista.getAsociadoNuevo();

		assert asoc != null : "el asociado retornado de la vista no debe ser null";

		try {
			dao.agregarAsociado(asoc);
			vista.agregarAsociadoPermanencia(asoc);
		} catch (SQLException e) {
			vista.displayError("Error: Ya existe un asociado con ese dni.");
		} catch (SinConexionException e) {
			vista.displayError(e.getMessage());
		}

	}

	/**
	 * Elimina de la BD un asociado y refleja el cambio en la vista o muestra el
	 * error
	 */
	public void eliminarAsociadoPermanencia() {
		String dniAEliminar = vista.getDniAEliminar();

		assert dniAEliminar != null : "el dni retornado de la vista no debe ser null";

		try {
			dao.eliminarAsociado(dniAEliminar);
			vista.eliminarAsociadoPermanencia(dniAEliminar);
		} catch (SQLException e) {
			vista.displayError("Ocurrio un error tratando de eliminar al asociado");
		} catch (SinConexionException | NoEliminadoException e) {
			vista.displayError(e.getMessage());
		}
	}

	/**
	 * Quita todos los asociados de la BD y registra nuevos asociados que estan en
	 * un archivo. Refleja el cambio en la vista o muestra error
	 */
	public void reiniciarInicializarTabla() {
		vista.vaciarListasAsoc();
		try {
			dao.reiniciarTablaAsociados();

			// esta parte del codigo lee a los asociados del XML de inicializacion y carga
			// un numero aleatorio de ellos en la base de datos (entre 3 y 7)
			ArrayList<AsociadoDTO> asociadosInicializacion = new ArrayList<>(
					ManagerXMLInicializacion.leerAsociadosInicializacionXML(direccionXMLInicializacion));
			Collections.shuffle(asociadosInicializacion);
			int tope = Util.numeroAleatorio(3, 7);
			for (int i = 0; i < asociadosInicializacion.size() && i < tope; i++)
				dao.agregarAsociado(asociadosInicializacion.get(i));

			List<AsociadoDTO> lista = dao.leerAsociados();

			assert lista != null : "la lista de asociados de la BD no debe ser null";

			vista.cargarListaAsociados(lista);
		} catch (SQLException e) {
			vista.displayError("Ocurrio un error en la base de datos"); // no es muy descriptivo pero no se que otra
																		// cosa podria decir
		} catch (SinConexionException e) {
			vista.displayError(e.getMessage());
		}

	}

	/**
	 * Cierra la conexion con la BD
	 */
	public void cerrarConexion() {
		try {
			dao.cerrarConexion();
		} catch (SQLException e) {
			vista.displayError("Ocurrio un error tratando de cerrar la coneccion con la base de datos");
		} catch (SinConexionException e) {
			vista.displayError(e.getMessage());
		}
	}

}

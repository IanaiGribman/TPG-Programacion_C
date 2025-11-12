package controladores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import persistencia.AsociadoDTO;
import persistencia.IBaseDeDatos;
import util.Util;
import vista.IVistaAsociados;

public class ControladorAsociados {
	IBaseDeDatos dao;
	IVistaAsociados vista;
	private String direccionXMLInicializacion = "src/controladores/InicializacionConfig.xml";
	
	public ControladorAsociados(IBaseDeDatos dao, IVistaAsociados vista) {
		assert vista != null: "la referencia a la vista no debe ser null";
		assert dao != null: "la referencia al dao no debe ser null";
		
		this.vista = vista;
		this.dao = dao;
	}
	
	/**
	 * Realiza tareas de inicio en la base de datos y en la vista 
	 */
	public void iniciar() {
		try {
			dao.abrirConexion();
			dao.crearTablaAsociados();
			List<AsociadoDTO> lista = dao.leerAsociados();
			
			assert lista != null: "la lista de asociados de la BD no debe ser null";
			
			vista.cargarListaAsociados(lista);
		} 
		catch (Exception e) {
			vista.displayError(e.getMessage());
		}
	}
	
	/**
	 * Registra en la base de datos un nuevo asociado y refleja el cambio en la vista o muestra el error
	 */
	public void agregarAsociadoPermanencia() {
		try {
			AsociadoDTO asoc = vista.getAsociadoNuevo();
			
			assert asoc != null: "el asociado retornado de la vista no debe ser null";
			
			dao.agregarAsociado(asoc);
			vista.agregarAsociadoPermanencia(asoc);
		}
		catch(Exception e) {
			vista.displayError(e.getMessage());
		}
	}
	
	/**
	 * Elimina de la BD un asociado y refleja el cambio en la vista o muestra el error
	 */
	public void eliminarAsociadoPermanencia() {
		try {
			String dniAEliminar = vista.getDniAEliminar();
			
			assert dniAEliminar != null: "el dni retornado de la vista no debe ser null";
			
			dao.eliminarAsociado(dniAEliminar);
			vista.eliminarAsociadoPermanencia(dniAEliminar);
		}
		catch(Exception e) {
			vista.displayError(e.getMessage());
		}
	}
	
	/**
	 * Quita todos los asociados de la BD y registra nuevos asociados que estan en un archivo. Refleja
	 * el cambio en la vista o muestra error
	 */
	public void reiniciarInicializarTabla() {
		try {
			vista.vaciarListasAsoc();
			dao.reiniciarTablaAsociados();
			
			ArrayList<AsociadoDTO> asociadosInicializacion = new ArrayList<>(ManagerXMLInicializacion.leerAsociadosInicializacionXML(direccionXMLInicializacion));
			Collections.shuffle(asociadosInicializacion);
			int tope = Util.numeroAleatorio(3, 7);
			for (int i = 0; i<asociadosInicializacion.size() && i<tope; i++)
				dao.agregarAsociado(asociadosInicializacion.get(i));
			
			List<AsociadoDTO> lista = dao.leerAsociados();

			assert lista != null: "la lista de asociados de la BD no debe ser null";
			
			vista.cargarListaAsociados(lista);
		}
		catch(Exception e) {
			vista.displayError(e.getMessage());
		}
	}
	
	/**
	 * Cierra la conexion con la BD
	 */
	public void cerrarConexion() {
		try {
			dao.cerrarConexion();
		}
		catch(Exception e) {
			vista.displayError(e.getMessage());
		}
	}
}

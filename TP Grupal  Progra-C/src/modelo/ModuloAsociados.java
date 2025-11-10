package modelo;

import java.util.List;

import Util.Acciones;
import patrones.observer.ObservableAbstracto;
import persistencia.AsociadoDTO;
import persistencia.IBaseDeDatos;

public class ModuloAsociados extends ObservableAbstracto {
	private IBaseDeDatos dao;

	public ModuloAsociados(IBaseDeDatos dao) {
		assert dao != null : "el dao no puede ser null";
		this.dao = dao;
	}

	/**
	 * Devuelve lista de los asociados en newValue o transmite el mensaje de error
	 * en caso de fallar
	 */
	public void leerAsociados() {
		try {
			List<AsociadoDTO> lista = this.dao.leerAsociados();
			this.firePropertyChange(Acciones.CARGAR, null, lista);
		} catch (Exception e) {
			this.eventoError(e);
		}
	}

	/**
	 * Devuelve un dto de asociado registrado en newValue o transmite el mensaje de
	 * error en caso de fallar
	 * 
	 * @param asociado que se quiere registrar
	 */
	public void agregarAsociado(AsociadoDTO asociado) {
		assert asociado != null : "no se puede registrar en la base de datos con un dto null";
		try {
			this.dao.agregarAsociado(asociado);
			this.firePropertyChange(Acciones.REGISTRAR, null, asociado);
		} catch (Exception e) {
			this.eventoError(e);
		}
	}

	/**
	 * Devuelve el dni del eliminado en newValue o transmite el mensaje de error en
	 * caso de fallar
	 * 
	 * @param dniAEliminar
	 */
	public void eliminarAsociado(String dniAEliminar) {
		assert dniAEliminar != null : "no se puede eliminar en la base de datos con un dni null";
		try {
			this.dao.eliminarAsociado(dniAEliminar);
			this.firePropertyChange(Acciones.ELIMINAR, null, dniAEliminar);
		} catch (Exception e) {
			this.eventoError(e);
		}
	}

	public void abrirConexion() {
		try {
			this.dao.abrirConexion();
		} catch (Exception e) {
			this.eventoError(e);
		}
	}

	public void cerrarConexion() {
		try {
			this.dao.cerrarConexion();
		} catch (Exception e) {
			this.eventoError(e);
		}
	}

	public void crearTablaAsociados() {
		try {
			this.dao.crearTablaAsociados();
		} catch (Exception e) {
			this.eventoError(e);
		}
	}

	public void reiniciarTablaAsociados() {
		try {
			this.dao.reiniciarTablaAsociados();
		} catch (Exception e) {
			this.eventoError(e);
		}
	}

	/**
	 * Transmite un mensaje de error a los observadores
	 * 
	 * @param e
	 */
	protected void eventoError(Exception e) {
		e.printStackTrace();
		this.firePropertyChange(Acciones.ERROR, null, e.getMessage());
	}
}

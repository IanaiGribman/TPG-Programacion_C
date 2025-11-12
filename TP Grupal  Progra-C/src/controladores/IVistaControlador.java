package controladores;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.util.Collection;
import java.util.List;

import modelo.simulacion.Solicitante;
import patrones.state.IEstado;
import persistencia.AsociadoDTO;

public interface IVistaControlador {	

	/**
	 * Muestra la ventana de gestion
	 */
	public void mostrarGestion();
	
	/**
	 * Muestra la ventana de simulacion
	 */
	public void mostrarSimulacion(); 
	
	/**
	 * Muestra ventana de confirmar inicializacion
	 */
	public void displayConfirmarInicializacion();

	/**
	 * Agrega el mismo action listener a los botones de la vista
	 * @param actionListener
	 */
	public void setActionListener(ActionListener actionListener);
	
	/**
	 * Agrega un window listener a la vista
	 * @param windowListener
	 */
	public void setWindowListener(WindowListener windowListener);
}

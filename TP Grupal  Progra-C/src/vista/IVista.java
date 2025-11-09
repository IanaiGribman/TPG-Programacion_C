package vista;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Collection;
import java.util.List;

import modelo.Solicitante;
import patrones.state.IEstado;
import persistencia.AsociadoDTO;

public interface IVista {	
	/**
	 * @return un AsociadoDTO con los datos de un nuevo asociado para cargar el la BD
	 */
	public AsociadoDTO getAsociadoNuevo();
	/**
	 * 
	 * @return el dni del asociado que se desea eliminar de la persistencia
	 */
	public String getDniAEliminar();
	
	/**
	 * pasa de mostrar la simulacion a mostrar la gestion
	 */
	public void mostrarGestion();
	/**
	 * pasa de mostrar la gestion a mostrar la simulacion
	 */
	public void mostrarSimulacion(); 
	
	/**
	 * vacia la lista de simulacion y asociados
	 */
	public void vaciarListasAsoc();
	
	/**
	 * muestra un PopUp con el error, se puede usar este metodo para comunicar errores al usuario
	 * @param mensajeError
	 */
	public void displayError(String mensajeError);
	
	/**
	 * muestra un PopUp con un mensaje de advertencia
	 * @param mensajeWarning
	 */
	public void displayWarning(String mensajeWarning);
	
	
	// LES PARECE BIEN ESTOS METODOS PARA LA SIMULACION DE LA AMBULANCIA?
	public void aniadirLlamado(Solicitante solicitante, String tipoDeSolicitud);
	public void retirarLlamado(Solicitante solicitante);
	// se le puede aniadir a los estados de ambulancia un toString que diga que esta haciendo la ambulancia en el momento?
	public void infomarCambioEstado(IEstado estadoAmbulancia);	
	
	public void propertyChange(PropertyChangeEvent evt);
	public void setActionListener(ActionListener actionListener);

}

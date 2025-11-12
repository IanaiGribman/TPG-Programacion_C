package controladores;

import java.util.List;

import persistencia.AsociadoDTO;

public interface IVistaAsociados {
	
	/**
	 * Muestra un nuevo asociado alamacenado en la BD
	 * @param asociado nuevo
	 */
	public void agregarAsociadoPermanencia(AsociadoDTO asociado);
	
	/**
	 * Quita de la vista un asociado que ya no esta en la BD
	 * @param dniAEliminar
	 */
	public void eliminarAsociadoPermanencia(String dniAEliminar);
	
	/**
	 * Devuelve un asociadoDTO en base a los datos llenados en un formulario de la vista
	 * @return
	 */
	public AsociadoDTO getAsociadoNuevo();
	
	/**
	 * Devuelve el dni de un asociado que se quiera eliminar de la BD
	 * @return
	 */
	public String getDniAEliminar();
	
	/**
	 * Muestra todos los asociados que hay en la BD en momento determinado
	 * @param lista de AsociadoDTO de todos los asociados de la BD
	 */
	public void cargarListaAsociados(List<AsociadoDTO> lista);
	
	/**
	 * Muestra un mensaje de error
	 * @param mensaje
	 */
	public void displayError(String mensaje);
	
	/**
	 * Vacia la listas de asociados que se muestran en la vista
	 */
	public void vaciarListasAsoc();
}

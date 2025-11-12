package controladores;

import java.util.List;

import modelo.simulacion.Llamado;
import patrones.state.IEstado;
import persistencia.AsociadoDTO;

/**
 * Define los metodos de la vista que conciernen a la simulacion
 */
public interface IVistaSimulacion {
	
	/**
	 * Muestra en la vista un nuevo llamado/solicitud de un solicitante al modelo (ambulancia)
	 * @param llamado del solicitante
	 */
	public void agregarLlamadoNuevoEspera(Llamado llamado);
	
	/**
	 *  Quita de la vista un llamado que anteriormente era nuevo pero ya ha sido atendido
	 * @param llamado
	 */
	public void quitarLlamadoEspera(Llamado llamado);
	public void agregarLlamadoAtendidos(Llamado llamado);
	
	/**
	 * Muestra el nuevo estado de la ambulancia
	 * @param estado nuevo de la ambulancia
	 */
	public void cambiarEstado(IEstado estado);
	
	/**
	 * Muestra un mensaje informativo
	 * @param mensaje
	 */
	public void displayInfo(String mensaje);
	
	/**
	 * Devuelve lista con los asociados para la simulacion
	 * @return 
	 */
	public List<AsociadoDTO> getListaAsociadosSimulacion();
}
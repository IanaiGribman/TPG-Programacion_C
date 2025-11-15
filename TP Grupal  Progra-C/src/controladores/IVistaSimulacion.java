package controladores;

import java.util.List;

import persistencia.AsociadoDTO;

/**
 * Define los metodos de la vista que conciernen a la simulacion
 */
public interface IVistaSimulacion {
	//se usan para decidir el mensaje de un boton desactivado
	public static final String OPERARIO_REPETIDO = "OPERARIO_REP";
	public static final String FIN_SIMULACION = "FIN_SIMULACION";

	/**
	 * Muestra en la vista un nuevo llamado/solicitud de un solicitante al modelo
	 * (ambulancia)
	 * 
	 * @param llamado del solicitante
	 */
	public void agregarLlamadoNuevoEspera(String mensaje);

	/**
	 * Quita de la vista un llamado que anteriormente era nuevo pero ya ha sido
	 * atendido
	 * 
	 * @param llamado
	 */
	public void quitarLlamadoEspera(String llamado);

	/**
	 * Muestra en la vista un llamadado que ya fue atendido
	 * 
	 * @param llamado
	 */
	public void agregarLlamadoAtendidos(String llamado);

	/**
	 * Muestra el nuevo estado de la ambulancia
	 * 
	 * @param estado nuevo de la ambulancia
	 */
	public void cambiarEstadoAmbulancia(String estado);

	/**
	 * Muestra un mensaje informativo
	 * 
	 * @param mensaje
	 */
	public void displayInfo(String mensaje);

	/**
	 * Devuelve lista con los asociados para la simulacion
	 * 
	 * @return
	 */
	public List<AsociadoDTO> getListaAsociadosSimulacion();

	/**
	 * Habilita/desabilita el boton para volver a la seccion de gestion
	 */
	public void cambiarEstadoBotonVolver(boolean activo);

	/**
	 * Habilita/desabilita el boton para solicitar mantenimiento
	 */
	public void cambiarEstadoBotonMantenimiento(boolean activo);
	/**
	 * Habilita/desabilita el boton para solicitar mantenimiento pudiendo indicar el motivo
	 */
	public void cambiarEstadoBotonMantenimiento(boolean activo, String motivo);

	/**
	 * Habilita/desabilita el boton para finalizar la simulacion
	 */
	public void cambiarEstadoBotonFinalizar(boolean activo);

}
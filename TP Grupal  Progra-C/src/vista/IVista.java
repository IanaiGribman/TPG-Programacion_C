package vista;

import java.util.Collection;
import java.util.Observer;

import modelo.Solicitante;
import patrones.IEstado;
import persistencia.AsociadoDTO;

public interface IVista extends Observer{
	public static final String SIMULACION = "SIMULACION";
	public static final String REGISTRAR = "REGISTRAR";
	public static final String ELIMINAR = "ELIMINAR";
	public static final String CARGAR = "CARGAR";
	public static final String GUARDAR = "GUARDAR";
	public static final String INICIALIZAR = "INICIALIZAR";
	
	public static final String MANTENIMIENTO = "MANTENIMIENTO";
	public static final String GESTION = "GESTION";
	
	/**
	 * 
	 * @return un AsociadoDTO con los datos de un nuevo asociado
	 */
	public AsociadoDTO getNewAsociado();
	/**
	 * 
	 * @return el dni del asociado que se desea eliminar
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
	 * actualiza la tabla de asociados visualmente
	 * TODO deberia lanzar excepcion si se quiere actualizar mientras se esta simulando?
	 * @param asociados
	 */
	public void actualizarTablaAsociados(Collection<AsociadoDTO> asociados);
	
	// MUESTRA UN PopUp con el error, se puede usar este metodo para comunicar errores al usuario, pero algun error mas especifico podria requerir metodos especificos
	public void displayError(String mensajeError);
	
	
	
	// LES PARECE BIEN ESTOS METODOS PARA LA SIMULACION DE LA AMBULANCIA?
	public void aniadirLlamado(Solicitante solicitante, String tipoDeSolicitud);
	public void retirarLlamado(Solicitante solicitante);
	// se le puede aniadir a los estados de ambulancia un toString que diga que esta haciendo la ambulancia en el momento?
	public void infomarCambioEstado(IEstado estadoAmbulancia);
	
	
}

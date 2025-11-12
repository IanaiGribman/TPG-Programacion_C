package controladores;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import modelo.Ambulancia;
import modelo.simulacion.Asociado;
import modelo.simulacion.EventoRetorno;
import modelo.simulacion.Llamado;
import modelo.simulacion.NotificacionSimulacion;
import modelo.simulacion.Operario;
import modelo.simulacion.Solicitante;
import patrones.state.IEstado;
import persistencia.AsociadoDTO;
import util.Acciones;
import vista.IVistaSimulacion;

/**
 * Observa a la ambulancia y a los hilos y gestiona la vista de acuerdo a sus notificaciones.
 * Tambien crea los hilos y es responsable de activar o desactivar la simulacion
 */
public class ControladorSimulacion implements Observer {
	private Ambulancia ambulancia;
	private IVistaSimulacion vista;
	//TODO modificar esta direccion
	private String direccionXMLSimulacion = "src/controladores/SimulacionConfig.xml";
	
	public ControladorSimulacion(Ambulancia ambulancia, IVistaSimulacion vista) {
		assert ambulancia != null: "la referencia a la ambulancia no debe ser null";
		assert vista != null: "la referencia a la vista no debe ser null";
		
		this.ambulancia = ambulancia;
		this.ambulancia.addObserver(this);
		this.vista = vista;
	}
	
	/**
	 * Pone en true el flag de simulacion del recurso compartido y
	 * crea los hilos al iniciar la simulacion: hilos de los asociados y el de retorno a clinica
	 * @param lista
	 */
	public void iniciarSimulacion() {
		List<AsociadoDTO> lista = vista.getListaAsociadosSimulacion();
		
		assert lista != null: "la lista de asociados DTO no debe ser null";
		
		vista.cambiarEstado(ambulancia.getEstado());
		this.ambulancia.activarSimulacion();
		this.crearHilosAsociados(lista);
		this.crearHilo(new EventoRetorno(this.ambulancia));
	}
	
	/**
	 * Crea un hilo de operario y lo inicia
	 */
	public void crearHiloOperario() {
		this.crearHilo(new Operario(this.ambulancia));
	}
	
	/**
	 * Pone en false el flag de simulacion activa en el recurso compartido
	 */
	public void finalizarSimulacion() {
		this.ambulancia.finalizarSimulacion();
	}
	

	@Override
	public void update(Observable o, Object arg) {
		NotificacionSimulacion notif = (NotificacionSimulacion) arg;
		switch(notif.getNombreAccion()) {
		
		case Acciones.ESTADO: {
			IEstado estado = (IEstado) notif.getNuevoValor();
			vista.cambiarEstado(estado);
			break;
		}
		
		case Acciones.INFORMAR: {
			String mensaje = (String) notif.getNuevoValor();
			vista.displayInfo(mensaje);
			break;
		}
		
		case Acciones.NUEVO_LLAMADO: {
			Llamado llamado = (Llamado) notif.getNuevoValor();
			vista.agregarLlamadoNuevo(llamado);
			break;
		}
		
		case Acciones.QUITAR_LLAMADO: {
			Llamado llamado = (Llamado) notif.getNuevoValor();
			vista.quitarLlamado(llamado);
			break;
		}	
		}
		
	}
	
	/**
	 * Crea los hilos de los asociados y los inicia
	 * @param lista de asociados DTO
	 */
	protected void crearHilosAsociados(List<AsociadoDTO> lista) {
		ParametrosSimulacion parametrosSimulacion = ManagerXMLSimulacion.leerSimulacionXML(direccionXMLSimulacion);
		int maxSolicitudes = parametrosSimulacion.getCantMaximaSolicitudes();
		int minSolicitudes = parametrosSimulacion.getCantMinimaSolicitudes();
		Iterator cantSolicitudes = new Random().ints(lista.size(), minSolicitudes, maxSolicitudes).iterator();
		for (AsociadoDTO asocDTO: lista) 
			this.crearHilo(new Asociado(this.ambulancia, (int)cantSolicitudes.next(), asocDTO));
	}

	/**
	 * Dado un solicitante, le agrega el observador/ojo, crea el hilo y lo inicia
	 * @param solicitante
	 */
	protected void crearHilo(Solicitante solicitante) {
		solicitante.addObserver(this);
		new Thread(solicitante).start();
	}
}

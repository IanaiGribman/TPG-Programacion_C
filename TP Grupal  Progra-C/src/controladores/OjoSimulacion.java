package controladores;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

import modelo.simulacion.Ambulancia;
import modelo.simulacion.EventoRetorno;
import modelo.simulacion.NotificacionSimulacion;
import modelo.simulacion.Solicitante;
import util.Acciones;

/**
 * Observa a los hilos y a la ambulancia. Gestiona a la vista para que refleje los cambios notificados
 */
public class OjoSimulacion implements Observer {
	private Observable ambulancia;
	private Observable eventoRetorno;
	private IVistaSimulacion vista;
	private ModuloSimulacion moduloSimulacion;
	private List<Solicitante> solicitantesActivos;

	public OjoSimulacion(Ambulancia ambulancia, IVistaSimulacion vista, ModuloSimulacion moduloSimulacion) {
		super();
		this.ambulancia = ambulancia;
		ambulancia.addObserver(this);
		this.vista = vista;
		this.moduloSimulacion = moduloSimulacion;
		this.solicitantesActivos = new ArrayList<Solicitante>();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o == this.ambulancia) {
			NotificacionSimulacion notif = (NotificacionSimulacion) arg;
			vista.cambiarEstadoAmbulancia(notif.getMensaje());

		} 
		else if (o == this.eventoRetorno) {
			
			NotificacionSimulacion notif = (NotificacionSimulacion) arg;
			switch (notif.getNombreAccion()) {
			case Acciones.NUEVO_LLAMADO: {
				vista.agregarLlamadoNuevoEspera(notif.getMensaje());
				break;
			}

			case Acciones.QUITAR_LLAMADO_NUEVOS_LLAMADOS: {
			    vista.quitarLlamadoEspera(notif.getMensaje());
			    vista.agregarLlamadoAtendidos(notif.getMensaje());
				break;
			}
			
			case Acciones.NO_HAY_HILOS: {
			    vista.cambiarEstadoBotonVolver(true);
			    vista.cambiarEstadoBotonMantenimiento(false);
			    vista.cambiarEstadoBotonFinalizar(false);
			    moduloSimulacion.setSePuedeVolver(true);
				break;
			}
			}
		}
		else if (this.solicitantesActivos.contains(o)) {
			Solicitante soli = (Solicitante) o;
			NotificacionSimulacion notif = (NotificacionSimulacion) arg;
			
			switch (notif.getNombreAccion()) {
				case Acciones.NUEVO_LLAMADO: {
					vista.agregarLlamadoNuevoEspera(notif.getMensaje());
					break;
				}
	
				case Acciones.QUITAR_LLAMADO_NUEVOS_LLAMADOS: {
				    vista.quitarLlamadoEspera(notif.getMensaje());
				    vista.agregarLlamadoAtendidos(notif.getMensaje());
					break;
				}
				
				case Acciones.QUITAR_SOLICITANTE_ACTIVO: {
					this.sacarSolicitanteLista(soli);
					break;
				}
				case Acciones.OPERARIO_ATENDIDO: {
					if (!this.moduloSimulacion.sePuedeVolver())
						this.vista.cambiarEstadoBotonMantenimiento(true);
					else
						this.vista.cambiarEstadoBotonMantenimiento(false,IVistaSimulacion.FIN_SIMULACION);
				}
			}
		}

	}
	
	/**
	 * Agrega un solicitante (debe ser un asociado o un operario) a la lista de asociados y agrega el observer a ellos
	 * @param solicitante
	 */
	public void agregarSolicitanteLista(Solicitante solicitante) {
		this.solicitantesActivos.add(solicitante);
		solicitante.addObserver(this);
	}
	
	/**
	 * Saca el solicitante de la lista y lo deja de observar. Notifica si dicha lista quedo vacia luego de quitar un elemento
	 * @param solicitante
	 */
	public void sacarSolicitanteLista(Solicitante solicitante) {
		solicitante.deleteObserver(this);//ya no lo observo mas, no esta en la lista
		this.solicitantesActivos.remove(solicitante);
		if(this.solicitantesActivos.isEmpty())
			this.moduloSimulacion.finHilos();
	}
	
	public void agregarEventoRetorno(EventoRetorno evt) {
		this.eventoRetorno = evt;
		evt.addObserver(this);
	}
	
}

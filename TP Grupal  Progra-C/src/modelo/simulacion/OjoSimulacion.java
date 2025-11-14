package modelo.simulacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

import controladores.IVistaSimulacion;
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
			vista.cambiarEstado(notif.getMensaje());

		} 
		else if (o == this.eventoRetorno) {
			
			NotificacionSimulacion notif = (NotificacionSimulacion) arg;
			switch (notif.getNombreAccion()) {
			case Acciones.NUEVO_LLAMADO: {
				vista.agregarLlamadoNuevoEspera(notif.getMensaje());
				break;
			}

			case Acciones.QUITAR_LLAMADO: {
			    vista.quitarLlamadoEspera(notif.getMensaje());
			    vista.agregarLlamadoAtendidos(notif.getMensaje());
				break;
			}
			
			case Acciones.NO_HAY_HILOS: {
			    vista.habilitarBotonGestion();
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
	
				case Acciones.QUITAR_LLAMADO: {
				    vista.quitarLlamadoEspera(notif.getMensaje());
				    vista.agregarLlamadoAtendidos(notif.getMensaje());
					break;
				}
				
				case Acciones.QUITAR_SOLICITANTE_ACTIVO: {
					this.sacarSolicitanteLista(soli);
					break;
				}
			}
		}

	}

	public void agregarSolicitanteLista(Solicitante solicitante) {
		this.solicitantesActivos.add(solicitante);
		solicitante.addObserver(this);
	}
	
	public void sacarSolicitanteLista(Solicitante solicitante) {
		solicitante.deleteObserver(this);//ya no lo observo mas, no esta en la lista
		this.solicitantesActivos.remove(solicitante);
		if(this.solicitantesActivos.isEmpty())
			this.moduloSimulacion.finHilos();
	}
	
	//esto es solamente para que se muestre el retorno automatico en la vista
	public void agregarEventoRetorno(EventoRetorno evt) {
		this.eventoRetorno = evt;
		evt.addObserver(this);
	}
	
}

package modelo.simulacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import controladores.IVistaSimulacion;
import patrones.state.IEstado;
import util.Acciones;

public class OjoSimulacion implements Observer {
	private Observable ambulancia;
	private IVistaSimulacion vista;
	private ModuloSimulacion moduloSimulacion;
	private List<Solicitante> solicitantesActivos;

	public OjoSimulacion(Observable ambulancia, IVistaSimulacion vista, ModuloSimulacion moduloSimulacion) {
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
			
			switch (notif.getNombreAccion()) {
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
			}
		} else if (this.solicitantesActivos.contains(o)) {
			Solicitante soli = (Solicitante) o;
			NotificacionSimulacion notif = (NotificacionSimulacion) arg;
			
			switch (notif.getNombreAccion()) {
				case Acciones.NUEVO_LLAMADO: {
					Llamado llamado = (Llamado) notif.getNuevoValor();
					vista.agregarLlamadoNuevoEspera(llamado);
					break;
				}
	
				case Acciones.QUITAR_LLAMADO: {
					Llamado llamado = (Llamado) notif.getNuevoValor();
					vista.quitarLlamadoEspera(llamado);
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
			this.moduloSimulacion.finalizarSimulacion();
	}
}

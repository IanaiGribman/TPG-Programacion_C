package modelo;

import Util.Acciones;
import patrones.observer.ObservableAbstracto;
import patrones.state.EstadoDisponible;
import patrones.state.IEstado;

public class Ambulancia extends ObservableAbstracto {
	IEstado estado; // representa estado actual de la ambulancia
	private volatile boolean simulacionActiva;
	
	public Ambulancia() {
		super();
		this.estado = new EstadoDisponible(this); // estado inicial de la ambulancia
	}

	/**
	 * Metodo que cambia el estado actual de la ambulancia por el nuevo que se manda
	 * @par estadoNuevo: estado al que quiero que pase la ambulancia
	 */
	
	public synchronized void setEstado(IEstado estadoNuevo) {
	    IEstado estadoViejo = this.estado;
	    this.estado = estadoNuevo;
	    this.firePropertyChange(Acciones.ESTADO, estadoViejo, estadoNuevo); // notifico el cambio
	}
	
	/**
	 * Metodos sincronizados para las solicitudes, organizan las llamadas al recurso compartido
	 */

	public synchronized void solicitarAtencionDomicilio(Asociado asociado) throws InterruptedException {
		this.estado.atencionADomicilio(); // transicion
	    this.firePropertyChange(Acciones.QUITAR_LLAMADO, null, asociado);
	} 
	
	public synchronized void solicitarTraslado(Asociado asociado) throws InterruptedException {
		this.estado.trasladoAClinica();
	    this.firePropertyChange(Acciones.QUITAR_LLAMADO, null, asociado);
	}
	
	public synchronized void solicitarMantenimiento(Operario operario) throws InterruptedException {
		this.estado.mantenimiento();
	    this.firePropertyChange(Acciones.QUITAR_LLAMADO, null, operario);
	}
	
	public synchronized void retornoAutomatico(EventoRetorno evt) throws InterruptedException {
		this.estado.retorno();
		this.firePropertyChange(Acciones.QUITAR_LLAMADO, null, evt);
	}

	public boolean isSimulacionActiva() {
		return simulacionActiva;
	}

	public void finalizarSimulacion() {
		this.simulacionActiva = false;
	}
	
	public void activarSimulacion() {
		this.simulacionActiva = true;
	}
	
	public void informarSolicitudAnulada(String mensaje) {
		this.firePropertyChange(Acciones.INFORMAR, null, mensaje);
	}
	
	public void notificarEstadoInicial() {
		this.firePropertyChange(Acciones.ESTADO, null, this.estado);
	}
}

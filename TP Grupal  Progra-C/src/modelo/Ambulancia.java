package modelo;

import patrones.observer.ObservableAbstracto;
import patrones.state.EstadoDisponible;
import patrones.state.EstadoRegresaSinPaciente;
import patrones.state.IEstado;
import util.Acciones;

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
	    notifyAll();
	}
	
	/**
	 * Metodos sincronizados para las solicitudes, organizan las llamadas al recurso compartido
	 */

	public synchronized void solicitarAtencionDomicilio(Asociado asociado) throws InterruptedException {
		this.firePropertyChange(Acciones.NUEVO_LLAMADO, null, new Llamado(asociado, "traslado a clinica"));
	   while (!(this.estado instanceof EstadoDisponible || this.estado instanceof EstadoRegresaSinPaciente)) {
	        wait();
	    }
		this.estado.atencionADomicilio(); // transicion
	    this.firePropertyChange(Acciones.QUITAR_LLAMADO, null, asociado);
	} 
	
	public synchronized void solicitarTraslado(Asociado asociado) throws InterruptedException {
		this.firePropertyChange(Acciones.NUEVO_LLAMADO, null, new Llamado(asociado, "atencion a domicilio"));
	    while (!(this.estado instanceof EstadoDisponible || this.estado instanceof EstadoRegresaSinPaciente)) {
	        wait();
	    }
		this.estado.trasladoAClinica();
	    this.firePropertyChange(Acciones.QUITAR_LLAMADO, null, asociado);
	}
	
	public synchronized void solicitarMantenimiento(Operario operario) throws InterruptedException {
		while (!(this.estado instanceof EstadoDisponible)) {
	        wait();
	    }
		this.estado.mantenimiento();
	    this.firePropertyChange(Acciones.QUITAR_LLAMADO, null, operario);
	}
	
	public synchronized void retornoAutomatico(EventoRetorno evt) throws InterruptedException {
		//this.firePropertyChange(Acciones.NUEVO_LLAMADO, null, new Llamado(evt, "traslado a clinica"));
		this.estado.retorno();
		//this.firePropertyChange(Acciones.QUITAR_LLAMADO, null, evt);
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

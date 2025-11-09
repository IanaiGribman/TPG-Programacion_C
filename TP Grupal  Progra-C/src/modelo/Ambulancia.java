package modelo;

import Util.Acciones;
import patrones.observer.ObservableAbstracto;
import patrones.state.EstadoDisponible;
import patrones.state.EstadoRegresaSinPaciente;
import patrones.state.IEstado;

public class Ambulancia extends ObservableAbstracto {
	IEstado estado; // representa estado actual de la ambulancia
	private volatile boolean ocupado;
	private volatile boolean simulacionActiva;
	
	public Ambulancia() {
		super();
		this.estado = new EstadoDisponible(this); // estado inicial de la ambulancia
		this.firePropertyChange(Acciones.ESTADO, null, this.estado);
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
	  
		this.firePropertyChange(Acciones.NUEVO_LLAMADO, null, new Llamado(asociado, "atencion a domicilio"));
		// espera si no son los estados correspondientes
	    while (this.ocupado) {
	        wait();
	    }
	    
	    this.ocupado = true;
	    this.firePropertyChange(Acciones.QUITAR_LLAMADO, null, asociado);
	    this.estado.atencionADomicilio(); // transicion
	    this.ocupado = false;
	    notifyAll(); // despierto hilos que podrian estar esperando
	} 
	
	public synchronized void solicitarTraslado(Asociado asociado) throws InterruptedException {
		this.firePropertyChange(Acciones.NUEVO_LLAMADO, null, new Llamado(asociado, "traslado a clinica"));
	    while (this.ocupado) {
	        wait();
	    }
	    
	    this.ocupado = true;
	    this.firePropertyChange(Acciones.QUITAR_LLAMADO, null, asociado);
	    this.estado.trasladoAClinica();
	    this.ocupado = false;
	    notifyAll();
	}
	
	public synchronized void solicitarMantenimiento(Operario operario) throws InterruptedException {

		this.firePropertyChange(Acciones.NUEVO_LLAMADO, null, new Llamado(operario, "traslado a clinica"));
	    while (this.ocupado) {
	        wait();
	    }
	    
	    this.ocupado = true;
	    this.firePropertyChange(Acciones.QUITAR_LLAMADO, null, operario);
	    this.estado.mantenimiento();
	    this.ocupado = false;
	    notifyAll();
	}
	
	public synchronized void retornoAutomatico(EventoRetorno evt) throws InterruptedException{
	    
		this.firePropertyChange(Acciones.NUEVO_LLAMADO, null, new Llamado(evt, "retorno a clinica"));
		while (this.ocupado)
			wait();
		
		this.ocupado = true;
		this.firePropertyChange(Acciones.QUITAR_LLAMADO, null, evt);
		this.estado.retorno();
		this.ocupado = false;
	    notifyAll();
	}

	public boolean isOcupado() {
		return ocupado;
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
}

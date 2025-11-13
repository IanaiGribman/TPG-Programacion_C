package modelo.simulacion;

import java.util.Observable;

import patrones.state.EstadoDisponible;
import patrones.state.IEstado;
import util.Acciones;

/**
 * Es el recurso compartido y la clase Contexto en el patron state
 */
public class Ambulancia extends Observable {
	IEstado estado; // representa estado actual de la ambulancia
	private boolean simulacionActiva;
	private boolean hayHilosActivos;
	
	public Ambulancia() {
		this.estado = new EstadoDisponible(this); // estado inicial de la ambulancia
		this.simulacionActiva = false;
	}
	
	public synchronized void setEstado(IEstado estado) {
		this.estado = estado;
		setChanged();
		notifyObservers(new NotificacionSimulacion(Acciones.ESTADO, estado.toString()));
		notifyAll();
	}
	
	public synchronized void solicitarAtencionADomicilio(Solicitante solicitante) throws InterruptedException {
		
		while (!this.estado.puedeAtencionADomicilio())
			wait();
		
		this.estado.atencionADomicilio();
	}
	
	public synchronized void solicitarTrasladoAClinica(Solicitante solicitante) throws InterruptedException {		
		while (!this.estado.puedeTrasladoAClinica())
			wait();
		
		this.estado.trasladoAClinica();
	}
	
	public synchronized void solicitarRetorno(Solicitante solicitante) throws InterruptedException {		
		while (!this.estado.puedeRetorno())
			wait();
		
		this.estado.retorno();
	}
	
	public synchronized void solicitarMantenimiento(Solicitante solicitante) throws InterruptedException {
		while (!this.estado.puedeMantenimiento())
			wait();
		
		this.estado.mantenimiento();
	}
	
	/**
	 * Pone en false el flag de simulacion activa
	 */
	public synchronized void finalizarSimulacion() {
		this.simulacionActiva = false;
	}
	
	public synchronized boolean isSimulacionActiva() {
		return simulacionActiva;
	}
	
	public synchronized boolean hayHilosActivos() {
		return hayHilosActivos;
	}

	/**
	 * Pone en false el flag de que hay hilos activos
	 */
	public synchronized void finHilosActivos() {
		this.hayHilosActivos = false;
	}

	/**
	 * Pone en true todos los flags
	 */
	public void activarSimulacion() {
		this.simulacionActiva = true;
		this.hayHilosActivos = true;
	}
	
	public IEstado getEstado() {
		return estado;
	}
}

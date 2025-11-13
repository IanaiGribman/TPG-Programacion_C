package modelo.simulacion;

import java.util.Observable;

import patrones.state.EstadoDisponible;
import patrones.state.IEstado;
import util.Acciones;

public class Ambulancia extends Observable {
	IEstado estado; // representa estado actual de la ambulancia
	private boolean simulacionActiva;
	
	public Ambulancia() {
		this.estado = new EstadoDisponible(this); // estado inicial de la ambulancia
		this.simulacionActiva = false;
	}
	
	public synchronized void setEstado(IEstado estado) {
		this.estado = estado;
		setChanged();
		notifyObservers(new NotificacionSimulacion(Acciones.ESTADO, estado));
		notifyAll();
	}
	
	public synchronized void solicitarAtencionADomicilio(Solicitante solicitante) throws InterruptedException {
		if (!this.estado.puedeAtencionADomicilio()) {
			setChanged();
			notifyObservers(new NotificacionSimulacion(Acciones.INFORMAR, "No se puede cumplir la solicitud de " + solicitante.getNombre()));
		}
		
		while (this.simulacionActiva && !this.estado.puedeAtencionADomicilio())
			wait();
		
		this.estado.atencionADomicilio();
	}
	
	public synchronized void solicitarTrasladoAClinica(Solicitante solicitante) throws InterruptedException {
		if (!this.estado.puedeTrasladoAClinica()) {
			setChanged();
			notifyObservers(new NotificacionSimulacion(Acciones.INFORMAR, "No se puede cumplir la solicitud de " + solicitante.getNombre()));
		}
		
		while (this.simulacionActiva && !this.estado.puedeTrasladoAClinica())
			wait();
		
		this.estado.trasladoAClinica();
	}
	
	public synchronized void solicitarRetorno(Solicitante solicitante) throws InterruptedException {
		if (!this.estado.puedeRetorno()) {
			setChanged();
			notifyObservers(new NotificacionSimulacion(Acciones.INFORMAR, "No se puede cumplir la solicitud de " + solicitante.getNombre()));
		}
		
		while (this.simulacionActiva && !this.estado.puedeRetorno())
			wait();
		
		this.estado.retorno();
	}
	
	public synchronized void solicitarMantenimiento(Solicitante solicitante) throws InterruptedException {
		if (!this.estado.puedeMantenimiento()) {
			setChanged();
			notifyObservers(new NotificacionSimulacion(Acciones.INFORMAR, "No se puede cumplir la solicitud de " + solicitante.getNombre()));
		}
		
		while (this.simulacionActiva && !this.estado.puedeMantenimiento())
			wait();
		
		this.estado.mantenimiento();
	}
	
	public synchronized void finalizarSimulacion() {
		this.simulacionActiva = false;
		notifyAll();
	}
	
	public synchronized boolean isSimulacionActiva() {
		return simulacionActiva;
	}
	
	public void activarSimulacion() {
		this.simulacionActiva = true;
	}
	
	public IEstado getEstado() {
		return estado;
	}
}

package modelo;

import java.util.Observable;

import modelo.simulacion.NotificacionSimulacion;
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
	
	public synchronized void solicitarAtencionADomicilio(String nombreSolicitante) throws InterruptedException {
		while (this.simulacionActiva && !this.estado.atencionADomicilio()) {
			setChanged();
			notifyObservers(new NotificacionSimulacion(Acciones.INFORMAR, "No se puede cumplir la solicitud de " + nombreSolicitante));
			wait();
		}
	}
	
	public synchronized void solicitarTrasladoAClinica(String nombreSolicitante) throws InterruptedException {
		while (this.simulacionActiva && !this.estado.trasladoAClinica()) {
			setChanged();
			notifyObservers(new NotificacionSimulacion(Acciones.INFORMAR, "No se puede cumplir la solicitud de " + nombreSolicitante));
			wait();
		}
	}
	
	public synchronized void solicitarRetorno(String nombreSolicitante) throws InterruptedException {
		while (this.simulacionActiva && !this.estado.retorno()) {
			setChanged();
			notifyObservers(new NotificacionSimulacion(Acciones.INFORMAR, "No se puede cumplir la solicitud de " + nombreSolicitante));
			wait();
		}
	}
	
	public synchronized void solicitarMantenimiento(String nombreSolicitante) throws InterruptedException {
		while (this.simulacionActiva && !this.estado.mantenimiento()) {
			setChanged();
			notifyObservers(new NotificacionSimulacion(Acciones.INFORMAR, "No se puede cumplir la solicitud de " + nombreSolicitante));
			wait();
		}
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

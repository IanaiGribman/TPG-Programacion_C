package modelo.simulacion;

import util.Acciones;
import util.Util;

/**
 * Clase Sistema, corresponde al solicitante del retorno automatico de la Ambulancia a la clinica
 */
public class EventoRetorno extends Solicitante {

	public EventoRetorno(Ambulancia ambulancia) {
		super(ambulancia);
	}

	@Override
	public String getNombre() {
		return "Sistema";
	}

	@Override
	public void run() {
		try {
			String mensaje;
			while (this.ambulancia.hayHilosActivos())
			{
				Util.tiempoMuerto();
				mensaje = this.getNombre() +  " solicita retorno a clinica";
				this.setChanged();
				this.notifyObservers(new NotificacionSimulacion(Acciones.NUEVO_LLAMADO, mensaje));
				Util.tiempoMuerto();
				this.ambulancia.solicitarRetorno(this);
				this.setChanged();
				this.notifyObservers(new NotificacionSimulacion(Acciones.QUITAR_LLAMADO, mensaje));
				
				Util.tiempoMuerto();
			}
			this.setChanged();
			this.notifyObservers(new NotificacionSimulacion(Acciones.NO_HAY_HILOS));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}

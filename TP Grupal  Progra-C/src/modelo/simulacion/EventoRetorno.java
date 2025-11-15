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
	
	private void llamarAmbulancia() throws InterruptedException {
		Util.tiempoMuerto();
		String mensaje = this.getNombre() +  " solicita retorno a clinica";
		this.setChanged();
		this.notifyObservers(new NotificacionSimulacion(Acciones.NUEVO_LLAMADO, mensaje));
		Util.tiempoMuerto();
		this.ambulancia.solicitarRetorno(this);
		this.setChanged();
		this.notifyObservers(new NotificacionSimulacion(Acciones.QUITAR_LLAMADO_NUEVOS_LLAMADOS, mensaje));
	}

	@Override
	public void run() {
		try {
			while (this.ambulancia.hayHilosActivos())
			{
				llamarAmbulancia();
				Util.tiempoMuerto();
			}
			//llamarAmbulancia();
			//llamarAmbulancia();//una para regresar sin paciente (por las dudas) y la otra para terminar y estar disponible
			this.setChanged();
			this.notifyObservers(new NotificacionSimulacion(Acciones.NO_HAY_HILOS));
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}

package modelo.simulacion;

import modelo.Ambulancia;
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
			Llamado llamado;
			while (this.ambulancia.isSimulacionActiva())
			{
				Util.tiempoMuerto();
				llamado = new Llamado(this, "retorno a clinica");
				this.setChanged();
				this.notifyObservers(new NotificacionSimulacion(Acciones.NUEVO_LLAMADO, llamado));
				Util.tiempoMuerto();
				this.ambulancia.solicitarRetorno(getNombre());
				
				if (this.ambulancia.isSimulacionActiva()) {
					this.setChanged();
					this.notifyObservers(new NotificacionSimulacion(Acciones.QUITAR_LLAMADO, llamado));
				}
				
				Util.tiempoMuerto();
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}

package modelo;

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
			while (this.ambulancia.isSimulacionActiva())
			{
				this.firePropertyChange(Acciones.NUEVO_LLAMADO, null, new Llamado(this, "retorno a clinica"));
				Util.tiempoMuerto();
				this.ambulancia.retornoAutomatico(this);
				Util.tiempoMuerto();
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}

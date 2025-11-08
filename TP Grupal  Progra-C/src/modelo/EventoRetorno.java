package modelo;

import Util.Util;

/**
 * Clase Sistema, corresponde al solicitante del retorno automatico de la Ambulancia a la clinica
 */
public class EventoRetorno extends Solicitante {

	private int cantSolicitudes;
	
	public EventoRetorno(Ambulancia ambulancia, int cantSolicitudes) {
		super(ambulancia);
		this.cantSolicitudes = cantSolicitudes;
	}

	@Override
	public String getNombre() {
		return "Sistema";
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < this.cantSolicitudes && !Thread.currentThread().isInterrupted(); i++)
			{
				Util.tiempoMuerto();
				this.ambulancia.retornoAutomatico();
				Util.tiempoMuerto();
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}

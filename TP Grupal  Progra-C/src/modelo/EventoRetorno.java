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
			int i = 0;
			while (this.ambulancia.isSimulacionActiva() && i < this.cantSolicitudes)
			{
				Util.tiempoMuerto();
				this.ambulancia.retornoAutomatico(this);
				Util.tiempoMuerto();
				i++;
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}

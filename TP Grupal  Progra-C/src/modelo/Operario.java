package modelo;

import Util.Util;

/**
 *  Clase hija de Solicitante, es uno de los que puede pedir el recurso compartido
 */
public class Operario extends Solicitante {

	public Operario(Ambulancia ambulancia) {
		super(ambulancia);
	}

	@Override
	public String getNombre() {
		return "Operario";
	}

	
	@Override
	public void run() {
		try {
			if (this.ambulancia.isSimulacionActiva()) {
				Util.tiempoMuerto(); // espera entre intentos de pedir mantenimiento
				this.ambulancia.solicitarMantenimiento(this);
				Util.tiempoMuerto(); // tiempo despues de realizar mantenimiento
			}
		} catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
	}
	
}

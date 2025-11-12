package modelo.simulacion;

import modelo.Ambulancia;
import util.Acciones;
import util.Util;

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
				Llamado llamado = new Llamado(this, "retorno a clinica");
				this.setChanged();
				this.notifyObservers(new NotificacionSimulacion(Acciones.NUEVO_LLAMADO, llamado));
				Util.tiempoMuerto();
				this.ambulancia.solicitarMantenimiento(getNombre());
				
				if (this.ambulancia.isSimulacionActiva()) {
					this.setChanged();
					this.notifyObservers(new NotificacionSimulacion(Acciones.QUITAR_LLAMADO, llamado));
				}
			}
		} catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
	}
	
}

package modelo.simulacion;

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
				this.ambulancia.solicitarMantenimiento(this);
				
				if (this.ambulancia.isSimulacionActiva()) {
					this.setChanged();
					this.notifyObservers(new NotificacionSimulacion(Acciones.QUITAR_LLAMADO, llamado));
					this.setChanged();
					this.notifyObservers(new NotificacionSimulacion(Acciones.QUITAR_SOLICITANTE_ACTIVO, llamado));//no necesita nuevo valor, podria hacerse una clase padre de notificacion que no tenga un valor, solo una accion/mensaje
				}
			}
		} catch(InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
	}
	
}

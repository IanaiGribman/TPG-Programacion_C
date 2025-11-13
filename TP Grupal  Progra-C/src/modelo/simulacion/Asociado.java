package modelo.simulacion;

import modelo.IPersona;
import persistencia.AsociadoDTO;
import util.Acciones;
import util.Util;

public class Asociado extends Solicitante implements IPersona
{
	private String dni;
	private String nombre;
	private int cantSolicitudes;
	
	public Asociado(Ambulancia ambulancia, int cantSolicitudes, AsociadoDTO dto) {
		super(ambulancia);
		this.cantSolicitudes = cantSolicitudes;
		this.dni = dto.getDni();
		this.nombre = dto.getNombre();
	}

	@Override
	public String getNombre() {
		return this.nombre;
	}
	
	@Override
	public String getDni() {
		return this.dni;
	}

	@Override
	public void run() {
		try {
			int i = 0;
			while (this.ambulancia.isSimulacionActiva() && i < this.cantSolicitudes)
			{
				boolean traslado = Math.random() < 0.5; // elegir tipo de solicitud (50/50)
				Util.tiempoMuerto();
				String mensaje = this.nombre;
				if (traslado) {
					mensaje += " solicita traslado a clinica";
					this.setChanged();
					this.notifyObservers(new NotificacionSimulacion(Acciones.NUEVO_LLAMADO, mensaje));
					this.ambulancia.solicitarTrasladoAClinica(this);
				}
				else {
					mensaje += " solicita atencion a domicilio";
					this.setChanged();
					this.notifyObservers(new NotificacionSimulacion(Acciones.NUEVO_LLAMADO, mensaje));
					this.ambulancia.solicitarAtencionADomicilio(this);
				}
				this.setChanged();
				this.notifyObservers(new NotificacionSimulacion(Acciones.QUITAR_LLAMADO, mensaje));
				Util.tiempoMuerto(); // tiempo entre solicitudes	
				i++;
			}
			
			this.setChanged();
			this.notifyObservers(new NotificacionSimulacion(Acciones.QUITAR_SOLICITANTE_ACTIVO));//hay que hacer la clase padre como en operario
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
}

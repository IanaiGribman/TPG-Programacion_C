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
				Llamado llamado = null;
				Util.tiempoMuerto();
				if (traslado) {
					llamado = new Llamado(this, "traslado a clinica");
					this.setChanged();
					this.notifyObservers(new NotificacionSimulacion(Acciones.NUEVO_LLAMADO, llamado));
					this.ambulancia.solicitarTrasladoAClinica(this);
				}
				else {
					llamado = new Llamado(this, "atencion a domicilio");
					this.setChanged();
					this.notifyObservers(new NotificacionSimulacion(Acciones.NUEVO_LLAMADO, llamado));
					this.ambulancia.solicitarAtencionADomicilio(this);
				}
				this.setChanged();
				this.notifyObservers(new NotificacionSimulacion(Acciones.QUITAR_LLAMADO, llamado));
				Util.tiempoMuerto(); // tiempo entre solicitudes	
				i++;
			}
			
			this.setChanged();
			this.notifyObservers(new NotificacionSimulacion(Acciones.QUITAR_SOLICITANTE_ACTIVO, null));//hay que hacer la clase padre como en operario
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
}

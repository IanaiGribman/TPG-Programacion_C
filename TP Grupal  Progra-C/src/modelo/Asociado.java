package modelo;

import persistencia.AsociadoDTO;
import Util.Util;

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
				
				if (traslado) 
					this.ambulancia.solicitarTraslado(this);
				else
					this.ambulancia.solicitarAtencionDomicilio(this);
				
				Util.tiempoMuerto(); // tiempo entre solicitudes	
				i++;
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
}

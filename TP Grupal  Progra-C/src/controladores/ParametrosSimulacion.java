package controladores;

/**
 * Clase que contiene la cantidad maxima y minima de solicitudes que puede hacer un asociado.
 *  Utilizada para poder almacenarla y/o accederla en un archivo XML
 */
public class ParametrosSimulacion {
	private int cantMaximaSolicitudes;
	private int cantMinimaSolicitudes;

	public ParametrosSimulacion() {
		super();
	}

	public int getCantMaximaSolicitudes() {
		return cantMaximaSolicitudes;
	}

	public void setCantMaximaSolicitudes(int cantMaximaSolicitudes) {
		this.cantMaximaSolicitudes = cantMaximaSolicitudes;
	}

	public int getCantMinimaSolicitudes() {
		return cantMinimaSolicitudes;
	}

	public void setCantMinimaSolicitudes(int cantMinimaSolicitudes) {
		this.cantMinimaSolicitudes = cantMinimaSolicitudes;
	}

	
	
}

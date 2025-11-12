package modelo.simulacion;

public class NotificacionSimulacion {
	private String nombreAccion;
	private Object nuevoValor;
	
	public NotificacionSimulacion(String nombreAccion, Object nuevoValor) {
		assert nombreAccion != null: "el nombre de la accion no debe ser null";
		assert nuevoValor != null: "el objeto transmitido en la notificacion no debe ser null";
		this.nombreAccion = nombreAccion;
		this.nuevoValor = nuevoValor;
	}

	public String getNombreAccion() {
		return nombreAccion;
	}

	public Object getNuevoValor() {
		return nuevoValor;
	}
}

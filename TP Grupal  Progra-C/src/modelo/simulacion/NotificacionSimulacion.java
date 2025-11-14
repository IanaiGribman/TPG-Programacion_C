package modelo.simulacion;

public class NotificacionSimulacion {
	private String nombreAccion;
	private String mensaje;
	
	
	public NotificacionSimulacion(String nombreAccion, String mensaje) {
		assert nombreAccion != null: "el nombre de la accion no debe ser null";
		assert mensaje != null: "el mensaje transmitido en la notificacion no debe ser null";
		this.nombreAccion = nombreAccion;
		this.mensaje = mensaje;;
	}
	
	public NotificacionSimulacion(String nombreAccion) {
		this(nombreAccion, null);//no salta el aserto si el mensaje es null?
	}

	public String getNombreAccion() {
		return nombreAccion;
	}

	public String getMensaje() {
		return this.mensaje;
	}
}

package modelo;

public class Llamado {
	private Solicitante solicitante;
	private String mensajeLlamado;
	
	public Llamado(Solicitante solicitante, String mensajeLlamado) {
		super();
		this.solicitante = solicitante;
		this.mensajeLlamado = mensajeLlamado;
	}

	public Solicitante getSolicitante() {
		return solicitante;
	}

	public String getMensajeLlamado() {
		return mensajeLlamado;
	}

	@Override
	public String toString() {
		return this.solicitante.getNombre() + " solicita " + this.mensajeLlamado;
	}
	
}

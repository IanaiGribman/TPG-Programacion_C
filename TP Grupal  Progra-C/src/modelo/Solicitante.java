package modelo;

public abstract class Solicitante implements INombrable, Runnable {
	private Ambulancia ambulancia;

	public Solicitante(Ambulancia ambulancia) {

		this.ambulancia = ambulancia;
	}
	
}

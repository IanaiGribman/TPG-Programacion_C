package modelo;

/**
 * Clase que representa los posibles solicitantes de la ambulancia
 */

public abstract class Solicitante implements INombrable, Runnable {
	protected Ambulancia ambulancia;

	public Solicitante(Ambulancia ambulancia) {

		this.ambulancia = ambulancia;
	}
	
}

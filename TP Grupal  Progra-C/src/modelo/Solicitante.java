package modelo;

import patrones.observer.ObservableAbstracto;

/**
 * Clase que representa los posibles solicitantes de la ambulancia
 */

public abstract class Solicitante extends ObservableAbstracto implements INombrable, Runnable {
	protected Ambulancia ambulancia;

	public Solicitante(Ambulancia ambulancia) {

		this.ambulancia = ambulancia;
	}
	
}

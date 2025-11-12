package modelo.simulacion;

import java.util.Observable;

import modelo.INombrable;

/**
 * Clase que representa los posibles solicitantes de la ambulancia
 */

public abstract class Solicitante extends Observable implements INombrable, Runnable {
	protected Ambulancia ambulancia;

	public Solicitante(Ambulancia ambulancia) {

		this.ambulancia = ambulancia;
	}
	
}

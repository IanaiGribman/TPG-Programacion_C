package modelo.medicos;

import modelo.Domicilio;
import modelo.IMedico;
import modelo.Persona;

/**
 * Clase abstracta que representa al Medico mas generico de todos, 
 * es una persona e implementa las interfaces necesarias para tener
 * una matricula, una especialidad y un honorario
 */
public abstract class Medico extends Persona implements IMedico {
	private String nroMatricula;
	protected static double HonorarioBasico = 20000; //honorario comun a todos los medicos
	
	/**
	 * 
	 * @param nombre
	 * @param domicilio
	 * @param telefono
	 * @param dni
	 * @param nroMatricula
	 **/
	protected Medico(String nombre, Domicilio domicilio, String telefono, String dni, String nroMatricula) {
		super(nombre, domicilio, telefono, dni);
		this.nroMatricula = nroMatricula;
	}
	
	@Override
	public String getNroMatricula(){
		return this.nroMatricula;
	}

	public static double getHonorarioBasico() {
		return HonorarioBasico;
	}

	public static void setHonorarioBasico(double honorarioBasico) {
		HonorarioBasico = honorarioBasico;
	}
	
}

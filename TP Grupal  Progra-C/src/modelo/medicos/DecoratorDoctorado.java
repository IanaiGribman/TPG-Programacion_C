package modelo.medicos;

import modelo.IMedico;

/**
 * Clase concreta que representa a un doctor doctorado
 */
public class DecoratorDoctorado extends MedicoDecorator {
	public DecoratorDoctorado(IMedico encapsulado) {
		super(encapsulado);
	}
	
	//Devuelve el honorario para un medico doctorado sobre su especialidad
	@Override
	public double getHonorario() {
		return super.getHonorario() * 1.1;
	}

	@Override
	public String toString() {
		return super.toString() + ", posgrado=doctorado";
	}
	
	
}

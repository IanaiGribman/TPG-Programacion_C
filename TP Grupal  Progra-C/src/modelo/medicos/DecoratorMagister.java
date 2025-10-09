package modelo.medicos;

import modelo.IMedico;

/**
 * Clase concreta que representa a un doctor con magister
 */
public class DecoratorMagister extends MedicoDecorator {
	public DecoratorMagister(IMedico encapsulado) {
		super(encapsulado);
	}
	
	//Devuelve el honorario para un medico magister sobre su especialidad
	@Override
	public double getHonorario() {
		return super.getHonorario() * 1.05;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", posgrado=magister";
	}
	
}

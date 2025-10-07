package modelo.medicos;

import modelo.IMedico;

/**
 * Clase concreta que representa a un doctor residente
 */
public class DecoratorResidente extends MedicoDecorator {
	public DecoratorResidente(IMedico encapsulado) {
		super(encapsulado);
	}
	
	//Da el honorario para un residente segun su especialidad y posgrado
	@Override
	public double getHonorario() {
		return super.getHonorario() * 1.05;
	}
	
}

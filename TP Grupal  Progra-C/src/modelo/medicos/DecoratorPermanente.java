package modelo.medicos;

import modelo.IMedico;

/**
 * Clase concreta que representa a un doctor permanente
 */
public class DecoratorPermanente extends MedicoDecorator {
	public DecoratorPermanente(IMedico encapsulado) {
		super(encapsulado);
	}
	
	//Devuelve el honorario para un medico permanente sobre su especialidad y doctorado
	@Override
	public double getHonorario() {
		return super.getHonorario() * 1.1;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", contrato=permanente";
	}
	
}

package modelo.medicos;

import modelo.IMedico;

public abstract class MedicoDecorator implements IMedico {
	private IMedico encapsulado;
	
	public MedicoDecorator(IMedico encapsulado) {
		this.encapsulado = encapsulado;
	}
	
	@Override
	public String getDni() {
		return this.encapsulado.getDni();
	}
	
	@Override
	public String getEspecialidad() {
		return this.encapsulado.getEspecialidad();
	}
	
	@Override
	public String getNombre() {
		return this.encapsulado.getNombre();
	}
	
	@Override
	public String getNroMatricula() {
		return this.encapsulado.getNroMatricula();
	}
	
	@Override
	public double getHonorario() {
		return this.encapsulado.getHonorario();
	}

	@Override
	public String toString() {
		return encapsulado.toString();
	}
	
	
}

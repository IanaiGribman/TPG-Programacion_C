package patrones.state;

import modelo.Ambulancia;

public class EstadoAtiendeDomicilio implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoAtiendeDomicilio(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}

	public boolean atencionADomicilio() {
		return false;
	}
	
	public boolean trasladoAClinica() {
		return false;
	}
	
	public boolean retorno() {
		ambulancia.setEstado(new EstadoRegresaSinPaciente(ambulancia));
		return true;
	}
	
	public boolean mantenimiento() {
		return false;
	}

	@Override
	public String toString() {
		return "Atendiendo a domicilio";
	}
	
	

}

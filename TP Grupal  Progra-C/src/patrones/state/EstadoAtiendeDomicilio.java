package patrones.state;

import modelo.simulacion.Ambulancia;

public class EstadoAtiendeDomicilio implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoAtiendeDomicilio(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}

	@Override
	public void atencionADomicilio() {}
	
	@Override
	public void trasladoAClinica() {}
	
	@Override
	public void retorno() {
		ambulancia.setEstado(new EstadoRegresaSinPaciente(ambulancia));
	}
	
	@Override
	public void mantenimiento() {}

	@Override
	public String toString() {
		return "Atendiendo a domicilio";
	}

	@Override
	public boolean puedeAtencionADomicilio() {
		return false;
	}

	@Override
	public boolean puedeTrasladoAClinica() {
		return false;
	}

	@Override
	public boolean puedeRetorno() {
		return true;
	}

	@Override
	public boolean puedeMantenimiento() {
		return false;
	}
	
	

}

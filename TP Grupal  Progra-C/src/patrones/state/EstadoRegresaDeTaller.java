package patrones.state;

import Util.Acciones;
import modelo.Ambulancia;

public class EstadoRegresaDeTaller implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoRegresaDeTaller(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}

	public void atencionADomicilio() {} // permanece regresando del taller
	
	public void trasladoAClinica() {
		this.ambulancia.firePropertyChange(Acciones.ERROR,null,"No es posible realizar la solicitud en este momento, la ambulancia esta regresando del taller");
	}
	
	public void retorno() {
		this.ambulancia.setEstado(new EstadoDisponible(this.ambulancia));
	}
	
	public void mantenimiento() {
		this.ambulancia.firePropertyChange(Acciones.ERROR,null,"No es posible realizar la solicitud en este momento. la ambulancia esta regresando del taller");
	}
	
	@Override
	public String toString() {
		return "Regresando del taller";
	}

}

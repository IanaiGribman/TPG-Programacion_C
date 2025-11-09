package patrones.state;

import Util.Acciones;
import modelo.Ambulancia;

public class EstadoAtiendeDomicilio implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoAtiendeDomicilio(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}

	public void atencionADomicilio() {} // permanece atendiendo a dom
	
	public void trasladoAClinica() {
		this.ambulancia.firePropertyChange(Acciones.ERROR,null,"No es posible realizar la solicitud en este momento, se esta atendiendo un domicilio");
	}
	
	public void retorno() {
		this.ambulancia.setEstado(new EstadoRegresaSinPaciente(this.ambulancia));
	}
	
	public void mantenimiento() {
		this.ambulancia.firePropertyChange(Acciones.ERROR,null,"No es posible realizar la solicitud en este momento, se esta atendiendo un domicilio");
	}

	@Override
	public String toString() {
		return "Atendiendo a domicilio";
	}
	
	

}

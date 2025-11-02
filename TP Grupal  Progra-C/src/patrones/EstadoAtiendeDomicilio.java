package patrones;

import modelo.Ambulancia;

public class EstadoAtiendeDomicilio implements IEstado{
	
	private Ambulancia ambulancia; // atributo de la clase contexto
	
	public EstadoAtiendeDomicilio(Ambulancia ambulancia) {
		super();
		this.ambulancia = ambulancia;
	}

	public void atencionADomicilio() {} // permanece atendiendo a dom
	
	public void trasladoAClinica() {
		// informa PASARLE A CONTROLADOR
	}
	
	public void retorno() {
		this.ambulancia.setEstado(new EstadoRegresaSinPaciente(this.ambulancia));
	}
	
	public void mantenimiento() {
		// informa PASARLE A CONTROLADOR
	}

}

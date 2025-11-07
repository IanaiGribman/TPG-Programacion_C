package modelo;

import patrones.IEstado;
import patrones.observer.ObservableAbstracto;
import patrones.EstadoDisponible;
import patrones.EstadoRegresaSinPaciente;

public class Ambulancia extends ObservableAbstracto {
	IEstado estado; // representa estado actual de la ambulancia
	
	public Ambulancia() {
		super();
		this.estado = new EstadoDisponible(this); // estado inicial de la ambulancia
	}

	/**
	 * Metodo que cambia el estado actual de la ambulancia por el nuevo que se manda
	 * @par estadoNuevo: estado al que quiero que pase la ambulancia
	 */
	
	public synchronized void setEstado(IEstado estadoNuevo) {
	    IEstado estadoViejo = this.estado;
	    this.estado = estadoNuevo;
	    
	    this.firePropertyChange("estado", estadoViejo, estadoNuevo); // notifico el cambio
	    notifyAll(); //despierto los demas hilos
	}
	
	// Transiciones entre estados
	public void atencionADomicilio()
	{
		this.estado.atencionADomicilio();
	}
	
	public void trasladoAClinica()
	{
		this.estado.trasladoAClinica();
	}
	
	public void retorno()
	{
		this.estado.retorno();
	}
	
	public void mantenimiento()
	{
		this.estado.mantenimiento();
	}
	
	/**
	 * Metodos sincronizados para las solicitudes, organizan las llamadas al recurso compartido
	 */

	public synchronized void solicitarAtencionDomicilio(Asociado asociado) throws InterruptedException {
	  
		// espera si no son los estados correspondientes
	    while (!(this.estado instanceof EstadoDisponible || this.estado instanceof EstadoRegresaSinPaciente)) {
	        wait();
	    }
	    
	    this.estado.atencionADomicilio(); // transicion
	    this.firePropertyChange("estado", null, this.estado); // notifico al observable
	    notifyAll(); // despierto hilos que podrian estar esperando
	} 
	
	public synchronized void solicitarTraslado(Asociado asociado) throws InterruptedException {
	    while (!(this.estado instanceof EstadoDisponible || this.estado instanceof EstadoRegresaSinPaciente)) {
	        wait();
	    }

	    this.estado.trasladoAClinica();
	    this.firePropertyChange("estado", null, this.estado);
	    notifyAll();
	}
	
	public synchronized void solicitarMantenimiento(Operario operario) throws InterruptedException {

	    while (!(this.estado instanceof EstadoDisponible)) {
	        wait();
	    }

	    this.estado.mantenimiento();
	    this.firePropertyChange("estado", null, this.estado);
	    notifyAll();
	}
	
	public synchronized void retornoAutomatico() throws InterruptedException{
	    
		// mientras no se pueda retornar, esperar
		while(!(this.estado instanceof EstadoDisponible || this.estado instanceof EstadoRegresaSinPaciente))
			wait();
		
		this.estado.retorno();
	    this.firePropertyChange("estado", null, this.estado);
	    notifyAll();
	}

	@Override
	public String toString() {
		return "Ambulancia [estado=" + estado + "]";
	}
	
	
}

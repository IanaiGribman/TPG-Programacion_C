package modelo.simulacion;

import java.util.List;

import controladores.IVistaSimulacion;
import controladores.ManagerXMLSimulacion;
import controladores.ParametrosSimulacion;
import persistencia.AsociadoDTO;
import util.Util;

/**
 * Crea los hilos y es responsable de activar o
 * desactivar la simulacion
 */
public class ModuloSimulacion {
	private Ambulancia ambulancia;
	private IVistaSimulacion vista;
	private String direccionXMLSimulacion = "src/controladores/SimulacionConfig.xml";
	private OjoSimulacion ojoSimulacion;
	private boolean sePuedeVolver;

	public ModuloSimulacion(Ambulancia ambulancia, IVistaSimulacion vista) {
		assert ambulancia != null : "la referencia a la ambulancia no debe ser null";
		assert vista != null : "la referencia a la vista no debe ser null";

		this.ambulancia = ambulancia;
		this.vista = vista;
		this.ojoSimulacion = new OjoSimulacion(this.ambulancia, this.vista, this);
	}

	/**
	 * Pone en true el flag de simulacion del recurso compartido y crea los hilos al
	 * iniciar la simulacion: hilos de los asociados y el de retorno a clinica
	 * 
	 * @param lista
	 */
	public void iniciarSimulacion() {
		List<AsociadoDTO> lista = vista.getListaAsociadosSimulacion();

		assert lista != null : "la lista de asociados DTO no debe ser null";

		vista.cambiarEstado(ambulancia.getEstado().toString());
		this.ambulancia.activarSimulacion();
		this.crearHilosAsociados(lista);
		EventoRetorno evt = new EventoRetorno(this.ambulancia);
		
		//no
		this.ojoSimulacion.agregarEventoRetorno(evt);
		this.crearHilo(evt);
		sePuedeVolver = false;
	}

	/**
	 * Crea un hilo de operario y lo inicia
	 */
	public void crearHiloOperario() {
		Solicitante operario = new Operario(this.ambulancia);
		this.ojoSimulacion.agregarSolicitanteLista(operario);
		this.crearHilo(operario);
	}

	/**
	 * Pone en false el flag de simulacion activa en el recurso compartido
	 */
	public void finalizarSimulacion() {
		this.ambulancia.finalizarSimulacion();
		vista.cambiarEstadoBotonMantenimiento(false);
		vista.cambiarEstadoBotonFinalizar(false);
	}
	
	/**
	 * Pone en false el flag que indica que hay hilos (ademas del retorno automatico) que utilizan la ambulancia
	 */
	public void finHilos() {
		this.ambulancia.finHilosActivos();
	}
	
	public void setSePuedeVolver(boolean sePuedeVolver) {
		this.sePuedeVolver = sePuedeVolver;
	}
	
	public boolean sePuedeVolver() {
		System.out.println(sePuedeVolver);
		return sePuedeVolver;
	}
	
	/**
	 * Crea los hilos de los asociados y los inicia
	 * 
	 * @param lista de asociados DTO
	 */
	protected void crearHilosAsociados(List<AsociadoDTO> lista) {
		ParametrosSimulacion parametrosSimulacion = ManagerXMLSimulacion.leerSimulacionXML(direccionXMLSimulacion);
		int maxSolicitudes = parametrosSimulacion.getCantMaximaSolicitudes();
		int minSolicitudes = parametrosSimulacion.getCantMinimaSolicitudes();

		for (AsociadoDTO asocDTO : lista) {
			Solicitante asociado = new Asociado(ambulancia, Util.numeroAleatorio(minSolicitudes, maxSolicitudes), asocDTO);
			this.ojoSimulacion.agregarSolicitanteLista(asociado);
			this.crearHilo(asociado);
		}
	}

	/**
	 * Dado un solicitante, crea el hilo y lo inicia
	 * 
	 * @param solicitante
	 */
	protected void crearHilo(Solicitante solicitante) {
		new Thread(solicitante).start();
	}
	
}

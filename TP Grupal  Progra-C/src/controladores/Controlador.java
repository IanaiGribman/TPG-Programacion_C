package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import modelo.Ambulancia;
import modelo.Asociado;
import modelo.EventoRetorno;
import modelo.ModuloAsociados;
import modelo.Operario;
import modelo.Solicitante;
import persistencia.AsociadoDTO;
import util.Acciones;
import vista.IVista;

/**
 * Controlador que escucha a la vista y a los modelos (Ambulancia y ModuloAsociados)
 */
public class Controlador extends WindowAdapter implements ActionListener, PropertyChangeListener{

	private IVista vista;
	private Ambulancia ambulancia;
	private ModuloAsociados moduloAsociados;
	private String direccionXMLInicializacion = "src/controladores/InicializacionConfig.xml";
	private String direccionXMLSimulacion = "src/controladores/SimulacionConfig.xml";
	
	
	
	public Controlador(IVista vista, Ambulancia ambulancia, ModuloAsociados moduloAsociados) {
		assert vista != null && ambulancia != null && moduloAsociados != null;
		this.vista = vista;
		this.ambulancia = ambulancia;
		this.moduloAsociados = moduloAsociados;
		//setteo que el controlador observe estos objetos
		this.vista.setActionListener(this);
		this.vista.setWindowListener(this);
		this.ambulancia.addPropertyChangeListener(this);
		this.moduloAsociados.addPropertyChangeListener(this);
		//abro la conexion
		this.moduloAsociados.abrirConexion();
		//crea tabla si no existe
		this.moduloAsociados.crearTablaAsociados();
		//que el modelo le "pase" la lista de asociados a la vista
		this.moduloAsociados.leerAsociados();
		// que la ambulancia notifique el estado inicial
		this.ambulancia.notificarEstadoInicial();
	}
	
	/**
	 * Delega las notificaciones de cambio del modelo a la vista
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.vista.propertyChange(evt);
	}
	
	/**
	 * Controla a los modelos en base a los eventos recibidos de los botones de la vista
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		
		case Acciones.GESTION: {
			this.ambulancia.finalizarSimulacion();
			//vista.mostrarGestion();
			break;
		}
		
		case Acciones.REGISTRAR: {
			AsociadoDTO asociadoNuevo = vista.getAsociadoNuevo();
			this.moduloAsociados.agregarAsociado(asociadoNuevo);
			break;
		}
		
		case Acciones.ELIMINAR: {
			this.moduloAsociados.eliminarAsociado(vista.getDniAEliminar());
			break;
		}
		
		case Acciones.INICIALIZAR_CONSULTA: {
			this.vista.displayConfirmarInicializacion();
			break;
		}
		case Acciones.INICIALIZAR_CONFIRMADO:{
			this.vista.vaciarListasAsoc();
			this.moduloAsociados.reiniciarTablaAsociados();
			Collection<AsociadoDTO> asociadosInicializacion = ManagerXMLInicializacion.leerAsociadosInicializacionXML(direccionXMLInicializacion);
			for(AsociadoDTO asociado : asociadosInicializacion)
				this.moduloAsociados.agregarAsociado(asociado);
			break;
			
		}
		
		case Acciones.SIMULACION: {
			Random r = new Random();
			vista.mostrarSimulacion();
			this.ambulancia.activarSimulacion();
			this.crearHilosAsociados(this.vista.getListaAsociadosSimulacion());
			this.crearHilo(new EventoRetorno(this.ambulancia));
			break;
		}
		
		case Acciones.MANTENIMIENTO: {
			this.crearHilo(new Operario(this.ambulancia));
			break;
		}
		}

	}

	/**
	 * Cierra la conexion de base de datos cuando la ventana se cierra
	 */
	@Override
    public void windowClosing(WindowEvent e) {
        this.moduloAsociados.cerrarConexion();
    }
	
	protected void crearHilosAsociados(List<AsociadoDTO> lista) {
		ParametrosSimulacion parametrosSimulacion = ManagerXMLSimulacion.leerSimulacionXML(direccionXMLSimulacion);
		int maxSolicitudes = parametrosSimulacion.getCantMaximaSolicitudes();
		int minSolicitudes = parametrosSimulacion.getCantMinimaSolicitudes();
		Iterator cantSolicitudes = new Random().ints(lista.size(), minSolicitudes, maxSolicitudes).iterator();
		for (AsociadoDTO asocDTO: lista)
			this.crearHilo(new Asociado(this.ambulancia, (int)cantSolicitudes.next(), asocDTO));
	}

	protected void crearHilo(Solicitante solicitante) {
		new Thread(solicitante).start();
	}
}

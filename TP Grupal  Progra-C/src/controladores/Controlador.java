package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Random;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Util.Acciones;
import modelo.Ambulancia;
import modelo.Asociado;
import modelo.EventoRetorno;
import modelo.ModuloAsociados;
import modelo.Operario;
import persistencia.AsociadoDTO;
import vista.IVista;

/**
 * Controlador que escucha a la vista y a los modelos (Ambulancia y ModuloAsociados)
 */
public class Controlador extends WindowAdapter implements ActionListener, PropertyChangeListener{

	private IVista vista;
	private Ambulancia ambulancia;
	private ModuloAsociados moduloAsociados;
	//horrible depsues lo cambio
	private int maxSolicitudes = 5;
	
	
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
		//que el modelo le "pase" la lista de asociados a la vista
		this.moduloAsociados.leerAsociados();
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
		
		case Acciones.INICIALIZAR: {
			this.vista.displayWarning("Esta accion borrara la tabla de asociados (si existe) y la creara");
			this.vista.vaciarListasAsoc();
			this.moduloAsociados.crearTablaAsociados();
			// despues veo bien para que no quede tan hardcodeado
			this.moduloAsociados.agregarAsociado(new AsociadoDTO("Gojo Satoru", "99999999"));
			this.moduloAsociados.agregarAsociado(new AsociadoDTO("Greta Thunberg", "88888888"));
			this.moduloAsociados.agregarAsociado(new AsociadoDTO("Franco Colapinto", "55555555"));
			break;
		}
		
		case Acciones.SIMULACION: {
			vista.mostrarSimulacion();
			this.ambulancia.activarSimulacion();
			this.crearHilosInicio(this.vista.getListaAsociadosSimulacion());
			break;
		}
		
		case Acciones.MANTENIMIENTO: {
			new Thread(new Operario(this.ambulancia)).start();
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
	
	protected void crearHilosInicio(List<AsociadoDTO> lista) {
		Random r = new Random();
		for (AsociadoDTO asocDTO: lista) {
			new Thread(new Asociado(this.ambulancia, r.nextInt(this.maxSolicitudes), asocDTO)).start();
		}
		new Thread(new EventoRetorno(this.ambulancia, r.nextInt(this.maxSolicitudes))).start();;
	}

}

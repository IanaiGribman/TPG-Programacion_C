package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import util.Acciones;

/**
 * Recibe los eventos producidos por los botones de la vista y delega las tareas a los controladores 
 * especificos. Tambien recibe el evento de cierre de ventana.
 */
public class Controlador extends WindowAdapter implements ActionListener {
	private ModuloSimulacion moduloSimulacion;
	private ControladorAsociados controladorAsociados;
	private IVistaControlador vista;
	
	

	public Controlador(ModuloSimulacion moduloSimulacion, ControladorAsociados controladorAsociados,
			IVistaControlador vista) {
		assert moduloSimulacion != null;
		assert controladorAsociados != null;
		assert vista != null;
		
		this.moduloSimulacion = moduloSimulacion;
		this.controladorAsociados = controladorAsociados;
		this.vista = vista;
		vista.setActionListener(this);
		vista.setWindowListener(this);
		controladorAsociados.iniciar();
		vista.mostrarGestion();
	}

	/**
	 * Gestiona los controladores de acuerdo a los eventos disparados por los botones
	 * de la vista
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		
		case Acciones.FINALIZAR_SIMULACION: {
			moduloSimulacion.finalizarSimulacion();
			break;
		}
		
		case Acciones.VOLVER_A_GESTION:{
			if(moduloSimulacion.sePuedeVolver()) {
				vista.mostrarGestion();
				controladorAsociados.abrirConexion(); 
			}
			break;
		}
		
		case Acciones.REGISTRAR: {
			controladorAsociados.agregarAsociadoPermanencia();
			break;
		}
		
		case Acciones.ELIMINAR: {
			controladorAsociados.eliminarAsociadoPermanencia();
			break;
		}
		
		case Acciones.INICIALIZAR_CONSULTA: {
			this.vista.displayConfirmarInicializacion();
			break;
		}
		
		case Acciones.INICIALIZAR_CONFIRMADO:{
			controladorAsociados.reiniciarInicializarTabla();
			break;
			
		}
		
		case Acciones.SIMULACION: {
			controladorAsociados.cerrarConexion();
			vista.mostrarSimulacion();
			moduloSimulacion.iniciarSimulacion();
			break;
		}
		
		case Acciones.MANTENIMIENTO: {
			moduloSimulacion.crearHiloOperario();
			break;
		}
		}
	}
	
	/**
	 * Cierra la conexion de base de datos cuando la ventana se cierra
	 */
	@Override
    public void windowClosing(WindowEvent e) {
        controladorAsociados.cerrarConexion();
    }

}

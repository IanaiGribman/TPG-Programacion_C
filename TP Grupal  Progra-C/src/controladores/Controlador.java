package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import modelo.simulacion.EventoRetorno;
import modelo.simulacion.Operario;
import persistencia.AsociadoDTO;
import util.Acciones;
import util.Util;
import vista.IVistaControlador;

public class Controlador extends WindowAdapter implements ActionListener {
	private ControladorSimulacion controladorSimulacion;
	private ControladorAsociados controladorAsociados;
	private IVistaControlador vista;
	
	
	
	public Controlador(ControladorSimulacion controladorSimulacion, ControladorAsociados controladorAsociados,
			IVistaControlador vista) {
		assert controladorSimulacion != null;
		assert controladorAsociados != null;
		assert vista != null;
		
		this.controladorSimulacion = controladorSimulacion;
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
		
		case Acciones.GESTION: {
			controladorSimulacion.finalizarSimulacion();
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
			controladorSimulacion.iniciarSimulacion();
			break;
		}
		
		case Acciones.MANTENIMIENTO: {
			controladorSimulacion.crearHiloOperario();
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

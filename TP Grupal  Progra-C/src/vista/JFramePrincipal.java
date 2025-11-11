package vista;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import modelo.Llamado;
import modelo.Solicitante;
import patrones.state.IEstado;
import persistencia.AsociadoDTO;
import util.Acciones;

public class JFramePrincipal extends JFrame implements IVista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VentanaGestion ventanaGestion;
	private VentanaSimulacion ventanaSimulacion;
	private ActionListener controlador;


	/**
	 * Create the frame.
	 */
	public JFramePrincipal() {
		ventanaSimulacion = new VentanaSimulacion();
		ventanaGestion = new VentanaGestion();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 900, 600);
		this.mostrarGestion();
	}

	public void mostrarGestion() {
		this.setTitle("Gestion de socios");
		this.contentPane = ventanaGestion;
		setContentPane(this.contentPane);
		this.revalidate();
	}

	public void mostrarSimulacion() {
		this.setTitle("Simulacion");
		this.contentPane = ventanaSimulacion;
		setContentPane(this.contentPane);
		this.revalidate();
	}

	@Override
	public String getDniAEliminar() {
		return this.ventanaGestion.getDNI();
	}
		
	/**
	 * Maneja las notificaciones de cambio en el modelo
	 * 
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case Acciones.ERROR: {
			this.displayError((String) evt.getNewValue());
			break;
		}
		
		case Acciones.INFORMAR: {
			this.displayInfo((String) evt.getNewValue());
			break;
		}
		
		case Acciones.REGISTRAR: {
			this.ventanaGestion.addAsociadoPermanencia((AsociadoDTO) evt.getNewValue());
			break;
		}
		
		case Acciones.ELIMINAR: {
			this.ventanaGestion.removeAsociadoPermanencia((String) evt.getNewValue());
			break;
		}
		
		case Acciones.CARGAR: {
			@SuppressWarnings("unchecked")
			List<AsociadoDTO> newValue = (List<AsociadoDTO>) evt.getNewValue();
			this.ventanaGestion.cargarListaAsociados(newValue);
			break;
		}
		
		case Acciones.NUEVO_LLAMADO: {
			this.ventanaSimulacion.aniadirLlamadoNuevo((Llamado) evt.getNewValue());
			break;
		}
		
		case Acciones.QUITAR_LLAMADO: {
			this.ventanaSimulacion.retirarLlamadoNuevo((Solicitante) evt.getNewValue());
			break;
		}
		
		case Acciones.ESTADO: {
			this.ventanaSimulacion.informarCambioEstado((IEstado) evt.getNewValue());
			break;
		}
		}
		// y el resto de las acciones por cada cambio
	}

	/**
	 * Agrega el action listener a los botones de cada ventana
	 */
	@Override
	public void setActionListener(ActionListener actionListener) {
		assert actionListener != null: "el action listener no puede ser null";
		this.ventanaGestion.setActionListener(actionListener);
		this.ventanaSimulacion.addActionListener(actionListener);
		this.controlador = actionListener;
	}

	@Override
	public AsociadoDTO getAsociadoNuevo() {
		return ventanaGestion.getAsociado();
	}

	@Override
	public void vaciarListasAsoc() {
		this.ventanaGestion.vaciarListas();
	}

	@Override
	public void displayWarning(String mensajeWarning) {
		this.displayPopUp("Advertencia", mensajeWarning, "Aceptar");
	}

	@Override
	public void displayError(String mensajeError) {
		this.displayPopUp("Error", mensajeError, "Aceptar");
	}
	
	public void displayInfo(String mensajeinfo) {
		this.displayPopUp("Informacion", mensajeinfo, "Aceptar");
	}
	
	protected void displayPopUp(String titulo, String mensaje, String textoBoton) {
		CustomPopUp cpu = new CustomPopUp(this);
		cpu.mostrar(titulo, mensaje, textoBoton);
	}

	@Override
	public void setWindowListener(WindowListener windowListener) {
		assert windowListener != null;
		this.addWindowListener(windowListener);
	}

	@Override
	public List<AsociadoDTO> getListaAsociadosSimulacion() {
		return this.ventanaGestion.getListaAsociadosSimulacion();
	}

	@Override
	public void displayConfirmarInicializacion() {
	ConfirmationPopUp cpu = new ConfirmationPopUp(this, controlador, Acciones.INICIALIZAR_CONFIRMADO);
	cpu.mostrar("Atencion", "Esta accion borrara la tabla de asociados (si existe) y creara una nueva", "Confirmar", "Cancelar");
	}
}

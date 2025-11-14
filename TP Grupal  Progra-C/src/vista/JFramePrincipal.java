package vista;

import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controladores.IVistaAsociados;
import controladores.IVistaControlador;
import controladores.IVistaSimulacion;
import persistencia.AsociadoDTO;
import util.Acciones;

public class JFramePrincipal extends JFrame implements IVistaControlador, IVistaAsociados, IVistaSimulacion {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VentanaGestion ventanaGestion;
	private VentanaSimulacion ventanaSimulacion;
	private ActionListener controlador;


	/**
	 * Create the frame.
	 */
	public JFramePrincipal() {
		ventanaGestion = new VentanaGestion();
		ventanaSimulacion = new VentanaSimulacion();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 900, 600);
	}

	// =====METODOS IMPLEMENTADOS DE IVistaControlador=====
	
	@Override
	public void mostrarGestion() {
		this.setTitle("Gestion de socios");
		this.contentPane = ventanaGestion; 
		setContentPane(this.contentPane);
		this.revalidate();
	}

	@Override
	public void mostrarSimulacion() {
		this.setTitle("Simulacion");
		this.contentPane = ventanaSimulacion;
		ventanaSimulacion.inicializarValores();
		setContentPane(this.contentPane);
		this.revalidate();
	}

	@Override
	public void displayConfirmarInicializacion() {
	ConfirmationPopUp cpu = new ConfirmationPopUp(this, controlador, Acciones.INICIALIZAR_CONFIRMADO);
	cpu.mostrar("Atencion", "<html>Esta accion borrara la tabla <br> de asociados (si existe) y creara <br> una nueva</html>", "Confirmar", "Cancelar");
	}
	
	
	@Override
	public void setActionListener(ActionListener actionListener) {
		assert actionListener != null: "el action listener no puede ser null";
		this.ventanaGestion.setActionListener(actionListener);
		this.ventanaSimulacion.addActionListener(actionListener);
		this.controlador = actionListener;
	}
	
	@Override
	public void setWindowListener(WindowListener windowListener) {
		assert windowListener != null;
		this.addWindowListener(windowListener);
	}

	// =====METODOS IMPLEMENTADOS DE IVistaAsociados=====
	
	@Override
	public void agregarAsociadoPermanencia(AsociadoDTO asociado) {
		ventanaGestion.addAsociadoPermanencia(asociado);
	}
	
	@Override
	public void eliminarAsociadoPermanencia(String dniAEliminar) {
		ventanaGestion.removeAsociadoPermanencia(dniAEliminar);	
	}
	
	@Override
	public AsociadoDTO getAsociadoNuevo() {
		return ventanaGestion.getAsociado();
	}
	
	@Override
	public String getDniAEliminar() {
		return this.ventanaGestion.getDNI();
	}

	@Override
	public void cargarListaAsociados(List<AsociadoDTO> lista) {
		ventanaGestion.cargarListaAsociados(lista);
	}
	
	@Override
	public void displayError(String mensajeError) {
		this.displayPopUp("Error", mensajeError, "Aceptar");
	}
	
	@Override
	public void vaciarListasAsoc() {
		this.ventanaGestion.vaciarListas();
	}

	// =====METODOS IMPLEMENTADOS DE IVistaSimulacion=====
	
	@Override
	public void agregarLlamadoNuevoEspera(String llamado) {
		ventanaSimulacion.aniadirLlamadoNuevo(llamado);
	}
	
	@Override
	public void quitarLlamadoEspera(String llamado) {
		ventanaSimulacion.retirarLlamadoNuevo(llamado);
	}
	
	@Override
	public void agregarLlamadoAtendidos(String llamado) {
		ventanaSimulacion.aniadirLlamadoAtendidos(llamado);
	}
	
	@Override
	public void cambiarEstado(String estado) {
		ventanaSimulacion.informarCambioEstado(estado);
	}
	
	@Override
	public void displayInfo(String mensajeinfo) {
		this.displayPopUp("Informacion", mensajeinfo, "Aceptar");
	}
	
	@Override
	public List<AsociadoDTO> getListaAsociadosSimulacion() {
		return this.ventanaGestion.getListaAsociadosSimulacion();
	}
	
	
	// =====METODOS AUXILIARES=====
	
	protected void displayPopUp(String titulo, String mensaje, String textoBoton) {
		CustomPopUp cpu = new CustomPopUp(this);
		cpu.mostrar(titulo, mensaje, textoBoton);
	}

	@Override
	public void cambiarEstadoBotonVolver(boolean activo) {
		ventanaSimulacion.cambiarEstadoBotonVolver(activo);
	}

	@Override
	public void cambiarEstadoBotonMantenimiento(boolean activo) {
		ventanaSimulacion.cambiarEstadoBotonMantenimiento(activo);
	}

	@Override
	public void cambiarEstadoBotonFinalizar(boolean activo) {
		ventanaSimulacion.cambiarEstadoBotonFinalizar(activo);
	}

}

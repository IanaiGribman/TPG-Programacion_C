package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Util.Acciones;
import modelo.Solicitante;
import patrones.IEstado;
import persistencia.AsociadoDTO;

public class JFramePrincipal extends JFrame implements ActionListener, IVista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VentanaGestion ventanaGestion;
	private VentanaSimulacion ventanaSimulacion;


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
	public AsociadoDTO getNewAsociado() {
		return this.ventanaGestion.getAsociado();
	}

	@Override
	public String getDniAEliminar() {
		return this.ventanaGestion.getDNI();
	}

	@Override
	public void actualizarTablaAsociados(Collection<AsociadoDTO> asociados) {
		this.ventanaGestion.setTablaAsociados(asociados);
	}

	@Override
	public void displayError(String mensajeError) {
		CustomPopUp cpu = new CustomPopUp(this);
		cpu.mostrar("Error", mensajeError, "Ok.");
	}

	@Override
	public void aniadirLlamado(Solicitante solicitante, String tipoDeSolicitud) {
		this.ventanaSimulacion.aniadirLlamado(solicitante, tipoDeSolicitud);
	}

	@Override
	public void retirarLlamado(Solicitante solicitante) {
		this.ventanaSimulacion.retirarLlamado(solicitante);
	}

	@Override
	public void infomarCambioEstado(IEstado estadoAmbulancia) {
		this.ventanaSimulacion.informarCambioEstado(estadoAmbulancia);
	}

	/*Creo que lo más sencillo es que cuando se apreta registrar, se registra en la base de datos automáticamente.
	 * Y que cuando se abre la ventana, se carguen todos los asociados.*/
	
	/*Cada vez que se registra o se elimina un asociado, la vista recibe en evt.getNewValue el dto agregado o un dni y 
	 * se modifica  el DefaultListModel de la ventana gestión a partir de eso. O sea, no hay que pasarle la lista entera
	 * de asociados en cada operación.*/
	
	/*El problema que veo ahora es que si guardás después de cargar, va a tirar una excepción la base de datos por DNIs
	 * repetidos y además no podés elegir qué asociados van a la simulación.*/
	
	/*Una alternativa podría ser que se vean dos listas: todos los asociados y los que van a la simulación. Para
	 * agregar asociados a la lista de la simulación, apretás en uno de la lista de asociados de la base de datos. 
	 * Si la lista de la simulación no está vacía, no se puede eliminar asociados, si no habría que revisar en ambas
	 * listas. */
	
	
	/**
	 * Maneja las notificaciones de cambio en el modelo
	 * 
	 * (es como si esta clase fuera un controlador de las ventanas)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case Acciones.ERROR: {
			// lo comento de momento porque es molesto
			//this.displayError((String) evt.getNewValue()); //hay que hacer cast siempre
			break;
		}
		case Acciones.CARGAR: {
			
			//acá llegaría la lista de asociadoDTO, 
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
		this.ventanaSimulacion.setActionListener(actionListener);
	}


	public AsociadoDTO getAsociadoNuevo() {
		return ventanaGestion.getAsociado();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAsociadoPermanencia(AsociadoDTO asociadoNuevo) {
		this.ventanaGestion.addAsociadoPermanencia(asociadoNuevo);
	}

	@Override
	public void addAsociadoSimulacion(AsociadoDTO asociado) {
		ventanaGestion.addAsociadoSimulacion(asociado);
	}

	@Override
	public void removeAsociadoPermanencia(AsociadoDTO asociado) {
		ventanaGestion.removeAsociadoPermanencia(asociado);
	}

	@Override
	public void removeAsociadoSimulacion(AsociadoDTO asociado) {
		ventanaGestion.removeAsociadoSimulacion(asociado);
	}
}

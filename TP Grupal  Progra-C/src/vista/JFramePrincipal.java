package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JPanel;

import persistencia.AsociadoDTO;

public class JFramePrincipal extends JFrame implements ActionListener, IVista{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VentanaGestion ventanaGestion;
	private VentanaSimulacion ventanaSimulacion; 

	/**
	 * Launch the application.
	 * ESTO ES TEMPORAL, LUEGO LO TIENE QUE HACER UNA CLASE MAIN
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFramePrincipal frame = new JFramePrincipal();

					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFramePrincipal() {
		ventanaSimulacion = new VentanaSimulacion(this); 
		ventanaGestion = new VentanaGestion(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 900, 600);
		
		this.mostrarGestion();

	}

	/**
	 * ESTO TENDRIA QUE IR EN EL MODELO?
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand()) {
		case IVista.GESTION:{
			this.mostrarGestion();
			break;
		}
		case IVista.SIMULACION:{ //ESTO SEGURO VA EN EL MODELO
			this.mostrarSimulacion();
			break;
		}
		case IVista.REGISTRAR:{ // TEMPORAL
			AsociadoDTO as = this.ventanaGestion.getAsociado();
			this.ventanaGestion.addSocio(as);
			this.ventanaGestion.redibujar();
			this.ventanaGestion.clearRegistroTextFields();
			break;
		}
		case IVista.GUARDAR:{
			CustomPopUp cpu = new CustomPopUp(this);
			cpu.mostrar("Guardado con exito", "Los cambios se guardaron en la base de datos", "Ok.");
		}
		case IVista.ELIMINAR:{
			this.ventanaGestion.redibujar();
			this.ventanaGestion.clearEliminacionTextFields();
		}
		}
		
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

	// esto es para actualizar la simulacion
	@Override
	public void update(Observable arg0, Object arg1) {
		
		
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
		this.ventanaGestion.redibujar();
	}

}

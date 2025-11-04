package vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JFramePrincipal extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VentanaGestion ventanaGestion;
	private VentanaSimulacion ventanaSimulacion; 

	/**
	 * Launch the application.
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
		
		this.setVentanaGestion();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand()) {
		case IVista.GESTION:{
			this.setVentanaGestion();
			break;
		}
		case IVista.SIMULACION:{
			this.setVentanaSimulacion();
			break;
		}
		}
		
	}
	
	private void setVentanaGestion() {
		this.setTitle("Gestion de socios");
		this.contentPane = ventanaGestion;
		setContentPane(this.contentPane);
		this.revalidate();
	}
	private void setVentanaSimulacion() {
		this.setTitle("Simulacion");
		this.contentPane = ventanaSimulacion;
		setContentPane(this.contentPane);
		this.revalidate();
	}

}

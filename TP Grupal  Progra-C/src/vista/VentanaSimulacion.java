package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import modelo.Solicitante;
import patrones.IEstado;

public class VentanaSimulacion extends JPanel {
	Map <Solicitante, String> solicitantesMotivo = new IdentityHashMap<>(); //esta bien esto?
	private DefaultListModel<String> llamadoDLM = new DefaultListModel<>();
	private JList<String> llamadosJList;

	private static final long serialVersionUID = 1L;
	private JPanel panelSimulacion;
	private JPanel panelIzquierdo;
	private JPanel panelDerecho;
	private JPanel bordeIzquierdo;
	private JPanel panelEstado;
	private JPanel panelControl;
	private JPanel bordeEstado;
	private JPanel bordeControl;
	private JButton btnMantenimiento;
	private JButton btnFinalizar;
	private JScrollPane scrollPane;
	
	private JLabel labelEstadoAmbulancia;

	public VentanaSimulacion(ActionListener padre) {
		setLayout(new BorderLayout(0, 0));
		this.hacerPanelSimulacion();
		
		this.btnFinalizar.setActionCommand(IVista.GESTION);
		this.btnFinalizar.addActionListener(padre);
		
		this.llamadosJList.setModel(llamadoDLM);

	}

	private void hacerPanelSimulacion() {
		this.panelSimulacion = new JPanel();
		add(this.panelSimulacion);
		GridBagLayout gbl_panelSimulacion = new GridBagLayout();
		gbl_panelSimulacion.columnWidths = new int[]{0, 0, 0};
		gbl_panelSimulacion.rowHeights = new int[]{0, 0};
		gbl_panelSimulacion.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelSimulacion.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.panelSimulacion.setLayout(gbl_panelSimulacion);
		
		this.bordeIzquierdo = new JPanel();
		this.bordeIzquierdo.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Llamados", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_bordeIzquierdo = new GridBagConstraints();
		gbc_bordeIzquierdo.fill = GridBagConstraints.BOTH;
		gbc_bordeIzquierdo.insets = new Insets(0, 0, 0, 5);
		gbc_bordeIzquierdo.gridx = 0;
		gbc_bordeIzquierdo.gridy = 0;
		this.panelSimulacion.add(this.bordeIzquierdo, gbc_bordeIzquierdo);
		GridBagLayout gbl_bordeIzquierdo = new GridBagLayout();
		gbl_bordeIzquierdo.columnWidths = new int[]{0, 0};
		gbl_bordeIzquierdo.rowHeights = new int[]{0, 0};
		gbl_bordeIzquierdo.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_bordeIzquierdo.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.bordeIzquierdo.setLayout(gbl_bordeIzquierdo);
		
		this.panelIzquierdo = new JPanel();
		GridBagConstraints gbc_panelIzquierdo = new GridBagConstraints();
		gbc_panelIzquierdo.fill = GridBagConstraints.BOTH;
		gbc_panelIzquierdo.gridx = 0;
		gbc_panelIzquierdo.gridy = 0;
		this.bordeIzquierdo.add(this.panelIzquierdo, gbc_panelIzquierdo);
		this.panelIzquierdo.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane = new JScrollPane();
		this.panelIzquierdo.add(this.scrollPane);
		
		this.llamadosJList = new JList();
		this.scrollPane.setViewportView(this.llamadosJList);
		
		this.panelDerecho = new JPanel();
		GridBagConstraints gbc_panelDerecho = new GridBagConstraints();
		gbc_panelDerecho.fill = GridBagConstraints.BOTH;
		gbc_panelDerecho.gridx = 1;
		gbc_panelDerecho.gridy = 0;
		this.panelSimulacion.add(this.panelDerecho, gbc_panelDerecho);
		GridBagLayout gbl_panelDerecho = new GridBagLayout();
		gbl_panelDerecho.columnWidths = new int[]{0, 0};
		gbl_panelDerecho.rowHeights = new int[]{0, 0, 0};
		gbl_panelDerecho.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelDerecho.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		this.panelDerecho.setLayout(gbl_panelDerecho);
		
		this.bordeEstado = new JPanel();
		this.bordeEstado.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Estado Ambulancia", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_bordeEstado = new GridBagConstraints();
		gbc_bordeEstado.fill = GridBagConstraints.BOTH;
		gbc_bordeEstado.insets = new Insets(0, 0, 5, 0);
		gbc_bordeEstado.gridx = 0;
		gbc_bordeEstado.gridy = 0;
		this.panelDerecho.add(this.bordeEstado, gbc_bordeEstado);
		GridBagLayout gbl_bordeEstado = new GridBagLayout();
		gbl_bordeEstado.columnWidths = new int[]{0, 0};
		gbl_bordeEstado.rowHeights = new int[]{0, 0};
		gbl_bordeEstado.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_bordeEstado.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.bordeEstado.setLayout(gbl_bordeEstado);
		
		this.panelEstado = new JPanel();
		GridBagConstraints gbc_panelEstado = new GridBagConstraints();
		gbc_panelEstado.fill = GridBagConstraints.BOTH;
		gbc_panelEstado.gridx = 0;
		gbc_panelEstado.gridy = 0;
		this.bordeEstado.add(this.panelEstado, gbc_panelEstado);
		
		this.labelEstadoAmbulancia = new JLabel("New label");
		this.panelEstado.add(this.labelEstadoAmbulancia);
		
		this.bordeControl = new JPanel();
		this.bordeControl.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Control", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_bordeControl = new GridBagConstraints();
		gbc_bordeControl.fill = GridBagConstraints.BOTH;
		gbc_bordeControl.gridx = 0;
		gbc_bordeControl.gridy = 1;
		this.panelDerecho.add(this.bordeControl, gbc_bordeControl);
		GridBagLayout gbl_bordeControl = new GridBagLayout();
		gbl_bordeControl.columnWidths = new int[]{0, 0};
		gbl_bordeControl.rowHeights = new int[]{0, 0};
		gbl_bordeControl.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_bordeControl.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.bordeControl.setLayout(gbl_bordeControl);
		
		this.panelControl = new JPanel();
		GridBagConstraints gbc_panelControl = new GridBagConstraints();
		gbc_panelControl.fill = GridBagConstraints.BOTH;
		gbc_panelControl.gridx = 0;
		gbc_panelControl.gridy = 0;
		this.bordeControl.add(this.panelControl, gbc_panelControl);
		
		this.btnMantenimiento = new JButton("Mantenimiento");
		this.panelControl.add(this.btnMantenimiento);
		
		this.btnFinalizar = new JButton("Finalizar");
		this.panelControl.add(this.btnFinalizar);
	}
	private void refrescarLista() {
		this.llamadoDLM.clear();
		for (Entry<Solicitante, String> solicitanteMotivo : solicitantesMotivo.entrySet()) {
			this.llamadoDLM.addElement(solicitanteMotivo.getKey() + " " + solicitanteMotivo.getValue());
		}
		this.llamadosJList.revalidate();
	}
	

	public void aniadirLlamado(Solicitante solicitante, String tipoDeSolicitud) {
		this.solicitantesMotivo.put(solicitante, tipoDeSolicitud);
		this.refrescarLista();
	}

	public void retirarLlamado(Solicitante solicitante) {
		this.solicitantesMotivo.remove(solicitante);
		this.refrescarLista();
	}

	public void informarCambioEstado(IEstado estadoAmbulancia) {
		this.labelEstadoAmbulancia.setText(estadoAmbulancia.toString());
		//TODO
	}

}

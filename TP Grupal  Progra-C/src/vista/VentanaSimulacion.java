package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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

import Util.Acciones;
import modelo.Llamado;
import modelo.Solicitante;
import patrones.state.IEstado;

public class VentanaSimulacion extends JPanel {
	private DefaultListModel<Llamado> llamadosNuevosDLM = new DefaultListModel<>();
	private JList<Llamado> llamadosNuevosJList = new JList<>();
	private DefaultListModel<Llamado> llamadosAtendidosDLM = new DefaultListModel<>();
	private JList<Llamado> llamadosAtendidosJList = new JList<>();

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
	JPanel panelLlamadosNuevosBorde;
	JPanel panelLlamadosNuevos;
	JScrollPane scrollPaneLlamadosNuevos;
	JPanel panelLlamadosAtendidosBorde;
	JPanel panelLlamadosAtendidos;
	JScrollPane scrollPaneLlamadosAtendidos;
	
	private JLabel labelEstadoAmbulancia;

	public VentanaSimulacion() {
		setLayout(new BorderLayout(0, 0));
		this.hacerPanelSimulacion();
		this.llamadosNuevosJList.setModel(llamadosNuevosDLM);
		this.llamadosAtendidosJList.setModel(llamadosAtendidosDLM);
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
		
		this.panelIzquierdo = new JPanel();
		GridBagConstraints gbc_panelIzquierdo = new GridBagConstraints();
		gbc_panelIzquierdo.fill = GridBagConstraints.BOTH;
		gbc_panelIzquierdo.gridx = 0;
		gbc_panelIzquierdo.gridy = 0;
		this.panelSimulacion.add(this.panelIzquierdo, gbc_panelIzquierdo);
	    this.panelIzquierdo.setLayout(new GridLayout(1, 2, 10, 0));
		
		this.hacerPanelLlamados();
		
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
		gbc_panelEstado.fill = GridBagConstraints.HORIZONTAL;
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
		gbc_panelControl.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelControl.gridx = 0;
		gbc_panelControl.gridy = 0;
		this.bordeControl.add(this.panelControl, gbc_panelControl);
		
		this.btnMantenimiento = new JButton("Mantenimiento");
		this.panelControl.add(this.btnMantenimiento);
		
		this.btnFinalizar = new JButton("Finalizar");
		this.panelControl.add(this.btnFinalizar);
		configurarBotones();
	}
	
	private void hacerPanelLlamados() {
	    this.panelIzquierdo.setLayout(new GridLayout(1, 2, 0, 0)); // dos columnas (nuevos y atendidos), con un pequeño espacio entre ellas

	    // === Panel borde para llamados nuevos ===
	    panelLlamadosNuevosBorde = new JPanel();
	    panelLlamadosNuevosBorde.setBorder(new TitledBorder(
	        new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
	        "Llamados nuevos",
	        TitledBorder.LEFT,
	        TitledBorder.BELOW_TOP,
	        null,
	        new Color(0, 0, 0)
	    ));
	    this.panelIzquierdo.add(panelLlamadosNuevosBorde);

	    GridBagLayout gbl_panelLlamadosNuevosBorde = new GridBagLayout();
	    gbl_panelLlamadosNuevosBorde.columnWidths = new int[]{0, 0};
	    gbl_panelLlamadosNuevosBorde.rowHeights = new int[]{0, 0};
	    gbl_panelLlamadosNuevosBorde.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	    gbl_panelLlamadosNuevosBorde.rowWeights = new double[]{1.0, Double.MIN_VALUE};
	    panelLlamadosNuevosBorde.setLayout(gbl_panelLlamadosNuevosBorde);

	    panelLlamadosNuevos = new JPanel(new BorderLayout());
	    GridBagConstraints gbc_panelLlamadosNuevos = new GridBagConstraints();
	    gbc_panelLlamadosNuevos.fill = GridBagConstraints.BOTH;
	    gbc_panelLlamadosNuevos.gridx = 0;
	    gbc_panelLlamadosNuevos.gridy = 0;
	    panelLlamadosNuevosBorde.add(panelLlamadosNuevos, gbc_panelLlamadosNuevos);

	    // === Configurar lista de llamados nuevos ===
	    this.llamadosNuevosJList.setVisibleRowCount(0);

	    scrollPaneLlamadosNuevos = new JScrollPane(this.llamadosNuevosJList);
	    panelLlamadosNuevos.add(scrollPaneLlamadosNuevos, BorderLayout.CENTER);

	    // === Panel borde para llamados atendidos ===
	    panelLlamadosAtendidosBorde = new JPanel();
	    panelLlamadosAtendidosBorde.setBorder(new TitledBorder(
	        new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
	        "Llamados atendidos",
	        TitledBorder.LEFT,
	        TitledBorder.BELOW_TOP,
	        null,
	        new Color(0, 0, 0)
	    ));
	    this.panelIzquierdo.add(panelLlamadosAtendidosBorde);

	    GridBagLayout gbl_panelLlamadosAtendidosBorde = new GridBagLayout();
	    gbl_panelLlamadosAtendidosBorde.columnWidths = new int[]{0, 0};
	    gbl_panelLlamadosAtendidosBorde.rowHeights = new int[]{0, 0};
	    gbl_panelLlamadosAtendidosBorde.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	    gbl_panelLlamadosAtendidosBorde.rowWeights = new double[]{1.0, Double.MIN_VALUE};
	    panelLlamadosAtendidosBorde.setLayout(gbl_panelLlamadosAtendidosBorde);

	    panelLlamadosAtendidos = new JPanel(new BorderLayout());
	    GridBagConstraints gbc_panelLlamadosAtendidos = new GridBagConstraints();
	    gbc_panelLlamadosAtendidos.fill = GridBagConstraints.BOTH;
	    gbc_panelLlamadosAtendidos.gridx = 0;
	    gbc_panelLlamadosAtendidos.gridy = 0;
	    panelLlamadosAtendidosBorde.add(panelLlamadosAtendidos, gbc_panelLlamadosAtendidos);

	    // === Configurar lista de llamados atendidos ===
	    this.llamadosAtendidosJList.setVisibleRowCount(0);

	    scrollPaneLlamadosAtendidos = new JScrollPane(this.llamadosAtendidosJList);
	    panelLlamadosAtendidos.add(scrollPaneLlamadosAtendidos, BorderLayout.CENTER);
	}
	
	public void configurarBotones() {
		this.btnFinalizar.setActionCommand(Acciones.GESTION);
		this.btnMantenimiento.setActionCommand(Acciones.MANTENIMIENTO);
	}
	
	public void aniadirLlamadoNuevo(Llamado llamado) {
		this.llamadosNuevosDLM.addElement(llamado);
	}

	public void retirarLlamadoNuevo(Solicitante solicitante) {
		assert solicitante != null;
		int i = 0;
		while(!this.llamadosNuevosDLM.get(i).getSolicitante().equals(solicitante))
			i++;
		this.llamadosAtendidosDLM.addElement(this.llamadosNuevosDLM.get(i));
		this.llamadosNuevosDLM.remove(i);
	}

	public void informarCambioEstado(IEstado estadoAmbulancia) {
		this.labelEstadoAmbulancia.setText(estadoAmbulancia.toString());
	}
	
	
	public void setActionListener(ActionListener actionListener)
	{
		this.btnFinalizar.addActionListener(actionListener);
		this.btnMantenimiento.addActionListener(actionListener);
	}

}

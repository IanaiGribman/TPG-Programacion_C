package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class VentanaGestion extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel panelGestionAsociados;
	private JPanel panelIzquierdo;
	private JPanel panelDerecho;
	private JPanel panelAsociados;
	private JPanel panelPersistencia;
	private JScrollPane scrollPane;
	private JList<String> list;
	private JPanel panelAsociadosBorde;
	private JPanel panelPersistenciaBorde;
	private JPanel panelCreacion;
	private JPanel panelEliminacion;
	private JPanel panelControles;
	private JLabel labelNombeCreacion;
	private JLabel labelDNICreacion;
	private JLabel labelRepeticionesCreacion;
	private JTextField textFieldNombre;
	private JTextField textFieldDNI;
	private JTextField textFieldRepeticiones;
	private JButton btnRegistrar;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel BordeCreacion;
	private JButton btnCargarPersistencia;
	private JButton btnGuardarPersistencia;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel labelDNIEliminacion;
	private JTextField textFieldDniEliminacion;
	private JButton btnEliminar;
	private JPanel BordeEliminacion;
	private JButton btnSimular;
	private JPanel panel_6;
	private JPanel panel_7;

	public VentanaGestion(ActionListener padre) {
		setLayout(new BorderLayout(0, 0));
		this.hacerPanelGestionAsociados();
		this.btnSimular.setActionCommand(IVista.SIMULACION);
		this.btnSimular.addActionListener(padre);
	}
 
	private void hacerPanelGestionAsociados() {
	    this.panelGestionAsociados = new JPanel();
	    this.add(this.panelGestionAsociados, BorderLayout.CENTER);

	    GridBagLayout gbl = new GridBagLayout();
	    gbl.columnWidths = new int[]{0, 0};
	    gbl.rowHeights = new int[]{0};
	    gbl.columnWeights = new double[]{0.7, 0.3}; // proporciones
	    gbl.rowWeights = new double[]{1.0};
	    this.panelGestionAsociados.setLayout(gbl);

	    // --- Panel izquierdo (70%) ---
	    this.hacerPanelIzquierdo();
	    GridBagConstraints gbcIzq = new GridBagConstraints();
	    gbcIzq.fill = GridBagConstraints.BOTH;
	    gbcIzq.gridx = 0;
	    gbcIzq.gridy = 0;
	    gbcIzq.weightx = 0.7;
	    gbcIzq.weighty = 1.0;
	    gbcIzq.insets = new Insets(0, 0, 0, 3);
	    this.panelGestionAsociados.add(this.panelIzquierdo, gbcIzq);

	    // --- Panel derecho (30%) ---
	    this.hacerPanelDerecho();
	    GridBagConstraints gbcDer = new GridBagConstraints();
	    gbcDer.fill = GridBagConstraints.BOTH;
	    gbcDer.gridx = 1;
	    gbcDer.gridy = 0;
	    gbcDer.weightx = 0.3;
	    gbcDer.weighty = 1.0;
	    this.panelGestionAsociados.add(this.panelDerecho, gbcDer);
	}

	private void hacerPanelDerecho() {
		this.panelDerecho = new JPanel();
		GridBagLayout gbl_panelDerecho = new GridBagLayout();
		gbl_panelDerecho.columnWidths = new int[]{0, 0};
		gbl_panelDerecho.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelDerecho.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelDerecho.rowWeights = new double[]{0.55, 0.2, 0.25, Double.MIN_VALUE};
		this.panelDerecho.setLayout(gbl_panelDerecho);
		
		this.BordeCreacion = new JPanel();
		this.BordeCreacion.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Creacion", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_bordeCreacion = new GridBagConstraints();
		gbc_bordeCreacion.fill = GridBagConstraints.BOTH;
		gbc_bordeCreacion.insets = new Insets(0, 0, 5, 0);
		gbc_bordeCreacion.gridx = 0;
		gbc_bordeCreacion.gridy = 0;
		this.panelDerecho.add(this.BordeCreacion, gbc_bordeCreacion);
		GridBagLayout gbl_bordeCreacion = new GridBagLayout();
		gbl_bordeCreacion.columnWidths = new int[]{0, 0};
		gbl_bordeCreacion.rowHeights = new int[]{0, 0};
		gbl_bordeCreacion.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_bordeCreacion.rowWeights = new double[]{0.55, Double.MIN_VALUE};
		this.BordeCreacion.setLayout(gbl_bordeCreacion);
		
		this.panelCreacion = new JPanel();
		GridBagConstraints gbc_panelCreacion = new GridBagConstraints();
		gbc_panelCreacion.fill = GridBagConstraints.BOTH;
		gbc_panelCreacion.gridx = 0;
		gbc_panelCreacion.gridy = 0;
		this.BordeCreacion.add(panelCreacion, gbc_panelCreacion);
		this.panelCreacion.setLayout(new GridLayout(3, 0, 0, 0));
		
		this.hacerPanelCreacion();
		
		this.BordeEliminacion = new JPanel();
		this.BordeEliminacion.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Eliminacion", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_bordeEliminacion = new GridBagConstraints();
		gbc_bordeEliminacion.fill = GridBagConstraints.BOTH;
		gbc_bordeEliminacion.insets = new Insets(0, 0, 5, 0);
		gbc_bordeEliminacion.gridx = 0;
		gbc_bordeEliminacion.gridy = 1;
		this.panelDerecho.add(this.BordeEliminacion, gbc_bordeEliminacion);
		GridBagLayout gbl_bordeEliminacion = new GridBagLayout();
		gbl_bordeEliminacion.columnWidths = new int[]{0, 0};
		gbl_bordeEliminacion.rowHeights = new int[]{0, 0};
		gbl_bordeEliminacion.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_bordeEliminacion.rowWeights = new double[]{0.2, Double.MIN_VALUE};
		this.BordeEliminacion.setLayout(gbl_bordeEliminacion);
		
		this.panelEliminacion = new JPanel();
		GridBagConstraints gbc_panelEliminacion = new GridBagConstraints();
		gbc_panelEliminacion.fill = GridBagConstraints.BOTH;
		gbc_panelEliminacion.gridx = 0;
		gbc_panelEliminacion.gridy = 0;
		this.BordeEliminacion.add(this.panelEliminacion, gbc_panelEliminacion);
		this.panelEliminacion.setLayout(new GridLayout(0, 3, 0, 0));
		
		this.labelDNIEliminacion = new JLabel("DNI");
		this.panelEliminacion.add(this.labelDNIEliminacion);
		
		this.panel_7 = new JPanel();
		this.panelEliminacion.add(this.panel_7);
		
		this.textFieldDniEliminacion = new JTextField();
		this.panel_7.add(this.textFieldDniEliminacion);
		this.textFieldDniEliminacion.setColumns(10);
		
		this.panel_6 = new JPanel();
		this.panelEliminacion.add(this.panel_6);
		
		this.btnEliminar = new JButton("Eliminar");
		this.panel_6.add(this.btnEliminar);
		
		this.panelControles = new JPanel();
		GridBagConstraints gbc_panelControles = new GridBagConstraints();
		gbc_panelControles.fill = GridBagConstraints.BOTH;
		gbc_panelControles.gridx = 0;
		gbc_panelControles.gridy = 2;
		this.panelDerecho.add(this.panelControles, gbc_panelControles);
		this.panelControles.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.btnSimular = new JButton("Simular");
		this.panelControles.add(this.btnSimular);
		
	}

	private void hacerPanelCreacion() {
		this.labelNombeCreacion = new JLabel("Nombre");
		panelCreacion.add(this.labelNombeCreacion);
		
		this.labelDNICreacion = new JLabel("DNI");
		panelCreacion.add(this.labelDNICreacion);
		
		this.labelRepeticionesCreacion = new JLabel("Repeticiones");
		panelCreacion.add(this.labelRepeticionesCreacion);
		
		this.panel_3 = new JPanel();
		panelCreacion.add(this.panel_3);
		
		this.textFieldNombre = new JTextField();
		this.panel_3.add(this.textFieldNombre);
		this.textFieldNombre.setColumns(10);
		
		this.panel_2 = new JPanel();
		panelCreacion.add(this.panel_2);
		
		this.textFieldDNI = new JTextField();
		this.panel_2.add(this.textFieldDNI);
		this.textFieldDNI.setColumns(10);
		
		this.panel_1 = new JPanel();
		panelCreacion.add(this.panel_1);
		
		this.textFieldRepeticiones = new JTextField();
		this.panel_1.add(this.textFieldRepeticiones);
		this.textFieldRepeticiones.setColumns(10);
		
		this.panel = new JPanel();
		panelCreacion.add(this.panel);
		
		this.btnRegistrar = new JButton("Registrar");
		this.panel.add(this.btnRegistrar);
		
		this.panelCreacion = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 0 , 0, 0 };
		gbl.rowHeights = new int[] { 0, 0, 0 };
		gbl.columnWeights = new double[] { 1.0, 1.0, 1.0 };
		gbl.rowWeights = new double[] { 0.3, 0.3, 0.2 }; // proporciones
		this.panelCreacion.setLayout(gbl);
		
	}

	private void hacerPanelIzquierdo() {
		this.panelIzquierdo = new JPanel();
		GridBagLayout gbl_panelIzquierdo = new GridBagLayout();
		gbl_panelIzquierdo.columnWidths = new int[] { 0 };
		gbl_panelIzquierdo.rowHeights = new int[] { 0, 0 };
		gbl_panelIzquierdo.columnWeights = new double[] { 1.0 };
		gbl_panelIzquierdo.rowWeights = new double[] { 0.75, 0.25 }; // proporciones
		this.panelIzquierdo.setLayout(gbl_panelIzquierdo);

		this.hacerPanelAsociados();
		this.hacerPanelPersistencia();
	}

	private void hacerPanelAsociados() {
		// --- Asociados borde ---
		this.panelAsociadosBorde = new JPanel();
		this.panelAsociadosBorde.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Asociados", TitledBorder.LEFT, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelAsociadosBorde = new GridBagConstraints();
		gbc_panelAsociadosBorde.fill = GridBagConstraints.BOTH;
		gbc_panelAsociadosBorde.insets = new Insets(0, 0, 5, 0);
		gbc_panelAsociadosBorde.gridx = 0;
		gbc_panelAsociadosBorde.gridy = 0;
		this.panelIzquierdo.add(this.panelAsociadosBorde, gbc_panelAsociadosBorde);
		GridBagLayout gbl_panelAsociadosBorde = new GridBagLayout();
		gbl_panelAsociadosBorde.columnWidths = new int[]{0, 0};
		gbl_panelAsociadosBorde.rowHeights = new int[]{0, 0};
		gbl_panelAsociadosBorde.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelAsociadosBorde.rowWeights = new double[]{0.75, Double.MIN_VALUE};
		this.panelAsociadosBorde.setLayout(gbl_panelAsociadosBorde);
		
		this.panelAsociados = new JPanel(new BorderLayout());
		GridBagConstraints gbc_panelAsociados = new GridBagConstraints();
		gbc_panelAsociados.fill = GridBagConstraints.BOTH;
		gbc_panelAsociados.gridx = 0;
		gbc_panelAsociados.gridy = 0;
		this.panelAsociadosBorde.add(this.panelAsociados, gbc_panelAsociados);

		this.list = new JList<>();

		this.list.setVisibleRowCount(0); // 0 significa "no forzar un numero de filas visible"

		this.scrollPane = new JScrollPane(this.list);
		//this.scrollPane.setPreferredSize(new Dimension(0, 0));
		
		this.panelAsociados.add(this.scrollPane, BorderLayout.CENTER);
	}

	private void hacerPanelPersistencia() {
		// Borde
		this.panelPersistenciaBorde = new JPanel();
		this.panelPersistenciaBorde.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Persistencia", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_panelPersistenciaBorde = new GridBagConstraints();
		gbc_panelPersistenciaBorde.fill = GridBagConstraints.BOTH;
		gbc_panelPersistenciaBorde.gridx = 0;
		gbc_panelPersistenciaBorde.gridy = 1;
		this.panelIzquierdo.add(this.panelPersistenciaBorde, gbc_panelPersistenciaBorde);
		GridBagLayout gbl_panelPersistenciaBorde = new GridBagLayout();
		gbl_panelPersistenciaBorde.columnWidths = new int[]{0, 0};
		gbl_panelPersistenciaBorde.rowHeights = new int[]{0, 0};
		gbl_panelPersistenciaBorde.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelPersistenciaBorde.rowWeights = new double[]{0.25, Double.MIN_VALUE};
		this.panelPersistenciaBorde.setLayout(gbl_panelPersistenciaBorde);
		
		this.panelPersistencia = new JPanel();
		GridBagConstraints gbc_panelPersistencia = new GridBagConstraints();
		gbc_panelPersistencia.fill = GridBagConstraints.BOTH;
		gbc_panelPersistencia.gridx = 0;
		gbc_panelPersistencia.gridy = 0;
		this.panelPersistenciaBorde.add(this.panelPersistencia, gbc_panelPersistencia);
		this.panelPersistencia.setLayout(new GridLayout(1, 0, 0, 0));
		
		this.panel_4 = new JPanel();
		this.panelPersistencia.add(this.panel_4);
		
		this.btnCargarPersistencia = new JButton("Cargar");
		this.panel_4.add(this.btnCargarPersistencia);
		
		this.panel_5 = new JPanel();
		this.panelPersistencia.add(this.panel_5);
		
		this.btnGuardarPersistencia = new JButton("Guardar");
		this.panel_5.add(this.btnGuardarPersistencia);
	}
}

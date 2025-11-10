package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Util.Acciones;
import persistencia.AsociadoDTO;

/**
 * Es la vista en la cual los usuarios manipulan a los asociados y configuran la simulacion
 */
public class VentanaGestion extends JPanel implements KeyListener, ListSelectionListener, ActionListener{
	private static final long serialVersionUID = 1L;
	private static final String toolTipCompleteAmbos = "<html> <b> <font color='red'>" +
													   		"Error: " +
													   "</font> </b> <b> <font color='black'> "  +
															"complete ambos campos de texto" +
													   "</font> </b> </html>";
	private static final String toolTipDniNumerico = "<html> <b> <font color='red'>" +
													 "Error: " +
												  "</font> </b> <b> <font color='black'> " +
													 "el dni debe ser numerico" +
												  "</font> </b> </html>";
	private static final String toolTipVaciaSimulacion = "<html> <b> <font color='red'>" +
														 "Error: " +
													  "</font> </b> <b> <font color='black'> " +
														 "Debe vaciar la ventana de simulacion para eliminar un asociado" +
													  "</font> </b> </html>";
	private static final String toolTipSimulacionVacia = "<html> <b> <font color='red'>" +
															 "Error: " +
														  "</font> </b> <b> <font color='black'> " +
															 "Debe a�adir asociados a la ventana de simulacion para empezar la simulacion" +
														  "</font> </b> </html>";
	private static final String toolTipSeleccionPersistenciaVacia = "<html> <b> <font color='red'>" +
																	 "Error: " +
																  "</font> </b> <b> <font color='black'> " +
																	 "Debe a�adir asociados a la ventana de simulacion para empezar la simulacion" +
																  "</font> </b> </html>";

	
	private DefaultListModel<AsociadoDTO> asociadosPersistenciaDLM = new DefaultListModel<>();
	private JList<AsociadoDTO> asociadosPersistenciaJList;
	
	private DefaultListModel<AsociadoDTO> asociadosSimulacionDLM = new DefaultListModel<>();
	private JList<AsociadoDTO> asociadosSumulacionJList;
	
	private JPanel panelGestionAsociados;
	private JPanel panelIzquierdo;
	private JPanel panelDerecho;
	private JPanel panelAsociadosPermanencia;
	private JScrollPane scrollPanePermanencia;
	private JPanel panelAsociadosPermanenciaBorde;
	private JPanel panelCreacion;
	private JPanel panelEliminacion;
	private JPanel panelInicializacion;
	private JLabel labelNombeCreacion;
	private JLabel labelDNICreacion;
	private JTextField textFieldNombre;
	private JTextField textFieldDniCreacion;
	private JButton btnRegistrar;
	private JPanel panelBotonRegistrar;
	private JPanel panelTextFieldDNICreacion;
	private JPanel panelTextFieldNombre;
	private JPanel BordeCreacion;
	private JLabel labelDNIEliminacion;
	private JTextField textFieldDniEliminacion;
	private JButton btnEliminar;
	private JPanel BordeEliminacion;
	private JButton btnSimular;
	private JPanel panel_6;
	private JPanel panel_7;
	private JButton btnInicializar;
	private JPanel panelAsociadosSimulacion;
	private JScrollPane scrollPaneSimulacion;
	private JPanel panelAsociadosSimulacionBorde;
	private JPanel BordeSimulacion;
	private JPanel panelSimulacion;
	private JButton btnAgregarASimulacion;
	private JPanel BordeInicializacion;
	private boolean deseleccionando;

	public VentanaGestion() {
		setLayout(new BorderLayout(0, 0));
		this.hacerPanelGestionAsociados();
		
		this.configurarBotones();
		this.deseleccionando = false;
		
		this.asociadosPersistenciaJList.setModel(this.asociadosPersistenciaDLM);
		this.asociadosPersistenciaJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.panelAsociadosSimulacionBorde = new JPanel();
		this.panelAsociadosSimulacionBorde.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Asociados para la simulacion", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		this.panelIzquierdo.add(this.panelAsociadosSimulacionBorde);
		this.panelAsociadosSimulacionBorde.setLayout(new BorderLayout(0, 0));
		
		this.panelAsociadosSimulacion = new JPanel();
		this.panelAsociadosSimulacionBorde.add(this.panelAsociadosSimulacion);
		this.panelAsociadosSimulacion.setLayout(new BorderLayout(0, 0));
		
		this.scrollPaneSimulacion = new JScrollPane();
		this.panelAsociadosSimulacion.add(this.scrollPaneSimulacion, BorderLayout.CENTER);
		
		this.asociadosSumulacionJList.setModel(asociadosSimulacionDLM);
		this.asociadosSumulacionJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.scrollPaneSimulacion.setViewportView(this.asociadosSumulacionJList);
		
		this.revalidadBotonEliminar();
		this.revalidadBotonRegistrar();
		this.revalidarBotonSimulacion();
		this.revalidarBotonAgregarSimulacion();
		
		}


	private void configurarBotones() {
		this.btnSimular.setActionCommand(Acciones.SIMULACION);	
		this.btnRegistrar.setActionCommand(Acciones.REGISTRAR);
		this.btnEliminar.setActionCommand(Acciones.ELIMINAR);		
		this.btnInicializar.setActionCommand(Acciones.INICIALIZAR);
		this.btnAgregarASimulacion.addActionListener(this);
		this.btnAgregarASimulacion.setEnabled(false);
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
		gbl_panelDerecho.rowWeights = new double[]{0.45, 0.2, 0.15, 0.2, Double.MIN_VALUE};
		this.panelDerecho.setLayout(gbl_panelDerecho);
		
		this.BordeCreacion = new JPanel();
		this.BordeCreacion.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Creacion", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		this.panelDerecho.add(this.BordeCreacion, crearGBCBorde(0));
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
		
		//armo el panel simulacion y luego lo añado al panel derecho
		this.hacerPanelSimulacion();
		this.panelDerecho.add(this.BordeSimulacion, crearGBCBorde(3));
		
		this.hacerPanelElimacion();
		this.panelDerecho.add(this.BordeEliminacion, crearGBCBorde(1));
		
		//armo el panel inicializcion BD
		this.hacerPanelInicializacion();
		this.panelDerecho.add(this.BordeInicializacion, crearGBCBorde(2));
		
	}

	private void hacerPanelCreacion() {
		this.labelNombeCreacion = new JLabel("Nombre", SwingConstants.CENTER);
		panelCreacion.add(this.labelNombeCreacion);
		
		this.labelDNICreacion = new JLabel("DNI", SwingConstants.CENTER);
		panelCreacion.add(this.labelDNICreacion);
		
		this.panelTextFieldNombre = new JPanel();
		panelCreacion.add(this.panelTextFieldNombre);
		
		this.textFieldNombre = new JTextField();
		this.textFieldNombre.addKeyListener(this);
		this.panelTextFieldNombre.add(this.textFieldNombre);
		this.textFieldNombre.setColumns(10);
		
		this.panelTextFieldDNICreacion = new JPanel();
		panelCreacion.add(this.panelTextFieldDNICreacion);
		
		this.textFieldDniCreacion = new JTextField();
		this.textFieldDniCreacion.addKeyListener(this);
		this.panelTextFieldDNICreacion.add(this.textFieldDniCreacion);
		this.textFieldDniCreacion.setColumns(10);
		
		this.panelBotonRegistrar = new JPanel();
		panelCreacion.add(this.panelBotonRegistrar);
		
		this.btnRegistrar = new JButton("Registrar");
		this.panelBotonRegistrar.add(this.btnRegistrar);
		
		this.panelCreacion = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 0 , 0, };
		gbl.rowHeights = new int[] { 0, 0, 0 };
		gbl.columnWeights = new double[] { 1.0, 1.0 };
		gbl.rowWeights = new double[] { 0.3, 0.3, 0.2 }; // proporciones
		this.panelCreacion.setLayout(gbl);
		
	}

	private void hacerPanelIzquierdo() {
		this.panelIzquierdo = new JPanel();

		this.hacerPanelAsociados();
	}

	private void hacerPanelAsociados() {
		this.panelIzquierdo.setLayout(new GridLayout(1, 0, 0, 0));
		// --- Asociados borde ---
		this.panelAsociadosPermanenciaBorde = new JPanel();
		this.panelAsociadosPermanenciaBorde.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Asociados en el sistema", TitledBorder.LEFT, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		this.panelIzquierdo.add(this.panelAsociadosPermanenciaBorde);
		GridBagLayout gbl_panelAsociadosPermanenciaBorde = new GridBagLayout();
		gbl_panelAsociadosPermanenciaBorde.columnWidths = new int[]{0, 0};
		gbl_panelAsociadosPermanenciaBorde.rowHeights = new int[]{0, 0};
		gbl_panelAsociadosPermanenciaBorde.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelAsociadosPermanenciaBorde.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.panelAsociadosPermanenciaBorde.setLayout(gbl_panelAsociadosPermanenciaBorde);
		
		this.panelAsociadosPermanencia = new JPanel(new BorderLayout());
		GridBagConstraints gbc_panelAsociadosPermanencia = new GridBagConstraints();
		gbc_panelAsociadosPermanencia.fill = GridBagConstraints.BOTH;
		gbc_panelAsociadosPermanencia.gridx = 0;
		gbc_panelAsociadosPermanencia.gridy = 0;
		this.panelAsociadosPermanenciaBorde.add(this.panelAsociadosPermanencia, gbc_panelAsociadosPermanencia);

		this.asociadosPersistenciaJList = new JList<>();
		asociadosPersistenciaJList.addListSelectionListener(this);
		this.asociadosSumulacionJList = new JList<>();
		asociadosSumulacionJList.addListSelectionListener(this);

		this.asociadosPersistenciaJList.setVisibleRowCount(0); // 0 significa "no forzar un numero de filas visible"

		this.scrollPanePermanencia = new JScrollPane(this.asociadosPersistenciaJList);
		//this.scrollPane.setPreferredSize(new Dimension(0, 0));
		
		this.panelAsociadosPermanencia.add(this.scrollPanePermanencia, BorderLayout.CENTER);
	}

	/**
	 * arma el bloque simulacion. no lo añade al panel derecho
	 */
	public void hacerPanelSimulacion() {
		this.BordeSimulacion = new JPanel();
		this.BordeSimulacion.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Simulacion", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		

		GridBagLayout gbl_bordeSimulacion = new GridBagLayout();
		gbl_bordeSimulacion.columnWidths = new int[]{0, 0};
		gbl_bordeSimulacion.rowHeights = new int[]{0, 0};
		gbl_bordeSimulacion.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_bordeSimulacion.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		this.BordeSimulacion.setLayout(gbl_bordeSimulacion);
		
		this.panelSimulacion = new JPanel();
		GridBagConstraints gbc_panelSimulacion = new GridBagConstraints();
		gbc_panelSimulacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelSimulacion.gridx = 0;
		gbc_panelSimulacion.gridy = 0;
		this.BordeSimulacion.add(this.panelSimulacion, gbc_panelSimulacion);
		FlowLayout flowLayoutSimulacion = new FlowLayout(FlowLayout.CENTER, 10, 5);
		// (alineación centrada, 10 px de espacio horizontal, 5 px vertical)
		this.panelSimulacion.setLayout(flowLayoutSimulacion);
		
		this.btnAgregarASimulacion = new JButton("Agregar a simulacion");
		this.panelSimulacion.add(btnAgregarASimulacion);
		this.btnSimular = new JButton("Simular");
		this.panelSimulacion.add(this.btnSimular);
	}
	
	/**
	 * arma el bloque eliminacion. no lo añade al panel derecho
	 */
	public void hacerPanelElimacion() {
		this.BordeEliminacion = new JPanel();
		this.BordeEliminacion.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Eliminacion", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		GridBagLayout gbl_bordeEliminacion = new GridBagLayout();
		gbl_bordeEliminacion.columnWidths = new int[]{0, 0};
		gbl_bordeEliminacion.rowHeights = new int[]{0, 0};
		gbl_bordeEliminacion.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_bordeEliminacion.rowWeights = new double[]{0.2, Double.MIN_VALUE};
		this.BordeEliminacion.setLayout(gbl_bordeEliminacion);
		
		this.panelEliminacion = new JPanel();
		GridBagConstraints gbc_panelEliminacion = new GridBagConstraints();
		gbc_panelEliminacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelEliminacion.gridx = 0;
		gbc_panelEliminacion.gridy = 0;
		this.BordeEliminacion.add(this.panelEliminacion, gbc_panelEliminacion);
		this.panelEliminacion.setLayout(new GridLayout(0, 3, 0, 0));
		
		this.labelDNIEliminacion = new JLabel("DNI", SwingConstants.CENTER);
		this.panelEliminacion.add(this.labelDNIEliminacion);
		
		this.panel_7 = new JPanel();
		this.panelEliminacion.add(this.panel_7);
		
		this.textFieldDniEliminacion = new JTextField();
		this.textFieldDniEliminacion.addKeyListener(this);
		this.panel_7.add(this.textFieldDniEliminacion);
		this.textFieldDniEliminacion.setColumns(10);
		
		this.panel_6 = new JPanel();
		this.panelEliminacion.add(this.panel_6);
		
		this.btnEliminar = new JButton("Eliminar");
		this.panel_6.add(this.btnEliminar);
	}
	
	/**
	 * arma el bloque inicializacion. no lo añade al panel derecho
	 */
	public void hacerPanelInicializacion() {
		this.BordeInicializacion = new JPanel();
		this.BordeInicializacion.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Inicializacion BD", TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));	
		
	    // Igual que en el panel de simulación
	    GridBagLayout gbl_bordeInicializacion = new GridBagLayout();
	    gbl_bordeInicializacion.columnWidths = new int[]{0, 0};
	    gbl_bordeInicializacion.rowHeights = new int[]{0, 0};
	    gbl_bordeInicializacion.columnWeights = new double[]{1.0, Double.MIN_VALUE};
	    gbl_bordeInicializacion.rowWeights = new double[]{1.0, Double.MIN_VALUE};
	    this.BordeInicializacion.setLayout(gbl_bordeInicializacion);

	    this.panelInicializacion = new JPanel();
	    GridBagConstraints gbc_panelInicializacion = new GridBagConstraints();
	    gbc_panelInicializacion.fill = GridBagConstraints.HORIZONTAL;
	    gbc_panelInicializacion.gridx = 0;
	    gbc_panelInicializacion.gridy = 0;
	    gbc_panelInicializacion.insets = new Insets(5, 0, 5, 0);
	    this.BordeInicializacion.add(this.panelInicializacion, gbc_panelInicializacion);

	    // Igual que simulación: centrado y con espacios uniformes
	    FlowLayout flowLayoutInicializacion = new FlowLayout(FlowLayout.CENTER, 10, 5);
	    this.panelInicializacion.setLayout(flowLayoutInicializacion);

	    this.btnInicializar = new JButton("Inicializar");
	    this.panelInicializacion.add(this.btnInicializar);
	}
	
	private GridBagConstraints crearGBCBorde(int fila) {
		GridBagConstraints gbc = new GridBagConstraints();
	    gbc.fill = GridBagConstraints.BOTH;
	    gbc.gridx = 0;
	    gbc.gridy = fila;
	    gbc.insets = new Insets(0, 0, 5, 0);
	    return gbc;
	}

	public AsociadoDTO getAsociado() {
		String nombre = this.textFieldNombre.getText();
		String dni = this.textFieldDniCreacion.getText();
		AsociadoDTO nuevo = new AsociadoDTO(nombre, dni);
		return nuevo;
	}

	public String getDNI() {
		return this.textFieldDniEliminacion.getText();
	}
	
	public void clearRegistroTextFields() {
		this.textFieldNombre.setText("");
		this.textFieldDniCreacion.setText("");
		this.revalidadBotonRegistrar();
	}
	public void clearEliminacionTextFields() {
		this.textFieldDniEliminacion.setText("");
		this.revalidadBotonEliminar();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {}
	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {
		JTextField tf = (JTextField)arg0.getSource();
		if (tf == this.textFieldDniCreacion || tf == this.textFieldNombre){
			this.revalidadBotonRegistrar();	
		}
		else if (tf == this.textFieldDniEliminacion) {
			this.revalidadBotonEliminar();
		}
	}

	private void revalidadBotonRegistrar() {
		if (textFieldDniCreacion.getText().trim().isEmpty() || textFieldNombre.getText().trim().isEmpty()) {
			actualizarBtn(this.btnRegistrar, false, VentanaGestion.toolTipCompleteAmbos);
		}
		else {
			try {
				Integer.parseInt(textFieldDniCreacion.getText());
				actualizarBtn(this.btnRegistrar, true, null);
			}
			catch (Exception e) {
				actualizarBtn(this.btnRegistrar, false, VentanaGestion.toolTipDniNumerico);
			}
		}	
	}
	private void revalidadBotonEliminar() {
		if (!this.asociadosSimulacionDLM.isEmpty())
			actualizarBtn(this.btnEliminar, false, toolTipVaciaSimulacion);
		else {
			try {
				Integer.parseInt(textFieldDniEliminacion.getText());
				actualizarBtn(this.btnEliminar, true, null);
			}
			catch (Exception e) {
				actualizarBtn(this.btnEliminar, false, VentanaGestion.toolTipDniNumerico);
			}
		}
	}
	 
	private void revalidarBotonSimulacion() {
		if (this.asociadosSimulacionDLM.isEmpty())
			this.actualizarBtn(this.btnSimular, false, this.toolTipSimulacionVacia);
		else
			this.actualizarBtn(this.btnSimular, true, "");
	}
	
	private void revalidarBotonAgregarSimulacion() {
		if (this.asociadosPersistenciaJList.isSelectionEmpty())
			this.actualizarBtn(this.btnAgregarASimulacion,false, this.toolTipSeleccionPersistenciaVacia);
		else
			this.actualizarBtn(this.btnAgregarASimulacion, true, "");
	}


	public void actualizarBtn(JButton boton, boolean activar, String mensajeToolTip) {
		boton.setEnabled(activar);
		boton.setToolTipText(mensajeToolTip);
	}

	/**
	 * Realiza acciones en base a la seleccion de un elemento en alguna lista JList
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
	        return; 
	    }
		//esto es para que cuando se hace clear selection no haga nada
		if (this.deseleccionando) {
			return;
		}
		
		//si el evento vino por la lista de persistencia:
		if (e.getSource().equals(this.asociadosPersistenciaJList)) {
			//quito la seleccion en la otra lista
			this.deseleccionarLista(asociadosSumulacionJList);
			
			AsociadoDTO asoc = this.asociadosPersistenciaJList.getSelectedValue();
			if (asoc != null) {
				this.textFieldDniEliminacion.setText(asoc.getDni());
				this.revalidadBotonEliminar();
				this.btnAgregarASimulacion.setEnabled(true);
			}
		}
		//si el evento vino por la lista de simulacion:
		else 
			if (e.getSource().equals(this.asociadosSumulacionJList)) {
				//quito la seleccion en la otra lista
				this.deseleccionarLista(asociadosPersistenciaJList);
				this.btnAgregarASimulacion.setEnabled(false);
				
				AsociadoDTO asoc = this.asociadosSumulacionJList.getSelectedValue();
				if (asoc != null) {
					this.removeAsociadoSimulacion(asoc);
					this.revalidadBotonEliminar();
			}
		}
	}
	
	/**
	 * Quita la seleccion sobre los elementos de una JList dada
	 * @param lista JList
	 */
	public void deseleccionarLista(JList<AsociadoDTO> lista) {
		this.deseleccionando = true;
		lista.clearSelection();
		SwingUtilities.invokeLater(() -> {
	        this.deseleccionando = false;
	    });
	}
	
	/**
	 * Saca un asociado de la lista de simulacion si ya no estaba
	 * @param asociado
	 */
	public void removeAsociadoSimulacion(AsociadoDTO asociado) {
		this.asociadosSimulacionDLM.removeElement(asociado);
	}

	/**
	 * Agrega un asociado a la lista de simulacion
	 * @param asociado
	 */
	public void addAsociadoSimulacion(AsociadoDTO asociado) {
		if (!this.asociadosSimulacionDLM.contains(asociado)) {
			this.asociadosSimulacionDLM.addElement(asociado);
		}
	}
	
	/**
	 * Agrega una asociado a la lista de persistencia y limpia el formulario de registro
	 * @param asociado
	 */
	public void addAsociadoPermanencia(AsociadoDTO asociado) {
		this.asociadosPersistenciaDLM.addElement(asociado);
		clearRegistroTextFields();
	}

	/**
	 * Saca un asociado de la lista de persistencia dado un dni y setea los botones
	 * @param dniAEliminar
	 */
	public void removeAsociadoPermanencia(String dniAEliminar) {
		int i = 0;
		while (!asociadosPersistenciaDLM.getElementAt(i).getDni().equals(dniAEliminar))
			i++;
		//por como esta diseñado, el dni debe estar si o si
		asociadosPersistenciaDLM.remove(i);
		this.clearEliminacionTextFields();
		this.revalidarBotonSimulacion();
	}
	
	/**
	 * Carga en la lista de simulacion un lista de asociados dada
	 * @param lista de asociadosDTO
	 */
	public void cargarListaAsociados(List<AsociadoDTO> lista) {
		for (AsociadoDTO asoc: lista)
			this.asociadosPersistenciaDLM.addElement(asoc);
	}

	/**
	 *Accion al recibir el evento del boton "Agregar Simulacion"
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		assert asociadosPersistenciaJList.getSelectedValue() != null;
		this.addAsociadoSimulacion(this.asociadosPersistenciaJList.getSelectedValue());
		this.deseleccionarLista(asociadosPersistenciaJList);
		this.btnAgregarASimulacion.setEnabled(false);
		this.revalidadBotonEliminar();
		this.revalidarBotonSimulacion();
	}
	
	/**
	 * Vacia las listas de persistencia y simulacion
	 */
	public void vaciarListas() {
		this.asociadosPersistenciaDLM.clear();
		this.asociadosSimulacionDLM.clear();
	}
	
	/**
	 * Coloca el action listener en los botonones
	 * @param actionListener
	 */
	public void setActionListener(ActionListener actionListener) {
		this.btnSimular.addActionListener(actionListener);
		this.btnRegistrar.addActionListener(actionListener);
		this.btnEliminar.addActionListener(actionListener);
		this.btnInicializar.addActionListener(actionListener);
	}
	
	public List<AsociadoDTO> getListaAsociadosSimulacion() {
		return Collections.list(this.asociadosSimulacionDLM.elements());
	}

}

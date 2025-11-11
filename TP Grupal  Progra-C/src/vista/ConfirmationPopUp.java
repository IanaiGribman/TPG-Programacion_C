package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ConfirmationPopUp extends JDialog {

    private JLabel lblMensaje;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JPanel panelConfirmar;
    private JPanel panelCancelar;

    /**
     * 
     * @param padre posicion relativa al padre
     * @param ActionCommandConfirmar el action comman de confirmar
     */
    public ConfirmationPopUp(JFrame padre, String ActionCommandConfirmar) {
        super(padre, true); // true = modal
        setSize(400, 200);
        setLocationRelativeTo(padre);
        getContentPane().setLayout(new BorderLayout());
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lblMensaje = new JLabel("", SwingConstants.CENTER);

        // Panel inferior con el boton
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 0, 0, 0));
        
        this.panelCancelar = new JPanel();
        panelBotones.add(this.panelCancelar);
        
        this.btnCancelar = new JButton("Cancelar");
        this.panelCancelar.add(this.btnCancelar);
        btnCancelar.addActionListener(e -> dispose()); //elimina la ventana al clickear
        
        this.panelConfirmar = new JPanel();
        panelBotones.add(this.panelConfirmar);
        //lblMensaje.setFont(new Font("Arial", Font.PLAIN, 14));

        btnConfirmar = new JButton();
        this.panelConfirmar.add(this.btnConfirmar);
        this.btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(e -> dispose()); //elimina la ventana al clickear
        btnConfirmar.setActionCommand(ActionCommandConfirmar);

        getContentPane().add(lblMensaje, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

    }
    public ConfirmationPopUp(JFrame padre, ActionListener actionListenerConfirmar,String ActionCommandConfirmar) {	
    	this(padre,ActionCommandConfirmar);
    	this.addActionListenerConfirmar(actionListenerConfirmar);
    }
    

    public void addActionListenerConfirmar(ActionListener actionListenerConfirmar) {
        btnConfirmar.addActionListener(actionListenerConfirmar);
	}
    
    /**
     * este metodo no se usa pero para mas personalizacion
     * @param actionListenerCancelar action listener del boton cancelar
     */
    public void addActionListenerCancelar(ActionListener actionListenerCancelar) {
    	btnCancelar.addActionListener(actionListenerCancelar);
    }
    
    public void setActionCommandCancelar(String actionCommand) {
    	btnCancelar.setActionCommand(actionCommand);
    }
	/**
     * Muestra el pop-up con los parámetros especificados.
     * @param titulo Título del cuadro de dialogo
     * @param mensaje Texto del mensaje a mostrar
     * @param textoBotonConfirmar Texto del boton de confirmacion
     * @param textoBotonCancelar Texto del boton cancelar
     */
    public void mostrar(String titulo, String mensaje, String textoBotonConfirmar, String textoBotonCancelar) {
        setTitle(titulo);
        lblMensaje.setText(mensaje);
        btnConfirmar.setText(textoBotonConfirmar);
        btnCancelar.setText(textoBotonCancelar);
        setVisible(true);
    }
}

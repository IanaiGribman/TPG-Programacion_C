package vista;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class CustomPopUp extends JDialog {

    private JLabel lblMensaje;
    private JButton btnConfirmar;

    public CustomPopUp(JFrame padre) {
        super(padre, true); // true = modal
        setSize(400, 200);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("SansSerif", Font.BOLD, 16));

        btnConfirmar = new JButton();
        btnConfirmar.addActionListener(e -> dispose()); //elimina la ventana al clickear

        // Panel inferior con el boton
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnConfirmar);

        add(lblMensaje, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        
    }

    /**
     * Muestra el pop-up con los par�metros especificados.
     * @param titulo Titulo del cuadro de dialogo
     * @param mensaje Texto del mensaje a mostrar
     * @param textoBoton Texto del boton de confirmacion
     */
    public void mostrar(String titulo, String mensaje, String textoBoton) {
        setTitle(titulo);
        lblMensaje.setText(mensaje);
        btnConfirmar.setText(textoBoton);
        setVisible(true);
    }
}

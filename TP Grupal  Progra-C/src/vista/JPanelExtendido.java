package vista;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * clase con algunos metodos comunes para ambas ventanas
 */
public abstract class JPanelExtendido extends JPanel {

	private static final long serialVersionUID = 1L;

	protected abstract void configurarBotones();
	protected abstract void verificarInvarianteDeClase();
	
	/**
	 * 
	 * @param boton boton a actualizar su estado
	 * @param activar true si queres habilitar, false desabilitar
	 * @param mensajeToolTip mensaje que se quiere que se muestre o "" si no se quiere mensaje
	 */
	protected void actualizarBtn(JButton boton, boolean activar, String mensajeToolTip) {
		assert boton != null;
		boton.setEnabled(activar);
		boton.setToolTipText(mensajeToolTip);
	}
	

}

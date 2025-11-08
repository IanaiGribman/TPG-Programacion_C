package prueba;

import controladores.Controlador;
import modelo.Ambulancia;
import modelo.ModuloAsociados;
import vista.JFramePrincipal;

public class Prueba4 {

	public static void main(String[] args) {
		Controlador c = new Controlador(new JFramePrincipal(), new Ambulancia(), new ModuloAsociados());

	}

}

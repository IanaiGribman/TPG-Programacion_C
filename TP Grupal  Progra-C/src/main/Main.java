package main;

import java.awt.EventQueue;

import controladores.Controlador;
import modelo.Ambulancia;
import modelo.ModuloAsociados;
import persistencia.BaseDeDatosDAO;
import persistencia.IBaseDeDatos;
import persistencia.ManagerXMLConfig;
import persistencia.ParametrosBaseDeDatos;
import vista.JFramePrincipal;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String direccionArchivo = "src/persistencia/BaseDeDatosConfig.xml";
					ParametrosBaseDeDatos parametros = ManagerXMLConfig.leerParametrosXML(direccionArchivo);
					BaseDeDatosDAO bd = new BaseDeDatosDAO(parametros);
					JFramePrincipal vista = new JFramePrincipal();
					Ambulancia ambulancia = new Ambulancia();
					ModuloAsociados ma = new ModuloAsociados(bd);
					new Controlador(vista, ambulancia, ma);
					
					vista.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

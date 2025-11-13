package main;

import java.awt.EventQueue;

import controladores.Controlador;
import controladores.ControladorAsociados;
import modelo.simulacion.Ambulancia;
import modelo.simulacion.ModuloSimulacion;
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
					IBaseDeDatos bd = new BaseDeDatosDAO(parametros);
					JFramePrincipal vista = new JFramePrincipal();
					Ambulancia ambulancia = new Ambulancia();
					
					ControladorAsociados ca = new ControladorAsociados(bd, vista);
					ModuloSimulacion cs = new ModuloSimulacion(ambulancia, vista);
					Controlador contr = new Controlador(cs, ca, vista);
					vista.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

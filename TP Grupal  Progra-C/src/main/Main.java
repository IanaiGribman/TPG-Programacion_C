package main;

import java.awt.EventQueue;

import controladores.Controlador;
import modelo.Ambulancia;
import modelo.ModuloAsociados;
import vista.JFramePrincipal;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFramePrincipal vista = new JFramePrincipal();
					Ambulancia ambulancia = new Ambulancia();
					ModuloAsociados ma = new ModuloAsociados();
					Controlador controlador = new Controlador(vista, ambulancia, ma);
					
					vista.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

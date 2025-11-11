package controladores;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ManagerXMLSimulacion {	
	static public ParametrosSimulacion leerSimulacionXML(String direccionArchivoXMLConfig) {
		ParametrosSimulacion parametros = null;
		try {
			XMLDecoder decoder = new XMLDecoder(
					new BufferedInputStream(new FileInputStream(direccionArchivoXMLConfig)));
			parametros = (ParametrosSimulacion) decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);// debug
			e.printStackTrace();
		}
		return parametros;
	}
}

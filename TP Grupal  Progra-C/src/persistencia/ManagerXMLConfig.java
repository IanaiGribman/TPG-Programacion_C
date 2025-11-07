package persistencia;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Clase para la carga y lectura del archivo xml que contiene los datos
 * relacionados a la base de datos (su host, usuario y clave)
 */
public class ManagerXMLConfig {
	static public ParametrosBaseDeDatos leerParametrosXML(String direccionArchivoXMLConfig) {
		ParametrosBaseDeDatos parametros = null;
		try {
			XMLDecoder decoder = new XMLDecoder(
					new BufferedInputStream(new FileInputStream(direccionArchivoXMLConfig)));
			parametros = (ParametrosBaseDeDatos) decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);// debug
			e.printStackTrace();
		}
		return parametros;
	}
}

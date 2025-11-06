package persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


/**
 * Clase para la carga y lectura del archivo xml que contiene los datos relacionados a la base de datos (su host, usuario y clave)
 */
public class GeneracionXMLConfig {
	static public void cargarParametrosXML(ParametrosBaseDeDatos parametros, String direccionArchivoXMLConfig) {
		try {
			XMLEncoder encoder = new XMLEncoder(
					new BufferedOutputStream(new FileOutputStream(direccionArchivoXMLConfig)));
			encoder.writeObject(parametros);
			encoder.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);// debug
			e.printStackTrace();
		}
	}

	// quizas esto deberia estar en algun util como xml manager
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
	
	
	public static void main(String[] args) {
		String direccionArchivo = "src/persistencia/BaseDeDatosConfig.xml";
		ParametrosBaseDeDatos parametros = new ParametrosBaseDeDatos();
		parametros.initialize("Direccion", "Usuario", "Clave");
		
		cargarParametrosXML(parametros, direccionArchivo);
	}

}

package persistencia;

public class GeneracionXMLConfig {
	
	public static void main(String[] args) {
		String direccionArchivo = "src/persistencia/BaseDeDatosConfig.xml";
		ParametrosBaseDeDatos parametros = new ParametrosBaseDeDatos();
		parametros.initialize("Direccion", "Usuario", "Clave");
		
		BaseDeDatosDAO.cargarParametrosXML(parametros, direccionArchivo);
	}

}

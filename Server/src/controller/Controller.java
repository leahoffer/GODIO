package controller;


public class Controller {

	private static Controller instance;
	
	public static Controller getInstance() {
		if (instance==null)
			instance = new Controller();
		return instance;
	}
	
	private Controller() {
		/**
		 * Ver si no tenemos ganas de crearlo con algo particular, o lo creamos as� vac�o sin nada.
		 * Pero es Singleton as� que sale constructor privado
		 */
	}

	public void crearCliente(String cuit, String razon_social, String telefono, String direccion, boolean r_inscripto,
			String notas) {
		/**
		 * Creo un m�todo para que me deje crear el RO y esas otras cosas
		 */
		
	}

}

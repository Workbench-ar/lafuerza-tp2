package laFuerza;

import java.io.IOException;
import java.util.Scanner;

import dao.DAOFactory;
import dao.UsuariosDAO;

public abstract class LectorConsola {
	private static Scanner IN;

	public static void abrirEscanner() {
		IN = new Scanner(System.in);
	}

	public static void cerrarEscanner() {
		IN.close();
	}

	public static String esperarRespuestaUsuario(Usuario usuario) {

		String entradaConsola = IN.nextLine().toUpperCase();

		while (!entradaConsola.equals("S") && !entradaConsola.equals("N")) {
			if (entradaConsola.equals("R")) {
				System.out.println(GeneradorResumenComprasUsuario.generarResumen(usuario));
				System.out.println("Esperando confirmacion de compra (S / N)");

			} else {
				System.out.println("No se ingreso un mensaje válido. Por favor intente nuevamente");

			}

			entradaConsola = IN.nextLine().toUpperCase();
		}
		System.out.println(entradaConsola);
		return entradaConsola;
	}

	public static void esperarRespuestaAdministrador() throws IOException {

		UsuariosDAO userDAO = DAOFactory.getUserDAO();

		String entradaConsola = IN.nextLine().toUpperCase();
		Usuario usuario = userDAO.findByname(entradaConsola);

		while (!entradaConsola.equals("I") && !entradaConsola.equals("F") && usuario == null) {

			System.out.println("No se ingreso un mensaje válido. Por favor intente nuevamente");

			entradaConsola = IN.nextLine().toUpperCase();
			usuario = userDAO.findByname(entradaConsola);

		}

		if (entradaConsola.equals("I")) {
			System.out.println("Sugiriendo propuestas nuevamente.");
			Ofertador.sugerirPropuestasAusuarios(CargadorDeDatos.getPropuestas(), CargadorDeDatos.getUsuarios());
		} else if (entradaConsola.equals("F")) {
			System.out.println("Sistema Finalizado.");
			Ofertador.cerrarSistema();
		} else {
			System.out.println(GeneradorResumenComprasUsuario.generarResumen(usuario));
			VisualizadorMensajesConsola.mostrarMensajeFinSistema();
		}
	}

}

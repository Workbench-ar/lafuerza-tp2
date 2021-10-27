package laFuerza;

import java.io.IOException;

public class Administrador {
	public void activarSistema() throws IOException {

		CargadorDeDatos.agregarAtracciones();
		CargadorDeDatos.agregarPromociones();
		CargadorDeDatos.agregarUsuarios();

		Ofertador.sugerirPropuestasAusuarios(CargadorDeDatos.getPropuestas(), CargadorDeDatos.getUsuarios());

	}

}

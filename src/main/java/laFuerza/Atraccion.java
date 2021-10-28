package laFuerza;

import java.util.LinkedList;

import dao.AtraccionesDAO;
import dao.DAOFactory;

public class Atraccion extends Propuesta {

	private int cupoDisponible;

	public Atraccion(int costo, double duracion, TipoAtraccion tipoAtraccion, int cupoDisponible, String nombre,
			int id) {

		this.propuestaID = id;
		this.costo = costo;
		this.tiempoTotal = duracion;
		this.tipoAtraccion = tipoAtraccion;
		this.cupoDisponible = cupoDisponible;
		this.nombre = nombre;

	}

	@Override
	public void actualizarCupoDisponible() {
		cupoDisponible--;
		AtraccionesDAO atraccionDAO = DAOFactory.getAtraccionesDAO();
		atraccionDAO.updateCupos(this);

	}

	@Override
	public String toString() {
		String toString = "";
		toString += "- Atracción: " + nombre + "\n";
		toString += "\tTipo de Atracción: " + tipoAtraccion.getNombre() + "\n";
		toString += "\tCosto: " + getCosto() + " créditos galácticos\n";
		toString += "\tDuración de la Atracción: " + ModificadorFormatoHora.obtenerHoraConFormato(getTiempoUtilizado())
				+ "\n\n";

		return toString;

	}

	@Override
	public boolean hayCupoDisponible() {
		return cupoDisponible > 0;
	}

	public LinkedList<Atraccion> getAtraccionesIncluidas() {
		LinkedList<Atraccion> atraccionesIncluidas = new LinkedList<Atraccion>();
		atraccionesIncluidas.add(this);
		return atraccionesIncluidas;
	}

	public int getCupoDisponible() {
		return cupoDisponible;
	}

	public int getId_atraccion() {
		return propuestaID;
	}

	public int setId_atraccion(int id) {
		return propuestaID = id;
	}

}

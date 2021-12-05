package services;

import java.util.List;

import model.Promocion;
import persistence.PromocionDAO;
import persistence.commons.DAOFactory;

public class PromocionService {

	public List<Promocion> list() {
		return DAOFactory.getPromocionDAO().findAll();
	}

	public Promocion create(Integer tipoPromocion, Integer tipoAtracciones, String nombre, String descripcion, Double variable) {

		Promocion promocion = new Promocion(-1, tipoPromocion, tipoAtracciones, nombre, descripcion, variable);

		if (promocion.isValid()) {
			PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
			promocionDAO.insert(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promocion;
	}

	public Promocion update(Integer id, Integer tipoPromocion, Integer tipoAtracciones, String nombre, String descripcion, Double variable) {

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		Promocion promocion = promocionDAO.find(id);

		promocion.setTipoPromocion(tipoPromocion);
		promocion.setTipoAtracciones(tipoAtracciones);
		promocion.setNombre(nombre);
		promocion.setDescripcion(descripcion);
		promocion.setVariable(variable);

		if (promocion.isValid()) {
			promocionDAO.update(promocion);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return promocion;
	}

	public void delete(Integer id) {
		Promocion promocion = new Promocion(id, null, null, null, null, null);

		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		promocionDAO.delete(promocion);
	}

	public Promocion find(Integer id) {
		PromocionDAO promocionDAO = DAOFactory.getPromocionDAO();
		return promocionDAO.find(id);
	}

}

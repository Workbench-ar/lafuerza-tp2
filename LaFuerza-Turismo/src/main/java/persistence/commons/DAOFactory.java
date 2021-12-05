package persistence.commons;

import persistence.AttractionDAO;
import persistence.PromocionDAO;
import persistence.UserDAO;
import persistence.impl.AttractionDAOImpl;
import persistence.impl.PromocionDAOImpl;
import persistence.impl.UserDAOImpl;

public class DAOFactory {

	public static UserDAO getUserDAO() {
		return new UserDAOImpl();
	}
	
	public static AttractionDAO getAttractionDAO() {
		return new AttractionDAOImpl();
	}
	
	public static PromocionDAO getPromocionDAO() {
		return new PromocionDAOImpl();
	}
}

package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Promocion;
import persistence.PromocionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MissingDataException;

public class PromocionDAOImpl implements PromocionDAO {

	public List<Promocion> findAll() {
		try {
			String sql = "SELECT * FROM PROMOCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Promocion> promociones = new LinkedList<Promocion>();
			while (resultados.next()) {
				promociones.add(toPromocion(resultados));
			}

			return promociones;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public Promocion find(Integer id) {
		try {
			String sql = "SELECT * FROM PROMOCIONES WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);

			ResultSet resultados = statement.executeQuery();

			Promocion promocion = null;
			if (resultados.next()) {
				promocion = toPromocion(resultados);
			}

			return promocion;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	
	private Promocion toPromocion(ResultSet promocionRegister) throws SQLException {
		return new Promocion(promocionRegister.getInt(1), promocionRegister.getInt(2), promocionRegister.getInt(3),
				promocionRegister.getString(4), promocionRegister.getString(5), promocionRegister.getDouble(6));
	}

	@Override
	public int insert(Promocion promocion) {
		try {
			String sql = "INSERT INTO PROMOCIONES (TIPO_PROMOCION, TIPO_ATRACCIONES, NOMBRE, DESCRIPCION, VARIABLE) VALUES (?, ?, ?, ?, ?)";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setInt(i++, promocion.getTipoPromocion());
			statement.setInt(i++, promocion.getTipoAtracciones());
			statement.setString(i++, promocion.getNombre());
			statement.setString(i++, promocion.getDescripcion());
			statement.setDouble(i++, promocion.getVariable());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int update(Promocion promocion) {
		try {
			String sql = "UPDATE PROMOCIONES SET TIPO_PROMOCION = ?, TIPO_ATRACCIONES = ?, NOMBRE = ?, DESCRIPCION = ?, VARIABLE = ? WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			int i = 1;
			statement.setInt(i++, promocion.getTipoPromocion());
			statement.setInt(i++, promocion.getTipoAtracciones());
			statement.setString(i++, promocion.getNombre());
			statement.setString(i++, promocion.getDescripcion());
			statement.setDouble(i++, promocion.getVariable());
			statement.setInt(i++, promocion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int delete(Promocion promocion) {
		try {
			String sql = "DELETE FROM PROMOCIONES WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, promocion.getId());
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}

	@Override
	public int countAll() {
		try {
			String sql = "SELECT COUNT(1) AS TOTAL FROM PROMOCIONES";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total;
		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
}

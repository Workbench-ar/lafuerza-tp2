package controller.promociones;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Promocion;
import model.TipoAtraccion;
import services.PromocionService;

@WebServlet("/promociones/edit.do")
public class EditPromocionServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private PromocionService promocionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionService = new PromocionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		Promocion promocion = promocionService.find(id);
		req.setAttribute("promocion", promocion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promociones/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		Integer tipoPromocion = Integer.parseInt(req.getParameter("Tipo_Promocion"));
		Integer tipoAtracciones = Integer.parseInt(req.getParameter("Tipo_Atracciones"));
		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		Double variable = Double.parseDouble(req.getParameter("variable"));

		Promocion promocion = promocionService.update(id, tipoPromocion, tipoAtracciones, nombre, descripcion, variable);

		if (promocion.isValid()) {
			resp.sendRedirect("/turismo/promociones/index.do");
		} else {
			req.setAttribute("promocion", promocion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promociones/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
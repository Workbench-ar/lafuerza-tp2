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

@WebServlet("/promociones/create.do")
public class CreatePromocionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private PromocionService promocionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionService = new PromocionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promociones/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer tipoPromocion = Integer.parseInt(req.getParameter("Tipo_Promocion"));
		Integer tipoAtracciones = Integer.parseInt(req.getParameter("Tipo_Atracciones"));
		String nombre = req.getParameter("nombre");
		String descripcion = req.getParameter("descripcion");
		Double variable = Double.parseDouble(req.getParameter("variable"));

		Promocion promocion = promocionService.create(tipoPromocion, tipoAtracciones, nombre, descripcion, variable);
		if (promocion.isValid()) {
			resp.sendRedirect("/turismo/promociones/index.do");
		} else {
			req.setAttribute("promocion", promocion);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/promociones/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
package jdbc_servlet_product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		double price = Double.parseDouble(req.getParameter("price"));
		String state = req.getParameter("state");
		String brand = req.getParameter("brand");

		Product product = new Product();
		product.setId(id);
		product.setName(name);
		product.setState(state);
		product.setBrand(brand);

		ServletContext context = getServletContext();
		double cgst = Double.parseDouble(context.getInitParameter("CGST"));
		double sgst = 0;
		if (state.equals("MH")) {
			ServletConfig config = getServletConfig();
			sgst = Double.parseDouble(config.getInitParameter("Maharashtra"));

		} else if ((state.equals("MP"))) {
			ServletConfig config = getServletConfig();
			sgst = Double.parseDouble(config.getInitParameter("MadhyaPradesh"));

		} else {
			ServletConfig config = getServletConfig();
			sgst = Double.parseDouble(config.getInitParameter("TamilNadu"));
		}
		price += (sgst + cgst) * price;
		product.setPrice(price);

		ProductCRUD crud = new ProductCRUD();
		try {
			int result = crud.saveProduct(product);
			if (result != 0) {
//				RequestDispatcher dispatcher = req.getRequestDispatcher("Success.html");
//				dispatcher.forward(req, resp);
				//clientside redirection
				//resp.sendRedirect("Success.html");
				resp.sendRedirect("https://www.facebook.com/");

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}

package ss.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ss.Product;
import ss.dao.ProductDAO;

/**
 * Servlet implementation class InsertProduct
 */
@WebServlet("/InsertProduct")
public class InsertProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product product = new Product();
		product.setDescription(request.getParameter("description"));
		product.setCategoryCode(request.getParameter("category"));
		product.setAvailableQuantity(Integer.parseInt(request.getParameter("availableQuantity")));
		product.setCost(Double.parseDouble(request.getParameter("cost")));
		product.setListPrice(Double.parseDouble(request.getParameter("listPrice")));
		
		ProductDAO productDAO = new ProductDAO();
		int productId = productDAO.insertProduct(product);
		
		response.sendRedirect("/SalesSystem/GetProduct?productId=" + productId + "&msg=" + "Product Inserted Successfully");
	}
}

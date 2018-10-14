package ss.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ss.dao.CustomerDAO;
import ss.dao.ProductDAO;

/**
 * Servlet implementation class CreateOrderForm
 */
@WebServlet("/CreateOrderForm")
public class CreateOrderForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrderForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDAO customerDAO = new CustomerDAO();
		request.setAttribute("customers", customerDAO.getCustomers());
		
		ProductDAO productDAO = new ProductDAO();
		request.setAttribute("products", productDAO.getProducts());
		
		getServletContext().getRequestDispatcher("/createOrder.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}

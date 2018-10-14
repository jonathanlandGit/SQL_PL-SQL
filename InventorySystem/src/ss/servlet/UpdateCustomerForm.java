package ss.servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ss.Customer;
import ss.dao.CustomerDAO;


/**
 * Servlet implementation class UpdateCustomerForm
 */
@WebServlet("/UpdateCustomerForm")
public class UpdateCustomerForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCustomerForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerDAO cDAO = new CustomerDAO();
		Customer customer = cDAO.getCustomer(Integer.parseInt(request.getParameter("customerNo")));
		request.setAttribute("customer", customer);
		getServletContext().getRequestDispatcher("/updateCustomer.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package ss.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ss.Customer;
import ss.dao.CustomerDAO;
import ss.util.DateUtil;

/**
 * Servlet implementation class InsertCustomer
 */
@WebServlet("/InsertCustomer")
public class InsertCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCustomer() {
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
		Customer customer = new Customer();
		customer.setFirstName(request.getParameter("firstName"));
		customer.setLastName(request.getParameter("lastName"));
		String bdate = request.getParameter("birthDate");
		if(bdate!=null && !bdate.equals(""))
			customer.setBirthDate(DateUtil.parseDate(bdate));
		customer.setEmail(request.getParameter("email"));
		customer.setAddress(request.getParameter("address"));
		String hphone = request.getParameter("homePhone");
		if(hphone != null && !hphone.equals("")){
			customer.setHomePhone(Long.parseLong(hphone));
		}
		String mobile = request.getParameter("mobile");
		if(mobile != null && !mobile.equals("")){
			customer.setMobile(Long.parseLong(mobile));
		}
		
		CustomerDAO customerDAO = new CustomerDAO();
		int customerNo = customerDAO.insertCustomer(customer);
		
		response.sendRedirect("/SalesSystem/GetCustomer?customerNo=" + customerNo + "&msg=" + "Customer Inserted Successfully");
	}

}

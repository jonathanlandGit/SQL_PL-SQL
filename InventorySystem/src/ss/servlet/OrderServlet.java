package ss.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ss.Order;
import ss.dao.OrderDAO;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("View"))
			doPost(request, response);
		else
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long orderNo = Long.parseLong(request.getParameter("orderNo"));
		
		OrderDAO odao = new OrderDAO();
		if(request.getParameter("action").equals("Ship")) {
			odao.shipOrder(orderNo);
		}
		else if(request.getParameter("action").equals("Cancel")) {
			odao.cancelOrder(orderNo);
		}
		
		Order order = odao.getOrder(orderNo);
		request.setAttribute("order", order);
		getServletContext().getRequestDispatcher("/displayOrder.jsp").forward(request, response);
	}
}

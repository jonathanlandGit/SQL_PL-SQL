package ss.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ss.Order;
import ss.OrderItem;
import ss.dao.OrderDAO;
import ss.util.DateUtil;

/**
 * Servlet implementation class CreateOrder
 */
@WebServlet("/CreateOrder")
public class CreateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateOrder() {
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
		Order order = new Order();
		order.setCustomerNo(Integer.parseInt(request.getParameter("customerNo")));
		order.setOrderDate(DateUtil.parseDate(request.getParameter("orderDate")));
		order.setOrderItems(getOrderItems(request));
		
		OrderDAO odao = new OrderDAO();
		long orderNo = odao.createOrder(order);
		
		response.sendRedirect("/SalesSystem/OrderServlet?orderNo=" + orderNo + "&action=View&msg=Created Order Successfully");
	}
	
	private ArrayList<OrderItem> getOrderItems(HttpServletRequest request) {
		ArrayList<OrderItem> items = new ArrayList<OrderItem>();
		for(int i=0; i<5; i++) {
			String p = request.getParameter("product" + i);
			String q = request.getParameter("quantity" + i);
			if(p!=null && !p.equals("") && q!=null && !q.equals("")) {
				OrderItem oi = new OrderItem();
				oi.setProductId(Integer.parseInt(p));
				oi.setQuantity(Integer.parseInt(q));
				items.add(oi);
			}
		}
		return items;
	}

}

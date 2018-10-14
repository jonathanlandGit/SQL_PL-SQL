package ss.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ss.CategorySale;
import ss.CustomerSale;
import ss.dao.ReportDAO;
import ss.util.DateUtil;

/**
 * Servlet implementation class SalesReport
 */
@WebServlet("/SalesReport")
public class SalesReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalesReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date fromDate = DateUtil.parseDate(request.getParameter("fromDate"));
		Date toDate = DateUtil.parseDate(request.getParameter("toDate"));
		ReportDAO rdao = new ReportDAO();
		
		if(request.getParameter("reportType").equals("By Category")) {
			ArrayList<CategorySale> categorySales = rdao.getSalesByCategory(fromDate, toDate);
			request.setAttribute("categorySales", categorySales);
			getServletContext().getRequestDispatcher("/salesByCategory.jsp").forward(request, response);
		}
		else if(request.getParameter("reportType").equals("By Customer")) {
			ArrayList<CustomerSale> customerSales = rdao.getSalesByCustomer(fromDate, toDate);
			request.setAttribute("customerSales", customerSales);
			getServletContext().getRequestDispatcher("/salesByCustomer.jsp").forward(request, response);
		}
	}

}

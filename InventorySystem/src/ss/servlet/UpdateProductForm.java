package ss.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ss.Product;
import ss.ProductCategory;
import ss.ProductManual;
import ss.dao.MongoDAO;
import ss.dao.ProductCategoryDAO;
import ss.dao.ProductDAO;

/**
 * Servlet implementation class UpdateProductForm
 */
@WebServlet("/UpdateProductForm")
public class UpdateProductForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO pDAO = new ProductDAO();
		Product product = pDAO.getProduct(Integer.parseInt(request.getParameter("productId")));
		request.setAttribute("product", product);
		
		ProductCategoryDAO pcDAO = new ProductCategoryDAO();
		ArrayList<ProductCategory> categories = pcDAO.getProductCategories();
		request.setAttribute("categories", categories);
		
		MongoDAO mongoDAO = MongoDAO.getInstance();
		ArrayList<ProductManual> manuals = mongoDAO.getProductManuals(product.getProductId());
		request.setAttribute("manuals", manuals);
		getServletContext().getRequestDispatcher("/updateProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

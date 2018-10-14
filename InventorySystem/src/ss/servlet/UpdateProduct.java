package ss.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import ss.Product;
import ss.dao.MongoDAO;
import ss.dao.ProductDAO;

/**
 * Servlet implementation class UpdateProduct
 */
@WebServlet("/UpdateProduct")
@MultipartConfig
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProduct() {
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
		String action = getStringParameter(request, "action");
		if(action!=null && action.equalsIgnoreCase("DELETE")) {
			// Delete Product Manual
			int productId = getIntParameter(request, "productId");
			String fileName = getStringParameter(request, "fileName");
			MongoDAO mongoDAO = MongoDAO.getInstance();
			mongoDAO.deleteProductManual(productId, fileName);
			response.sendRedirect("/SalesSystem/UpdateProductForm?productId=" + productId);
		}
		else {
			// Update Product
			Product product = new Product();
			int productId = getIntParameter(request, "productId");
			product.setProductId(productId);
			product.setDescription(getStringParameter(request, "description"));
			product.setCategoryCode(getStringParameter(request, "category"));
			product.setAvailableQuantity(getIntParameter(request, "availableQuantity"));
			product.setCost(getDoubleParameter(request, "cost"));
			product.setListPrice(getDoubleParameter(request, "listPrice"));
			
			ProductDAO productDAO = new ProductDAO();
			productDAO.updateProduct(product);
			
			// Insert product Manuals
			MongoDAO mongoDAO = MongoDAO.getInstance();
			for(int i=1; i<=3; i++) {
				Part part = request.getPart("manual" + i);
				if(part!=null) {
					String fileName = getFileName(part);
					InputStream is = part.getInputStream();
					if(fileName!=null && !fileName.trim().equals("") && is!=null) {
						mongoDAO.insertProductManual(productId, fileName.trim(), is);
						is.close();
					}
				}
			}
			
			response.sendRedirect("/SalesSystem/GetProduct?productId=" + productId + "&msg=" + "Product Updated Successfully");
		}
	}
	
	private String getStringParameter(HttpServletRequest request, String parameterName) throws IOException, ServletException {
		Scanner scanner = getParameterScanner(request, parameterName);
		String s = scanner.nextLine();
		scanner.close();
		return s;
	}
	
	private int getIntParameter(HttpServletRequest request, String parameterName) throws IOException, ServletException {
		Scanner scanner = getParameterScanner(request, parameterName);
		int i = scanner.nextInt();
		scanner.close();
		return i;
	}
	
	private double getDoubleParameter(HttpServletRequest request, String parameterName) throws IOException, ServletException {
		Scanner scanner = getParameterScanner(request, parameterName);
		double d = scanner.nextDouble();
		scanner.close();
		return d;
	}
	
	private Scanner getParameterScanner(HttpServletRequest request, String parameterName) throws IOException, ServletException {
		Part part = request.getPart(parameterName);
		return new Scanner(part.getInputStream());
	}
	
	private String getFileName(Part part) {
		String contentHeader = part.getHeader("content-disposition");
		String fileNameHeader = "filename";
		if(contentHeader!=null) {
			for(String h : contentHeader.split(";")) {
				h = h.trim();
				if(h.startsWith(fileNameHeader + "=")) {
					String fileName = h.substring(fileNameHeader.length()+1, h.length());
					if(fileName.startsWith("\"") && fileName.endsWith("\""))
						return fileName.substring(1,fileName.length()-1);
					else
						return fileName;
				}
			}
		}
		return null;
	}

}

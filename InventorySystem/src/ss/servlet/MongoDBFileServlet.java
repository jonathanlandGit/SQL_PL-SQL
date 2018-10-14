package ss.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ss.dao.MongoDAO;

/**
 * Servlet implementation class MongoDBFileServlet
 */
@WebServlet("/MongoDBFileServlet")
public class MongoDBFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MongoDBFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get input stream for the requested file from MongoDB
		int productId = Integer.parseInt(request.getParameter("productId"));
		String fileName = request.getParameter("fileName");
		MongoDAO mongoDAO = MongoDAO.getInstance();
		InputStream is = mongoDAO.getInputStream(productId, fileName);
		
		// Set headers
		OutputStream os = response.getOutputStream();
		String mimeType = getServletContext().getMimeType(fileName);
		if(mimeType!=null && !mimeType.equals(""))
			response.setContentType(mimeType);
		else
			response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		
		// Stream the contents of the file
		byte[] buf = new byte[64];
		int len;
		while((len=is.read(buf)) != -1) {
			os.write(buf, 0, len);
		}
		
		// Flush and close I/O streams
		is.close();
		os.flush();
		os.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

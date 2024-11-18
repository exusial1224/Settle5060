package DaoTest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PurchaseDAO;



@WebServlet(urlPatterns={"/DaoTest/hujiwaratest"})
public class HujiwaraTest extends HttpServlet {

	public void doGet (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			PurchaseDAO PurchaseDAO = new PurchaseDAO();
			int list = PurchaseDAO.getPurPrice(2);
			out.println(list);


		} catch(Exception e) {
			e.printStackTrace(out);
		}
	}
}

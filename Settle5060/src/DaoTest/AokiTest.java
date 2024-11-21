package DaoTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PurchaseDAO;



@WebServlet(urlPatterns={"/DaoTest/aokitest"})
public class AokiTest extends HttpServlet {

	public void doGet (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {

			PurchaseDAO pd = new PurchaseDAO();
			List<Integer> list = pd.Purchase(192,2,425,3,0);

			//SlotDAO sd = new SlotDAO();
			//int num = sd.getRemainingSlot(1);

			out.println(list);


		} catch(Exception e) {
			e.printStackTrace(out);
		}
	}
}

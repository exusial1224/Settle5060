package DaoTest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SlotDAO;



@WebServlet(urlPatterns={"/DaoTest/aokitest"})
public class AokiTest extends HttpServlet {

	public void doGet (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {

			//PurchaseDAO pd = new PurchaseDAO();
			//List<Integer> list = pd.Cancel(192,2,400,50,0);

			SlotDAO sd = new SlotDAO();
			int num = sd.getSlotMaxCancelOut(2);

			out.println(num);


		} catch(Exception e) {
			e.printStackTrace(out);
		}
	}
}

package DaoTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PurchaseExp;
import dao.PurchaseDAO;



@WebServlet(urlPatterns={"/DaoTest/aokitest"})
public class AokiTest extends HttpServlet {

	public void doGet (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {

			PurchaseDAO pd1 = new PurchaseDAO();
			//List<Integer> list = pd.Purchase(192,1,400,1,0);
			List<PurchaseExp> list = pd1.getPurchaseHistory(192);

			//SlotDAO sd = new SlotDAO();
			//int num = sd.getSlotMaxCancelOut(2);

			//PurchaseExp pd = pd1.getOneTkt(1);


			//out.println(pd1.getPur_id());
			//out.println(pd.getMbr_id());
			//out.println(pd.getSl_id());
			//out.println(pd.getPur_price());
			//out.println(pd.getNum_adlt_tkt());
			//out.println(pd.getNum_chld_tkt());
			//out.println(pd.getTime_pur());
			//out.println(pd.getRsv_admitted());


			for (PurchaseExp p : list) {
				out.println(p.getFac_name());
			}


			//out.println(pd.getStart_time());
			//out.println(pd.getEnd_time());

			//out.println(pd.getBus_date());

			//out.println(list);


		} catch(Exception e) {
			e.printStackTrace(out);
		}
	}
}

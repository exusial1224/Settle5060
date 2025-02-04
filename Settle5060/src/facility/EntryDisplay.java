package facility;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.OrganizationPurchaseExp;
import bean.SlotExp;
import dao.OrganizationPurchaseDAO;
import dao.SlotDAO;

@WebServlet("/facility/EntryDisplay")
public class EntryDisplay extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //セッション取得
    	HttpSession session = request.getSession();

    	//テスト用
    	//session.setAttribute("fac_id","1");

    	//今日日付を取得
    	LocalDate today = LocalDate.now();

    	//DAOの取得
    	SlotDAO slotDao = new SlotDAO();
    	OrganizationPurchaseDAO opDao = new OrganizationPurchaseDAO();

		try {
	    	//当日タイムスロット取得
	    	List<SlotExp> slotList = slotDao.getAllSlots(Integer.parseInt(session.getAttribute("facilityId").toString()),today);

	    	//test用
//	    	List<SlotExp> slotList = new ArrayList();
//	    	SlotExp se = new SlotExp();
//	    	se.setRemain(10);
//	    	se.setStart_time(new Time(10));
//	    	se.setEnd_time(new Time(20));
//	    	slotList.add(se);
//	    	SlotExp se2 = new SlotExp();
//	    	se2.setRemain(20);
//	    	se2.setStart_time(new Time(110));
//	    	se2.setEnd_time(new Time(220));
//	    	slotList.add(se2);

	    	//当日団体来場者
	    	List<OrganizationPurchaseExp> opList = opDao.getOrganization(Integer.parseInt(session.getAttribute("facilityId").toString()),today);

	    	//test用

//	    	List<OrganizationPurchaseExp> opList = new ArrayList();
//	    	OrganizationPurchaseExp op = new OrganizationPurchaseExp();
//	    	op.setOrg_name("株式会社　青木商事");
//	    	op.setRep_name("青木　淳晟");
//	    	op.setStart_time(new Time(10));
//	    	op.setOrg_tel("03-1234-5678");
//	    	op.setGr_tkt_admitted(true);
//	    	opList.add(op);
//	    	OrganizationPurchaseExp op2 = new OrganizationPurchaseExp();
//	    	op2.setOrg_name("株式会社　青木商事");
//	    	op2.setRep_name("青木　淳晟");
//	    	op2.setStart_time(new Time(10));
//	    	op2.setOrg_tel("03-1234-5678");
//	    	opList.add(op2);
//	    	System.out.println(today);
//	    	System.out.println(opList);
	    	//取得情報をrequestにつめつめ
	    	request.setAttribute("soltList", slotList);
	    	request.setAttribute("opList", opList);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			//エラー処理
			request.getRequestDispatcher("./facilityError.jsp").forward(request, response);
			return;
		}
    	//画面表示
    	request.getRequestDispatcher("/facility/entry.jsp").forward(request, response);

    }
}


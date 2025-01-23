package facility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrganizationPurchaseDAO;
import dao.SlotDAO;

@WebServlet("/facility/OrganizationCancelComplete")
public class OrganizationCancelComplete extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //セッション取得
    	HttpSession session = request.getSession();
    	//セッション上のメッセージを破棄
    	session.removeAttribute("message");


    	//DAOの取得
    	SlotDAO slotDao = new SlotDAO();
    	try {
    	int org_pur_id = Integer.parseInt(request.getParameter("org_pur_id"));
    		OrganizationPurchaseDAO organizationopurchaseDao = new OrganizationPurchaseDAO();
			List<Integer> result = organizationopurchaseDao.OrganizationCancel(org_pur_id);
			session.setAttribute("message", "団体購入のキャンセルが正常に終了しました。");
			response.sendRedirect("EntryDisplay");
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}




    }
}


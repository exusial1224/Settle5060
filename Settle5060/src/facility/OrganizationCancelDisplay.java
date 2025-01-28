package facility;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.OrganizationPurchaseExp;
import dao.OrganizationPurchaseDAO;
import dao.SlotDAO;

@WebServlet("/facility/OrganizationCancel")
public class OrganizationCancelDisplay extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //セッション取得
    	HttpSession session = request.getSession();
    	int org_sl_id = Integer.parseInt(request.getParameter("org_sl_id"));
    	LocalDate today = LocalDate.now();;
    	//DAOの取得
    	SlotDAO slotDao = new SlotDAO();
    	OrganizationPurchaseDAO opDao = new OrganizationPurchaseDAO();
    	try {
			OrganizationPurchaseExp org_tkt = opDao.getOneTktGr(org_sl_id);
			request.setAttribute("op", org_tkt);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


    	//画面表示
    	request.getRequestDispatcher("/facility/organizationCancel.jsp").forward(request, response);

    }
}


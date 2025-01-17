package membership;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PurchaseDAO;

@WebServlet("/membership/TicketcancelComplete")
public class TicketcancelComplete extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	try {
    		int cancel_num_adlt_tkt = Integer.parseInt(request.getParameter("cancel_adlt_tkt"));
    		int cancel_num_chld_tkt = Integer.parseInt(request.getParameter("cancel_chld_tkt"));
    		int pur_id = Integer.parseInt(request.getParameter("pur_id"));



        	// DAO から入場券情報を取得
        	PurchaseDAO PurchaseDAO = new PurchaseDAO();
        	//PurchaseExp ticket = PurchaseDAO.getOneTkt(pur_id);
    		//int can =PurchaseDAO.updateNull(pur_id);
        	//List<Integer> cancelcomp = PurchaseDAO.Cancel(ticket.getMbr_id(), ticket.getSl_id(), ticket.getPur_price(), cancel_num_adlt_tkt, cancel_num_chld_tkt);

        	List<Integer> cancelcomp = PurchaseDAO.Cancel(pur_id, cancel_num_adlt_tkt, cancel_num_chld_tkt);



        	request.setAttribute("cancelcomp",cancelcomp);
            request.getRequestDispatcher("/membership/ticketcancelComplete.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    }
}

package facility;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SlotExp;
import dao.SlotDAO;

@WebServlet("/facility/partyInfo")
public class PartyInfo extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 現在のセッションを取得
        try {
        HttpSession session = request.getSession();
        int fac_id= (int) session.getAttribute("fac_id");
        LocalDate bus_date =  LocalDate.parse(request.getParameter("party_visit"));
       	SlotDAO slotDao = new SlotDAO();
        	List<SlotExp> slotdata = slotDao.getAllSlots(fac_id, bus_date);
        	request.setAttribute("slotdata", slotdata);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}


       request.getRequestDispatcher("/Settle5060/facility/partyInfo.jsp");
    }
}

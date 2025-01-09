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


@WebServlet("/facility/DateSelect")
public class DateSelect extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            //セッションからfac_idを取得
                int fac_id= (int) session.getAttribute("facilityId");
             // 選択された日付を取得
                LocalDate bus_date =  LocalDate.parse(request.getParameter("selectedDate"));

            // スロット情報を取得
                SlotDAO slotDao = new SlotDAO();
            	List<SlotExp> slotdata = slotDao.getAllSlots(fac_id, bus_date);
            	request.setAttribute("slotdata",slotdata);
            	request.setAttribute("selectdate",bus_date);
    			} catch (ParseException e) {
    				e.printStackTrace();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}

            request.getRequestDispatcher("organizationPurchaseInfo.jsp").forward(request, response);
    }
}

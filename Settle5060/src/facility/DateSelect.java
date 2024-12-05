package facility;

import java.io.IOException;
import java.sql.Date;
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
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            // 選択された日付を取得
            String selectedDateStr = request.getParameter("selectedDate");
            if (selectedDateStr == null || selectedDateStr.isEmpty()) {
                response.sendRedirect("error.jsp");
                return;
            }

            session.setAttribute("selectedDate", selectedDateStr);

            Date selectedDate = Date.valueOf(selectedDateStr);

            // スロット情報を取得
            SlotDAO slotDao = new SlotDAO();
            int facilityId = (int) session.getAttribute("facilityId");
            List<SlotExp> timeSlots = slotDao.getAllSlots(facilityId, selectedDate.toLocalDate());

            session.setAttribute("facilityId", facilityId);
            session.setAttribute("timeSlots", timeSlots);

            request.getRequestDispatcher("organizationPurchaseInfo.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
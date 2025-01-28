package membership;

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
import dao.FacilityDAO;
import dao.SlotDAO;

@WebServlet("/membership/EmailLinkRedirect")
public class EmailLinkRedirect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            // リンクからパラメータを取得
            String facilityIdParam = request.getParameter("facilityId");
            String selectedDateParam = request.getParameter("selectedDate");

            if (facilityIdParam == null || facilityIdParam.isEmpty() || selectedDateParam == null || selectedDateParam.isEmpty()) {
                response.sendRedirect("error.jsp?message=Invalid parameters");
                return;
            }

            int facilityId = Integer.parseInt(facilityIdParam);
            session.setAttribute("facilityId", facilityId);

            String facilityName = new FacilityDAO().getFacilityName(facilityId);
            if (facilityName == null) {
                response.sendRedirect("error.jsp?message=Facility not found");
                return;
            }
            session.setAttribute("facilityName", facilityName);

            session.setAttribute("selectedDate", selectedDateParam);

            // スロット情報を取得
            Date selectedDate = Date.valueOf(selectedDateParam);
            SlotDAO slotDao = new SlotDAO();
            List<SlotExp> timeSlots = slotDao.getAllSlots(facilityId, selectedDate.toLocalDate());
            session.setAttribute("timeSlots", timeSlots);

            // スロット選択ページへ転送
            response.sendRedirect("top.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=An unexpected error occurred");
        }
    }
}


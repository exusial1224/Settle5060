package facility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FacilityDAO;
import dao.IrregularClosureDAO;

@WebServlet("/facility/CloseDayDisplay")
public class CloseDayDisplay extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            int facilityId = (int) session.getAttribute("facilityId");

            // 定期休館日を取得
            FacilityDAO facilityDAO = new FacilityDAO();
            String regularHolidays = facilityDAO.getRgHol(facilityId);
            request.setAttribute("regularHolidays", regularHolidays);

            // 不定休館日を取得
            IrregularClosureDAO irregularClosureDAO = new IrregularClosureDAO();
            List<java.sql.Date> irregularClosures = irregularClosureDAO.getIrregularClosures(facilityId);
            request.setAttribute("irregularClosures", irregularClosures);

            // JSP にデータを渡して遷移
            request.getRequestDispatcher("closeDay.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=予期しないエラーが発生しました."
            		+ "");
        }
    }
}

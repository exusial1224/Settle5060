package facility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Facility;
import dao.FacilityDAO;

@WebServlet("/facility/FacilityInfoDisplay")
public class FacilityInfoDisplay extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            System.out.println(session.getAttribute("facilityId"));
            int facilityId = (int) session.getAttribute("facilityId");
            System.out.println(facilityId);
            FacilityDAO facilityDao= new FacilityDAO();
            Facility facilityinfo = facilityDao.getOneFacility(facilityId);
            request.setAttribute("facilityinfo", facilityinfo);
            request.getRequestDispatcher("/facility/facilityInfo.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=An unexpected error occurred");
        }
    }
}

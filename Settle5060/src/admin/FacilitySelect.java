package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FacilityDAO;

@WebServlet("/admin/FacilitySelectComp")
public class FacilitySelect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String facilityIdParam = request.getParameter("facilityId");
        if (facilityIdParam == null || facilityIdParam.isEmpty()) {
            response.sendRedirect("error.jsp?message=Invalid facility ID");
            return;
        }

        try {
            int facilityId = Integer.parseInt(facilityIdParam);
            FacilityDAO facilityDao = new FacilityDAO();

            String facilityName = facilityDao.getFacilityName(facilityId);
            if (facilityName == null) {
                response.sendRedirect("error.jsp?message=Facility not found");
                return;
            }

            HttpSession session = request.getSession();
            session.setAttribute("facilityId", facilityId);
            session.setAttribute("facilityName", facilityName);

            response.sendRedirect("top.jsp");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Facility ID must be a number");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=An unexpected error occurred");
        }
    }
}

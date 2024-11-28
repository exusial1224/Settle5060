package admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Facility;
import dao.FacilityDAO;

@WebServlet("/admin/FacilitySelect")
public class FacilitySelectDisplay extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            FacilityDAO facilityDao = new FacilityDAO();
            List<Facility> all_fac= facilityDao.getFacilityInfos();
            System.out.println(all_fac);
            request.setAttribute("all_fac", all_fac);
            request.getRequestDispatcher("/admin/facilitySelect.jsp").forward(request, response);;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Facility ID must be a number");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=An unexpected error occurred");
        }
    }
}

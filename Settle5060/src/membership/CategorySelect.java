package membership;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Facility;
import dao.FacilityDAO;

@WebServlet("/membership/CategorySelect")
public class CategorySelect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            int category = Integer.parseInt(request.getParameter("category"));

            FacilityDAO dao = new FacilityDAO();
            List<Facility> facilities = dao.getFacilityByCategory(category);

            request.setAttribute("facilityList", facilities);

            request.getRequestDispatcher("facilitySelect.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

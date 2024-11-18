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

@WebServlet("/membership/FacilitySelect")
public class FacilitySelect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int category = Integer.parseInt(request.getParameter("category"));

        try {
            FacilityDAO dao = new FacilityDAO();
            List<Facility> facilityList = dao.getFacilityInfos();

            if (!facilityList.isEmpty()) {
                Facility selectedFacility = facilityList.get(0);
                request.setAttribute("facilityName", selectedFacility.getFac_name());
                request.getRequestDispatcher("top.jsp").forward(request, response);
            } else {
                response.sendRedirect("noFacilityFound.jsp"); // 該当施設がない場合の遷移先
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

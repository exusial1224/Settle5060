package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FacilityDAO;

@WebServlet("/admin/ChangeFacilityInfoComplete")
public class ChangeFacilityInfoComplete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   HttpSession session = request.getSession();
        try {
        	int fac_id = (int) session.getAttribute("facilityId");
        	String co_name = request.getParameter("co_name");
        	String fac_name = request.getParameter("fac_name");
        	String fac_address = request.getParameter("fac_address");
        	String fac_tel = request.getParameter("fac_tel");
            FacilityDAO facilityDao = new FacilityDAO();
            int change_result = facilityDao.updateFacilityAd(fac_id,co_name,fac_name,fac_address,fac_tel);
            request.setAttribute("change_result", change_result);
            request.getRequestDispatcher("/admin/changeFacilityInfoComplete.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=An unexpected error occurred");
        }
    }
}

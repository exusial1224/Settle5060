package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FacilityDAO;

@WebServlet("/admin/ChangeFacilityMailComplete")
public class ChangeFacilityMailComplete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   HttpSession session = request.getSession();
        try {
        	int fac_id = (int) session.getAttribute("facilityId");
        	String new_mail = request.getParameter("new_mail");
            FacilityDAO facilityDao = new FacilityDAO();
            int change_result = facilityDao.updateMailAd(fac_id,new_mail);
            request.setAttribute("change_result", change_result);
            request.getRequestDispatcher("/admin/changeFacilityComplete.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=An unexpected error occurred");
        }
    }
}

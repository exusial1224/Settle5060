package facility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/facility/OrganizationPurchase")
public class OrganizationPurchase extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	HttpSession session = request.getSession();
    	int facilityId = (int) session.getAttribute("facilityId");
    	session.setAttribute("facilityId", facilityId);

        request.getRequestDispatcher("/facility/organizationPurchase.jsp").forward(request, response);
    }
}

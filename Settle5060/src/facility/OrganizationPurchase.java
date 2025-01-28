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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	HttpSession session = request.getSession();
    	int facilityId = (int) session.getAttribute("facilityId");
    	session.setAttribute("facilityId", facilityId);

    	String selectedSlotId = request.getParameter("selectedSlotId");
    	System.out.println(selectedSlotId);
        session.setAttribute("selectedSlotId", selectedSlotId);

        request.getRequestDispatcher("/facility/organizationPurchase.jsp").forward(request, response);
    }
}

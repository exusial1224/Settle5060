package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Facility;

@WebServlet("/admin/AddNewFacility")
public class AddFacility extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();

    	String co_name = request.getParameter("co_name");
    	String fac_name = request.getParameter("fac_name");
    	String fac_address = request.getParameter("fac_address");
    	String fac_tel = request.getParameter("fac_tel");
    	String fac_mail = request.getParameter("fac_mail");
    	String password = request.getParameter("password");

    	Facility fac = new Facility();
    	fac.setCo_name(co_name);
    	fac.setFac_name(fac_name);
    	fac.setFac_mail(fac_mail);
    	fac.setFac_tel(fac_tel);
    	fac.setFac_password(password);;
    	fac.setFac_address(fac_address);

    	session.setAttribute("fac_data", fac);

    	request.setAttribute("fac_data", fac);
        request.getRequestDispatcher("addFacility.jsp").forward(request, response);

    }
}

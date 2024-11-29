package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Facility;

@WebServlet("/admin/ChangeFacilityInfo")
public class ChangeFacilityInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   HttpSession session = request.getSession();


        	String co_name = request.getParameter("co_name");
        	String fac_name = request.getParameter("fac_name");
        	String fac_address = request.getParameter("fac_address");
        	String fac_tel = request.getParameter("fac_tel");

        	Facility fac_info = new Facility();
        	fac_info.setCo_name(co_name);
        	fac_info.setFac_name(fac_name);
        	fac_info.setFac_address(fac_address);
        	fac_info.setFac_tel(fac_tel);

        	request.setAttribute("fac_info", fac_info);
        	request.getRequestDispatcher("/admin/changeFacilityInfoConfirm.jsp").forward(request, response);

    }
}

package admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/AdminTop")
public class AdminTop extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String facilityName = (String) request.getSession().getAttribute("facilityName");
    	request.setAttribute("facilityName", facilityName);
    	request.getRequestDispatcher("/admon/adminTop.jsp").forward(request, response);
    }
}


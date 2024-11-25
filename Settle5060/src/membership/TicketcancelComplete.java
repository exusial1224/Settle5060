package membership;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/membership/TicketcancelComplete")
public class TicketcancelComplete extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");

    	int num_adlt_tkt = Integer.parseInt(request.getParameter("num_adlt_tkt"));
    	int num_chld_tkt = Integer.parseInt(request.getParameter("num_chld_tkt"));

    	System.out.print(num_adlt_tkt);

//        request.setAttribute("mail",mail);

//        request.getRequestDispatcher("/membership/addNewMemberConfirm.jsp").forward(request, response);
    }
}

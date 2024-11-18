package membership;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/membership/AddNewMemberConfirm")
public class AddNewMemberConfirm extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");

    	String mail = request.getParameter("mail");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        String birth = request.getParameter("birth");


        request.setAttribute("mail",mail);
        request.setAttribute("name",name);
        request.setAttribute("password",password);
        request.setAttribute("tel",tel);
        request.setAttribute("address",address);
        request.setAttribute("birth",birth);
        request.getRequestDispatcher("/membership/addNewMemberConfirm.jsp").forward(request, response);
    }
}

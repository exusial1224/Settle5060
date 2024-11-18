package membership;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/membership/InformationChangeDisplay")
public class InformationChangeDisplay extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        String birth = request.getParameter("birth");

        request.setAttribute("name", name);
        request.setAttribute("tel", tel);
        request.setAttribute("address", address);
        request.setAttribute("birth", birth);
        // JSPへフォワード
        request.getRequestDispatcher("/membership/informationChange.jsp").forward(request, response);
    }
}

package membership;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MembershipDAO;

@WebServlet("/membership/EmailChangeComplete")
public class EmailChangeComplete extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newMail = request.getParameter("newMail");
        int userId = Integer.parseInt(request.getParameter("userId"));

        try {
            // メールアドレスの更新処理
            MembershipDAO dao = new MembershipDAO();
            int result = dao.updateMail(userId, newMail);

            if (result > 0) {
                response.sendRedirect("success.jsp");
            } else {
                response.sendRedirect("error1.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error2.jsp");
        }
    }
}


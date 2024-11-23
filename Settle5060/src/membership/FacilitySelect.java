package membership;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/membership/FacilitySelect")
public class FacilitySelect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 施設IDを取得
            int facilityId = Integer.parseInt(request.getParameter("facilityId"));


            // セッションに施設IDを保存
            HttpSession session = request.getSession();
            session.setAttribute("facilityId", facilityId);


            // top.jspへリダイレクト
            response.sendRedirect("top.jsp");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

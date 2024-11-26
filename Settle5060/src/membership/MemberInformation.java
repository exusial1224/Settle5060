package membership;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Membership;
import dao.MembershipDAO;

@WebServlet("/membership/MemberInformation")
public class MemberInformation extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Membership> membershipIds = (List<Membership>) session.getAttribute("membershipIds");

        if (membershipIds == null || membershipIds.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        Membership membership = membershipIds.get(0); // ログイン中の会員を取得
        int userId = membership.getMbr_id();

        MembershipDAO membershipDAO = new MembershipDAO();
        try {
            Membership memberInfo = membershipDAO.getMemberInfo(userId);
            request.setAttribute("memberInfo", memberInfo);
            request.getRequestDispatcher("/membership/memberInformation.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}

package membership;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Membership;
import dao.MembershipDAO;

@WebServlet("/membership/InformationChange")
public class InformationChange extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // セッションから会員情報を取得
        HttpSession session = request.getSession();
        // セッションから会員情報リストを取得
        List<Membership> membershipIds = (List<Membership>) session.getAttribute("membershipIds");

        // セッションに会員情報が存在しない場合、エラーページへ
        if (membershipIds == null || membershipIds.isEmpty()) {
            response.sendRedirect("error1.jsp");
            return;
        }

        Membership membership = membershipIds.get(0);

        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        String birth = request.getParameter("birth");

        // 更新処理の続き
        membership.setMbr_name(name);
        membership.setMbr_tel(tel);
        membership.setMbr_address(address);


        // 生年月日（StringからDateに変換）
        if (birth != null && !birth.isEmpty()) {
            try {
                membership.setMbr_birth(Date.valueOf(birth).toLocalDate());  // yyyy-MM-dd形式で送信されると仮定
            } catch (IllegalArgumentException e) {
                request.setAttribute("errorMessage", "生年月日の形式が不正です。");
                request.getRequestDispatcher("/membership/informationChange.jsp").forward(request, response);
                return;
            }
        }

        // DAOを使って会員情報を更新
        MembershipDAO dao = new MembershipDAO();
        try {
            int result = dao.changeMemberInfo(membership.getMbr_id(), membership.getMbr_name(),
                                              membership.getMbr_tel(), membership.getMbr_address(),
                                              membership.getMbr_birth());

            if (result > 0) {
                // 更新成功後、セッションの会員情報を更新
                session.setAttribute("membership", membership);

                // 会員情報変更成功ページにフォワード
                request.getRequestDispatcher("/membership/informationChangeSuccess.jsp").forward(request, response);
            } else {
                // 更新失敗
                response.sendRedirect("/baseball/customer/error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/baseball/customer/error.jsp");
        }
    }
}

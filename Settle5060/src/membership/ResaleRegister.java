package membership;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Membership;
import dao.ResaleDAO;

@WebServlet("/membership/ResaleRegister")
public class ResaleRegister extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	HttpSession session = request.getSession();
            List<Membership> membershipIds = (List<Membership>) session.getAttribute("membershipIds");

            if (membershipIds == null || membershipIds.isEmpty()) {
                response.sendRedirect("../error/mbrLoginSessionError.jsp");//ログインセッションが無い場合の遷移
                return;
            }

            Membership membership = membershipIds.get(0); // ログイン中の会員を取得
            int memberId = membership.getMbr_id();

            int slotId = Integer.parseInt(request.getParameter("selectedSlotId"));

            // リセール登録
            ResaleDAO resaleDao = new ResaleDAO();
            resaleDao.AddNewResale(memberId, slotId);

            // 登録済みスロットIDをセッションに保存
            List<Integer> registeredSlots = (List<Integer>) session.getAttribute("registeredSlots");
            if (registeredSlots == null) {
                registeredSlots = new ArrayList<>();
            }
            registeredSlots.add(slotId);
            session.setAttribute("registeredSlots", registeredSlots);

            // リセール登録成功メッセージ
            session.setAttribute("message", "リセールが登録されました。");

            // 元の画面にリダイレクト
            response.sendRedirect("top.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

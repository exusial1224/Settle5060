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

            // ログインセッションの確認
            List<Membership> membershipIds = (List<Membership>) session.getAttribute("membershipIds");
            if (membershipIds == null || membershipIds.isEmpty()) {
                response.sendRedirect("../error/mbrLoginSessionError.jsp"); // ログインセッションが無い場合の遷移
                return;
            }

            // ログイン中の会員情報を取得
            Membership membership = membershipIds.get(0);
            int memberId = membership.getMbr_id();

            // 選択されたスロットIDの取得
            String[] selectedSlotIds = request.getParameterValues("selectedSlotId[]");

            if (selectedSlotIds == null || selectedSlotIds.length == 0) {
                session.setAttribute("error", "スロットが選択されていません。");
                response.sendRedirect("error1.jsp");
                return;
            }

            ResaleDAO resaleDao = new ResaleDAO();

            // リセール登録処理
            for (String slotIdStr : selectedSlotIds) {
                int slotId = Integer.parseInt(slotIdStr);
                resaleDao.AddNewResale(memberId, slotId);
            }

            // 登録済みスロットIDをセッションに保存
            List<Integer> registeredSlots = (List<Integer>) session.getAttribute("registeredSlots");
            if (registeredSlots == null) {
                registeredSlots = new ArrayList<>();
            }
            for (String slotIdStr : selectedSlotIds) {
                registeredSlots.add(Integer.parseInt(slotIdStr));
            }
            session.setAttribute("registeredSlots", registeredSlots);

            // リセール登録成功メッセージ。
            session.setAttribute("message", "リセールが登録されました。");

            // 元の画面にリダイレクト
            response.sendRedirect("top.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

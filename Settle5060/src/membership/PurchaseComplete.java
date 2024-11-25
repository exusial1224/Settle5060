package membership;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Membership;
import dao.PurchaseDAO;
import dao.SlotDAO;

@WebServlet("/membership/PurchaseComplete")
public class PurchaseComplete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<Membership> membershipIds = (List<Membership>) session.getAttribute("membershipIds");
        if (membershipIds == null || membershipIds.isEmpty()) {
            session.setAttribute("errorMessage", "セッションエラー: ログイン情報が見つかりません。");
            response.sendRedirect("/Settle5060/membership/error1.jsp");
            return;
        }

        Membership membership = membershipIds.get(0); // ログイン中の会員を取得
        int memberId = membership.getMbr_id();

        try {
            // セッションからスロットID取得
        	int slotId = (int) session.getAttribute("slotId");

            // 購入情報を取得
            int adultCount = Integer.parseInt(request.getParameter("adultCount"));
            int childCount = Integer.parseInt(request.getParameter("childCount"));
            int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));

            // 残り枚数の確認
            SlotDAO slotDAO = new SlotDAO();
            int remainingNum = slotDAO.getRemainingSlot(slotId);

            if (adultCount + childCount > remainingNum) {
                // 枚数オーバーの場合のエラーメッセージ設定
                request.setAttribute("errorMessage", "選択した枚数が残り枚数を超えています。購入処理を中断しました。");
                request.setAttribute("remainingNum", remainingNum);

                RequestDispatcher dispatcher = request.getRequestDispatcher("purchase.jsp");
                dispatcher.forward(request, response);
                return;
            }

            // 購入処理を実行
            PurchaseDAO purchaseDAO = new PurchaseDAO();
            boolean isSuccess = false;
            String message = "";

            try {
                // 購入処理の実行
                List<Integer> result = purchaseDAO.Purchase(memberId, slotId, totalPrice, adultCount, childCount);

                if (result.get(0) > 0) { // 最初の値が 1 以上なら購入成功
                    isSuccess = true;
                    message = "購入が正常に完了しました。";
                } else {
                    message = "購入処理に失敗しました。もう一度お試しください。";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                message = "購入処理中にエラーが発生しました: " + e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                message = "予期しないエラーが発生しました: " + e.getMessage();
            }

            request.setAttribute("isSuccess", isSuccess);
            request.setAttribute("message", message);

            RequestDispatcher dispatcher = request.getRequestDispatcher("purchaseComplete.jsp");
            dispatcher.forward(request, response);

        } catch (NullPointerException | NumberFormatException e) {
            // セッションに必要な情報が存在しない場合
            e.printStackTrace();
            session.setAttribute("errorMessage", "セッションエラー: 購入情報が正しくありません。");
            response.sendRedirect("/Settle5060/membership/error2.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "予期しないエラーが発生しました: " + e.getMessage());
            response.sendRedirect("/Settle5060/membership/error3.jsp");
        }
    }
}

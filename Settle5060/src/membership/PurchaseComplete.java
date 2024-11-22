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

@WebServlet("/membership/PurchaseComplete")
public class PurchaseComplete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        List<Membership> membershipIds = (List<Membership>) session.getAttribute("membershipIds");

        Membership membership = membershipIds.get(0); // ログイン中の会員を取得
        int memberId = membership.getMbr_id();
        try {
            //購入情報を取得
            int slotId = Integer.parseInt(session.getAttribute("slotId").toString());
            String adultCountStr = request.getParameter("adultCount");
            int adultCount = Integer.parseInt(adultCountStr);

            String childCountStr = request.getParameter("childCount");
            int childCount = Integer.parseInt(childCountStr);

            String totalPriceStr = request.getParameter("totalPrice");
            int totalPrice = Integer.parseInt(totalPriceStr);

            // DAOを利用して購入処理を実行
            PurchaseDAO purchaseDAO = new PurchaseDAO();
            boolean isSuccess = false; // 購入成功フラグ
            String message = ""; // 処理結果メッセージ

            try {
                // 購入処理の実行
                List<Integer> result = purchaseDAO.Purchase(memberId, slotId, totalPrice, adultCount, childCount);

                // チェック結果を確認
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

            // リクエストスコープにメッセージを設定
            request.setAttribute("isSuccess", isSuccess);
            request.setAttribute("message", message);

            // 結果画面にフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("purchaseComplete.jsp");
            dispatcher.forward(request, response);

        } catch (NullPointerException | NumberFormatException e) {
            // セッションに必要な情報が存在しない場合
            e.printStackTrace();
            session.setAttribute("errorMessage", "セッション情報が不完全です。もう一度最初からやり直してください。");
            response.sendRedirect("/Settle5060/membership/error.jsp");
        }
    }
}

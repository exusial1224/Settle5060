package membership;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PurchaseExp;
import dao.PurchaseDAO;
import utils.EmailUtility;

@WebServlet("/membership/TicketcancelComplete")
public class TicketcancelComplete extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            // キャンセルする枚数と購入IDを取得
            int cancel_num_adlt_tkt = Integer.parseInt(request.getParameter("cancel_adlt_tkt"));
            int cancel_num_chld_tkt = Integer.parseInt(request.getParameter("cancel_chld_tkt"));
            int pur_id = Integer.parseInt(request.getParameter("pur_id"));

            // DAO を使用してキャンセル処理を実行
            PurchaseDAO purchaseDAO = new PurchaseDAO();
            PurchaseExp purchase = purchaseDAO.getOneTkt(pur_id);
            List<Integer> cancelcomp = purchaseDAO.Cancel(pur_id, cancel_num_adlt_tkt, cancel_num_chld_tkt);

            // リセールデータが存在するかをチェック
            boolean resaleDataExists = purchaseDAO.checkResaleData(pur_id); // リセールデータ存在確認メソッド

            if (resaleDataExists) {
                // 該当リセールデータの会員メールアドレスを取得
                String resaleMemberEmail = purchaseDAO.getResaleMemberEmail(pur_id); // メールアドレス取得メソッド

                if (resaleMemberEmail != null && !resaleMemberEmail.isEmpty()) {

                	String baseUrl = "http://" + request.getServerName() + ":" + request.getServerPort() +
                	        request.getContextPath() + "/membership/EmailLinkRedirect";
                	String encodedFacilityId = URLEncoder.encode(String.valueOf(purchase.getFac_id()), "UTF-8");
                	String encodedSelectedDate = URLEncoder.encode(purchase.getBus_date().toString(), "UTF-8");

                	String slotSelectionLink = baseUrl + "?facilityId=" + encodedFacilityId + "&selectedDate=" + encodedSelectedDate;

                	String subject = "【SETTLE】リセール入場券に関するお知らせ";
                	String content = "以下のリンクをクリックして、キャンセルされた入場券を購入できます。\n\n" +
                	                 slotSelectionLink + "\n\n" +
                	                 "SETTLE運営";

                	EmailUtility.sendEmail(resaleMemberEmail, subject, content);

                    // メール送信
                    try {
                        EmailUtility.sendEmail(resaleMemberEmail, subject, content);
                        System.out.println("メールを送信しました：" + resaleMemberEmail);
                    } catch (Exception e) {
                        System.err.println("メール送信中にエラーが発生しました：" + e.getMessage());
                    }
                }
            }

            // キャンセル完了画面へ転送
            request.setAttribute("cancelcomp", cancelcomp);
            request.getRequestDispatcher("/membership/ticketcancelComplete.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "チケットキャンセル処理中にエラーが発生しました");
            request.getRequestDispatcher("/membership/error.jsp").forward(request, response);
        }
    }
}
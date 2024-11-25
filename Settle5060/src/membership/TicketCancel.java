package membership;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Membership;
import bean.PurchaseExp;
import dao.PurchaseDAO;



@WebServlet("/membership/Ticketcancel")
public class TicketCancel extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // セッションから membershipIds リストを取得
            List<Membership> membershipIds = (List<Membership>) request.getSession().getAttribute("membershipIds");


            if (membershipIds == null || membershipIds.isEmpty()) {
                // リストが存在しない、もしくは空の場合エラーページにリダイレクト
                request.setAttribute("error", "ユーザーIDが設定されていません。ログインしてください。");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }
            //URLからpur_idの値を取得
            int pur_id = Integer.parseInt(request.getParameter("pur_id"));


            // DAO から入場券情報を取得
            PurchaseDAO PurchaseDAO = new PurchaseDAO();
            PurchaseExp ticket = PurchaseDAO.getOneTkt(pur_id);
            // リクエストに入場券情報をセットして JSP に転送
            request.setAttribute("ticket", ticket);
            request.getRequestDispatcher("ticketcancel.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "リセール一覧の取得中にエラーが発生しました");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
	}
}

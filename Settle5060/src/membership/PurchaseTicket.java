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

@WebServlet("/membership/PurchaseTicket")
public class PurchaseTicket extends HttpServlet {
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

           String fac_name= (String) request.getParameter("fac_name");
           String time_pur= (String) request.getParameter("time_pur");
           String start_time= (String) request.getParameter("fac_name");
           String end_time= (String) request.getParameter("fac_name");


           PurchaseExp purchaseList = new PurchaseExp();
           purchaseList.setFac_name(fac_name);
            // リクエストにリセール一覧をセットして JSP に転送
            request.setAttribute("purchaseList", purchaseList);
            request.getRequestDispatcher("purchaseHistorylist.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "リセール一覧の取得中にエラーが発生しました");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
	}
}

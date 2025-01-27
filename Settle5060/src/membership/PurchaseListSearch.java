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

@WebServlet("/membership/PurchaseListSearch")
public class PurchaseListSearch extends HttpServlet {
	@SuppressWarnings("null")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // セッションから membershipIds リストを取得
            List<Membership> membershipIds = (List<Membership>) request.getSession().getAttribute("membershipIds");
            System.out.println(membershipIds);
            if (membershipIds == null || membershipIds.isEmpty()) {
                // リストが存在しない、もしくは空の場合エラーページにリダイレクト
                request.setAttribute("error", "ユーザーIDが設定されていません。ログインしてください。");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            // リストから最初の Membership オブジェクトを取得し、その mbr_id を使用
            int mbr_id = membershipIds.get(0).getMbr_id();

            // 検索タイプと検索ワードを取得
            String search_type = request.getParameter("search_type");
            String keyword = request.getParameter("keyword");

            // DAO から入場券一覧を取得

            PurchaseDAO PurchaseDAO = new PurchaseDAO();
            List<PurchaseExp> purchaseList = PurchaseDAO.getPurchaseSearchByFacility(mbr_id,keyword);
            System.out.println(purchaseList);

            request.setAttribute("purchaseList", purchaseList);
            request.getRequestDispatcher("purchaseHistorylist.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "リセール一覧の取得中にエラーが発生しました");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
	}
}

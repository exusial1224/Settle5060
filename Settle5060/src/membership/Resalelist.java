package membership;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Membership;
import bean.ResaleExp;
import dao.ResaleDAO;

@WebServlet("/membership/Resalelist")
public class Resalelist extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // セッションから membershipIds リストを取得
            List<Membership> membershipIds = (List<Membership>) request.getSession().getAttribute("membershipIds");

            if (membershipIds == null || membershipIds.isEmpty()) {
                // リストが存在しない、もしくは空の場合エラーページにリダイレクト
                request.setAttribute("error", "ユーザーIDが設定されていません。ログインしてください。");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }

            // リストから最初の Membership オブジェクトを取得し、その mbr_id を使用
            int mbr_id = membershipIds.get(0).getMbr_id();

            // DAO からリセール一覧を取得
            ResaleDAO resaleDAO = new ResaleDAO();
            List<ResaleExp> resaleList = resaleDAO.getAllResales(mbr_id);

            // リクエストにリセール一覧をセットして JSP に転送
            request.setAttribute("resaleList", resaleList);
            request.getRequestDispatcher("resalelist.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "リセール一覧の取得中にエラーが発生しました");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

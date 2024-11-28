package membership;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ResaleDAO;

@WebServlet("/membership/ResaleCancel")
public class ResaleCancel extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // リクエストからリセールIDを取得
            int rsle_id = Integer.parseInt(request.getParameter("rsle_id"));

            // キャンセル処理を実行
            ResaleDAO resaleDAO = new ResaleDAO();
            int result = resaleDAO.cancelResale(rsle_id);

            if (result > 0) {
                // キャンセル成功時のメッセージを設定し、トップ画面にリダイレクト
                request.getSession().setAttribute("message", "キャンセルしました。");
                response.sendRedirect("top.jsp");
            } else {
                // キャンセル失敗時のエラーメッセージを設定
                request.setAttribute("error", "キャンセルに失敗しました。再試行してください。");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "処理中にエラーが発生しました。");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

package facility;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.IrregularClosureDAO;

@WebServlet("/facility/CloseDayUpdate")
public class CloseDayUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // セッションから施設IDを取得
            HttpSession session = request.getSession();
            int facilityId = (int) session.getAttribute("facilityId");

            // フォームから日付を取得
            String selectedDateStr = request.getParameter("selectedDate");
            LocalDate selectedDate = LocalDate.parse(selectedDateStr);

            // DAOを使用してデータベースに登録
            IrregularClosureDAO irregularClosureDAO = new IrregularClosureDAO();

            // 既に同じ日付が登録されているか確認
            if (irregularClosureDAO.checkSameClosure(facilityId, selectedDate) != null) {
                // 日付が既に登録済みの場合、エラーメッセージを設定
                request.setAttribute("errorMessage", "選択された日付は既に不定休館日に登録されています。");
            } else {
                // 不定休館日を登録
                irregularClosureDAO.insertIrregularClosure(facilityId, selectedDate);
                request.setAttribute("successMessage", "不定休館日を登録しました。");
            }

            // CloseDayDisplayにリダイレクト
            request.getRequestDispatcher("CloseDayDisplay").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=予期しないエラーが発生しました");
        }
    }
}

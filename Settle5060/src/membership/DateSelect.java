package membership;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Slot;
import dao.FacilityDAO;
import dao.SlotDAO;


@WebServlet("/membership/DateSelect")
public class DateSelect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // リクエストから選択された日付を取得
            String selectedDateStr = request.getParameter("selectedDate");
            if (selectedDateStr == null || selectedDateStr.isEmpty()) {
                // 日付が未選択の場合のエラー処理
                response.sendRedirect("error.jsp");
                return;
            }

            // 日付を `Date` 型に変換
            Date selectedDate = Date.valueOf(selectedDateStr);

            // セッションから選択された施設IDを取得
            HttpSession session = request.getSession();
            List<Integer> selectedCategories = (List<Integer>) session.getAttribute("selectedCategories");
            if (selectedCategories == null || selectedCategories.isEmpty()) {
                // 施設カテゴリが未選択の場合、エラー画面にリダイレクト
                response.sendRedirect("error.jsp");
                return;
            }

            // 最後に選択されたカテゴリを取得（複数選択される可能性がある場合への対応）
            int facId = selectedCategories.get(selectedCategories.size() - 1);

            FacilityDAO dao = new FacilityDAO(); // FacilityDAOを使用
            String facilityName = dao.getFacilityName(facId);

            // DAOを使用してスロット情報を取得
            SlotDAO dao2 = new SlotDAO();
            List<Slot> timeSlots = dao2.getAllSlots(facId, selectedDate);

            // セッションに選択された日付とスロット情報を保存
            session.setAttribute("selectedDate", selectedDate);
            session.setAttribute("timeSlots", timeSlots);
            session.setAttribute("facilityName", facilityName);

            // JSPにフォワードして結果を表示
            request.getRequestDispatcher("top.jsp").forward(request, response);

        } catch (IllegalArgumentException e) {
            // 日付の形式が不正だった場合のエラー処理
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } catch (Exception e) {
            // その他のエラー処理
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }

}

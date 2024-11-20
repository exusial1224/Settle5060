package membership;

import java.io.IOException;
import java.sql.Time;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SlotDAO;

@WebServlet("/membership/SlotSelect")
public class SlotSelect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // リクエストから選択されたスロットIDを取得
            String selectedSlotIdStr = request.getParameter("selectedSlotId");
            if (selectedSlotIdStr == null || selectedSlotIdStr.isEmpty()) {
                response.sendRedirect("error.jsp");
                return;
            }
            int selectedSlotId = Integer.parseInt(selectedSlotIdStr);

            // DAOを使用して開始時刻と終了時刻を取得
            SlotDAO dao = new SlotDAO();
            List<Time> times = dao.getTimes(selectedSlotId);
            if (times == null || times.size() < 2) {
                response.sendRedirect("error.jsp");
                return;
            }
            Time startTime = times.get(0);
            Time endTime = times.get(1);

            // セッションにスロット情報を保存
            HttpSession session = request.getSession();
            session.setAttribute("selectedSlotId", selectedSlotId);
            session.setAttribute("startTime", startTime);
            session.setAttribute("endTime", endTime);

            // 確認画面にフォワード
            request.getRequestDispatcher("slotConfirmation.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

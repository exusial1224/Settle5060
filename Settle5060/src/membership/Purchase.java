package membership;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/membership/Purchase")
public class Purchase extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // セッションからデータを取得
        String facilityName = (String) request.getSession().getAttribute("facilityName");
        Time startTime = (Time) request.getSession().getAttribute("startTime");
        Time endTime = (Time) request.getSession().getAttribute("endTime");

        // 時間をフォーマットして文字列に変換
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String formattedStartTime = startTime != null ? timeFormat.format(startTime) : "不明";
        String formattedEndTime = endTime != null ? timeFormat.format(endTime) : "不明";

        int adultPrice = Integer.parseInt(request.getSession().getAttribute("adultPrice").toString());
        int childPrice = Integer.parseInt(request.getSession().getAttribute("childPrice").toString());

        int adultCount = Integer.parseInt(request.getParameter("adultCount"));
        int childCount = Integer.parseInt(request.getParameter("childCount"));
        int remainingNum = Integer.parseInt(request.getSession().getAttribute("remainingNum").toString());

        //子供大人の合計金額
        int adultTotalPrice = (adultPrice * adultCount);
        int childTotalPrice = (childPrice * adultCount);
        // 合計金額を計算
        int totalPrice = (adultPrice * adultCount) + (childPrice * childCount);

        request.setAttribute("facilityName", facilityName);
        request.setAttribute("startTime", formattedStartTime);
        request.setAttribute("endTime", formattedEndTime);
        request.setAttribute("adultCount", adultCount);
        request.setAttribute("childCount", childCount);
        request.setAttribute("adultPrice", adultPrice);
        request.setAttribute("childPrice", childPrice);
        request.setAttribute("adultTotalPrice", adultTotalPrice);
        request.setAttribute("childTotalPrice", childTotalPrice);
        request.setAttribute("totalPrice", totalPrice);

        // 確認画面にフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("purchaseConfirm.jsp");
        dispatcher.forward(request, response);
    }
}

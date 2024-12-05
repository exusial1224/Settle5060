package facility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/facility/OrganizationPurchaseConfirm")
public class OrganizationPurchaseConfirm extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // フォームからデータを取得
            String groupName = request.getParameter("groupName");
            String representativeName = request.getParameter("representativeName");
            String phoneNumber = request.getParameter("phoneNumber");
            String adultsCount = request.getParameter("adultsCount");
            String childrenCount = request.getParameter("childrenCount");

            // リクエスト属性にデータを設定
            request.setAttribute("groupName", groupName);
            request.setAttribute("representativeName", representativeName);
            request.setAttribute("phoneNumber", phoneNumber);
            request.setAttribute("adultsCount", adultsCount);
            request.setAttribute("childrenCount", childrenCount);

            request.getRequestDispatcher("/facility/organizationPurchaseConfirm.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/membership/error.jsp");
        }
    }
}

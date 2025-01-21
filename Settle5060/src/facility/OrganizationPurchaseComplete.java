package facility;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PurchaseDAO;

@WebServlet("/facility/OrganizationPurchaseComplete")
public class OrganizationPurchaseComplete extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	 HttpSession session = request.getSession();
             String slotId_str = (String)session.getAttribute("selectedSlotId");
             int slotId = Integer.parseInt(slotId_str);
        	// フォームからデータを取得
            String groupName = (String)session.getAttribute("groupName");
            String representativeName = (String)session.getAttribute("representativeName");
            String phoneNumber = (String)session.getAttribute("phoneNumber");
            int adultsCount = Integer.parseInt((String)session.getAttribute("adultsCount"));
            int childrenCount = Integer.parseInt((String)session.getAttribute("childrenCount"));
            System.out.println(String.valueOf(slotId)+","+groupName+","+representativeName+","+phoneNumber+","+String.valueOf(adultsCount)+","+String.valueOf(childrenCount));
            PurchaseDAO purchaseDao = new PurchaseDAO();
            List<Integer> result_list = purchaseDao.OrganizationPurchase(slotId,groupName,representativeName,adultsCount,childrenCount,phoneNumber);
            int result = result_list.get(0);
            String message ="登録に失敗しました。もう一度お試しください。";
            if (result > 0) { // 最初の値が 1 以上なら購入成功
                message = "団体登録が正常に完了しました。";
            }


            request.setAttribute("message", message);
            request.getRequestDispatcher("/facility/organizationPurchaseComplete.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/membership/error.jsp");
        }
    }
}

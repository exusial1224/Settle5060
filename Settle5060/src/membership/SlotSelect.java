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

import dao.FacilityDAO;
import dao.SlotDAO;

@WebServlet("/membership/SlotSelect")
public class SlotSelect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            // スロットIDを取得
            String selectedSlotIdStr = request.getParameter("selectedSlotId");
            if (selectedSlotIdStr == null || selectedSlotIdStr.isEmpty()) {
                response.sendRedirect("error.jsp");
                return;
            }
            int slotId = Integer.parseInt(selectedSlotIdStr);

            // セッションから施設IDと施設名を取得
            int facilityId = (int) session.getAttribute("facilityId");
            String facilityName = (String) session.getAttribute("facilityName");

            // スロット情報を取得
            SlotDAO slotDao = new SlotDAO();
            List<Time> times = slotDao.getTimes(slotId);
            if (times == null || times.size() < 2) {
                response.sendRedirect("error.jsp");
                return;
            }
            Time startTime = times.get(0);
            Time endTime = times.get(1);

            // 大人料金を取得
            int adultPrice = slotDao.getSlotPrice(slotId);

            // 子供割引率を取得
            FacilityDAO facilityDao = new FacilityDAO();
            int childDiscount = facilityDao.getChldDsc(facilityId);

            // 子供料金を計算
            int childPrice = (int) (adultPrice * ((100 - childDiscount) * 0.01));

            // スロットの残り人数を計算
            SlotDAO sd = new SlotDAO();
            //PurchaseDAO purchaseDao = new PurchaseDAO();
            //int purchasedRsvCount = purchaseDao.purchasedOneSlotCountRsv(slotId);
            //int purchasedGrCount = purchaseDao.purchasedOneSlotCountGr(slotId);
            //int totalPurchasedCount = purchasedRsvCount + purchasedGrCount;

            //int maxNum = facilityDao.getMaxNum(facilityId);
            int remainingNum = sd.getRemainingSlot(slotId);//maxNum - totalPurchasedCount;

            if (remainingNum < 0) remainingNum = 0;

            session.setAttribute("slotId", slotId);
            session.setAttribute("startTime", startTime);
            session.setAttribute("endTime", endTime);
            session.setAttribute("remainingNum", remainingNum);
            session.setAttribute("adultPrice", adultPrice);
            session.setAttribute("childPrice", childPrice);

            request.getRequestDispatcher("purchase.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

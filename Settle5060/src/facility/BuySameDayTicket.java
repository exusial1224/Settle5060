package facility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SlotDAO;

@WebServlet("/facility/BuySameDayTicket")
public class BuySameDayTicket extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //セッション取得
    	HttpSession session = request.getSession();
    	//セッション上のメッセージを破棄
    	session.removeAttribute("message");

    	//リクエストパラメータ―取得
    	int adultNum=0;
    	String adultNumStr = request.getParameter("adultNum");
    	int childNum=0;
    	String childNumStr = request.getParameter("childNum");
    	if(adultNumStr != null && childNumStr != null){
    		adultNum = Integer.parseInt(adultNumStr);
    		childNum = Integer.parseInt(childNumStr);
    	}else{
    		//エラー処理
    		response.sendRedirect("./facilityError.jsp");
    		return;
    	}

    	//DAOの取得
    	SlotDAO slotDao = new SlotDAO();

    	//枚数チェック
    	try {
			int remain = slotDao.getRemainingSlot(Integer.parseInt(session.getAttribute("fac_id").toString()));
			if(remain < adultNum+childNum){
				//購入したい枚数が残り枚数より多い場合、購入不可な件をメッセージで表示する
				session.setAttribute("message", "購入枚数は残り枠以下にしてください。");
				//EntryDisplayへ
				response.sendRedirect("EntryDisplay");
				return;
			}
		} catch (Exception e) {
			//エラー処理
			request.getRequestDispatcher("./facilityError.jsp").forward(request, response);
			return;
		}

    	//団体者の入場処理
		try {
			slotDao.SamedayPurchase(Integer.parseInt(session.getAttribute("fac_id").toString()),adultNum,childNum);
			//メッセージで表示する
			session.setAttribute("message", "当日券の購入処理が正常に終了しました。");
	    	//EntryDisplayへ
			response.sendRedirect("EntryDisplay");
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			//エラー処理
			request.getRequestDispatcher("./facilityError.jsp").forward(request, response);
		}

    }
}


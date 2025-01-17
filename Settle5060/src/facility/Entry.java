package facility;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.PurchaseExp;
import dao.PurchaseDAO;

@WebServlet("/facility/Entry")
public class Entry extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //セッション取得
    	HttpSession session = request.getSession();
    	//セッション上のメッセージを破棄
    	session.removeAttribute("message");
    	//セッション上のチケット情報を破棄
    	session.removeAttribute("ticket");

    	//リクエストパラメータ―取得
    	int pur_id=0;
    	String pur_idStr = request.getParameter("pur_id");
    	if(pur_idStr != null && !pur_idStr.equals("")){
    		pur_id = Integer.parseInt(pur_idStr);
    	}else{
    		//代替フロー⑥QRからデータが読み取れなかった場合、メッセージで表示する
    		session.setAttribute("message", "このQRコードは使用できません。");
        	//EntryDisplayへ
    		response.sendRedirect("EntryDisplay");
    		return;
    	}

    	//DAOの取得
    	PurchaseDAO purDao = new PurchaseDAO();
    	PurchaseExp purExp;

    	//QRからIDが読めた場合のチェックと入場処理
    	try {
    		//購入情報取得
    		purExp = purDao.getOneTkt(pur_id);

    		//QRコードのチケット存在チェック
    		if(purExp.getStart_time() == null){
    			//代替フロー⑥読み取ったQRコードのチケットが存在しない場合、メッセージで表示する
        		session.setAttribute("message", "このQRコードは使用できません。");
            	//EntryDisplayへ
        		response.sendRedirect("EntryDisplay");
        		return;
    		}


    		//今の日付と時刻
    		LocalDateTime now = LocalDateTime.now();

       		//タイムスロットチェック
    		if(!now.toLocalDate().equals(purExp.getBus_date()) || !(now.toLocalTime().isAfter(purExp.getStart_time()) && now.toLocalTime().isBefore(purExp.getEnd_time()))){
    			//代替フロー④読み取った入場券が現在のタイムスロットと一致しない場合、メッセージで表示する
        		session.setAttribute("message", "入場日時が違うチケットです。");
            	//EntryDisplayへ
        		response.sendRedirect("EntryDisplay");
        		return;
    		}

    		//入場済みチェック
    		if(purExp.isRsv_admitted()){
    			//代替フロー⑤読み取った入場券の入場フラグが入場済みだった場合、メッセージで表示する
        		session.setAttribute("message", "この入場券は使用済みです。");
            	//EntryDisplayへ
        		response.sendRedirect("EntryDisplay");
        		return;
    		}

    		//入場処理
			purDao.updateRsvAdmitted(pur_id);

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			//エラー処理
			request.getRequestDispatcher("./facilityError.jsp").forward(request, response);
			return;
		}

		//メッセージで表示する
		session.setAttribute("message", "入場処理が正常に終了しました。");
		//チケットの情報を送る
		session.setAttribute("ticket", purExp );
		//EntryDisplayへ
		request.getRequestDispatcher("EntryDisplay").forward(request, response);
    }
}


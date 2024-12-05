package facility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrganizationPurchaseDAO;

@WebServlet("/facility/OrgEntry")
public class OrgEntry extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //セッション取得
    	HttpSession session = request.getSession();
    	//セッション上のメッセージを破棄
    	session.removeAttribute("message");

    	//リクエストパラメータ―取得
    	int org_pur_id=0;
    	String org_pur_idStr = request.getParameter("org_pur_id");
    	if(org_pur_idStr != null){
    		org_pur_id = Integer.parseInt(org_pur_idStr);
    	}else{
    		//エラー処理
    		response.sendRedirect("./facilityError.jsp");
    		return;
    	}

    	//DAOの取得
    	OrganizationPurchaseDAO opDao = new OrganizationPurchaseDAO();

    	//団体者の入場処理
		try {
			//opDao.updateGrTktAdmitted(org_pur_id);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			//エラー処理
			request.getRequestDispatcher("./facilityError.jsp").forward(request, response);
			return;
		}
		//メッセージで表示する
		session.setAttribute("message", "当日券の購入処理が正常に終了しました。");
    	//EntryDisplayへ
		response.sendRedirect("EntryDisplay");
    }
}


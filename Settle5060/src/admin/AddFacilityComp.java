package admin;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Facility;
import dao.FacilityDAO;

@WebServlet("/admin/AddNewFacilityComp")
public class AddFacilityComp extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();

    	try {
    		//Sessionからフォームに入力したデータを取得
    		Facility fac = (Facility) session.getAttribute("fac_data");

    		//登録の処理
    		FacilityDAO FacilityDao = new FacilityDAO();
    		//result->DAOからの結果(0=>失敗：1=>成功)　result_txt->adminResult.jspに送る値
			int result = FacilityDao.AddNewFacilityAd(fac.getCo_name(), fac.getFac_name(), hashPassword(fac.getFac_password()), fac.getFac_mail(), fac.getFac_address(), fac.getFac_tel());
			String result_txt = "追加処理に失敗しました。もう一度お試しください";
			if(result != 0){
				result_txt = "追加処理に成功しました。";
			}
			request.setAttribute("result", result_txt);
	        request.getRequestDispatcher("adminResult.jsp").forward(request, response);
    	} catch (Exception e) {
			e.printStackTrace();
		}



    }
    // パスワードのハッシュ化メソッド
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
        for (int i = 0; i < encodedhash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedhash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

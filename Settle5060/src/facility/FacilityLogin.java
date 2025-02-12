package facility;

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

import dao.FacilityDAO;

@WebServlet("/facility/facilityLogin")
public class FacilityLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");


        String admin_mail= "settle5060@gmail.com";
        String admin_pass= "020e5e47501be103bb0367269fb0fd1845a2f763c041cc26601849359479483d";
        FacilityDAO facilityDAO = new FacilityDAO();

        try {
            String hashedPassword = hashPassword(password);
            int facilityId = 0;
            facilityId = facilityDAO.loginFacility(mail, hashedPassword);

            HttpSession session = request.getSession();
            if (facilityId != 0) {
                // 施設IDをセッションに保存
                session.setAttribute("facilityId",facilityId );
                response.sendRedirect("/Settle5060/facility/facilityTop.jsp");
            } else {
            	if(mail.equals(admin_mail) && hashedPassword.equals(admin_pass)){
            		session.setAttribute("facilityId",facilityId);
            		response.sendRedirect("/Settle5060/admin/adminTop.jsp");
            	}else{
                // ログイン失敗時の処理せ
                request.setAttribute("loginError", "無効なメールアドレスまたはパスワードです。");
                request.getRequestDispatcher("/facility/login.jsp").forward(request, response);
            	}
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
        for (byte b : encodedhash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
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

       FacilityDAO facilityDAO = new FacilityDAO();

        try {
            String hashedPassword = hashPassword(password);
            int fac_id = 0;
            fac_id = facilityDAO.loginFacility(mail, hashedPassword);

            HttpSession session = request.getSession();
            if (fac_id != 0) {
                // 施設IDをセッションに保存
                session.setAttribute("fac_id",fac_id );
                response.sendRedirect("/Settle5060/facility/facilityTop.jsp");
            } else {
                // ログイン失敗時の処理
                request.setAttribute("loginError", "無効なメールアドレスまたはパスワードです。");
                request.getRequestDispatcher("/settle/membership/loginError.jsp").forward(request, response);
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
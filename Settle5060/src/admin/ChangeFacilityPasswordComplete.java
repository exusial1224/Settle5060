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

import dao.FacilityDAO;

@WebServlet("/admin/ChangeFacilityPasswordComplete")
public class ChangeFacilityPasswordComplete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   HttpSession session = request.getSession();
        try {
        	int fac_id = (int) session.getAttribute("facilityId");
        	String new_password = request.getParameter("new_password");
        	String hashed_newPassword = hashPassword(new_password);

            FacilityDAO facilityDao = new FacilityDAO();
            int change_result = facilityDao.updatePasswordAd(fac_id,hashed_newPassword);
            request.setAttribute("change_result", change_result);
            request.getRequestDispatcher("/admin/changeFacilityComplete.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=An unexpected error occurred");
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

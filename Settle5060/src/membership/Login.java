package membership;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Membership;
import dao.MembershipDAO;

@WebServlet("/membership/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        MembershipDAO membershipDAO = new MembershipDAO();

        try {
            String hashedPassword = hashPassword(password);
            Membership membership = membershipDAO.loginMember(mail, hashedPassword);

            HttpSession session = request.getSession();
            if (membership != null) {
                // ログイン成功時の処理
                List<Membership> membershipIds = (List<Membership>) session.getAttribute("membershipIds");
                if (membershipIds == null) {
                    membershipIds = new ArrayList<>();
                }

                // 会員IDをセッションに保存
                membershipIds.add(membership);
                session.setAttribute("membershipIds", membershipIds);
                response.sendRedirect("/Settle4048/membership/facilitySelect.jsp");
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

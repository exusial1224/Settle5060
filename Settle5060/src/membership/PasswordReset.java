package membership;

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

import dao.MembershipDAO;

@WebServlet("/membership/PasswordReset")
public class PasswordReset extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String newMail = request.getParameter("newMail");
        String newPassword = request.getParameter("newPassword");

        try {
            MembershipDAO dao = new MembershipDAO();

            //ハッシュ化
            String hashedNewPassword = hashPassword(newPassword);



            // 新パスワードが正しいか確認はクライアントnanodefuyou
//            if (hashedNewPassword != hashedConfirmPassword) {
//               request.setAttribute("error", "パスワードと確認用パスワードが一致しません。");
//                request.getRequestDispatcher("/passwordResetForm.jsp").forward(request, response);
//                return;
//            }


            //
            int check = dao.reconfigurePassword(newMail, hashedNewPassword);
            System.out.println(hashedNewPassword);
            System.out.println(newMail);

            if (check > 0) {

                // 現在のセッションを取得
                HttpSession session = request.getSession(false);

                //セッションが存在する場合は無効化
                if (session != null) {
                    session.invalidate();
                }
                request.setAttribute("message", "パスワードの再設定が完了しました。");
                request.getRequestDispatcher("./throwLogin.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "エラーが発生しました。");
                request.setAttribute("newMail", "newMail");
                request.getRequestDispatcher("./passwordResetForm.jsp").forward(request, response);
            }


        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "システムエラーが発生しました。");
            request.getRequestDispatcher("error4.jsp").forward(request, response);
        }
    }

    //ハッシュ化メソ
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

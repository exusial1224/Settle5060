package membership;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Membership;
import dao.MembershipDAO;

@WebServlet("/membership/PasswordChange")
public class PasswordChange extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String oldPassword = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        HttpSession session = request.getSession();

        // セッションから membershipIds リストを取得
        List<Membership> membershipIds = (List<Membership>) session.getAttribute("membershipIds");

        // 会員IDがない場合、エラーページへリダイレクト
        if (membershipIds == null || membershipIds.isEmpty()) {
            response.sendRedirect("error1.jsp");
            return;
        }

        // membershipIds の最初の要素から mbr_id を取得
        Integer mbr_id = membershipIds.get(0).getMbr_id();

        try {
            MembershipDAO dao = new MembershipDAO();

            // 旧パスワードのハッシュ化
            String hashedOldPassword = hashPassword(oldPassword);

            // 旧パスワードが正しいか確認
            if (!dao.checkPassword(mbr_id, hashedOldPassword)) {
                request.setAttribute("error", "旧パスワードが正しくありません。");
                request.getRequestDispatcher("error2.jsp").forward(request, response);
                return;
            }

            // 新しいパスワードの確認が一致しない場合エラー
            if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("error", "新しいパスワードが一致しません。");
                request.getRequestDispatcher("error3.jsp").forward(request, response);
                return;
            }

            // 新しいパスワードのハッシュ化
            String hashedNewPassword = hashPassword(newPassword);

            // 新しいパスワードを更新
            int result = dao.updatePassword(mbr_id, hashedNewPassword);

            if (result > 0) {
                request.setAttribute("message", "パスワードが正常に変更されました。");
            } else {
                request.setAttribute("error", "パスワードの変更に失敗しました。");
            }

            // 結果ページに転送
            request.getRequestDispatcher("passwordChangeResult.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "システムエラーが発生しました。");
            request.getRequestDispatcher("error4.jsp").forward(request, response);
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

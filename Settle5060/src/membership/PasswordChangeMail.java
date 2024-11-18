package membership;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MembershipDAO;
import utils.EmailUtility;

@WebServlet("/membership/PasswordChangeMail")
public class PasswordChangeMail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newMail = request.getParameter("newMail");
        MembershipDAO dao = new MembershipDAO();

        try {
            // メールアドレスが登録されているか確認
            String existingMail = dao.searchSameMail(newMail);

            if (existingMail != null && !existingMail.isEmpty()) {
                // メール認証用のリンクを生成
                String encodedMail = URLEncoder.encode(newMail, "UTF-8");
                String verificationLink = "http://" + request.getServerName() + ":" + request.getServerPort() +
                        request.getContextPath() + "/membership/PasswordResetDisplay?newMail=" + encodedMail;

                // メール内容を設定
                String subject = "パスワード変更の確認";
                String content = "以下のリンクをクリックしてパスワードの変更を完了してください:\n" + verificationLink;

                // メール送信
                EmailUtility.sendEmail(newMail, subject, content);

                // 確認ページへリダイレクト
                request.getRequestDispatcher("/membership/passwordChangeConfirm.jsp").forward(request, response);
            } else {
                // メールが登録されていない場合、エラーページへリダイレクト
                response.sendRedirect("error1.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/error2.jsp").forward(request, response);
        }
    }
}

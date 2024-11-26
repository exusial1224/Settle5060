package membership;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Membership;
import utils.EmailUtility;

@WebServlet("/membership/EmailChange")
public class EmailChange extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newMail = request.getParameter("newMail");

        // セッションから "membershipIds" を取得し、userId を取得
        HttpSession session = request.getSession();
        List<Membership> membershipIds = (List<Membership>) session.getAttribute("membershipIds");

        if (membershipIds == null || membershipIds.isEmpty()) {
            response.sendRedirect("error1.jsp");
            return;
        }

        // 現在のユーザーの ID を取得
        Membership membership = membershipIds.get(0);  // リストの最初の会員
        int userId = membership.getMbr_id();

        try {
            // メール認証用のリンクを生成
            String encodedMail = URLEncoder.encode(newMail, "UTF-8");
            String encodedUserId = URLEncoder.encode(String.valueOf(userId), "UTF-8");
            String verificationLink = "http://" + request.getServerName() + ":" + request.getServerPort() +
                    request.getContextPath() + "/membership/EmailChangeComplete?userId=" + encodedUserId + "&newMail=" + encodedMail;

            // メール内容を設定
            String subject = "メールアドレス変更の確認";
            String content = "以下のリンクをクリックしてメールアドレスの変更を完了してください:\n" + verificationLink;

            // メール送信
            EmailUtility.sendEmail(newMail, subject, content);

            // 新しいメールアドレスをセッションに一時保存
            session.setAttribute("newMail", newMail);

            // 確認ページへフォワード
            request.getRequestDispatcher("/membership/emailChangeConfirm.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("/error2.jsp").forward(request, response);
        }
    }
}

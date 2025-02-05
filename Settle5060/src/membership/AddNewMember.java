package membership;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MembershipDAO;
import utils.EmailUtility;

@WebServlet("/membership/AddNewMember")
public class AddNewMember extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // フォームからデータを取得
        String mail = request.getParameter("mail");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String tel = request.getParameter("tel");
        String address = request.getParameter("address");
        String birth = request.getParameter("birth");

        // セッションにデータを保存
        HttpSession session = request.getSession(true);
        session.setAttribute("mail", mail);
        session.setAttribute("name", name);
        session.setAttribute("password", password);
        session.setAttribute("tel", tel);
        session.setAttribute("address", address);
        session.setAttribute("birth", birth);

        System.out.println("(session_id): " + session.getId());
        System.out.println("mail (session): " + request.getSession().getAttribute("mail"));
        System.out.println("name (session): " + request.getSession().getAttribute("name"));
        System.out.println("password (session): " + request.getSession().getAttribute("password"));
        System.out.println("tel (session): " + request.getSession().getAttribute("tel"));
        System.out.println("address (session): " + request.getSession().getAttribute("address"));
        System.out.println("birth (session): " + request.getSession().getAttribute("birth"));

        String contextPath = request.getContextPath();

        try {
        	MembershipDAO membershipDAO = new MembershipDAO();
            String Search  = membershipDAO.searchSameMail(mail);

            if(Search == null){
	            // メール認証用のリンクを生成
	            String encodedMail = URLEncoder.encode(mail, "UTF-8");
	            String encodedSession = URLEncoder.encode(request.getSession().getId(), "UTF-8");
	            String verificationLink = "http://" + request.getServerName() + ":" + request.getServerPort() +
	                    contextPath + "/membership/AddNewMemberComplete?email=" + encodedMail + "&set=" +encodedSession;

	            // メール内容
	            String subject = "会員登録の確認";
	            String content = "以下のリンクをクリックして会員登録を完了させてください。\n" + verificationLink;

	            // メール送信
	            EmailUtility.sendEmail(mail, subject, content);

	            // 仮登録完了ページへフォワード
	            request.getRequestDispatcher("/membership/addNewMemberComplete.jsp").forward(request, response);
            }else{
            	request.setAttribute("error", "は既に登録されています。");
            	request.getRequestDispatcher("/membership/addNewMember.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(contextPath + "/membership/error.jsp");
        }
    }
}
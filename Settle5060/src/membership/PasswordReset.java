//package membership;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import dao.MembershipDAO;
//
//@WebServlet("/membership/PasswordReset")
//public class PasswordReset extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String newPassword = request.getParameter("newPassword");
//        HttpSession session = request.getSession();
//
//        try {
//            // DAOメソッドを呼び出してパスワードを更新
//            MembershipDAO dao = new MembershipDAO();
//            int updateStatus = dao.reconfigurePassword(userEmail, newPassword);
//
//            if (updateStatus > 0) {
//                // パスワード更新成功後、確認ページにリダイレクト
//                request.getRequestDispatcher("/membership/passwordChangeSuccess.jsp").forward(request, response);
//            } else {
//                // 更新失敗時、エラーページにリダイレクト
//                response.sendRedirect("error2.jsp");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            request.getRequestDispatcher("/error2.jsp").forward(request, response);
//        }
//    }
//}

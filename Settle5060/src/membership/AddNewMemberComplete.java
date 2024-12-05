package membership;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MembershipDAO;

@WebServlet("/membership/AddNewMemberComplete")
public class AddNewMemberComplete extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        if (session == null) {
            System.out.println("セッションが存在しません。");
            response.sendRedirect(request.getContextPath() + "/membership/error1.jsp");
            return;
        } else {
            System.out.println("セッションが正常に作成されました。");
        }

        // セッションからデータを取得
        String email = (String) session.getAttribute("mail");
        String name = (String) session.getAttribute("name");
        String password = (String) session.getAttribute("password");
        String tel = (String) session.getAttribute("tel");
        String address = (String) session.getAttribute("address");
        String birthDateStr = (String) session.getAttribute("birth");
        StringBuilder buildDateStr = new StringBuilder(birthDateStr);

        buildDateStr.insert(6, "-");
        buildDateStr.insert(4, "-");
        birthDateStr = buildDateStr.toString();

        System.out.println("(Session_id): " + session.getId());
        System.out.println("mail: " + email);
        System.out.println("name: " + name);
        System.out.println("password: " + password);
        System.out.println("tel: " + tel);
        System.out.println("address: " + address);
        System.out.println("birth: " + birthDateStr);

        // データが不足している場合、エラーページに遷移
        if (email == null || email.isEmpty() || name == null || name.isEmpty() ||
            password == null || password.isEmpty() || tel == null || tel.isEmpty() ||
            address == null || address.isEmpty() || birthDateStr == null || birthDateStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/membership/error2.jsp");
            return;
        }

        try {
            // パスワードのハッシュ化
            String hashedPassword = hashPassword(password);

            // 生年月日をDate型に変換
            Date birthDate = Date.valueOf(birthDateStr);
            System.out.println("変換後:"+birthDate);
            // データベースに登録
            MembershipDAO membershipDAO = new MembershipDAO();
            int result = membershipDAO.AddNewMember(name, hashedPassword, email, tel, address,birthDate.toLocalDate());

            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/membership/addNewMemberPerfect.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/membership/error3.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/membership/error4.jsp");
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

package membership;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import bean.Membership;
import bean.PurchaseExp;
import dao.PurchaseDAO;



@WebServlet("/membership/PurchaseTicket")
public class PurchaseTicket extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // セッションから membershipIds リストを取得
            List<Membership> membershipIds = (List<Membership>) request.getSession().getAttribute("membershipIds");


            if (membershipIds == null || membershipIds.isEmpty()) {
                // リストが存在しない、もしくは空の場合エラーページにリダイレクト
                request.setAttribute("error", "ユーザーIDが設定されていません。ログインしてください。");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }
            String pur_id_str = request.getParameter("pur_id");
            //QRコード生成時のエンコーディング
    		String qr_encoding = "UTF-8";
    		//サイズ(ピクセル)
    		int qr_size = 100;
    		//画像ファイルの保存先
    		String filePath = getServletContext().getRealPath("/membership/img/qr_code.png");
    		//生成処理
    		ConcurrentHashMap<EncodeHintType, Comparable> hints = new ConcurrentHashMap<EncodeHintType, Comparable>();
    		//エラー訂正レベル指定
    		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
    		//エンコーディング指定
    		hints.put(EncodeHintType.CHARACTER_SET, qr_encoding);
    		//マージン指定
    		hints.put(EncodeHintType.MARGIN, 0);
    		QRCodeWriter writer = new QRCodeWriter();
    		BitMatrix bitMatrix = writer.encode(pur_id_str, BarcodeFormat.QR_CODE, qr_size, qr_size, hints);
    		BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

    		//ファイルへの保存処理
    		ImageIO.write(image, "png", new File(filePath));

            //URLからpur_idの値を取得
            int pur_id = Integer.parseInt(request.getParameter("pur_id"));


            // DAO から入場券情報を取得
            PurchaseDAO PurchaseDAO = new PurchaseDAO();
            PurchaseExp ticket = PurchaseDAO.getOneTkt(pur_id);

         // 現在時刻を取得
            LocalDateTime currentTime = LocalDateTime.now();

            // 日付と終了時刻
            LocalDate busDate = ticket.getBus_date();
            LocalTime endTime = ticket.getEnd_time();
            LocalDateTime ticketEndDateTime = LocalDateTime.of(busDate, endTime);


            // 期限切れかどうかの判定
            boolean isExpired = currentTime.isAfter(ticketEndDateTime);
            request.setAttribute("isExpired", isExpired);

            // リクエストに入場券情報をセットして JSP に転送
            request.setAttribute("ticket", ticket);
            request.getRequestDispatcher("purchaseTicket.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "リセール一覧の取得中にエラーが発生しました");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
	}
}

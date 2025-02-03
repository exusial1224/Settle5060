package admin;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.SlotDAO;

@WebServlet("/admin/AddTimeSlotBatch")
public class AddTimeSlotBatch extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor(); // 1スレッドのスレッドプール

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.getWriter().println("バックグラウンド");

        // 非同期で実行
        // 上記により別スレッドで実行されスループット工場に
        // futureオブジェ関連は時間があるとき要検証
        executor.submit(() -> {
            try {
                SlotDAO dao = new SlotDAO();
                int count = dao.insertFutureSlots();
                System.out.println("終了: " + count + " kenn");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void destroy() {
        executor.shutdown(); //
        super.destroy();
    }
}

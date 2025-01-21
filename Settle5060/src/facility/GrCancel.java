package facility;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.OrganizationPurchaseDAO;
import dao.SlotDAO;

@WebServlet("/facility/GrCancel")
public class GrCancel extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //セッション取得
    	HttpSession session = request.getSession();

    	LocalDate today = LocalDate.now();;
    	//DAOの取得
    	SlotDAO slotDao = new SlotDAO();
    	OrganizationPurchaseDAO opDao = new OrganizationPurchaseDAO();
    	
    	

    	//画面表示
    	request.getRequestDispatcher("/facility/entry.jsp").forward(request, response);

    }
}


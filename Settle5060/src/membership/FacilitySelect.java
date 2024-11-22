package membership;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Facility;
import dao.FacilityDAO;

@WebServlet("/membership/FacilitySelect")
public class FacilitySelect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // パラメータからカテゴリIDを取得
            int category = Integer.parseInt(request.getParameter("category"));

            // DAOを使用して施設リストを取得
            FacilityDAO dao = new FacilityDAO();
            List<Facility> facilityList = dao.getFacilityByCategory(category);

            // セッション管理
            HttpSession session = request.getSession();

            // ユーザーが選択した施設のIDと名前をセッションに保存
            if (!facilityList.isEmpty()) {
                Facility selectedFacility = facilityList.get(0); // 仮に最初の施設を選択
                session.setAttribute("facilityId", selectedFacility.getFac_id());
                session.setAttribute("facilityName", selectedFacility.getFac_name());
            }

            // セッションにカテゴリIDを保存
            List<Integer> selectedCategories = (List<Integer>) session.getAttribute("selectedCategories");
            if (selectedCategories == null) {
                selectedCategories = new ArrayList<>();
            }
            if (!selectedCategories.contains(category)) {
                selectedCategories.add(category);
            }
            session.setAttribute("selectedCategories", selectedCategories);

            // セッションに施設リストを保存
            List<List<Facility>> storedFacilityLists = (List<List<Facility>>) session.getAttribute("facilityLists");
            if (storedFacilityLists == null) {
                storedFacilityLists = new ArrayList<>();
            }
            storedFacilityLists.add(facilityList);
            session.setAttribute("facilityLists", storedFacilityLists);

            // JSPにフォワード
            request.getRequestDispatcher("top.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

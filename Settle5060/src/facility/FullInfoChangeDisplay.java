package facility;

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

@WebServlet("/facility/FullInfoChangeDisplay")
public class FullInfoChangeDisplay extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   HttpSession session = request.getSession();
        try {
        	int fac_id = (int) session.getAttribute("facilityId");
            FacilityDAO facilityDao = new FacilityDAO();
            Facility fac_info = facilityDao.getOneFacility(fac_id);
            System.out.println(fac_info.getOpen_time());
            System.out.println(fac_id);
            String rg_hol = fac_info.getRg_hol();
            List<Integer> hol_num_list = new ArrayList<Integer>();
            if(rg_hol != null){
                for(int i=0; i<rg_hol.length(); i++){
                	int hol_num = rg_hol.charAt(i) - '0';
                	System.out.println(hol_num);
                	hol_num_list.add(hol_num);
                }
            }
            request.setAttribute("fac_info", fac_info);
            request.setAttribute("hol_num_list", hol_num_list);
            request.getRequestDispatcher("/facility/fullInfoChangeDisplay.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Facility ID must be a number");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=An unexpected error occurred");
        }
    }
}

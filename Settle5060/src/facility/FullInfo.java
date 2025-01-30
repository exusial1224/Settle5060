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

@WebServlet("/facility/FullInfo")
public class FullInfo extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   HttpSession session = request.getSession();
        try {
        	int fac_id = (int) session.getAttribute("facilityId");
            FacilityDAO facilityDao = new FacilityDAO();
            Facility fac_info = facilityDao.getOneFacility(fac_id);
            System.out.println(fac_info.getRg_hol());
            String rg_hol = fac_info.getRg_hol();
            List<String> hol_list = new ArrayList<String>();
            if(rg_hol != null){
                for(int i=0; i<rg_hol.length(); i++){
                	int hol_num = rg_hol.charAt(i) - '0';
                	System.out.println(hol_num);
                	hol_list.add(hol_num==0 ? "日": hol_num==1 ? "月" : hol_num==2 ? "火" : hol_num==3 ? "水" : hol_num==4 ? "木" : hol_num==5 ? "金" : "土");
                }
            }
            request.setAttribute("fac_info", fac_info);
            request.setAttribute("hol_list", hol_list);
            request.getRequestDispatcher("/facility/fullInfo.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Facility ID must be a number");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=An unexpected error occurred");
        }
    }
}

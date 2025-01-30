package facility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Facility;
import dao.FacilityDAO;

@WebServlet("/facility/FullInfoChangeComplete")
public class FullInfoChangeComplete extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   HttpSession session = request.getSession();
        try {

        	int fac_id = (int) session.getAttribute("facilityId");
            Facility fac_info = (Facility) session.getAttribute("fac_info");
            System.out.println(fac_info);
            FacilityDAO FacilityDao =new FacilityDAO();

            int result = FacilityDao.updateFacilityDetail(fac_id,fac_info.getOpen_time(),fac_info.getClose_time(),fac_info.getSls_str(),fac_info.getMax_num(),fac_info.getLow_price(),fac_info.getHigh_price(),fac_info.getInit_price(),fac_info.getSd_tkt_price(),fac_info.getRg_hol(),fac_info.getChld_dsc(),fac_info.getCategory());
            request.setAttribute("result", result);
            request.getRequestDispatcher("/facility/fullInfoChangeComp.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Facility ID must be a number");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=An unexpected error occurred");
        }
    }
}

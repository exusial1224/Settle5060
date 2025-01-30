package facility;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Facility;

@WebServlet("/facility/FullInfoChange")
public class FullInfoChange extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   HttpSession session = request.getSession();
        try {

        	LocalTime open_time =  LocalTime.parse(request.getParameter("open_time"));
        	LocalTime close_time =  LocalTime.parse(request.getParameter("close_time"));
        	int sls_str = Integer.parseInt(request.getParameter("sls_str"));
        	int max_num = Integer.parseInt(request.getParameter("max_num"));
        	int low_price = Integer.parseInt(request.getParameter("low_price"));
        	int high_price = Integer.parseInt(request.getParameter("high_price"));
        	int init_price = Integer.parseInt(request.getParameter("init_price"));
        	int sd_tkt_price = Integer.parseInt(request.getParameter("sd_tkt_price"));
        	String[] rg_hol_stres =request.getParameterValues("rg_hol");
        	int chld_dsc = Integer.parseInt(request.getParameter("chld_dsc"));
        	int category = Integer.parseInt(request.getParameter("category"));
        	String rg_hol = "";
            for (String rg_hol_str : rg_hol_stres) {
                System.out.println(rg_hol_str);
                rg_hol = rg_hol.concat(rg_hol_str);
            }
            System.out.println(rg_hol);
            List<String> hol_list = new ArrayList<String>();
            if(rg_hol != null){
                for(int i=0; i<rg_hol.length(); i++){
                	int hol_num = rg_hol.charAt(i) - '0';
                	System.out.println(hol_num);
                	hol_list.add(hol_num==0 ? "日": hol_num==1 ? "月" : hol_num==2 ? "火" : hol_num==3 ? "水" : hol_num==4 ? "木" : hol_num==5 ? "金" : "土");
                }
            }
            Facility fac_info = new Facility();
            fac_info.setOpen_time(open_time);
            fac_info.setClose_time(close_time);
            fac_info.setSls_str(sls_str);
            fac_info.setMax_num(max_num);
            fac_info.setLow_price(low_price);
            fac_info.setHigh_price(high_price);
            fac_info.setInit_price(init_price);
            fac_info.setSd_tkt_price(sd_tkt_price);
            fac_info.setRg_hol(rg_hol);
            fac_info.setChld_dsc(chld_dsc);
            fac_info.setCategory(category);
            session.setAttribute("fac_info", fac_info);
            request.setAttribute("fac_info", fac_info);
            request.setAttribute("hol_list", hol_list);
            request.getRequestDispatcher("/facility/fullInfoChange.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Facility ID must be a number");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=An unexpected error occurred");
        }
    }
}

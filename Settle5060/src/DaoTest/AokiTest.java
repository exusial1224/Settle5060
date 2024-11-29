package DaoTest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Facility;
import dao.FacilityDAO;



@WebServlet(urlPatterns={"/DaoTest/aokitest"})
public class AokiTest extends HttpServlet {

	public void doGet (
		HttpServletRequest request, HttpServletResponse response
	) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {

			FacilityDAO fd = new FacilityDAO();
			String hol = fd.getRgHol(1);

			Facility f = new Facility();
			f.setRg_hol(hol);

			out.println(f.getRegularHolidays());


		} catch(Exception e) {
			e.printStackTrace(out);
		}
	}
}

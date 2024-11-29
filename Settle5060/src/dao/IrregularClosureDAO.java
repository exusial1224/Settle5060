package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IrregularClosureDAO extends RootDAO {


	//同じ休館日が登録されていたら日付を返す
	public Date checkSameClosure(int fac_id, Date irr_hol) throws Exception {

		Date search_day = null;
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("SELECT IRR_HOL FROM IRREGULAR_CLOSURE WHERE FAC_ID = ? AND IRR_HOL = ?");
		st.setInt(1, fac_id);
		st.setDate(2, irr_hol);

		ResultSet rs = st.executeQuery();

		while (rs.next()) {

			search_day = rs.getDate("IRR_HOL");
		}

		st.close();
		con.close();
		return search_day;
	}



	public int insertIrregularClosure(int fac_id, Date irr_hol) throws Exception {

		Connection con = getConnection();
		int line = 0;

		PreparedStatement st = con.prepareStatement("INSERT INTO IRREGULAR_CLOSURE VALUES(?,?)");

		st.setInt(1, fac_id);
		st.setDate(2, irr_hol);

		line = st.executeUpdate();

		st.close();
		con.close();

		return line;

	}

}

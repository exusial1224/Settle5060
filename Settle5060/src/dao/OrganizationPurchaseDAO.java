package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bean.OrganizationPurchaseExp;

public class OrganizationPurchaseDAO extends RootDAO {


	//特定の施設ID、日付の団体来場者を所得
	public List<OrganizationPurchaseExp> getOrganization(int fac_id, LocalDate bus_date) throws Exception {

		List<OrganizationPurchaseExp> list = new ArrayList<>();
		OrganizationPurchaseExp ope = null;
		Connection con = getConnection();

	    PreparedStatement st = con.prepareStatement("SELECT * FROM ORGANIZATION_PURCHASE JOIN SLOT ON ORGANIZATION_PURCHASE.SL_ID = SLOT.SL_ID WHERE FAC_ID = ? AND BUD_DATE = ?");
	    st.setInt(1, fac_id);
	    st.setDate(2, Date.valueOf(bus_date));

	    ResultSet rs = st.executeQuery();

		while (rs.next()) {
		    ope = new OrganizationPurchaseExp();
		    ope.setOrg_pur_id(rs.getInt("ORG_PUR_ID"));
		    ope.setSl_id(rs.getInt("SL_ID"));
		    ope.setOrg_name(rs.getString("ORG_NAME"));
		    ope.setRep_name(rs.getString("REP_NAME"));
		    ope.setNum_adlt_tkt_gr(rs.getInt("NUM_ADLT_TKT_GR"));
		    ope.setCnc_gr_adlt(rs.getInt("CNC_GR_ADLT"));
		    ope.setNum_chld_tkt_gr(rs.getInt("NUM_CHLD_TKT_GR"));
		    ope.setCnc_gr_chld(rs.getInt("CNC_GR_CHLD"));
		    ope.setOrg_tel(rs.getString("ORG_TEL"));
		    ope.setGr_tkt_admitted(rs.getBoolean("GR_TKT_ADMITTED"));
		    //ope.setGr_cnc_flg(rs.getBoolean("GR_CNC_FLG"));
		    ope.setBus_date(rs.getDate("BUS_DATE").toLocalDate());
		    ope.setStart_time(rs.getTime("START_TIME").toLocalTime());
		    ope.setEnd_time(rs.getTime("END_TIME").toLocalTime());
			list.add(ope);
		}

		st.close();
		con.close();

		return list;
	}


    //団体入場券を入場済みにする
    public int updateGrTktAdmitted(int org_pur_id) throws Exception {

    	int check = 0;
    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("UPDATE ORGANIZATION_PURCHASE SET GR_TKT_ADMITTED = ? WHERE ORG_PUR_ID = ?");
    	st.setBoolean(1, true);
    	st.setInt(2, org_pur_id);

    	check = st.executeUpdate();

		st.close();
		con.close();

		return check;

    }

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Facility;

public class FacilityDAO extends RootDAO {


	//お問い合わせ画面用
	public Facility getContactInfo(int fac_id) throws Exception {

		Facility facility = null;

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("SELECT * FROM FACILITY WHERE FAC_ID = ?");
		st.setInt(1, fac_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		facility = new Facility();
		facility.setFac_tel(rs.getString("FAC_TEL"));
		facility.setOpen_time(rs.getTime("OPEN_TIME"));
		facility.setClose_time(rs.getTime("CLOSE_TIME"));

		st.close();
		con.close();

		return facility;
	}


	//施設情報の全件所得
	public List<Facility> getFacilityInfos() throws Exception {

		Facility facility = null;
		List<Facility> list = new ArrayList<>();

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("SELECT * FROM FACILITY");
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			facility = new Facility();
			facility.setFac_id(rs.getInt("FAC_ID"));
			facility.setCo_name(rs.getString("CO_NAME"));
			facility.setFac_name(rs.getString("FAC_NAME"));
			//facility.setFac_password(rs.getString("FAC_PASSWORD"));
			//facility.setFac_mail(rs.getString("FAC_MAIL"));
			//facility.setFac_address(rs.getString("FAC_ADDRESS"));
			//facility.setFac_tel(rs.getString("FAC_TEL"));
			//facility.setOpen_time(rs.getTime("OPEN_TIME"));
			//facility.setClose_time(rs.getTime("CLOSE_TIME"));
			//facility.setSls_str(rs.getInt("SLS_STR"));
			//facility.setMax_num(rs.getInt("MAX_NUM"));
			//facility.setLow_price(rs.getInt("LOW_PRICE"));
			//facility.setHigh_price(rs.getInt("HIGH_PRICE"));
			//facility.setInit_price(rs.getInt("INIT_PRICE"));
			//facility.setSd_tkt_price(rs.getInt("SD_TKT_PRICE"));
			//facility.setRg_hol(rs.getString("RG_HOL"));
			//facility.setChld_dsc(rs.getInt("CHLD_DSC"));
			facility.setCategory(rs.getInt("CATEGORY"));
			//facility.setFac_reg(rs.getTimestamp("FAC_REG"));
			//facility.setFac_mod(rs.getTimestamp("FAC_MOD"));
			list.add(facility);
		}

		st.close();
		con.close();

		return list;
	}

	//
	public List<Facility> getFacilityByCategory(int category) throws Exception {

	    Facility facility = null;
	    List<Facility> list = new ArrayList<>();

	    Connection con = getConnection();

	    PreparedStatement st = con.prepareStatement("SELECT * FROM FACILITY WHERE CATEGORY = ?");
	    st.setInt(1, category);

	    ResultSet rs = st.executeQuery();

	    while (rs.next()) {

	        facility = new Facility();

	        facility.setFac_id(rs.getInt("FAC_ID"));
	        facility.setFac_name(rs.getString("FAC_NAME"));
	        facility.setCategory(rs.getInt("CATEGORY"));

	        list.add(facility);

	    }

	    st.close();
	    con.close();

	    return list;

	}



   //引数と同じ施設メールアドレスを持つ場合、引数と同じ施設メールアドレスをreturn
	public String searchSameMail(String fac_mail) throws Exception {

			String search = "";
			Connection con = getConnection();

			PreparedStatement stdup = con.prepareStatement("SELECT * FROM FACILITY WHERE FAC_MAIL = ?");

				stdup.setString(1, fac_mail);

				ResultSet rsdup = stdup.executeQuery();

				while (rsdup.next()) {
					search = rsdup.getString("FAC_MAIL");
				}

			stdup.close();
			con.close();
			return search;
	}


	//ログイン、メールとパスワード一致で施設IDを取得
	public int loginFacility(String fac_mail, String fac_password) throws Exception {

		int id = 0;
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement(
			"SELECT * FROM FACILITY WHERE FAC_PASSWORD = ? AND FAC_MAIL = ?");

		st.setString(1, fac_password);
		st.setString(2, fac_mail);

		ResultSet rs = st.executeQuery();

		while (rs.next()) {

			id = rs.getInt("FAC_ID");

		}

		st.close();
		con.close();

		return id;
	}


	//引数の施設IDに紐づく施設情報所得
	public Facility getOneFacility(int fac_id) throws Exception {

		Facility facility = null;
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("SELECT * FROM FACILITY WHERE FAC_ID = ?");
		st.setInt(1, fac_id);

		ResultSet rs = st.executeQuery();

		rs.next();

		facility = new Facility();
		facility.setFac_id(rs.getInt("FAC_ID"));
		facility.setCo_name(rs.getString("CO_NAME"));
		facility.setFac_name(rs.getString("FAC_NAME"));
		facility.setFac_password(rs.getString("FAC_PASSWORD"));
		facility.setFac_mail(rs.getString("FAC_MAIL"));
		facility.setFac_address(rs.getString("FAC_ADDRESS"));
		facility.setFac_tel(rs.getString("FAC_TEL"));
		facility.setOpen_time(rs.getTime("OPEN_TIME"));
		facility.setClose_time(rs.getTime("CLOSE_TIME"));
		facility.setSls_str(rs.getInt("SLS_STR"));
		facility.setMax_num(rs.getInt("MAX_NUM"));
		facility.setLow_price(rs.getInt("LOW_PRICE"));
		facility.setHigh_price(rs.getInt("HIGH_PRICE"));
		facility.setInit_price(rs.getInt("INIT_PRICE"));
		facility.setSd_tkt_price(rs.getInt("SD_TKT_PRICE"));
		facility.setRg_hol(rs.getString("RG_HOL"));
		facility.setChld_dsc(rs.getInt("CHLD_DSC"));
		facility.setCategory(rs.getInt("CATEGORY"));
		facility.setFac_reg(rs.getTimestamp("FAC_REG"));
		facility.setFac_mod(rs.getTimestamp("FAC_MOD"));

		st.close();
		con.close();

		return facility;
	}


    //施設名所得
    public String getFacilityName(int fac_id) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("SELECT FAC_NAME FROM FACILITY WHERE FAC_ID = ?");
		st.setInt(1, fac_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		String name = rs.getString("FAC_NAME");

		st.close();
		con.close();

		return name;
    }


    //1タイムスロットの上限人数を所得
    public int getMaxNum(int fac_id) throws Exception {

    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT MAX_NUM FROM FACILITY WHERE FAC_ID = ?");
    	st.setInt(1, fac_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		int num = rs.getInt("MAX_NUM");

		st.close();
		con.close();

		return num;
    }


    //販売開始日を所得
    public int getSls_Str(int fac_id) throws Exception {

    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT SLS_STR FROM FACILITY WHERE FAC_ID = ?");
    	st.setInt(1, fac_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		int ss = rs.getInt("SLS_STR");

		st.close();
		con.close();

		return ss;
    }


    //初期価格を所得
    public int getInit_price(int fac_id) throws Exception {

    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT INIT_PRICE FROM FACILITY WHERE FAC_ID = ?");
    	st.setInt(1, fac_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		int ip = rs.getInt("INIT_PRICE");

		st.close();
		con.close();

		return ip;
    }


    //最低価格を所得
    public int getLow_price(int fac_id) throws Exception {

    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT LOW_PRICE FROM FACILITY WHERE FAC_ID = ?");
    	st.setInt(1, fac_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		int lp = rs.getInt("LOW_PRICE");

		st.close();
		con.close();

		return lp;
    }


    //最高価格を所得
    public int getHigh_price(int fac_id) throws Exception {

    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT HIGH_PRICE FROM FACILITY WHERE FAC_ID = ?");
    	st.setInt(1, fac_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		int hp = rs.getInt("HIGH_PRICE");

		st.close();
		con.close();

		return hp;
    }


    //キャンセルに伴う上限人数update
    public int updateMaxNum(int fac_id, int increase) throws Exception {

    	int check = 0;
    	Connection con = getConnection();

    	int current_max_num = getMaxNum(fac_id);

    	PreparedStatement st = con.prepareStatement("UPDATE FACILITY SET MAX_NUM = ? WHERE FAC_ID = ?");
    	st.setInt(1, current_max_num + increase);
    	st.setInt(2, fac_id);

    	check = st.executeUpdate();

		st.close();
		con.close();

		return check;
    }

}

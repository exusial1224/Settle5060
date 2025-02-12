package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
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
		facility.setOpen_time(rs.getTime("OPEN_TIME").toLocalTime());
		facility.setClose_time(rs.getTime("CLOSE_TIME").toLocalTime());

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

	//施設選択画面における施設のカテゴリ検索
	public List<Facility> getFacilityByCategory(int category) throws Exception {

	    Facility facility = null;
	    List<Facility> list = new ArrayList<>();

	    Connection con = getConnection();

	    PreparedStatement st = con.prepareStatement("SELECT * FROM FACILITY WHERE CATEGORY = ?");
	    st.setInt(1, category);

	    ResultSet rs = st.executeQuery();

	    while (rs.next()) {

	        facility = new Facility();
	        //2月 03日： 施設名のみだと見栄えが悪いので取得要素増
	        facility.setFac_id(rs.getInt("FAC_ID"));
	        facility.setFac_name(rs.getString("FAC_NAME"));
	        facility.setCategory(rs.getInt("CATEGORY"));
	        facility.setFac_address(rs.getString("FAC_ADDRESS"));
			facility.setOpen_time(rs.getTime("OPEN_TIME").toLocalTime());
			facility.setClose_time(rs.getTime("CLOSE_TIME").toLocalTime());
			facility.setFac_tel(rs.getString("FAC_TEL"));

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
		if(rs.getTimestamp("FAC_MOD") != null){
			facility.setOpen_time(rs.getTime("OPEN_TIME").toLocalTime());
			facility.setClose_time(rs.getTime("CLOSE_TIME").toLocalTime());
			facility.setSls_str(rs.getInt("SLS_STR"));
			facility.setMax_num(rs.getInt("MAX_NUM"));
			facility.setLow_price(rs.getInt("LOW_PRICE"));
			facility.setHigh_price(rs.getInt("HIGH_PRICE"));
			facility.setInit_price(rs.getInt("INIT_PRICE"));
			facility.setSd_tkt_price(rs.getInt("SD_TKT_PRICE"));
			facility.setRg_hol(rs.getString("RG_HOL"));
			facility.setChld_dsc(rs.getInt("CHLD_DSC"));
			facility.setCategory(rs.getInt("CATEGORY"));
			facility.setFac_mod(rs.getTimestamp("FAC_MOD").toLocalDateTime());
		}
		facility.setFac_reg(rs.getTimestamp("FAC_REG").toLocalDateTime());

		st.close();
		con.close();

		return facility;
	}
    //facilityNameからIDを取得　2/12追加
    public int getAddFacilityId(String fac_name) throws Exception {

    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT FAC_ID FROM FACILITY WHERE FAC_NAME = ?");
    	st.setString(1, fac_name);

		ResultSet rs = st.executeQuery();

		rs.next();
		int fac_id = rs.getInt("FAC_ID");

		st.close();
		con.close();

		return fac_id;
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


    //Adminによる新規施設アカウント情報登録、登録されると登録された施設IDを返す(パスワードはハッシュ済を入れる)
	public int AddNewFacilityAd(String co_name, String fac_name, String fac_password, String fac_mail, String fac_address, String fac_tel) throws Exception {

		Connection con = getConnection();
		int fac_id = 0;

		PreparedStatement st = con.prepareStatement("INSERT INTO FACILITY(FAC_ID,CO_NAME,FAC_NAME,FAC_PASSWORD,FAC_MAIL,FAC_ADDRESS,FAC_TEL,FAC_REG) VALUES(NULL,?,?,?,?,?,?,?)");

		st.setString(1, co_name);
		st.setString(2, fac_name);
		st.setString(3, fac_password);
		st.setString(4, fac_mail);
		st.setString(5, fac_address);
		st.setString(6, fac_tel);

		// 現在時刻のTimestampを取得
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		st.setTimestamp(7, currentTimestamp);


		fac_id = st.executeUpdate();
		if(fac_id != 0){
			fac_id = getAddFacilityId(fac_name);
		}
		st.close();
		con.close();

		return fac_id;
	}


	//Adminによる施設アカウント情報変更
	public int updateFacilityAd(int fac_id, String co_name, String fac_name, String fac_address, String fac_tel) throws Exception {

		int check = 0;
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("UPDATE FACILITY SET CO_NAME = ?, FAC_NAME = ?, FAC_ADDRESS = ?, FAC_TEL = ? WHERE FAC_ID = ?");

		st.setString(1, co_name);
		st.setString(2, fac_name);
		st.setString(3, fac_address);
		st.setString(4, fac_tel);
	    st.setInt(5, fac_id);

		check = st.executeUpdate();

		st.close();
		con.close();

		return check;

	}


	//Adminによる施設パスワードの変更
	public int updatePasswordAd(int fac_id, String fac_password) throws Exception {

		int check = 0;
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("UPDATE FACILITY SET FAC_PASSWORD = ? WHERE FAC_ID = ?");

		st.setString(1, fac_password);
		st.setInt(2, fac_id);

		check = st.executeUpdate();

		st.close();
		con.close();

		return check;

	}


	//Adminによる施設メールアドレスの変更
	public int updateMailAd(int fac_id, String fac_mail) throws Exception {

		Connection con = getConnection();

		PreparedStatement st=con.prepareStatement("UPDATE FACILITY SET FAC_MAIL = ? WHERE FAC_ID = ?");

		st.setString(1, fac_mail);
		st.setInt(2, fac_id);

		int check = st.executeUpdate();

		st.close();
		con.close();

		return check;

	}


	//入力されたパスワードが正しいのかチェック
	public boolean checkPasswordAd(int fac_id, String fac_password) throws Exception {

		boolean check = false;

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("SELECT FAC_PASSWORD FROM FACILITY WHERE FAC_ID = ?");
		st.setInt(1, fac_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		String pass = rs.getString("FAC_PASSWORD");

		if (pass.equals(fac_password)) {
			check = true;
		}

		st.close();
		con.close();

		return check;

	}


	//Adminの施設選択における施設名の部分一致検索
	public List<Facility> getFacilityByKeyAd(String keyword) throws Exception {

		Facility facility = null;
		List<Facility> list = new ArrayList<>();
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("SELECT * FROM FACILITY WHERE FAC_NAME LIKE ?");
		st.setString(1, "%" + keyword + "%");

		ResultSet rs = st.executeQuery();

		while (rs.next()) {
		    facility = new Facility();
		    facility.setFac_id(rs.getInt("FAC_ID"));
		    facility.setFac_name(rs.getString("FAC_NAME"));
			list.add(facility);
		}

		st.close();
		con.close();

		return list;

	}


	//施設IDに紐づく子ども割引を所得
	public int getChldDsc(int fac_id) throws Exception {

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


	//施設詳細情報設定
	public int updateFacilityDetail(int fac_id, LocalTime open_time, LocalTime close_time, int sls_str, int max_num, int low_price, int high_price, int init_price, int sd_tkt_price, String rg_hol,int chld_dsc,int category) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("UPDATE FACILITY SET OPEN_TIME = ?,CLOSE_TIME = ?,SLS_STR = ?,MAX_NUM = ?,LOW_PRICE = ?,HIGH_PRICE = ?,INIT_PRICE = ?,SD_TKT_PRICE = ?,RG_HOL = ?,CHLD_DSC = ?,CATEGORY = ?,FAC_MOD = ? WHERE FAC_ID = ?");

		st.setTime(1, Time.valueOf(open_time));
		st.setTime(2, Time.valueOf(close_time));
		st.setInt(3, sls_str);
		st.setInt(4, max_num);
		st.setInt(5, low_price);
		st.setInt(6, high_price);
		st.setInt(7, init_price);
		st.setInt(8, sd_tkt_price);
		st.setString(9, rg_hol);
		st.setInt(10, chld_dsc);
		st.setInt(11, category);

		// 現在時刻のTimestampを取得
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		st.setTimestamp(12, currentTimestamp);
		st.setInt(13, fac_id);


		int check = st.executeUpdate();

		st.close();
		con.close();

		return check;

	}


	//定期休館情報返す
	public String getRgHol(int fac_id) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("SELECT RG_HOL FROM FACILITY WHERE FAC_ID = ?");
		st.setInt(1, fac_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		String hol = rs.getString("RG_HOL");

		st.close();
		con.close();

		return hol;
	}




	//当日券価格返す
	public int getSdTktPrice(int fac_id) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("SELECT SD_TKT_PRICE FROM FACILITY WHERE FAC_ID = ?");
		st.setInt(1, fac_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		int stp = rs.getInt("SD_TKT_PRICE");

		st.close();
		con.close();

		return stp;
	}


}

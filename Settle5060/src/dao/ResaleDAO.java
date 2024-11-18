package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.Resale;
import bean.ResaleExp;

public class ResaleDAO extends RootDAO {


    //リセール一覧所得
	public List<ResaleExp> getAllResales(int mbr_id) throws Exception {

		Connection con = getConnection();

		ResaleExp re = null;
		List<ResaleExp> list = new ArrayList<>();

		PreparedStatement st = con.prepareStatement
			("SELECT * FROM RESALE JOIN SLOT ON RESALE.SL_ID = SLOT.SL_ID JOIN FACILITY ON SLOT.FAC_ID = FACILITY.FAC_ID WHERE MBR_ID = ?");
		st.setInt(1, mbr_id);

		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			re = new ResaleExp();
			re.setRsle_id(rs.getInt("RSLE_ID"));
			re.setMbr_id(rs.getInt("MBR_ID"));
			re.setSl_id(rs.getInt("SL_ID"));
			re.setFac_name(rs.getString("FAC_NAME"));
			re.setBus_date(rs.getDate("BUS_DATE"));
			re.setStart_time(rs.getTime("START_TIME"));
			re.setEnd_time(rs.getTime("END_TIME"));
			list.add(re);
		}

		st.close();
		con.close();

		return list;
	}


	//メール送信後に呼び出す、TRAN_FLGをTRUEに更新
	public int UpdateTranFlg(int rsle_id) throws Exception {

		int check = 0;
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("UPDATE RESALE SET TRAN_FLG = ? WHERE RSLE_ID = ?");
		st.setBoolean(1, true);
		st.setInt(2, rsle_id);

		check = st.executeUpdate();


		st.close();
		con.close();

		return check;
	}


	//リセールの登録
	public int AddNewResale(int mbr_id, int sl_id) throws Exception {

		int check = 0;
		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("INSERT INTO VALUES(NULL,?,?,?,?,?)");
		st.setInt(1, mbr_id);
		st.setInt(2, sl_id);

		// 現在時刻のTimestampを取得
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

		st.setTimestamp(3, currentTimestamp);

		st.setBoolean(4, false);
		st.setBoolean(5, false);


		check = st.executeUpdate();

		st.close();
		con.close();

		return check;
	}




	//同じの施設の同じ日付に対して現在有効なリセールを何個立てているか所得
    public int countResale(int mbr_id, int sl_id) throws Exception {

    	int count = 0;
        Connection con = getConnection();


        //SL_ID→FAC_ID
        SlotDAO sd = new SlotDAO();
        int fi = sd.slTofac(sl_id);

        //SL_IDから日付とってくる
        Date bd = sd.slToBusDate(sl_id);


        PreparedStatement st = con.prepareStatement("SELECT * FROM RESALE JOIN SLOT ON RESALE.SL_ID = SLOT.SL_ID WHERE MBR_ID = ? AND FAC_ID = ? AND BUS_DATE = ? AND TRAN_FLG = ? AND CNC_FLG = ?");
        st.setInt(1, mbr_id);
        st.setInt(2, fi);
        st.setDate(3, bd);
        st.setBoolean(4, false);
        st.setBoolean(5, false);

        ResultSet rs = st.executeQuery();



        while (rs.next()) {

        	count++;

        }

		st.close();
		con.close();

		return count;

    }


    //リセールIDに紐づく情報所得
    public Resale getResaleById(int rsle_id) throws Exception {

    	Resale resale = null;
		Connection con = getConnection();


		PreparedStatement st = con.prepareStatement
			("SELECT * FROM RESALE WHERE RSLE_ID = ?");
		st.setInt(1, rsle_id);

		ResultSet rs = st.executeQuery();

		rs.next();


		resale = new ResaleExp();
		resale.setRsle_id(rs.getInt("RSLE_ID"));
		resale.setMbr_id(rs.getInt("MBR_ID"));
		resale.setSl_id(rs.getInt("SL_ID"));
		resale.setPosting(rs.getTimestamp("POSTING"));
		resale.setTran_flg(rs.getBoolean("TRAN_FLG"));
		resale.setCnc_flg(rs.getBoolean("CNC_FLG"));

		st.close();
		con.close();

		return resale;
    }


    //会員×施設×日付でメールは何件受けたのか?
    //public int countTranFlg(int mbr_id,int sl_id) throws Exception {

    	//int mail_count = 0;
    	//Connection con = getConnection();


        //SL_ID→FAC_ID
        //SlotDAO sd = new SlotDAO();
        //int fi = sd.slTofac(sl_id);

        //SL_IDから日付とってくる
        //Date bd = sd.slToBusDate(sl_id);


        //PreparedStatement st =con.prepareStatement("");





    //}

}

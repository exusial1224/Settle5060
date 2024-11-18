package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import bean.Slot;

public class SlotDAO extends RootDAO {


	//トップ画面のスロット情報所得
	public List<Slot> getAllSlots(int fac_id, Date bus_date) throws Exception {

		Connection con = getConnection();

		Slot slot = null;
		List<Slot> list = new ArrayList<>();

		PreparedStatement st = con.prepareStatement("SELECT * FROM SLOT WHERE FAC_ID = ? AND BUS_DATE = ?");
		st.setInt(1, fac_id);
		st.setDate(2, bus_date);

		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			slot = new Slot();
			slot.setSl_id(rs.getInt("SL_ID"));
			slot.setFac_id(rs.getInt("FAC_ID"));
			slot.setBus_date(rs.getDate("BUS_DATE"));
			slot.setStart_time(rs.getTime("START_TIME"));
			slot.setEnd_time(rs.getTime("END_TIME"));
			slot.setSl_price(rs.getInt("SL_PRICE"));
			//slot.setPrice_counter(rs.getInt("PRICE_COUNTER"));
			//slot.setNum_adlt_tkt_sm(rs.getInt("NUM_ADLT_TKT_SM"));
			//slot.setNum_chld_tkt_sm(rs.getInt("NUM_CHLD_TKT_SM"));
			list.add(slot);
		}

		st.close();
		con.close();

		return list;
	}


    //タイムスロットの開始時刻、終了時刻をlistで返す
	public List<Time> getTimes(int sl_id) throws Exception {

		Connection con = getConnection();
		List<Time> list = new ArrayList<>();

		PreparedStatement st = con.prepareStatement("SELECT START_TIME,END_TIME FROM SLOT WHERE SL_ID = ?");
		st.setInt(1, sl_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		Time ot = rs.getTime("START_TIME");
		Time et = rs.getTime("END_TIME");

		list.add(ot);
		list.add(et);

		st.close();
		con.close();

		return list;
	}



    //タイムスロットの現在の価格を所得
	public int getSlotPrice(int sl_id) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("SELECT SL_PRICE FROM SLOT WHERE SL_ID = ?");
		st.setInt(1, sl_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		int price = rs.getInt("SL_PRICE");

		st.close();
		con.close();

		return price;
	}


    //営業日付所得
	public Date getBusDate(int sl_id) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("SELECT BUS_DATE FROM SLOT WHERE SL_ID = ?");
		st.setInt(1, sl_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		Date bs = rs.getDate("BUS_DATE");

		st.close();
		con.close();

		return bs;
	}


	//スロットID→施設ID
	public int slTofac(int sl_id) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("SELECT FAC_ID FROM SLOT WHERE SL_ID = ?");
		st.setInt(1, sl_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		int si = rs.getInt("FAC_ID");

		st.close();
		con.close();

		return si;
	}


    //スロットIDでprice_counterを所得
    public int getPriceCounter(int sl_id) throws Exception {

    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT PRICE_COUNTER FROM SLOT WHERE SL_ID = ?");
    	st.setInt(1, sl_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		int pc = rs.getInt("PRICE_COUNTER");

		st.close();
		con.close();

		return pc;
    }



    //スロットIDを日付に変換
    public Date slToBusDate(int sl_id) throws Exception {

    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT BUS_DATE FROM SLOT WHERE SL_ID = ?");
    	st.setInt(1, sl_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		Date bd = rs.getDate("BUS_DATE");

		st.close();
		con.close();

		return bd;
    }




}

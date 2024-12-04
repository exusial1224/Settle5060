package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import bean.SlotExp;

public class SlotDAO extends RootDAO {


	//トップ画面のスロット情報所得
	public List<SlotExp> getAllSlots(int fac_id, Date bus_date) throws Exception {

		Connection con = getConnection();

		SlotExp slotexp = null;
		List<SlotExp> list = new ArrayList<>();

		PreparedStatement st = con.prepareStatement("SELECT * FROM SLOT WHERE FAC_ID = ? AND BUS_DATE = ?");
		st.setInt(1, fac_id);
		st.setDate(2, bus_date);

		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			slotexp = new SlotExp();
			slotexp.setSl_id(rs.getInt("SL_ID"));
			//slotexp.setFac_id(rs.getInt("FAC_ID"));
			slotexp.setBus_date(rs.getDate("BUS_DATE"));
			slotexp.setStart_time(rs.getTime("START_TIME"));
			slotexp.setEnd_time(rs.getTime("END_TIME"));
			slotexp.setSl_price(rs.getInt("SL_PRICE"));
			//slotexp.setPrice_counter(rs.getInt("PRICE_COUNTER"));
			//slotexp.setNum_adlt_tkt_sm(rs.getInt("NUM_ADLT_TKT_SM"));
			//slotexp.setNum_chld_tkt_sm(rs.getInt("NUM_CHLD_TKT_SM"));

			slotexp.setRemain(getRemainingSlot(slotexp.getSl_id()));


			list.add(slotexp);
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


    //スロットIDに紐づく上限人数を所得(キャンセル時変動するのはこっち)
    public int getSlotMax(int sl_id) throws Exception {

    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT SLOT_MAX FROM SLOT WHERE SL_ID = ?");
    	st.setInt(1, sl_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		int sm = rs.getInt("SLOT_MAX");

		st.close();
		con.close();

		return sm;
    }


    //キャンセルに伴う上限人数update
    public int updateSlotMax(int sl_id, int increase) throws Exception {

    	int check = 0;
    	Connection con = getConnection();

    	int current_max_num = getSlotMax(sl_id);

    	PreparedStatement st = con.prepareStatement("UPDATE SLOT SET SLOT_MAX = ? WHERE SL_ID = ?");
    	st.setInt(1, current_max_num + increase);
    	st.setInt(2, sl_id);

    	check = st.executeUpdate();

		st.close();
		con.close();

		return check;
    }


    //指定されたタイムスロットの残り枠数を返す
    public int getRemainingSlot(int sl_id) throws Exception {

    	PurchaseDAO pd = new PurchaseDAO();
    	int rsv_sum = pd.purchasedOneSlotCountRsv(sl_id);
    	int gr_sum = pd.purchasedOneSlotCountGr(sl_id);

    	//当日券も
    	int sm = getTktSm(sl_id);



    	//↓今のタイムスロットの購入者合計
    	int sum = rsv_sum + gr_sum + sm;

    	//↓1タイムスロットごとのタイムスロットの上限人数(キャンセル分抜き)
    	int max_num = getSlotMaxCancelOut(sl_id);

    	return max_num - sum;

    }

    //キャンセル枚数を省いたSLOT_MAX所得
    public int getSlotMaxCancelOut(int sl_id) throws Exception {

    	//↓キャンセル枚数をプラスしたslot_max
    	int before_slot_max = getSlotMax(sl_id);


    	PurchaseDAO pd = new PurchaseDAO();

    	//予約券キャンセル枚数
    	int rsv_cancel = pd.countCancelRsvBySlot(sl_id);

    	//団体キャンセル枚数
    	int gr_cancel = pd.countCancelGrBySlot(sl_id);

    	int one_slot_cancel = rsv_cancel + gr_cancel;

    	return before_slot_max - one_slot_cancel;

    }


    //1タイムスロットの当日券購入者を所得する
    public int getTktSm(int sl_id) throws Exception {

    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT NUM_ADLT_TKT_SM, NUM_CHLD_TKT_SM FROM SLOT WHERE SL_ID = ?");
    	st.setInt(1, sl_id);

		ResultSet rs = st.executeQuery();

		rs.next();
	    int adlt = rs.getInt("NUM_ADLT_TKT_SM");
	    int chld = rs.getInt("NUM_CHLD_TKT_SM");

	    int sum = adlt + chld;

		st.close();
		con.close();

		return sum;

    }


    //当日券購入者数更新
    public int SamedayPurchase(int sl_id, int num_adlt_tkt_sm, int num_chld_tkt_sm) throws Exception {

    	int check = 0;
    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("UPDATE SLOT SET NUM_ADLT_TKT_SM = ?, NUM_CHLD_TKT_SM = ? WHERE SL_ID = ?");
    	st.setInt(1, num_adlt_tkt_sm);
    	st.setInt(2, num_chld_tkt_sm);
    	st.setInt(3, sl_id);

    	check = st.executeUpdate();

    	st.close();
    	con.close();

    	return check;

    }




}

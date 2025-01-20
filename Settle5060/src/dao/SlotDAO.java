package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bean.SlotExp;

public class SlotDAO extends RootDAO {


	//トップ画面のスロット情報所得
	public List<SlotExp> getAllSlots(int fac_id, LocalDate bus_date) throws Exception {

		Connection con = getConnection();

		SlotExp slotexp = null;
		List<SlotExp> list = new ArrayList<>();

		PreparedStatement st = con.prepareStatement("SELECT * FROM SLOT WHERE FAC_ID = ? AND BUS_DATE = ?");
		st.setInt(1, fac_id);
		//sql.Dateに
		st.setDate(2, Date.valueOf(bus_date));

		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			slotexp = new SlotExp();
			slotexp.setSl_id(rs.getInt("SL_ID"));
			slotexp.setFac_id(rs.getInt("FAC_ID"));
			slotexp.setBus_date(rs.getDate("BUS_DATE").toLocalDate());
			slotexp.setStart_time(rs.getTime("START_TIME").toLocalTime());
			slotexp.setEnd_time(rs.getTime("END_TIME").toLocalTime());
			slotexp.setSl_price(rs.getInt("SL_PRICE"));
			slotexp.setPrice_counter(rs.getInt("PRICE_COUNTER"));
			slotexp.setNum_adlt_tkt_sm(rs.getInt("NUM_ADLT_TKT_SM"));
			slotexp.setNum_chld_tkt_sm(rs.getInt("NUM_CHLD_TKT_SM"));

			slotexp.setRemain(getRemainingSlot(slotexp.getSl_id()));


			list.add(slotexp);
		}

		st.close();
		con.close();

		return list;
	}


    //タイムスロットの開始時刻、終了時刻をlistで返す
	public List<LocalTime> getTimes(int sl_id) throws Exception {

		Connection con = getConnection();
		List<LocalTime> list = new ArrayList<>();

		PreparedStatement st = con.prepareStatement("SELECT START_TIME,END_TIME FROM SLOT WHERE SL_ID = ?");
		st.setInt(1, sl_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		Time ot = rs.getTime("START_TIME");
		Time et = rs.getTime("END_TIME");

		list.add(ot.toLocalTime());
		list.add(et.toLocalTime());

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
	public LocalDate getBusDate(int sl_id) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement("SELECT BUS_DATE FROM SLOT WHERE SL_ID = ?");
		st.setInt(1, sl_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		Date bs = rs.getDate("BUS_DATE");

		st.close();
		con.close();

		return bs.toLocalDate();
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
    public LocalDate slToBusDate(int sl_id) throws Exception {

    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT BUS_DATE FROM SLOT WHERE SL_ID = ?");
    	st.setInt(1, sl_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		Date bd = rs.getDate("BUS_DATE");

		st.close();
		con.close();

		return bd.toLocalDate();
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
    public int getRemainingSlot(int fac_id) throws Exception {

    	//現在時刻を取得
    	int sl_id = getslIdFromfacId(fac_id);


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


    //当日大人券返す
    public int getTktAdltSm(int sl_id) throws Exception {

    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT NUM_ADLT_TKT_SM FROM SLOT WHERE SL_ID = ?");
    	st.setInt(1, sl_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		int asm = rs.getInt("NUM_ADLT_TKT_SM");

		st.close();
		con.close();

		return asm;

    }


    //当日子ども券返す
    public int getTktChldSm(int sl_id) throws Exception {

    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT NUM_CHLD_TKT_SM FROM SLOT WHERE SL_ID = ?");
    	st.setInt(1, sl_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		int csm = rs.getInt("NUM_CHLD_TKT_SM");

		st.close();
		con.close();

		return csm;

    }


    //当日券購入者数更新
    public int SamedayPurchase(int fac_id, int num_adlt_tkt_sm, int num_chld_tkt_sm) throws Exception {

    	//現在時刻を取得
    	int sl_id = getslIdFromfacId(fac_id);


    	int check = 0;
    	Connection con = getConnection();

    	int current_asm = getTktAdltSm(sl_id);
        int current_csm = getTktChldSm(sl_id);

    	PreparedStatement st = con.prepareStatement("UPDATE SLOT SET NUM_ADLT_TKT_SM = ?, NUM_CHLD_TKT_SM = ? WHERE SL_ID = ?");
    	st.setInt(1, current_asm + num_adlt_tkt_sm);
    	st.setInt(2, current_csm + num_chld_tkt_sm);
    	st.setInt(3, sl_id);

    	check = st.executeUpdate();

    	st.close();
    	con.close();

    	return check;

    }


    //fac_idと現在時刻からsl_idをreturn
    public int getslIdFromfacId(int fac_id) throws Exception {

    	//現在時刻から時間を取得
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	String now_date_str = (LocalDateTime.now()).format(dtf);
    	Date now_date = Date.valueOf(now_date_str);

    	DateTimeFormatter tmf = DateTimeFormatter.ofPattern("HH:mm:ss");
    	int now_starthour = LocalDateTime.now().getHour();
    	String now_starthour_str = String.valueOf(now_starthour)+":00:00";

    	int now_endhour = now_starthour+1;
    	String now_endhour_str = String.valueOf(now_endhour)+":00:00";
    	System.out.println(now_date);
    	System.out.println(now_starthour_str);
    	System.out.println(now_endhour_str);
    	System.out.println(fac_id);
    	Connection con = getConnection();
    	PreparedStatement st = con.prepareStatement("SELECT SL_ID FROM SLOT WHERE FAC_ID = ? AND BUS_DATE = ? AND START_TIME = ? AND END_TIME = ?");
    	st.setInt(1, fac_id);
    	st.setDate(2, now_date);
    	st.setString(3, now_starthour_str);
    	st.setString(4, now_endhour_str);

    	ResultSet rs = st.executeQuery();

		rs.next();
		int sl_id = rs.getInt("SL_ID");

    	return sl_id;

    }

    //施設IDからタイムスロットの残り枠数を返す
    //public int getRemainingSlot2(int fac_id) throws Exception {

    //	LocalDate today = LocalDate.now();
    //	LocalTime now = LocalTime.now();

    //	PurchaseDAO pd = new PurchaseDAO();
    	//int rsv_sum = pd.purchasedOneSlotCountRsv(sl_id);
    	//int gr_sum = pd.purchasedOneSlotCountGr(sl_id);

    	//当日券も
    	//int sm = getTktSm(sl_id);



    	//↓今のタイムスロットの購入者合計
    	//int sum = rsv_sum + gr_sum + sm;

    	//↓1タイムスロットごとのタイムスロットの上限人数(キャンセル分抜き)
    	//int max_num = getSlotMaxCancelOut(sl_id);

    	//return max_num - sum;

    //}








}

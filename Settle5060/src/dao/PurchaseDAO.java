package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.PurchaseExp;

public class PurchaseDAO extends RootDAO {


	//引数の施設ID,日付に紐づくタイムスロットの合計購入者数を返す
	public int purchasedCountRsv(int fac_id, Date bus_date) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement
			("SELECT SUM(NUM_ADLT_TKT), SUM(NUM_CHLD_TKT) FROM PURCHASE JOIN ORGANIZATION_PURCHASE ON PURCHASE.SL_ID = ORGANIZATION_PURCHASE.SL_ID JOIN SLOT ON PURCHASE.SL_ID = SLOT.SL_ID WHERE FAC_ID = ? AND BUS_DATE = ?");
		st.setInt(1, fac_id);
		st.setDate(2, bus_date);

		ResultSet rs = st.executeQuery();

		rs.next();
		int sum_adlt = rs.getInt("SUM(NUM_ADLT_TKT)");
		int sum_chld = rs.getInt("SUM(NUM_CHLD_TKT)");

		int sum = sum_adlt + sum_chld;

		st.close();
		con.close();

		return sum;
	}


	//引数の施設ID,日付に紐づくタイムスロットの団体来場者の合計購入者数を返す
	public int purchasedCountGr(int fac_id, Date bus_date) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement
			("SELECT SUM(NUM_ADLT_TKT_GR), SUM(NUM_CHLD_TKT_GR) FROM ORGANIZATION_PURCHASE JOIN SLOT ON ORGANIZATION_PURCHASE.SL_ID = SLOT.SL_ID WHERE FAC_ID = ? AND BUS_DATE = ?");
		st.setInt(1, fac_id);
		st.setDate(2, bus_date);

		ResultSet rs = st.executeQuery();

		rs.next();
		int sum_adlt_gr = rs.getInt("SUM(NUM_ADLT_TKT_GR)");
		int sum_chld_gr = rs.getInt("SUM(NUM_CHLD_TKT_GR)");

		int sum = sum_adlt_gr + sum_chld_gr;

		st.close();
		con.close();

		return sum;
	}


	//1つのタイムスロットの一般購入者数をreturn
	public int purchasedOneSlotCountRsv(int sl_id) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement
			("SELECT SUM(NUM_ADLT_TKT), SUM(NUM_CHLD_TKT) FROM PURCHASE WHERE SL_ID = ?");
		st.setInt(1, sl_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		int sum_adlt = rs.getInt("SUM(NUM_ADLT_TKT)");
		int sum_chld = rs.getInt("SUM(NUM_CHLD_TKT)");

		int sum = sum_adlt + sum_chld;

		st.close();
		con.close();

		return sum;

	}


	//1つのタイムスロットの団体購入者数をreturn
	public int purchasedOneSlotCountGr(int sl_id) throws Exception {

		Connection con = getConnection();

		PreparedStatement st = con.prepareStatement
			("SELECT SUM(NUM_ADLT_TKT_GR), SUM(NUM_CHLD_TKT_GR) FROM ORGANIZATION_PURCHASE WHERE SL_ID = ?");
		st.setInt(1, sl_id);

		ResultSet rs = st.executeQuery();

		rs.next();
		int sum_adlt_gr = rs.getInt("SUM(NUM_ADLT_TKT_GR)");
		int sum_chld_gr = rs.getInt("SUM(NUM_CHLD_TKT_GR)");

		int sum = sum_adlt_gr + sum_chld_gr;

		st.close();
		con.close();

		return sum;
	}


	//購入情報追加
	public List<Integer> Purchase(int mbr_id, int sl_id, int pur_price, int num_adlt_tkt, int num_chld_tkt) throws Exception {

	    List<Integer> list = new ArrayList<>();
	    //購入のinsertが正しく行われたのかチェック(listの最初)
	    int check = 0;

	    //価格が上がった場合スロットのprice_counterのupdateに成功したのか(list2番目)
	    int check2 = 0;

	    //価格が上がった場合タイムスロットのprice_counterを何個デクリメントしたのか?(list3番目)
	    int check3 = 0;

	    //価格が上がった場合最終的な価格の挿入は正しく行われたのか?(list最後)
	    int check4 = 0;

	    try (Connection con = getConnection()) {


	        // トランザクションの開始
	        con.setAutoCommit(false);


	        // 購入前スロット価格を所得before_slot_priceへ
	        SlotDAO sd = new SlotDAO();
	        int before_slot_price = sd.getSlotPrice(sl_id);






	        // 購入
	        try (PreparedStatement st = con.prepareStatement(
	                "INSERT INTO PURCHASE VALUES(NULL,?,?,?,?,?,?,FALSE)")) {
	            st.setInt(1, mbr_id);
	            st.setInt(2, sl_id);
	            st.setInt(3, pur_price);
	            st.setInt(4, num_adlt_tkt);
	            st.setInt(5, num_chld_tkt);
	            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));

	            check = st.executeUpdate();
	        }

	        //↑こうにゅうここまで😊



	        // 購入後スロット購入者数を取得し、合計をall_sumに設定
	        int rsv_sum = purchasedOneSlotCountRsv(sl_id);
	        int gr_sum = purchasedOneSlotCountGr(sl_id);
	        int all_sum = rsv_sum + gr_sum;


	        // SL_ID→FAC_ID
	        int fi = sd.slTofac(sl_id);





	        FacilityDAO fd = new FacilityDAO();

	        // 初期価格と上限人数の取得
	        int init_price = fd.getInit_price(fi);
	        int max_num = fd.getMaxNum(fi);



	        // 購入後スロット価格を取得→after_slot_price1へ
	        DynamicPricing dp = new DynamicPricing();
	        int after_slot_price1 = dp.calculatePrice(all_sum, init_price, max_num);


	        // 価格が上がったら?
	        if (after_slot_price1 > before_slot_price) {
	            int price_counter = sd.getPriceCounter(sl_id);
	            int after_price_counter = price_counter + 1;



	            // 購入したスロットのprice_counterをインクリメントしてスロット価格を更新
	            try (PreparedStatement st2 = con.prepareStatement(
	                    "UPDATE SLOT SET PRICE_COUNTER = ?,SL_PRICE = ? WHERE SL_ID = ?")) {

	                st2.setInt(1, after_price_counter);
	                st2.setInt(2, after_slot_price1);
	                st2.setInt(3, sl_id);

	                check2 = st2.executeUpdate();
	            }

	            // 日付を取得
	            Date d = sd.slToBusDate(sl_id);



	            // 条件に該当するスロットのprice_counterをデクリメント
	            try (PreparedStatement st3 = con.prepareStatement(
	                    "SELECT SL_ID, PRICE_COUNTER FROM SLOT WHERE FAC_ID = ? AND BUS_DATE = ? AND SL_ID != ?")) {
	                st3.setInt(1, fi);
	                st3.setDate(2, d);
	                st3.setInt(3, sl_id);



	                try (ResultSet rs2 = st3.executeQuery()) {
	                    while (rs2.next()) {
	                        if (rs2.getInt("PRICE_COUNTER") < after_price_counter) {
	                            try (PreparedStatement st4 = con.prepareStatement(
	                                    "UPDATE SLOT SET PRICE_COUNTER = ? WHERE SL_ID = ?")) {
	                                st4.setInt(1, rs2.getInt("PRICE_COUNTER") - 1);
	                                st4.setInt(2, rs2.getInt("SL_ID"));

	                                check3 += st4.executeUpdate();
	                            }


	                        }

	                    }
	                }
	            }

	            // price_counterに基づいて価格を下げる設定
	            int low_price = fd.getLow_price(fi);
	            int high_price = fd.getHigh_price(fi);

	            try (PreparedStatement st5 = con.prepareStatement(
	                    "SELECT SL_ID, SL_PRICE, PRICE_COUNTER FROM SLOT WHERE FAC_ID = ? AND BUS_DATE = ?")) {
	                st5.setInt(1, fi);
	                st5.setDate(2, d);




	                try (ResultSet rs3 = st5.executeQuery()) {

	                    while (rs3.next()) {

	                        int des_price = dp.decisionPrice(rs3.getInt("SL_PRICE"), rs3.getInt("PRICE_COUNTER"), low_price, high_price);

	                        try (PreparedStatement st6 = con.prepareStatement(
	                                "UPDATE SLOT SET SL_PRICE = ? WHERE SL_ID = ?")) {
	                            st6.setInt(1, des_price);
	                            st6.setInt(2, rs3.getInt("SL_ID"));

	                            check4 += st6.executeUpdate();
	                        }



	                    }
	                }



	            }

	        }

	        list.add(check);
	        list.add(check2);
	        list.add(check3);
	        list.add(check4);

	        // トランザクションのコミット
	        con.commit();
	    } catch (Exception e) {
	        throw e;
	    }

	    return list;
	}


	//会員の購入履歴を全件所得
	public List<PurchaseExp> getPurchaseHistory(int mbr_id) throws Exception {

		List<PurchaseExp> list = new ArrayList<>();
		PurchaseExp purchaseexp = null;

		Connection con = getConnection();


		PreparedStatement st = con.prepareStatement(
			"SELECT * FROM PURCHASE WHERE MBR_ID = ?");
		st.setInt(1, mbr_id);

		ResultSet rs = st.executeQuery();

		SlotDAO sd = new SlotDAO();
		FacilityDAO fd = new FacilityDAO();



		while (rs.next()) {
			purchaseexp = new PurchaseExp();
			purchaseexp.setPur_id(rs.getInt("PUR_ID"));
			purchaseexp.setMbr_id(rs.getInt("MBR_ID"));
			purchaseexp.setSl_id(rs.getInt("SL_ID"));
			purchaseexp.setPur_price(rs.getInt("PUR_PRICE"));
			purchaseexp.setNum_adlt_tkt(rs.getInt("NUM_ADLT_TKT"));
			purchaseexp.setNum_chld_tkt(rs.getInt("NUM_CHLD_TKT"));
			purchaseexp.setTime_pur(rs.getTimestamp("TIME_PUR"));
			purchaseexp.setRsv_admitted(rs.getBoolean("RSV_ADMITTED"));

			//SL_IDからFAC_IDを所得
			int fi = sd.slTofac(purchaseexp.getSl_id());

			//FAC_IDから施設名を所得する
			String f_name = fd.getFacilityName(fi);

			purchaseexp.setFac_name(f_name);

		    list.add(purchaseexp);

		    //さいご初期化する
		    sd = new SlotDAO();
		    fd = new FacilityDAO();
		}

		st.close();
		con.close();

		return list;
	}


	//購入情報から大人入場券購入枚数をとってくる
    public int getAdltTkt(int pur_id) throws Exception {

    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT NUM_ADLT_TKT FROM PURCHASE WHERE PUR_ID = ?");
    	st.setInt(1, pur_id);

    	ResultSet rs = st.executeQuery();


		rs.next();
		int ad = rs.getInt("NUM_ADLT_TKT");


		st.close();
		con.close();

		return ad;

    }


    //購入情報から大人入場券購入枚数をとってくる
    public int getChldTkt(int pur_id) throws Exception {

    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT NUM_CHLD_TKT FROM PURCHASE WHERE PUR_ID = ?");
    	st.setInt(1, pur_id);

    	ResultSet rs = st.executeQuery();

		rs.next();
		int ct = rs.getInt("NUM_CHLD_TKT");


		st.close();
		con.close();

		return ct;

    }



    //購入をキャンセル + スロットの上限人数の更新
    public List<Integer> Cancel(int mbr_id, int sl_id, int pur_price, int num_adlt_tkt, int num_chld_tkt) throws Exception {


        List<Integer> list = new ArrayList<>();

        //リセールメールの送信精査をするかどうか
        boolean shouldSendResaleMail = false;

        //正しくキャンセル情報が追加されたのかチェック(listの最初に入ってる)
        int cancelInsertResult = 0;

        //上限の更新に成功したのかチェック(listの2番目)
        int maxUpdateResult = 0;



        //SL_ID→FAC_ID
        SlotDAO slotDao = new SlotDAO();
        int facilityId = slotDao.slTofac(sl_id);


        //いまの上限はどれくらい？
        FacilityDAO facilityDao = new FacilityDAO();
        int maxCapacity = facilityDao.getMaxNum(facilityId);

        int rsvSum = purchasedOneSlotCountRsv(sl_id);
        int grSum = purchasedOneSlotCountGr(sl_id);
        //↓これ購入枚数合計1スロットにおける
        int currentTotal = rsvSum + grSum;


        //上限とおなじならメール精査
        if (currentTotal == maxCapacity) {
            shouldSendResaleMail = true;
        }

        try (Connection con = getConnection()) {

        	//トランザクション開始
            con.setAutoCommit(false);



            // キャンセル情報をinsert
            try (PreparedStatement st = con.prepareStatement("INSERT INTO PURCHASE VALUES(NULL,?,?,?,?,?,?,NULL)")) {


                st.setInt(1, mbr_id);
                st.setInt(2, sl_id);
                st.setInt(3, pur_price);
                st.setInt(4, -num_adlt_tkt);
                st.setInt(5, -num_chld_tkt);
                st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));

                cancelInsertResult = st.executeUpdate();
                list.add(cancelInsertResult);
            }

            // タイムスロットの上限更新
            maxUpdateResult = facilityDao.updateMaxNum(facilityId, num_adlt_tkt + num_chld_tkt);



            list.add(maxUpdateResult);


            if (shouldSendResaleMail) {
                try (PreparedStatement st2 = con.prepareStatement("SELECT MBR_ID FROM RESALE WHERE SL_ID = ?")) {
                    st2.setInt(1, sl_id);
                    ResultSet rs2 = st2.executeQuery();

                    while (rs2.next()) {
                        list.add(rs2.getInt("MBR_ID"));
                    }
                }
            }

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("キャンセル処理中にエラーが発生しました", e);
        }

        return list;
    }



    //購入価格を所得
    public int getPurPrice(int pur_id) throws Exception {

    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT PUR_PRICE FROM PURCHASE WHERE PUR_ID = ?");
    	st.setInt(1, pur_id);

    	ResultSet rs = st.executeQuery();

		rs.next();
		int pp = rs.getInt("PUR_PRICE");


		st.close();
		con.close();

		return pp;

    }



}

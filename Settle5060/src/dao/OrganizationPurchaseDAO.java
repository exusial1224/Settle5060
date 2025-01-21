package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	    PreparedStatement st = con.prepareStatement("SELECT * FROM ORGANIZATION_PURCHASE JOIN SLOT ON ORGANIZATION_PURCHASE.SL_ID = SLOT.SL_ID WHERE FAC_ID = ? AND BUS_DATE = ?");
	    st.setInt(1, fac_id);
	    st.setString(2, String.valueOf(bus_date));

	    ResultSet rs = st.executeQuery();
	    ope = new OrganizationPurchaseExp();
		while (rs.next()) {
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
		System.out.println(list);
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
  //購入をキャンセル + スロットの上限人数の更新
    public List<Integer> Cancel(int org_pur_id, int cnc_gr_adlt, int cnc_gr_chld) throws Exception {


        List<Integer> list = new ArrayList<>();

        //リセールメールの送信精査をするかどうか
        boolean shouldSendResaleMail = false;

        //正しくキャンセル情報が更新されたのかチェック(listの最初に入ってる)
        int cancelInsertResult = 0;

        //上限の更新に成功したのかチェック(listの2番目)
        int maxUpdateResult = 0;



        OrganizationPurchaseExp ope = getOneTktGr(org_pur_id);




        //いまの上限(キャンセルout)はどれくらい？
        SlotDAO sd = new SlotDAO();
        PurchaseDAO pd = new PurchaseDAO();
        int maxCapacity = sd.getSlotMaxCancelOut(ope.getSl_id());

        int rsvSum = pd.purchasedOneSlotCountRsv(ope.getSl_id());
        int grSum = pd.purchasedOneSlotCountGr(ope.getSl_id());
        //↓これ購入枚数合計1スロットにおける
        int currentTotal = rsvSum + grSum;


        //上限とおなじならメール精査
        if (currentTotal == maxCapacity) {
            shouldSendResaleMail = true;
        }

        try (Connection con = getConnection()) {

        	//トランザクション開始
            con.setAutoCommit(false);



            // キャンセル情報を更新
            try (PreparedStatement st = con.prepareStatement("UPDATE PURCHASE SET CNC_RSV_ADLT = ?, SET CNC_RSV_CHLD = ? WHERE PUR_ID = ?")) {


                st.setInt(1, cnc_gr_adlt);
                st.setInt(2, cnc_gr_chld);
                st.setInt(3, org_pur_id);

                cancelInsertResult = st.executeUpdate();
                list.add(cancelInsertResult);
            }

            // タイムスロットの上限更新
            maxUpdateResult = sd.updateSlotMax(ope.getSl_id(), cnc_gr_adlt + cnc_gr_chld);



            list.add(maxUpdateResult);


            if (shouldSendResaleMail) {
                try (PreparedStatement st2 = con.prepareStatement("SELECT MBR_ID FROM RESALE WHERE SL_ID = ? AND TRAN_FLG = ? AND CNC_FLG = ?")) {
                    st2.setInt(1, ope.getSl_id());
                    st2.setBoolean(2, false);
                    st2.setBoolean(3, false);

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
  //購入情報返す
    public OrganizationPurchaseExp getOneTktGr(int org_pur_id) throws Exception {

    	OrganizationPurchaseExp organizationpurchaseexp = null;
    	Connection con = getConnection();

    	PreparedStatement st = con.prepareStatement("SELECT * FROM ORGANIZATION_PURCHASE JOIN SLOT ON  ORGANIZATION_PURCHASE.SL_ID = SLOT.SL_ID JOIN FACILITY ON SLOT.FAC_ID = FACILITY.FAC_ID WHERE ORG_PUR_ID = ?");
    	st.setInt(1, org_pur_id);

    	ResultSet rs = st.executeQuery();

    	organizationpurchaseexp = new OrganizationPurchaseExp();

    	while(rs.next()){
    		organizationpurchaseexp.setOrg_pur_id(rs.getInt("ORG_PUR_ID"));
	    	organizationpurchaseexp.setSl_id(rs.getInt("SL_ID"));
	    	organizationpurchaseexp.setOrg_name(rs.getString("ORG_NAME"));
	    	organizationpurchaseexp.setRep_name(rs.getString("REP_NAME"));
	    	organizationpurchaseexp.setCnc_gr_adlt(rs.getInt("CNC_RSV_ADLT"));
	    	organizationpurchaseexp.setNum_chld_tkt_gr(rs.getInt("NUM_CHLD_TKT"));
	    	organizationpurchaseexp.setCnc_gr_chld(rs.getInt("CNC_RSV_CHLD"));
	    	organizationpurchaseexp.setGr_tkt_admitted(rs.getBoolean("RSV_ADMITTED"));

	    	organizationpurchaseexp.setStart_time(rs.getTime("START_TIME").toLocalTime());
	    	organizationpurchaseexp.setEnd_time(rs.getTime("END_TIME").toLocalTime());

	    	organizationpurchaseexp.setBus_date(rs.getDate("BUS_DATE").toLocalDate());
    	}

		st.close();
		con.close();

		return organizationpurchaseexp;
    }



}

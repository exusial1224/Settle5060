package bean;

import java.io.Serializable;

public class OrganizationPurchase implements Serializable {
	private int org_pur_id;
	private int sl_id;
	private String org_name;
	private String rep_name;
	private int num_adlt_tkt_gr;
	private int num_chld_tkt_gr;
	private String org_tel;
	private boolean gr_tkt_admitted = false;
	private boolean gr_cnc_flg = false;


	public int getOrg_pur_id() {
		return org_pur_id;
	}
	public void setOrg_pur_id(int org_pur_id) {
		this.org_pur_id = org_pur_id;
	}
	public int getSl_id() {
		return sl_id;
	}
	public void setSl_id(int sl_id) {
		this.sl_id = sl_id;
	}
	public String getOrg_name() {
		return org_name;
	}
	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}
	public String getRep_name() {
		return rep_name;
	}
	public void setRep_name(String rep_name) {
		this.rep_name = rep_name;
	}
	public int getNum_adlt_tkt_gr() {
		return num_adlt_tkt_gr;
	}
	public void setNum_adlt_tkt_gr(int num_adlt_tkt_gr) {
		this.num_adlt_tkt_gr = num_adlt_tkt_gr;
	}
	public int getNum_chld_tkt_gr() {
		return num_chld_tkt_gr;
	}
	public void setNum_chld_tkt_gr(int num_chld_tkt_gr) {
		this.num_chld_tkt_gr = num_chld_tkt_gr;
	}
	public String getOrg_tel() {
		return org_tel;
	}
	public void setOrg_tel(String org_tel) {
		this.org_tel = org_tel;
	}
	public boolean isGr_tkt_admitted() {
		return gr_tkt_admitted;
	}
	public void setGr_tkt_admitted(boolean gr_tkt_admitted) {
		this.gr_tkt_admitted = gr_tkt_admitted;
	}
	public boolean isGr_cnc_flg() {
		return gr_cnc_flg;
	}
	public void setGr_cnc_flg(boolean gr_cnc_flg) {
		this.gr_cnc_flg = gr_cnc_flg;
	}


}

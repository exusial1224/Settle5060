package bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Purchase implements Serializable {
	private int pur_id;
	private int mbr_id;
	private int sl_id;
	private int pur_price;
	private int num_adlt_tkt;
	private int cnc_rsv_adlt = 0;
	private int num_chld_tkt;
	private int cnc_rsv_chld = 0;
	private LocalDateTime time_pur;
	private boolean rsv_admitted = false;


	public int getPur_id() {
		return pur_id;
	}
	public void setPur_id(int pur_id) {
		this.pur_id = pur_id;
	}
	public int getMbr_id() {
		return mbr_id;
	}
	public void setMbr_id(int mbr_id) {
		this.mbr_id = mbr_id;
	}
	public int getSl_id() {
		return sl_id;
	}
	public void setSl_id(int sl_id) {
		this.sl_id = sl_id;
	}
	public int getPur_price() {
		return pur_price;
	}
	public void setPur_price(int pur_price) {
		this.pur_price = pur_price;
	}
	public int getNum_adlt_tkt() {
		return num_adlt_tkt;
	}
	public void setNum_adlt_tkt(int num_adlt_tkt) {
		this.num_adlt_tkt = num_adlt_tkt;
	}
	public int getNum_chld_tkt() {
		return num_chld_tkt;
	}
	public void setNum_chld_tkt(int num_chld_tkt) {
		this.num_chld_tkt = num_chld_tkt;
	}
	public LocalDateTime getTime_pur() {
		return time_pur;
	}
	public void setTime_pur(LocalDateTime time_pur) {
		this.time_pur = time_pur;
	}
	public boolean isRsv_admitted() {
		return rsv_admitted;
	}
	public void setRsv_admitted(boolean rsv_admitted) {
		this.rsv_admitted = rsv_admitted;
	}
	public int getCnc_rsv_adlt() {
		return cnc_rsv_adlt;
	}
	public void setCnc_rsv_adlt(int cnc_rsv_adlt) {
		this.cnc_rsv_adlt = cnc_rsv_adlt;
	}
	public int getCnc_rsv_chld() {
		return cnc_rsv_chld;
	}
	public void setCnc_rsv_chld(int cnc_rsv_chld) {
		this.cnc_rsv_chld = cnc_rsv_chld;
	}



	//現在の大人券は何枚なのか
	public int current_adlt_tkt() {
		return this.num_adlt_tkt - this.cnc_rsv_adlt;
	}


	//現在の子ども券は何枚なのか
	public int current_chld_tkt() {
		return this.num_chld_tkt - this.cnc_rsv_chld;
	}


}

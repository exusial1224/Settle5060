package bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Resale implements Serializable {
	private int rsle_id;
	private int mbr_id;
	private int sl_id;
	private Timestamp posting;
	private boolean tran_flg = false;
	private boolean cnc_flg = false;


	public int getRsle_id() {
		return rsle_id;
	}
	public void setRsle_id(int rsle_id) {
		this.rsle_id = rsle_id;
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
	public Timestamp getPosting() {
		return posting;
	}
	public void setPosting(Timestamp posting) {
		this.posting = posting;
	}
	public boolean isTran_flg() {
		return tran_flg;
	}
	public void setTran_flg(boolean tran_flg) {
		this.tran_flg = tran_flg;
	}
	public boolean isCnc_flg() {
		return cnc_flg;
	}
	public void setCnc_flg(boolean cnc_flg) {
		this.cnc_flg = cnc_flg;
	}


}

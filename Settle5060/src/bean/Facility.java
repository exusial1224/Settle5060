package bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Facility implements Serializable {
	private int fac_id;
	private String co_name;
	private String fac_name;
	private String fac_password;
	private String fac_mail;
	private String fac_address;
	private String fac_tel;
	private LocalTime open_time;
	private LocalTime close_time;
	private int sls_str;
	private int max_num;
	private int low_price;
	private int high_price;
	private int init_price;
	private int sd_tkt_price;
	private String rg_hol;
	private int chld_dsc;
	private int category;
	private LocalDateTime fac_reg;
	private LocalDateTime fac_mod;


	public int getFac_id() {
		return fac_id;
	}
	public void setFac_id(int fac_id) {
		this.fac_id = fac_id;
	}
	public String getCo_name() {
		return co_name;
	}
	public void setCo_name(String co_name) {
		this.co_name = co_name;
	}
	public String getFac_name() {
		return fac_name;
	}
	public void setFac_name(String fac_name) {
		this.fac_name = fac_name;
	}
	public String getFac_password() {
		return fac_password;
	}
	public void setFac_password(String fac_password) {
		this.fac_password = fac_password;
	}
	public String getFac_mail() {
		return fac_mail;
	}
	public void setFac_mail(String fac_mail) {
		this.fac_mail = fac_mail;
	}
	public String getFac_address() {
		return fac_address;
	}
	public void setFac_address(String fac_address) {
		this.fac_address = fac_address;
	}
	public String getFac_tel() {
		return fac_tel;
	}
	public void setFac_tel(String fac_tel) {
		this.fac_tel = fac_tel;
	}
	public LocalTime getOpen_time() {
		return open_time;
	}
	public void setOpen_time(LocalTime open_time) {
		this.open_time = open_time;
	}
	public LocalTime getClose_time() {
		return close_time;
	}
	public void setClose_time(LocalTime close_time) {
		this.close_time = close_time;
	}
	public int getSls_str() {
		return sls_str;
	}
	public void setSls_str(int sls_str) {
		this.sls_str = sls_str;
	}
	public int getMax_num() {
		return max_num;
	}
	public void setMax_num(int max_num) {
		this.max_num = max_num;
	}
	public int getLow_price() {
		return low_price;
	}
	public void setLow_price(int low_price) {
		this.low_price = low_price;
	}
	public int getHigh_price() {
		return high_price;
	}
	public void setHigh_price(int high_price) {
		this.high_price = high_price;
	}
	public int getInit_price() {
		return init_price;
	}
	public void setInit_price(int init_price) {
		this.init_price = init_price;
	}
	public int getSd_tkt_price() {
		return sd_tkt_price;
	}
	public void setSd_tkt_price(int sd_tkt_price) {
		this.sd_tkt_price = sd_tkt_price;
	}
	public String getRg_hol() {
		return rg_hol;
	}
	public void setRg_hol(String rg_hol) {
		this.rg_hol = rg_hol;
	}
	public int getChld_dsc() {
		return chld_dsc;
	}
	public void setChld_dsc(int chld_dsc) {
		this.chld_dsc = chld_dsc;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public LocalDateTime getFac_reg() {
		return fac_reg;
	}
	public void setFac_reg(LocalDateTime fac_reg) {
		this.fac_reg = fac_reg;
	}
	public LocalDateTime getFac_mod() {
		return fac_mod;
	}
	public void setFac_mod(LocalDateTime fac_mod) {
		this.fac_mod = fac_mod;
	}


	//休館日を曜日名のリストでreturn
	public List<String> getRegularHolidays() {

		List<String> list = new ArrayList<>();

		if (this.rg_hol.contains("0")) {
			list.add("日");
		}

		if (this.rg_hol.contains("1")) {
			list.add("月");
		}

		if (this.rg_hol.contains("2")) {
			list.add("火");
		}

		if (this.rg_hol.contains("3")) {
			list.add("水");
		}

		if (this.rg_hol.contains("4")) {
			list.add("木");
		}

		if (this.rg_hol.contains("5")) {
			list.add("金");
		}

		if (this.rg_hol.contains("6")) {
			list.add("土");
		}

		return list;
	}



	//カテゴリをstringで返す
	public String getStrCategory() {

		String strCategory = "";

		if (this.category == 0) {
			strCategory = "温泉";
		} else if (this.category == 1) {
			strCategory = "寺・寺院";
		} else if (this.category == 2) {
			strCategory = "博物館";
		} else if (this.category == 3) {
			strCategory = "植物園";
		} else if (this.category == 4) {
			strCategory = "水族館";
		} else if (this.category == 5) {
			strCategory = "動物園";
		} else if (this.category == 6) {
			strCategory = "果樹園";
		} else if (this.category == 7) {
			strCategory = "レジャー";
		} else if (this.category == 8) {
			strCategory = "その他";
		}

		return strCategory;
	}


}

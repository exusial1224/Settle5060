package bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Slot implements Serializable {
	private int sl_id;
	private int fac_id;
	private LocalDate bus_date;
	private LocalTime start_time;
	private LocalTime end_time;
	private int sl_price;
	private int price_counter;
	private int num_adlt_tkt_sm;
	private int num_chld_tkt_sm;
	private int slot_max;


	public int getSl_id() {
		return sl_id;
	}
	public void setSl_id(int sl_id) {
		this.sl_id = sl_id;
	}
	public int getFac_id() {
		return fac_id;
	}
	public void setFac_id(int fac_id) {
		this.fac_id = fac_id;
	}
	public LocalDate getBus_date() {
		return bus_date;
	}
	public void setBus_date(LocalDate bus_date) {
		this.bus_date = bus_date;
	}
	public LocalTime getStart_time() {
		return start_time;
	}
	public void setStart_time(LocalTime start_time) {
		this.start_time = start_time;
	}
	public LocalTime getEnd_time() {
		return end_time;
	}
	public void setEnd_time(LocalTime end_time) {
		this.end_time = end_time;
	}
	public int getSl_price() {
		return sl_price;
	}
	public void setSl_price(int sl_price) {
		this.sl_price = sl_price;
	}
	public int getPrice_counter() {
		return price_counter;
	}
	public void setPrice_counter(int price_counter) {
		this.price_counter = price_counter;
	}
	public int getNum_adlt_tkt_sm() {
		return num_adlt_tkt_sm;
	}
	public void setNum_adlt_tkt_sm(int num_adlt_tkt_sm) {
		this.num_adlt_tkt_sm = num_adlt_tkt_sm;
	}
	public int getNum_chld_tkt_sm() {
		return num_chld_tkt_sm;
	}
	public void setNum_chld_tkt_sm(int num_chld_tkt_sm) {
		this.num_chld_tkt_sm = num_chld_tkt_sm;
	}
	public int getSlot_max() {
		return slot_max;
	}
	public void setSlot_max(int slot_max) {
		this.slot_max = slot_max;
	}


}

package bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class ResaleExp extends Resale implements Serializable {
	private String fac_name;
	private Date bus_date;
	private Time start_time;
	private Time end_time;


	public String getFac_name() {
		return fac_name;
	}
	public void setFac_name(String fac_name) {
		this.fac_name = fac_name;
	}
	public Date getBus_date() {
		return bus_date;
	}
	public void setBus_date(Date bus_date) {
		this.bus_date = bus_date;
	}
	public Time getStart_time() {
		return start_time;
	}
	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}
	public Time getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}



}

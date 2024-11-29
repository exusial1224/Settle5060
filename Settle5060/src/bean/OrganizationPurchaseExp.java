package bean;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class OrganizationPurchaseExp extends OrganizationPurchase implements Serializable {
	private Date bus_date;
	private Time start_time;
	private Time end_time;

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

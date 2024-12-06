package bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class ResaleExp extends Resale implements Serializable {
	private String fac_name;
	private LocalDate bus_date;
	private LocalTime start_time;
	private LocalTime end_time;


	public String getFac_name() {
		return fac_name;
	}
	public void setFac_name(String fac_name) {
		this.fac_name = fac_name;
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



}

package bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class PurchaseExp extends Purchase implements Serializable {

<<<<<<< HEAD
	private String mbr_name;
=======
>>>>>>> branch 'master' of https://github.com/exusial1224/Settle5060.git
	private int fac_id;
	private String fac_name;
	private LocalTime start_time;
	private LocalTime end_time;
	private LocalDate bus_date;

	public int getFac_id() {
        return fac_id;
    }

    public void setFac_id(int fac_id) {
        this.fac_id = fac_id;
    }

	public String getFac_name() {
		return fac_name;
	}

	public void setFac_name(String fac_name) {
		this.fac_name = fac_name;
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

	public LocalDate getBus_date() {
		return bus_date;
	}

	public void setBus_date(LocalDate bus_date) {
		this.bus_date = bus_date;
	}

	public String getMbr_name() {
		return mbr_name;
	}

	public void setMbr_name(String mbr_name) {
		this.mbr_name = mbr_name;
	}



}

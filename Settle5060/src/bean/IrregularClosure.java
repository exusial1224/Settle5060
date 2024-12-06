package bean;

import java.io.Serializable;
import java.time.LocalDate;

public class IrregularClosure implements Serializable {
	private int fac_id;
	private LocalDate irr_hol;


	public int getFac_id() {
		return fac_id;
	}
	public void setFac_id(int fac_id) {
		this.fac_id = fac_id;
	}
	public LocalDate getIrr_hol() {
		return irr_hol;
	}
	public void setIrr_hol(LocalDate irr_hol) {
		this.irr_hol = irr_hol;
	}


}

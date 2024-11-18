package bean;

import java.io.Serializable;

public class PurchaseExp extends Purchase implements Serializable {

	private String fac_name;

	public String getFac_name() {
		return fac_name;
	}

	public void setFac_name(String fac_name) {
		this.fac_name = fac_name;
	}

}

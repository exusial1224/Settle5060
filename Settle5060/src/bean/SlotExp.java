package bean;

import java.io.Serializable;

public class SlotExp extends Slot implements Serializable {
	private int remain;

	public int getRemain() {
		return remain;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}

}

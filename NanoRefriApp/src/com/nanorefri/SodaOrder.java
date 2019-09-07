package com.nanorefri;

import java.io.Serializable;

public class SodaOrder implements Serializable {
	
	String flavor, amount;

	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getFlavor() {
		return flavor;
	}
	
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

}

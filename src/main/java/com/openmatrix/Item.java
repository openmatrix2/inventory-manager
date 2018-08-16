package com.openmatrix;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	private String name;
	private int inventory;

	public int reduceInventory() {
		if (this.inventory > 50) {
			this.inventory = this.inventory - 10;
		} else {
			this.inventory = 500;
		}
		return this.inventory;
	}

}
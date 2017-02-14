package com.hcl.vo;

import com.hcl.model.ShopAddress;

public class ShopVO {
	private String shopName;
	private ShopAddress shopAddress;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public ShopAddress getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}
}

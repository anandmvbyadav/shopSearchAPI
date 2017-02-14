package com.hcl.model;

import com.hcl.model.googlehelper.Location;

public class ShopDetails {

	private String shopName;
	private ShopAddress shopAddress;
	private Location location;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

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

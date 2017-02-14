package com.hcl.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.hcl.exception.InsufficientInputException;
import com.hcl.exception.ResponseException;
import com.hcl.helper.GoogleResponseHelper;
import com.hcl.model.ShopDetails;
import com.hcl.model.googlehelper.GoogleResponse;
import com.hcl.vo.ShopVO;

@Repository
public class ShopRepository {

	private List<ShopDetails> shopList;

	private final double NEAREST_DISTANCE = 1.0;

	@PostConstruct
	public void init() {
		shopList = new ArrayList<ShopDetails>();
	}

	public void addShopDetails(ShopVO shopVO) {
		
		GoogleResponse response = null;

		if (shopVO.getShopName() == null | shopVO.getShopAddress().getPostCode() == null
				| shopVO.getShopAddress().getAddress() == null) {
			throw new InsufficientInputException("Please provdie all the Inputs");
		}
		response = GoogleResponseHelper.getLocationDetails(shopVO.getShopName() + " "
				+ shopVO.getShopAddress().getAddress() + " " + shopVO.getShopAddress().getPostCode());
		
		if (response.getStatus().equals("OK")) 
		{
			if(shopList==null){
				shopList = new ArrayList<ShopDetails>();
			}
			ShopDetails shop = new ShopDetails();
			shop.setShopName(shopVO.getShopName());
			shop.setShopAddress(shopVO.getShopAddress());
			shop.setLocation(response.getResults()[0].getGeometry().getLocation());
			shopList.add(shop);
			
		} 
		else if (response.getStatus().equals("ZERO_RESULTS")) 
		{
			throw new InsufficientInputException("There are no resut found for data");
		} 
		else 
		{
			throw new ResponseException("Unknown Error");
		}
	}

	public List<ShopDetails> getNearestShopList(Double latitude, Double longitude) {
		List<ShopDetails> shopLists = new ArrayList<ShopDetails>();
		if (latitude == null || longitude == null) {
			throw new InsufficientInputException("inputs are missing");
		}
		for (ShopDetails shop : shopList) {
			double distance = GoogleResponseHelper.getDistance(shop.getLocation().getLat(), shop.getLocation().getLng(),
					latitude, longitude);
			if (distance <= NEAREST_DISTANCE) {
				shopLists.add(shop);
			}
		}
		return shopLists;
	}

	public List<ShopDetails> getShopList() {
		return shopList;
	}

	public void setShopList(List<ShopDetails> shopList) {
		this.shopList = shopList;
	}
}
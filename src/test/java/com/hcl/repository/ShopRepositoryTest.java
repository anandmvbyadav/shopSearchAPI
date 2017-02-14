package com.hcl.repository;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hcl.ApplicationLauncher.ApplicationLauncher;
import com.hcl.exception.GoogleResponseException;
import com.hcl.exception.InsufficientInputException;
import com.hcl.model.ShopAddress;
import com.hcl.model.ShopDetails;
import com.hcl.vo.ShopVO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationLauncher.class)
@WebAppConfiguration
public class ShopRepositoryTest {

	//@Autowired
	private ShopRepository shopRepository;	
	
	@Before
	  public void setup() {
	    shopRepository = new ShopRepository();
	  }
	
	@Test
	public void testAddShopAddress() throws GoogleResponseException, InsufficientInputException{
		ShopVO shop1 = getShopDetails("Vijay Sales","plot no.09, survey no.48/2, prabhakar heights, below chate classes,near rupee bank, pune nagar road chandan nagar, kharadi pune, Pune, Maharashtra","411014");
		ShopVO shop2 = getShopDetails("Pizza Hut", "Ganga Fortune Society, Koregaon Park, Pune, Maharashtra","411001");
		shopRepository.addShopDetails(shop1);
		shopRepository.addShopDetails(shop2);

		Assert.assertEquals(shopRepository.getShopList().size(), 2);
	}
	
	@Test
	public void testGetNearestShopListWithOutNearestShops() throws IOException, GoogleResponseException, InsufficientInputException{
		ShopVO shop1 = getShopDetails("Vijay Sales","plot no.09, survey no.48/2, prabhakar heights, below chate classes,near rupee bank, pune nagar road chandan nagar, kharadi pune, Pune, Maharashtra","411014");
		ShopVO shop2 = getShopDetails("Pizza Hut", "Ganga Fortune Society, Koregaon Park, Pune, Maharashtra","411001");
		shopRepository.addShopDetails(shop1);
		shopRepository.addShopDetails(shop2);
		List<ShopDetails> shopList = shopRepository.getNearestShopList(19.0766818, 72.7331199);
		Assert.assertEquals(shopList.size(), 0);
	}
	
	@Test(expected=InsufficientInputException.class)
	public void testAddShopAddressWithException(){
		ShopVO shop1 = getShopDetails("Vijay Sales", null, "411007");
		shopRepository.addShopDetails(shop1);
	}
	
	private ShopVO getShopDetails(String shopName,String shopAddress,String postalCode){
		ShopVO shopVO = new ShopVO();
		shopVO.setShopName(shopName);
		ShopAddress address  = new ShopAddress();
		address.setAddress(shopAddress);
		
		address.setPostCode(postalCode);
		shopVO.setShopAddress(address);
		shopVO.setShopAddress(address);
		return shopVO;
	}
}

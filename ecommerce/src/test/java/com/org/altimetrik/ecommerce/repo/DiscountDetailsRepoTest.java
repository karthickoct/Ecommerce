package com.org.altimetrik.ecommerce.repo;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.org.altimetrik.ecommerce.EcommerceApplication;
import com.org.altimetrik.ecommerce.model.DiscountDetails;
import com.org.altimetrik.ecommerce.model.GlobalEntity;
import com.org.altimetrik.ecommerce.repo.DiscountDetailsRepo;
import com.org.altimetrik.ecommerce.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplication.class)
public class DiscountDetailsRepoTest {

	private List<DiscountDetails> discountDetailsList = new ArrayList<>();
	
	@Autowired
	private DiscountDetailsRepo discountDetailsRepo;
	@Before
	public void setUpData() throws ParseException {
		Date defaultDate = new SimpleDateFormat("yyyy/MM/dd").parse(Constants.defaultDate);
		Date currentDate = new Date();
		
		GlobalEntity globalEntity = new GlobalEntity();
		globalEntity.createdBy = "System";
		globalEntity.createdOn = currentDate;
		globalEntity.modifiedBy = "";
		globalEntity.modifiedOn = defaultDate;

		DiscountDetails discountDetails = new DiscountDetails();
		discountDetails.discountLimit = 1500;
		discountDetails.discountPercentage = 15;
		discountDetails.globalEntity = globalEntity;
		discountDetailsList.add(discountDetails);
		
		discountDetails = new DiscountDetails();
		discountDetails.discountLimit = 2000;
		discountDetails.discountPercentage = 20;
		discountDetails.globalEntity = globalEntity;
		discountDetailsList.add(discountDetails);
	}
	
	@Test
	public void addDiscountDetails() {
		discountDetailsRepo.saveAll(discountDetailsList);
		assertNotNull(discountDetailsList);
	}
}

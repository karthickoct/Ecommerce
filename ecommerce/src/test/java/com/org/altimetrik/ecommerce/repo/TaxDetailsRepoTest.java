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
import com.org.altimetrik.ecommerce.model.GlobalEntity;
import com.org.altimetrik.ecommerce.model.TaxDetails;
import com.org.altimetrik.ecommerce.repo.TaxDetailsRepo;
import com.org.altimetrik.ecommerce.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EcommerceApplication.class)
public class TaxDetailsRepoTest {

	private List<TaxDetails> taxDetailsList = new ArrayList<>();
	
	@Autowired
	private TaxDetailsRepo taxDetailsRepo; 
	@Before
	public void setUpData() throws ParseException {
		Date defaultDate = new SimpleDateFormat("yyyy/MM/dd").parse(Constants.defaultDate);
		Date currentDate = new Date();
		
		GlobalEntity globalEntity = new GlobalEntity();
		globalEntity.createdBy = "System";
		globalEntity.createdOn = currentDate;
		globalEntity.modifiedBy = "";
		globalEntity.modifiedOn = defaultDate;
		
		TaxDetails taxDetails = new TaxDetails();
		taxDetails.taxType = "SALE_TAX";
		taxDetails.taxDescription = "Sale Tax as per Government Norms";
		taxDetails.taxPercentage = 7;
		taxDetails.globalEntity = globalEntity;
		taxDetailsList.add(taxDetails);
		
		taxDetails = new TaxDetails();
		taxDetails.taxType = "IMPORT_TAX";
		taxDetails.taxDescription = "Import Tax as per Government Norms";
		taxDetails.taxPercentage = 5;
		taxDetails.globalEntity = globalEntity;
		taxDetailsList.add(taxDetails);
	}
	
	@Test
	public void addTaxDetails() {
		taxDetailsRepo.saveAll(taxDetailsList);
		assertNotNull(taxDetailsList);
	}
}

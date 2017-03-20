package com.driveu.zzdriveuautomation;

import java.util.HashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import backgroundutils.PropertiesFileReader;
import uiobjects.HulkObject;

public class AutomationTest {

	WebDriver webdriver;
	static String bookingTriptype;
	public static HashMap<String, String> hashmap= new HashMap<String, String>();

	@BeforeTest
	public void initailizePropertiesFile() throws ConfigurationException {
		PropertiesFileReader.readPropertiesFile();
	}
	
	@Test(dataProvider = "getcollectfareData")
	public void hulkTest(String triptype,String gpsmode) throws ConfigurationException{

		System.out.println("Triptype ::" + triptype);
		System.out.println("Gpsmode ::" + gpsmode);
		
		triptype(triptype);
		
		HulkObject hulkobject = new HulkObject(webdriver);
		hulkobject.bookingassigndriver(bookingTriptype);
		
	}
	
	public void triptype(String triptype) {
		try {
			if (triptype.equals("One Way Trip")) {
				bookingTriptype = triptype.substring(0, 7);
				System.out.println(bookingTriptype);
			} else {
				bookingTriptype = triptype;
				System.out.println(bookingTriptype);
			}
		} catch (Exception e) {
				System.out.println("Trip type:" + e.toString());
		}
	}
	
	
	@DataProvider
	public Object[][] getcollectfareData() {
		// Rows - Number of times the tests has to be repeated.
		// Columns - Number of parameters in test data.
		Object[][] collectfaredata = new Object[1][2];

		// 1st row
		collectfaredata[0][0] = "One Way Trip";
		collectfaredata[0][1] = "High Accuracy";

		// 2nd row
//		collectfaredata[1][0] = "Round Trip";
//		collectfaredata[1][1] = "Battery Saving";
//
//		// 3rd row
//		collectfaredata[2][0] = "Outstation";
//		collectfaredata[2][1] = "Device Only";

		return collectfaredata;
	}
}

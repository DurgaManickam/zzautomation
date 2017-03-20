package backgroundutils;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


public class PropertiesFileReader {
	static String driverMobile, driverPasscode;
	static String emailId, emailPass,hulkUrl;
	static String customerMobile, customerName;
	static String pickupAddress, dropOneAddress, tripType;

	public static PropertiesConfiguration propertiesConfig;

	public static void readPropertiesFile() throws ConfigurationException{
		File propertyfile = new File("/Users/durgadevi/Documents/workspace/zzdriveuautomation/config/app_values.properties");
		
		
		propertiesConfig = new PropertiesConfiguration(propertyfile);

		driverMobile = (String) propertiesConfig.getProperty("driver_mobile");
		driverPasscode = (String) propertiesConfig.getProperty("driver_passcode");
		emailId = (String) propertiesConfig.getProperty("email_id");
		emailPass = (String) propertiesConfig.getProperty("email_passcode");
		hulkUrl =(String) propertiesConfig.getProperty("hulk_url");
		customerMobile = (String) propertiesConfig.getProperty("customer_mobile");
		customerName = (String) propertiesConfig.getProperty("customer_name");
		pickupAddress = (String) propertiesConfig.getProperty("pickup_address_one");
		dropOneAddress = (String) propertiesConfig.getProperty("drop_address_one");
		tripType = (String) propertiesConfig.getProperty("trip_type");

		System.out.println(driverMobile);
		System.out.println(driverPasscode);
		System.out.println(customerMobile);
	}

	public static PropertiesConfiguration getPropertiesConfig() {
		return propertiesConfig;
	}

	public static String getDriverMobile() {
		return driverMobile;
	}

	public static String getDriverPasscode() {
		return driverPasscode;
	}
	
	public static String getEmailId() {
		return emailId;
	}

	public static String getEmailPass() {
		return emailPass;
	}

	public static String getHulkUrl() {
		return hulkUrl;
	}

	public static String getCustomerMobile() {
		return customerMobile;
	}

	public static String getCustomerName() {
		return customerName;
	}

	public static String getPickupAddress() {
		return pickupAddress;
	}

	public static String getDropOneAddress() {
		return dropOneAddress;
	}
	
	public static String getTripType() {
		return tripType;
	}

}

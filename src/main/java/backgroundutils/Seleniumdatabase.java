package backgroundutils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Logger;

import com.driveu.zzdriveuautomation.AutomationTest;

import uiobjects.HulkObject;

public class Seleniumdatabase {

	private Connection connection;
	private static Statement statement;
	private static ResultSet rs;
	String BookingId;
	String tripamount;
	private static final Logger LOGGER = Logger.getLogger(Seleniumdatabase.class.getName());

	public void setUp() {
		String databaseURL = "jdbc:mysql://176.9.18.111:3306/driveu";
		String user = "driveu";
		String password = "driveu123@";
		connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to Database...");
			connection = DriverManager.getConnection(databaseURL, user, password);
			if (connection != null) {
				System.out.println("Connected to the Database...");
			}
		} catch (SQLException ex) {
			LOGGER.info("SQLException:" + ex.toString());
		} catch (ClassNotFoundException ex) {
			LOGGER.info("ClassNotFoundException:" + ex.toString());
		}
	}

	public String getAppBookingData() {
		//System.out.println("Deviceeee:::" + deviceName);

		
			try {
				String query = "select id,booking_id,status from app_booking where customer_mobile="+PropertiesFileReader.getCustomerMobile() +" and status=0";

				System.out.println(query);
				statement = connection.createStatement();
				rs = statement.executeQuery(query);
				while (rs.next()) {
					BookingId = rs.getString("booking_id");
					System.out.println("Booking Id in getAppBookingDb if block::" + BookingId);
					String queryone = "update app_booking set status=1 where booking_id=" + "\"" + BookingId + "\"";
					System.out.print(queryone);
					statement.executeUpdate(queryone);
					//AutomationTest.hashmap.put(deviceName, BookingId);

				}
			} catch (SQLException ex) {
				LOGGER.info("SQLException:" + ex.toString());
			}
			return BookingId;
		}
		
//	public HashMap<String, String> getDriverBookingHashMap() {
//		System.out.println("getDriverBookingHashMap:" + AutomationTest.hashmap);
//		return AutomationTest.hashmap;
//
//	}
	
}

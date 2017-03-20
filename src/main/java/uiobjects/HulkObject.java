package uiobjects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import backgroundutils.PropertiesFileReader;
import backgroundutils.Seleniumdatabase;

public class HulkObject {

	WebDriver driver;
	static String bookingid;
	String fareamount;
	private static final Logger LOGGER = Logger.getLogger(HulkObject.class.getName());
	static Seleniumdatabase seldb;

	public HulkObject(WebDriver driver) throws ConfigurationException{
		this.driver = driver;

	}

	public void openhulk() {
		driver = new FirefoxDriver();
		driver.get(PropertiesFileReader.getHulkUrl());
		driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
	}

	public void loginauthen() {
		System.out.println(PropertiesFileReader.getEmailId());
		System.out.println(PropertiesFileReader.getEmailPass());
		driver.findElement(By.xpath("//a[@href='/login/google-oauth2/']")).click();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(PropertiesFileReader.getEmailId());
		driver.findElement(By.xpath("//input[@id='next']")).click();
		driver.findElement(By.xpath("//input[@id='Passwd']")).sendKeys(PropertiesFileReader.getEmailPass());
		driver.findElement(By.xpath("//input[@id='PersistentCookie']")).click();
		driver.findElement(By.xpath("//input[@id='signIn']")).click();
	}

	public void booking_pickup_address() {
		WebElement address = driver.findElement(By.xpath("//input[@id='id_booking-pickup_address']"));
		address.sendKeys(PropertiesFileReader.getPickupAddress());
		List<WebElement> addresslist = driver.findElements(By.xpath("//div[@class='pac-item']"));
		addresslist.get(1).click();
	}

	public void booking_pickup_datetime() {
		// driver.findElement(By.xpath("//input[@id='id_booking-pickup_datetime']")).click();

		DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date(System.currentTimeMillis() + 10 * 60 * 1000);
		String newdatetime = dateformat.format(date);
		System.out.println(newdatetime);

		WebElement datetime = driver.findElement(By.xpath("//input[@id='id_booking-pickup_datetime']"));
		datetime.sendKeys(newdatetime);
		System.out.println(newdatetime);
		datetime.sendKeys(Keys.TAB);
	}

	public void booking_userdetails() {
		
		WebElement loginmobile = driver.findElement(By.xpath("//input[@id='id_login-mobile']"));
		loginmobile.sendKeys(PropertiesFileReader.getCustomerMobile());
		loginmobile.sendKeys(Keys.TAB);

	}

	public void booking_other_spec() {
		WebElement logininvitecode = driver.findElement(By.xpath("//input[@id='id_login-invite_code']"));
		logininvitecode.sendKeys(Keys.TAB);

		WebElement bookingins = driver.findElement(By.xpath("//input[@id='id_booking-instructions']"));
		bookingins.sendKeys(Keys.TAB);

		WebElement foundus = driver.findElement(By.name("booking-found_us_through"));
		Select sefoundus = new Select(foundus);
		sefoundus.selectByIndex(1);
		foundus.sendKeys(Keys.TAB);
	}

	public void booking_booking_car_type(String bookingTriptype) {
		WebElement booking_bookingtype = driver.findElement(By.xpath("//select[@id='id_booking-booking_type']"));
		Select sebookingbookingtype = new Select(booking_bookingtype);
		sebookingbookingtype.selectByVisibleText(bookingTriptype);
		System.out.println("Book:" + bookingTriptype);

		try {
			if (bookingTriptype.equals("One Way")) {
				WebElement addressdrop = driver.findElement(By.xpath("//input[@id='id_booking-drop_address']"));
				addressdrop.sendKeys(PropertiesFileReader.getDropOneAddress());
				driver.findElement(By.xpath("/html/body/div[4]/div[2]")).click();
			} else if (bookingTriptype.equals("Round Trip")) {
				WebElement estimatehours = driver
						.findElement(By.xpath("//input[@id='id_booking-estimated_trip_hours']"));
				estimatehours.sendKeys("2");
			} else {
				WebElement addressdropout = driver.findElement(By.xpath("//input[@id='id_booking-drop_address']"));
				addressdropout.sendKeys("Tirupur");
				driver.findElement(By.xpath("/html/body/div[4]/div[3]")).click();
			}

		} catch (Exception e) {
			LOGGER.info("Hulk Object Booking_Booking_car_type:" + e.toString());
		}

		WebElement booking_cartype = driver.findElement(By.xpath("//select[@id='id_booking-car_type']"));
		Select sebookingcartype = new Select(booking_cartype);
		sebookingcartype.selectByIndex(6);
		booking_cartype.sendKeys(Keys.TAB);
	}

	public void request_driver() {
		driver.findElement(By.xpath("//button[@id='create_booking']")).click();

		driver.findElement(By.xpath("//a[text()='Select Action']")).click();
		driver.findElement(By.xpath("//a[text()='Request Driver']")).click();

		driver.findElement(By.xpath("//strong[text()='Booking']")).click();
		driver.findElement(By.xpath("//a[@href='/abs/driver-requests/']")).click();
	}

	public void assign_driver(String bookingTriptype) {

		driver.findElement(By.xpath("//a[text()='Select Action']")).click();
		driver.findElement(By.xpath("//a[text()='Assign driver']")).click();
		driver.findElement(By.xpath("//span[@class='input-group-addon dropdown-toggle']")).click();
		// if(bookingTriptype.equals("One Way")){
		System.out.println("oneeee" + bookingTriptype);
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("9");
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("1");
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("2");
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("3");
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("4");
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("6");
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("7");
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("8");
		driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("0");
		// }
		// else if(bookingTriptype.equals("Round Trip")){
		// System.out.println("round"+bookingTriptype);
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("9");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("0");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("9");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("0");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("9");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("0");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("9");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("0");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("9");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("9");
		// }
		// else{
		// System.out.println("out"+bookingTriptype);
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("9");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("7");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("8");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("7");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("5");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("4");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("4");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("5");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("6");
		// driver.findElement(By.xpath("//input[@class='form-control']")).sendKeys("1");
		//
		// }
		driver.findElement(By.xpath("//li[@class='active']")).click();
		driver.findElement(By.xpath("//button[text()='Assign']")).click();

	}

	public void create_booking(String bookingTriptype) {
		openhulk();// opens the hulk
		loginauthen();// google login authentication

		driver.findElement(By.xpath("//strong[text()='Booking']")).click();
		driver.findElement(By.xpath("//a[@href='/app/create-booking/']")).click();

		booking_pickup_address();
		booking_pickup_datetime();
		booking_userdetails();
		booking_other_spec();// login invite code,booking ins,found us through
		booking_booking_car_type(bookingTriptype);// booking and car type

		request_driver();

	}

	public void bookingassigndriver(String bookingTriptype) {

		create_booking(bookingTriptype);// booking creation

		seldb = new Seleniumdatabase();
		seldb.setUp();
		bookingid = seldb.getAppBookingData();

		driver.findElement(By.xpath("//input[@class='form-control search-go']")).sendKeys(bookingid);
		driver.findElement(By.xpath("//button[@class='btn btn-booking-status btn-danger']")).click();

		assign_driver(bookingTriptype);// assigning the driver

		driver.findElement(By.xpath("//a[@href='/accounts/logout/']")).click();
		driver.close();

	}

}

package com.weborder;

import java.awt.List;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class Order {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/rismayilov/Documents/selenium dependencies/drivers/chromedriver.exe");
		
//		1.Open browser
//		2.Go to url http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx (Links to an external site.)Links to an external site.
//		3.Login using username Tester and password test
//		4.Click on Order link
//		5.Enter a random quantity between 1 and 100
//		6.Enter Customer Name: John <middle_name> Smith. Instead of <middle_name> your code should enter a random string every time.
//		7. Enter street: 123 Any st
//		8. Enter City: Anytown
//		9. Enter State: Virginia
//		10.Enter a random 5 digit number to the zip code field
//		11.Select any card type. Every time your code should select a different type.
//		12. Enter any card number. If you selected Visa, card number should start with 4. If you selected Master, card number should start with 5. If you selected American Express, card number should start with 3. New card number should be auto generated every time you run the test. Card numbers should be 16 digits for Visa and Master, 15 for American Express.
//		13. Enter any valid expiration date 
//		14. Click on Process
//		Verify than the page contains text New order has been successfully added.
	
		WebDriver driver = new ChromeDriver();
		 driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/Process.aspx");
		 driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("tester");
		 driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		 driver.findElement(By.id("ctl00_MainContent_login_button")).click();
		 driver.findElement(By.linkText("Order")).click();
		 driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")).click();
		 driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),Random_num());
		 driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),"John "+randomIdentifier()+" Smith");
		 driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys("124 Home Street");
		 driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys("Houston");
		 driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys("TX");
		 Thread.sleep(1000);
		 driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(Keys.HOME,Keys.chord(Keys.SHIFT,Keys.END),zipCode());
		 int cardType= getRandomInteger(1,3);
		 driver.findElement(By.xpath("(//input[@name='ctl00$MainContent$fmwOrder$cardList'])["+cardType+"]")).click();
		 Thread.sleep(1000);
		 
		 switch(cardType) {
			case 1: driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("4"+randomCard(16) ); break; 
			case 2: driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("5"+randomCard(16)  ); break;
			case 3: driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys("3"+randomCard(15)  ); break;
			}
		 Thread.sleep(2000);
		 driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys("10/21");
		 Thread.sleep(2000);
		 driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		 Thread.sleep(2000);
		 if( driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder > tbody > tr > td > div > strong")).isDisplayed()){
				System.out.println("Verified");
				//*[@id="ctl00_MainContent_fmwOrder"]/tbody/tr/td/div/strong
		}else{
				System.out.println("unVerified");
		}
		 
		 
		// driver.close();
		 
	}
	public static String Random_num() {
		Random rn = new Random();
		int random = rn.nextInt(100);
		String run_num = Integer.toString(random);
		return run_num;
	}
	public static String randomIdentifier() {
	
	final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	Random rn = new Random();
	int random1 = rn.nextInt(24);
	
    
    return lexicon.charAt(random1)+"";
	}
    
	public static String zipCode() {
		
		Random rn = new Random();
		int random = rn.nextInt(10);
		int randomfirst=rn.nextInt(9)+1; 
		String zipcode=""+randomfirst+random+random+random+random; 
		return  zipcode; 
			
	}
	
	
	private static int getRandomInteger(int min, int max){
	    return ThreadLocalRandom.current().nextInt(min, max+1);
	}
	
	public static String randomCard(int numbers)
	 {
	 String CreditCardNumber ="";
	 int nums = numbers;
	 Random num = new Random();
	 for (int i = 0; i < nums; i++)
	 {
	 CreditCardNumber = num.nextInt(9) + CreditCardNumber;
	 }

	return CreditCardNumber;
	 }
		 
 

	}


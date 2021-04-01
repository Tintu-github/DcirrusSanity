package main.java.com.dcirrus.login;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class download 
{
		 
	WebDriver driver;
	 @BeforeClass
	   @Parameters({"URL","id","password","corporateID"})
	   public void setUp(String URL, String id,String password,String corporateID)
	   {
	     
	  	 driver=new ChromeDriver();
	  	 driver.manage().window().maximize();
	  	 driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	  	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  	// driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 
		 driver.manage().deleteAllCookies();
	  	 driver.get(URL);
	  	 driver.findElement(By.linkText("Business User")).click();
		 driver.findElement(By.id("loginid")).sendKeys(id);
		 driver.findElement(By.id("password")).sendKeys(password);
		 driver.findElement(By.id("corporateid")).sendKeys(corporateID);
		 String capchaval=JOptionPane.showInputDialog("Please enter the capcha value");
		 driver.findElement(By.id("logincaptcha")).sendKeys(capchaval);
		 driver.findElement(By.id("btnlogin")).click();
	   }
	 
	 @AfterClass
	   public void tearDown() throws InterruptedException
	   {
		 
		   driver.quit();

	   }
	 
	     @DataProvider(name="dataproviderfiles")
	     public Object[] dataprovider() 
	     {

	    	 String fName = System.getProperty("FILENAME");
	    	 String cSeparator = System.getProperty("COMMONSEPARATOR");
	    	 String thisLine;
	    	 FileInputStream fis = null;
	    	 BufferedReader myInput = null;
	    	 Object[] retValue = null;
	    	 try
	    	 {
	    		 fis = new FileInputStream(fName);
	    		 myInput = new BufferedReader(new InputStreamReader(fis));
	    		 List<String[]> lines = new ArrayList<String[]>();
	    		 while ((thisLine = myInput.readLine()) != null) {
	    			 lines.add(thisLine.split(cSeparator));
	    		 }
	    		 // convert our list to a String array.
	    		 retValue = new String[lines.size()][0];
	    		 lines.toArray(retValue);
	    	 }
	    	 catch(Exception ex)
	    	 {
	    		 ex.printStackTrace();
	    	 }
	    	 finally
	    	 {
	    		 if(myInput!=null)
	    			 try {
	    				 myInput.close();
	    			 } catch (IOException e) {
	    				 
	    				 e.printStackTrace();
	    			 }
	    	 }
	    	 return retValue;


	     } 
	  
	  @Test(dataProvider = "dataproviderfiles")
	  public void createNewFolder(String name) throws InterruptedException         
	   {
		    driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click(); 
		    driver.findElement(By.id("btncreatefolder")).click();
		    driver.findElement(By.id("admfoldername")).sendKeys(name);
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[@id='btncreatefolderdone']")).click();
		    WebDriverWait wait2=new WebDriverWait(driver,20);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		    String txt= driver.findElement(By.className("top-alert-message")).getText();
		    System.out.println(txt);
		    WebDriverWait wait3=new WebDriverWait(driver,20);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-close")));
		   
		    driver.findElement(By.className("top-alert-close")).click();
          
           Thread.sleep(3000);
	   }
	  
	  @Test(dataProvider = "dataproviderfiles")
	   public void deleteFolder(String name) throws InterruptedException
	   {
		   driver.get("https://dcirrus.co.in/appnew/drive.html");
		   driver.findElement(By.xpath("//*[@data-foldername='"+name+"']/div[7]/ul/li/img")).click();
			  
		   driver.findElement(By.linkText("Delete")).click();
		   Thread.sleep(4000);
		   
	       driver.findElement(By.xpath("//*[@data-foldername='"+name+"']/div[7]/ul/li/img")).click();
	       driver.findElement(By.linkText("Delete Permanently")).click();
	       driver.findElement(By.xpath("//*[@id='btndeleteconfirmdone']")).click();
	       Thread.sleep(5000);
	       System.out.println("deleteFolder executed");
	  }
}

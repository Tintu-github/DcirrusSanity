package main.java.com.dcirrus.login;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class upload 
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
	 
	 @Test(invocationCount =200)
     @Parameters({"foldername1","URL"})
	  public void uploadFolder(String foldername1, String URL) throws InterruptedException, IOException
	  {  
		  //  driver.get(URL);
   	        driver.findElement(By.xpath("//*[@data-foldername='"+foldername1+"']/h2/a")).click();
   	        Thread.sleep(10000);
		 
		    driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click();
		    Thread.sleep(2000);
		
		    driver.findElement(By.xpath("//*[@id='btnuploadfolder']")).click();
		    WebDriverWait wait1=new WebDriverWait(driver,20);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("adm_fileuploadId")));
		
		    driver.switchTo().frame("adm_fileuploadId");
			 
		    WebElement folderInput = driver.findElement(By.id("uploadFolderId"));
			  
			 folderInput.sendKeys(System.getProperty("user.dir") + File.pathSeparator + "main/java/config");
	
			  driver.switchTo().parentFrame();
			  WebDriverWait wait2=new WebDriverWait(driver,20);
			  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
			  
			  String txt= driver.findElement(By.className("top-alert-message")).getText();
			  System.out.println(txt);
			  Assert.assertEquals(txt,"Files uploaded. Processing the update..");
			  System.out.println("uploadFolder executed");
		   
		  } 
	 
	  @Test(dependsOnMethods = { "uploadFolder" })
	   @Parameters({"URL","foldername1","nameFolder"})
	   public void deleteFolder(String URL,String foldername1,String nameFolder) throws InterruptedException
	   {
		 driver.get(URL);
		 driver.findElement(By.xpath("//*[@data-foldername='"+foldername1+"']/h2/a")).click();  
		 driver.findElement(By.xpath("//*[@data-foldername='"+nameFolder+"']/div[7]/ul/li/img")).click();
		 driver.findElement(By.linkText("Delete")).click();
	     
	       Thread.sleep(5000);
	       driver.findElement(By.xpath("//*[@data-foldername='"+nameFolder+"']/div[7]/ul/li/img")).click();
	       driver.findElement(By.linkText("Delete Permanently")).click();
	       driver.findElement(By.xpath("//*[@id='btndeleteconfirmdone']/div/label/p")).click();
	       
	       Thread.sleep(6000);
	  }
}



package main.java.com.dcirrus.login;

import java.util.List ;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.io.File;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import main.java.com.dcirrus.login.UserModule.SwitchCurrentWindow;

	public class ViewPermissions 
	{  
		public static  WebDriver driver;
		public Random random = new Random();
		public int randomValue1 = 0 ;
		public int randomValue2 = 0 ;
		public int randommail = 0;
		public int randomValue3 = 0 ;
		public int randomValue4 = 0 ;
		public int randomValue5 = 0 ;
		public int randomValue6 = 0 ;
		
		static class SwitchCurrentWindow
		{

			static void GetNewWindow()
			{
				for(String windowName: driver.getWindowHandles()) 
				{

					driver.switchTo().window(windowName);
				}
			}

		}
		
			
		

	   @BeforeClass
	   @Parameters({"URL","id","password","corporateID"})
	   public void setUp(String URL, String id,String password,String corporateID) throws InterruptedException
	   {
	  	 driver=new ChromeDriver();
	  	 driver.manage().window().maximize();
	  	 driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	  	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  	// driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 
		 //driver.manage().deleteAllCookies();
	  	 driver.get(URL);
	  	 driver.findElement(By.linkText("Business User")).click();
		 driver.findElement(By.id("loginid")).sendKeys(id);
		 driver.findElement(By.id("password")).sendKeys(password);
		 driver.findElement(By.id("corporateid")).sendKeys(corporateID);
		 String capchaval=JOptionPane.showInputDialog("Please enter the capcha value");
		 driver.findElement(By.id("logincaptcha")).sendKeys(capchaval);
		 driver.findElement(By.id("btnlogin")).click();
		 Thread.sleep(30000);
	   }
	   

	   
      @AfterClass
	   @Parameters({"URL"})
	   public void tearDown(String URL) throws InterruptedException
	   {
		  
		  driver.quit();

	   }
      
      public void cpDirectory(String sourceDir, String destinationDir) {
    	  try {
    		    // source & destination directories
    		    File src = new File(sourceDir);
    		    File dest = new File(destinationDir);

    		    // copy all files and folders from `src` to `dest`
    		    FileUtils.copyDirectory(src, dest);
    		        
    		} catch (IOException ex) {
    		    ex.printStackTrace();
    		}
      }
//    
//      @Test(priority=3, groups="createFolder")
//	  @Parameters({"URL","folder1"})
//	  public void newRootCreateFolder(String URL, String folder1) throws InterruptedException
//	  {
//		    driver.get(URL);
//			Thread.sleep(2000);
//			this.randomValue1 = 1 + random.nextInt(1000000000);
//			WebDriverWait wait1=new WebDriverWait(driver,20);
//			WebDriverWait wait3=new WebDriverWait(driver,20);
//			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'DCirrus Sync')]")));
//			driver.findElement(By.xpath("//a[contains(text(),'DCirrus Sync')]")).click();
//			Thread.sleep(10000);
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncreatefolderpopup")));
//			driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();;
//			Thread.sleep(2000);
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Sorry, you do not have right to perform the action. Please contact your administrator')]")));
//			try{
//				driver.findElement(By.xpath("//span[contains(text(), 'Sorry, you do not have right to perform the action. Please contact your administrator')]")).getText();
//			    Assert.assertEquals(true, true);
//			} catch( Exception e ) {
//				Assert.assertEquals(false, true);
//			}
//			Thread.sleep(10000);
//			
//	  }
//      
//
//	
//	 @Test(priority=4, groups="createFolder")
//	  @Parameters({"URL","folder1"})
//	  public void newSubCreateFolder(String URL, String folder1) throws InterruptedException
//	  {
//		    driver.get(URL);
//			Thread.sleep(2000);
//			this.randomValue1 = 1 + random.nextInt(1000000000);
//			WebDriverWait wait3=new WebDriverWait(driver,20);
//			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'DCirrus Sync')]")));
//			driver.findElement(By.xpath("//a[contains(text(),'DCirrus Sync')]")).click();
//			Thread.sleep(10000);
//			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'SYNC1')]")));
//			driver.findElement(By.xpath("//a[contains(text(),'SYNC1')]")).click();
//			Thread.sleep(10000);
//			WebDriverWait wait1=new WebDriverWait(driver,20);
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncreatefolderpopup")));
//			driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
//			Thread.sleep(2000);
//			  WebDriverWait wait2=new WebDriverWait(driver,20);
//			  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btncreatefolder']")));
//			  driver.findElement(By.id("btncreatefolder")).click();
//			  Thread.sleep(2000);
//			  WebDriverWait wait12=new WebDriverWait(driver,20);
//			  wait12.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='admfoldername']")));
//			  driver.findElement(By.id("admfoldername")).sendKeys(folder1 + this.randomValue1);
//			  Thread.sleep(2000);
//			  WebDriverWait wait13=new WebDriverWait(driver,20);
//			  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='ADD']")));
//			  driver.findElement(By.xpath("//button[text()='ADD']")).click();
//			  Thread.sleep(10000);
//			
//	  } 
//	 
//	 @Test(priority=5, groups="createFolder")
//	   @Parameters({"URL", "folder1"})
//	   public void newsubFolderPermissions(String URL, String folder1) throws InterruptedException         //Software folder is created at 3rd
//	   {  
//		   driver.get(URL);
//	       JavascriptExecutor js = (JavascriptExecutor) driver;
//	       driver.findElement(By.xpath("//a[@title = 'Admin and User Permissions']")).click();
//	       Thread.sleep(2000);
//	       driver.findElement(By.xpath("//a[@id = 'permission_module']/i")).click();
//	       Thread.sleep(2000);	
//	       SwitchCurrentWindow.GetNewWindow();
//	       Thread.sleep(2000);	
//	       driver.findElement(By.xpath("//li[contains(text(),'DCirrus Sync')]")).click();
//	       Thread.sleep(2000);
//	       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//	       Thread.sleep(2000);	
//	       boolean selected = driver.findElement(By.xpath("//input[@title='Raap Raap']")).isSelected();	
//	       Thread.sleep(2000);
//           System.out.println(selected);
//           Assert.assertEquals(selected, true);
//		   for(int j = 3 ; j<=10 ; j++) {
//      		   selected = driver.findElement(By.xpath("//input[@title='Raap Raap']//..//..//..//td["+ j + "]//div//input")).isSelected();		       
//                 System.out.println(selected);
//  		       Thread.sleep(1000);
//  		       if(j == 3)
//  		       Assert.assertEquals(selected, true);
//  		       else
//  		    	 Assert.assertEquals(selected, false);   
//      	   }
//   	  try {
//   		 driver.findElement(By.xpath("//li[contains(text(),'SYNC1')]")).click();
//      	   } catch(Exception e) {          		 
//      		   driver.findElement(By.xpath("//li[contains(text(),'SYNC1')]")).sendKeys(Keys.PAGE_DOWN);
//      		   Thread.sleep(2000);
//      		   js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//li[contains(text(),'SYNC1')]")));
//      		   Thread.sleep(5000);
//      		   driver.findElement(By.xpath("//li[contains(text(),'SYNC1')]")).click();
//      	   }
//		       Thread.sleep(2000);
//		       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//		       Thread.sleep(2000);
//		       selected = driver.findElement(By.xpath("//input[@title='Raap Raap']")).isSelected();	
//		       Thread.sleep(2000);
//              System.out.println(selected);
//              Assert.assertEquals(selected, true);
//      	   for(int j = 3 ; j<=10 ; j++) {
//      		   selected = driver.findElement(By.xpath("//input[@title='Raap Raap']//..//..//..//td[" + j + "]//div//input")).isSelected();		       
//                 System.out.println(selected);
//  		       Thread.sleep(1000);
//  		       if(j ==3 || j == 4 || j == 7)
//  		       Assert.assertEquals(selected, true);
//  		       else
//  		    	 Assert.assertEquals(selected, false);   
//      	   }
//      	   driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//      	   Thread.sleep(1000);
//      	 try {
//       		 driver.findElement(By.xpath("//li[contains(text(),'" + folder1 + this.randomValue1 + "')]")).click();
//          	   } catch(Exception e) {          		 
//          		   driver.findElement(By.xpath("//li[contains(text(),'" + folder1 + this.randomValue1 + "')]")).sendKeys(Keys.PAGE_DOWN);
//          		   Thread.sleep(2000);
//          		   js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//li[contains(text(),'" + folder1 + this.randomValue1 + "')]")));
//          		   Thread.sleep(5000);
//          		   driver.findElement(By.xpath("//li[contains(text(),'" + folder1 + this.randomValue1 + "')]")).click();
//          	   }
//    		       Thread.sleep(2000);
//    		       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//    		       Thread.sleep(2000);
//    		       selected = driver.findElement(By.xpath("//input[@title='Raap Raap']")).isSelected();	
//    		       Thread.sleep(2000);
//                  System.out.println(selected);
//                  Assert.assertEquals(selected, true);
//          	   for(int j = 3 ; j<=10 ; j++) {
//          		   selected = driver.findElement(By.xpath("//input[@title='Raap Raap']//..//..//..//td[" + j + "]//div//input")).isSelected();		       
//                     System.out.println(selected);
//      		       Thread.sleep(1000);
//      		       if(j ==3 || j == 4 || j == 7)
//      		       Assert.assertEquals(selected, true);
//      		       else
//      		    	 Assert.assertEquals(selected, false);   
//          	   }
//          	   driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//          	   Thread.sleep(1000);
//}
//		   
//	   
//	
//	@Test(priority=6, groups="uploadFolder")
//	  @Parameters({"URL","folder1"})
//	  public void newSubUploadFolderFiles(String URL, String folder1) throws InterruptedException
//	  {
//		    driver.get(URL);
//		    String sourceDir = System.getProperty("user.dir") + "/src/main/java/config/TestData";
//			String destinationDir = System.getProperty("user.dir") + "/src/main/java/config/TestData" + this.randomValue1;
//            this.cpDirectory(sourceDir, destinationDir);
//			Thread.sleep(10000);
//			WebDriverWait wait3=new WebDriverWait(driver,20);
//			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'DCirrus Sync')]")));
//			driver.findElement(By.xpath("//a[contains(text(),'DCirrus Sync')]")).click();
//			Thread.sleep(10000);
//			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'SYNC1')]")));
//			driver.findElement(By.xpath("//a[contains(text(),'SYNC1')]")).click();
//			Thread.sleep(10000);
//			WebDriverWait wait1=new WebDriverWait(driver,20);
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncreatefolderpopup")));
//			driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
//			Thread.sleep(10000);
//			  WebDriverWait wait2=new WebDriverWait(driver,20);
//			  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuploadfolder']")));
//			  driver.findElement(By.id("btnuploadfolder")).click();
//			  Thread.sleep(10000);
//			  WebDriverWait wait13=new WebDriverWait(driver,20);
//			  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='adm_fileuploadId']")));
//            Thread.sleep(4000);
//			  driver.switchTo().frame("adm_fileuploadId");
//
//			  WebElement folderInput = driver.findElement(By.id("uploadFolderId"));
//			  folderInput.sendKeys(destinationDir);
//			  Thread.sleep(20000);
//               driver.switchTo().parentFrame();						
//			
//	  } 
//	
//	 @Test(priority=7, groups="uploadFolder")
//	   @Parameters({"URL", "folder1"})
//	   public void newsubUploadFolderPermissions(String URL, String folder1) throws InterruptedException         //Software folder is created at 3rd
//	   {  
//		   driver.get(URL);
//	       JavascriptExecutor js = (JavascriptExecutor) driver;
//	       driver.findElement(By.xpath("//a[@title = 'Admin and User Permissions']")).click();
//	       Thread.sleep(2000);
//	       driver.findElement(By.xpath("//a[@id = 'permission_module']/i")).click();
//	       Thread.sleep(2000);	
//	       SwitchCurrentWindow.GetNewWindow();
//	       Thread.sleep(2000);	
//	       driver.findElement(By.xpath("//li[contains(text(),'DCirrus Sync')]")).click();
//	       Thread.sleep(2000);
//	       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//	       Thread.sleep(2000);	
//	       boolean selected = driver.findElement(By.xpath("//input[@title='Raap Raap']")).isSelected();	
//	       Thread.sleep(2000);
//         System.out.println(selected);
//         Assert.assertEquals(selected, true);
//		   for(int j = 3 ; j<=10 ; j++) {
//    		   selected = driver.findElement(By.xpath("//input[@title='Raap Raap']//..//..//..//td["+ j + "]//div//input")).isSelected();		       
//               System.out.println(selected);
//		       Thread.sleep(1000);
//		       if(j == 3)
//		       Assert.assertEquals(selected, true);
//		       else
//		    	 Assert.assertEquals(selected, false);   
//    	   }
// 	  try {
// 		 driver.findElement(By.xpath("//li[contains(text(),'SYNC1')]")).click();
//    	   } catch(Exception e) {          		 
//    		   driver.findElement(By.xpath("//li[contains(text(),'SYNC1')]")).sendKeys(Keys.PAGE_DOWN);
//    		   Thread.sleep(2000);
//    		   js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//li[contains(text(),'SYNC1')]")));
//    		   Thread.sleep(5000);
//    		   driver.findElement(By.xpath("//li[contains(text(),'SYNC1')]")).click();
//    	   }
//		       Thread.sleep(2000);
//		       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//		       Thread.sleep(2000);
//		       selected = driver.findElement(By.xpath("//input[@title='Raap Raap']")).isSelected();	
//		       Thread.sleep(2000);
//            System.out.println(selected);
//            Assert.assertEquals(selected, true);
//    	   for(int j = 3 ; j<=10 ; j++) {
//    		   selected = driver.findElement(By.xpath("//input[@title='Raap Raap']//..//..//..//td[" + j + "]//div//input")).isSelected();		       
//               System.out.println(selected);
//		       Thread.sleep(1000);
//		       if(j ==3 || j == 4 || j == 7)
//		       Assert.assertEquals(selected, true);
//		       else
//		    	 Assert.assertEquals(selected, false);   
//    	   }
//    	   driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//    	   Thread.sleep(1000);
//    	 try {
//     		 driver.findElement(By.xpath("//li[contains(text(),'TestData" + this.randomValue1 + "')]")).click();
//        	   } catch(Exception e) {          		 
//        		   driver.findElement(By.xpath("//li[contains(text(), 'TestData" + this.randomValue1 + "')]")).sendKeys(Keys.PAGE_DOWN);
//        		   Thread.sleep(2000);
//        		   js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//li[contains(text(),'TestData" + this.randomValue1 + "')]")));
//        		   Thread.sleep(5000);
//        		   driver.findElement(By.xpath("//li[contains(text(),'TestData" + this.randomValue1 + "')]")).click();
//        	   }
//  		       Thread.sleep(2000);
//  		       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//  		       Thread.sleep(2000);
//  		       selected = driver.findElement(By.xpath("//input[@title='Raap Raap']")).isSelected();	
//  		       Thread.sleep(2000);
//                System.out.println(selected);
//                Assert.assertEquals(selected, true);
//        	   for(int j = 3 ; j<=10 ; j++) {
//        		   selected = driver.findElement(By.xpath("//input[@title='Raap Raap']//..//..//..//td[" + j + "]//div//input")).isSelected();		       
//                   System.out.println(selected);
//    		       Thread.sleep(1000);
//    		       if(j ==3 || j == 4 || j == 7)
//    		       Assert.assertEquals(selected, true);
//    		       else
//    		    	 Assert.assertEquals(selected, false);   
//        	   }
//        	   driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//        	   Thread.sleep(1000);
//}
	
//	 @Test(priority=8, groups="uploadFile")
//	  @Parameters({"URL","folder1"})
//	  public void newSubUploadFiles(String URL, String folder1) throws InterruptedException
//	  {
//		    driver.get(URL);
//			String destinationDirfile = System.getProperty("user.dir") + "/src/main/java/config/TestData" + this.randomValue1 + "/selenium.docx";
//			Thread.sleep(10000);
//			WebDriverWait wait3=new WebDriverWait(driver,20);
//			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'DCirrus Sync')]")));
//			driver.findElement(By.xpath("//a[contains(text(),'DCirrus Sync')]")).click();
//			Thread.sleep(10000);
//			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'SYNC1')]")));
//			driver.findElement(By.xpath("//a[contains(text(),'SYNC1')]")).click();
//			Thread.sleep(10000);
//			WebDriverWait wait1=new WebDriverWait(driver,20);
//			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncreatefolderpopup")));
//			driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
//			Thread.sleep(10000);
//			  WebDriverWait wait2=new WebDriverWait(driver,20);
//			  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuploadfile']")));
//			  driver.findElement(By.id("btnuploadfile")).click();
//			  Thread.sleep(10000);
//			  WebDriverWait wait13=new WebDriverWait(driver,20);
//			  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='adm_fileuploadId']")));
//           Thread.sleep(4000);
//			  driver.switchTo().frame("adm_fileuploadId");
//
//			  WebElement folderInput = driver.findElement(By.id("uploadFileId"));
//			  folderInput.sendKeys(destinationDirfile);
//			  Thread.sleep(10000);
//              driver.switchTo().parentFrame();						
//			
//	  } 
	 
	 @Test(priority=9, groups="renameFolder")
	  @Parameters({"URL","folder1"})
	  public void renameFolder(String URL, String folder1) throws InterruptedException
	  {
		    driver.get(URL);
		    JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait3=new WebDriverWait(driver,20);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-foldername,'renameFolder')]/div[7]/ul/li/img")));
			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[contains(@data-foldername,'delrenamefolder')]/div[7]/ul/li/img")));
			driver.findElement(By.xpath("//*[contains(@data-foldername,'renameFolder')]/div[7]/ul/li/img")).click();
			Thread.sleep(10000);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-foldername,'renameFolder') and contains(text(), 'Rename')]")));
			driver.findElement(By.xpath("//*[contains(@data-foldername,'renameFolder') and contains(text(), 'Rename')]")).click();
			Thread.sleep(10000);
			WebDriverWait wait1=new WebDriverWait(driver,20);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("rename_admfoldername")));
			driver.findElement(By.xpath("//*[@id='rename_admfoldername']")).clear();
			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[@id='rename_admfoldername']")).sendKeys("renameFolder1");
			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[@id='rename_btncreatefolderdone']")).click();
			Thread.sleep(10000);		
	  } 
	 
	 @Test(priority=10, groups="delrestoreFolder")
	  @Parameters({"URL","folder1"})
	  public void delrestoreFolder(String URL, String folder1) throws InterruptedException
	  {
		    driver.get(URL);
		    JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait3=new WebDriverWait(driver,20);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-foldername,'renameFolder1')]/div[7]/ul/li/img")));
			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[contains(@data-foldername,'delrenamefolder')]/div[7]/ul/li/img")));
			driver.findElement(By.xpath("//*[contains(@data-foldername,'renameFolder1')]/div[7]/ul/li/img")).click();
			Thread.sleep(10000);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-foldername,'renameFolder1') and contains(text(), 'Delete')]")));
			driver.findElement(By.xpath("//*[contains(@data-foldername,'renameFolder1') and contains(text(), 'Delete')]")).click();
			Thread.sleep(10000);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-foldername,'renameFolder1')]/div[7]/ul/li/img")));
			driver.findElement(By.xpath("//*[contains(@data-foldername,'renameFolder1')]/div[7]/ul/li/img")).click();
			Thread.sleep(10000);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-foldername,'renameFolder1') and contains(text(), 'Restore')]")));
			driver.findElement(By.xpath("//*[contains(@data-foldername,'renameFolder1') and contains(text(), 'Restore')]")).click();
			Thread.sleep(10000);
			
	  } 
	 
	 
	 
	 @Test(priority=11, groups="downloadFolder")
	  @Parameters({"URL","folder1"})
	  public void reqFileDeposit(String URL, String folder1) throws InterruptedException
	  {
		    driver.get(URL);
		    JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait3=new WebDriverWait(driver,20);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-foldername,'renameFolder1')]/div[7]/ul/li/img")));
			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[contains(@data-foldername,'delrenamefolder')]/div[7]/ul/li/img")));
			driver.findElement(By.xpath("//*[contains(@data-foldername,'renameFolder1')]/div[7]/ul/li/img")).click();
			Thread.sleep(10000);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-foldername,'renameFolder1') and contains(text(), 'Request File Deposit')]")));
			driver.findElement(By.xpath("//*[contains(@data-foldername,'renameFolder1') and contains(text(), 'Request File Deposit')]")).click();
			Thread.sleep(10000);
			Thread.sleep(2000);
			
			driver.findElement(By.id("inbound_admsharesubject")).sendKeys("Document required");
			driver.findElement(By.id("inbound_admaddreceiver")).click();
			driver.findElement(By.id("adm_mobileemail_txt1_0")).sendKeys("user1prod@protonmail.com");
			driver.findElement(By.id("btnshareemaildone")).click();
			driver.findElement(By.id("inbound_admsharesend")).click();
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
			String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
			System.out.println(Popupmsg);
			Assert.assertEquals("File deposit request sent successfully", Popupmsg);
			driver.get("https://mail.protonmail.com/login");
			driver.findElement(By.id("username")).sendKeys("user1prod@protonmail.com");
			driver.findElement(By.name("password")).sendKeys("Test@123");
			driver.findElement(By.id("login_btn")).click();
			Thread.sleep(3000);
			WebDriverWait wait4=new WebDriverWait(driver,20);
			wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Document required']")));
			driver.findElement(By.xpath("//span[text()='Document required']")).click();
			Thread.sleep(4000);
			driver.findElement(By.linkText("Deposit File(s)/Folder")).click();
			
	  } 
	 
	 @Test(priority=12, groups="delrestoreFolder")
	  @Parameters({"URL","folder1"})
	  public void delpermaFolder(String URL, String folder1) throws InterruptedException
	  {
		    driver.get(URL);
		    JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait3=new WebDriverWait(driver,20);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-foldername,'renameFolder1')]/div[7]/ul/li/img")));
			js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[contains(@data-foldername,'delrenamefolder')]/div[7]/ul/li/img")));
			driver.findElement(By.xpath("//*[contains(@data-foldername,'renameFolder1')]/div[7]/ul/li/img")).click();
			Thread.sleep(10000);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-foldername,'renameFolder1') and contains(text(), 'Delete')]")));
			driver.findElement(By.xpath("//*[contains(@data-foldername,'renameFolder1') and contains(text(), 'Delete')]")).click();
			Thread.sleep(10000);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-foldername,'renameFolder1')]/div[7]/ul/li/img")));
			driver.findElement(By.xpath("//*[contains(@data-foldername,'renameFolder1')]/div[7]/ul/li/img")).click();
			Thread.sleep(10000);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-foldername,'renameFolder1') and contains(text(), 'Delete permanently')]")));
			driver.findElement(By.xpath("//*[contains(@data-foldername,'renameFolder1') and contains(text(), 'Delete permanently')]")).click();
			Thread.sleep(10000);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.id("btndeleteconfirmdone")));
			driver.findElement(By.id("btndeleteconfirmdone")).click();
			Thread.sleep(10000);
			
	  }


	}

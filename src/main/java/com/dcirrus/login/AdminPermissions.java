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

	public class AdminPermissions 
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
		 driver.get(URL);
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
    
      @Test(priority=3, groups="createFolder")
	  @Parameters({"URL","folder1"})
	  public void newCreateFolder(String URL, String folder1) throws InterruptedException
	  {
		    driver.get(URL);
			Thread.sleep(2000);
			this.randomValue1 = 1 + random.nextInt(1000000000);
			WebDriverWait wait1=new WebDriverWait(driver,20);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncreatefolderpopup")));
			driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
			Thread.sleep(2000);
			  WebDriverWait wait2=new WebDriverWait(driver,20);
			  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btncreatefolder']")));
			  driver.findElement(By.id("btncreatefolder")).click();
			  Thread.sleep(2000);
			  WebDriverWait wait12=new WebDriverWait(driver,20);
			  wait12.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='admfoldername']")));
			  driver.findElement(By.id("admfoldername")).sendKeys(folder1 + this.randomValue1);
			  Thread.sleep(2000);
			  WebDriverWait wait13=new WebDriverWait(driver,20);
			  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='ADD']")));
			  driver.findElement(By.xpath("//button[text()='ADD']")).click();
			  Thread.sleep(4000);
			
	  }
      
      
//      
     @Test(priority=4, groups="createFolder")
	   @Parameters({"URL", "folder1"})
	   public void newFolderPermissions(String URL, String folder1) throws InterruptedException         //Software folder is created at 3rd
	   {  
	       driver.get(URL);
	       JavascriptExecutor js = (JavascriptExecutor) driver;
	       driver.findElement(By.xpath("//a[@title = 'Admin and User Permissions']")).click();
	       Thread.sleep(2000);
	       driver.findElement(By.xpath("//a[@id = 'permission_module']/i")).click();
	       Thread.sleep(2000);	
	       SwitchCurrentWindow.GetNewWindow();
	       Thread.sleep(2000);	
	       driver.findElement(By.xpath("//li[contains(text(),'"+ folder1 + this.randomValue1 + "')]")).click();
	       Thread.sleep(2000);
	       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
	       Thread.sleep(2000);	
	       boolean selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
	       Thread.sleep(2000);
           System.out.println(selected);
           Assert.assertEquals(selected, true);
           for(int j = 3 ; j<=10 ; j++) {
    		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
               System.out.println(selected);
		       Thread.sleep(1000);
		       Assert.assertEquals(selected, true);
    		   
    	   }
           driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
    	   Thread.sleep(1000);
}
     
//          @Test(priority=5, groups="uploadFolder")
//		  @Parameters({"URL"})
//		  public void newFolderUpload(String URL) throws InterruptedException
//		  {
//			    driver.get(URL);
//			    String sourceDir = System.getProperty("user.dir") + "/src/main/java/config/TestData";
//				String destinationDir = System.getProperty("user.dir") + "/src/main/java/config/TestData" + this.randomValue1;
//                this.cpDirectory(sourceDir, destinationDir);
//				Thread.sleep(2000);
//				WebDriverWait wait1=new WebDriverWait(driver,20);
//				wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncreatefolderpopup")));
//				driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
//				  WebDriverWait wait2=new WebDriverWait(driver,20);
//				  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuploadfolder']")));
//				  driver.findElement(By.id("btnuploadfolder")).click();
//				  WebDriverWait wait12=new WebDriverWait(driver,20);
//				  WebDriverWait wait13=new WebDriverWait(driver,20);
//				  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='adm_fileuploadId']")));
//                Thread.sleep(4000);
//				  driver.switchTo().frame("adm_fileuploadId");
//
//				  WebElement folderInput = driver.findElement(By.id("uploadFolderId"));
//				  folderInput.sendKeys(destinationDir);
//				  Thread.sleep(10000);
//                   driver.switchTo().parentFrame();						
//		  }
       
//	       @Test(priority=6,groups="uploadFolder")
//		   @Parameters({"URL", "name1"})
//		   public void uploadFoldersPermissions(String URL, String name1) throws InterruptedException         //Software folder is created at 3rd
//		   {  
//		       driver.get(URL);
//		       JavascriptExecutor js = (JavascriptExecutor) driver;
//		       driver.findElement(By.xpath("//a[@title = 'Admin and User Permissions']")).click();
//		       Thread.sleep(2000);
//		       driver.findElement(By.xpath("//a[@id = 'permission_module']/i")).click();
//		       Thread.sleep(2000);	
//		       SwitchCurrentWindow.GetNewWindow();
//		       Thread.sleep(2000);	
//		       driver.findElement(By.xpath("//li[contains(text(),'TestData" + this.randomValue1 + "')]")).click();
//		       Thread.sleep(2000);
//		       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//		       Thread.sleep(2000);	
//		       boolean selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//		       Thread.sleep(2000);
//               System.out.println(selected);
//               Assert.assertEquals(selected, true);
//               for(int j = 3 ; j<=10 ; j++) {
//        		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//                   System.out.println(selected);
//    		       Thread.sleep(1000);
//    		       Assert.assertEquals(selected, true);
//        		   
//        	   }
//               driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//        	   Thread.sleep(1000);
//        	   
//}
//	       
//	       @Test(priority=7, groups="uploadFolder")
//			  @Parameters({"URL"})
//			  public void newEmptyFolderUpload(String URL) throws InterruptedException
//			  {
//				    driver.get(URL);
//					Thread.sleep(2000);
//					String sourceDir = System.getProperty("user.dir") + "/src/main/java/config/TestDataEmpty";
//					  String destinationDir = System.getProperty("user.dir") + "/src/main/java/config/TestDataEmpty" + this.randomValue1;
//	                  this.cpDirectory(sourceDir, destinationDir);
//					WebDriverWait wait1=new WebDriverWait(driver,20);
//					wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncreatefolderpopup")));
//					driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
//					  WebDriverWait wait2=new WebDriverWait(driver,20);
//					  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuploadfolder']")));
//					  driver.findElement(By.id("btnuploadfolder")).click();
//					  WebDriverWait wait12=new WebDriverWait(driver,20);
//					  WebDriverWait wait13=new WebDriverWait(driver,20);
//					  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='adm_fileuploadId']")));
//	                Thread.sleep(4000);
//					  driver.switchTo().frame("adm_fileuploadId");
//
//					  WebElement folderInput = driver.findElement(By.id("uploadFolderId"));
//
//					  folderInput.sendKeys(destinationDir);
//					  Thread.sleep(10000);
//	                   driver.switchTo().parentFrame();						
//			  }
//	       
//		       @Test(priority=8,groups="uploadFolder")
//			   @Parameters({"URL", "name1"})
//			   public void uploadEmptyFoldersPermissions(String URL, String name1) throws InterruptedException         //Software folder is created at 3rd
//			   {  
//			       driver.get(URL);
//			       JavascriptExecutor js = (JavascriptExecutor) driver;
//			       driver.findElement(By.xpath("//a[@title = 'Admin and User Permissions']")).click();
//			       Thread.sleep(2000);
//			       driver.findElement(By.xpath("//a[@id = 'permission_module']/i")).click();
//			       Thread.sleep(2000);	
//			       SwitchCurrentWindow.GetNewWindow();
//			       Thread.sleep(2000);	
//			       driver.findElement(By.xpath("//li[contains(text(),'TestDataEmpty" + this.randomValue1 + "')]")).click();
//			       Thread.sleep(2000);
//			       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//			       Thread.sleep(2000);	
//			       boolean selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//			       Thread.sleep(2000);
//	               System.out.println(selected);
//	               Assert.assertEquals(selected, true);
//	               for(int j = 3 ; j<=10 ; j++) {
//	        		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//	                   System.out.println(selected);
//	    		       Thread.sleep(1000);
//	    		       Assert.assertEquals(selected, true);
//	        		   
//	        	   }
//	               driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//	        	   Thread.sleep(1000);        	   
//	}
//		       
//		       
//		       @Test(priority=9, groups="createFolder")
//		 	  @Parameters({"URL","folder1"})
//		 	  public void newCreateSubFolder(String URL, String folder1) throws InterruptedException
//		 	  {
//		 		    driver.get(URL);	 		    
//		 			Thread.sleep(10000);
//		 			WebDriverWait wait3=new WebDriverWait(driver,20);
//					wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + folder1 +  this.randomValue1+"')]")));
//					driver.findElement(By.xpath("//a[contains(text(),'" + folder1 +  this.randomValue1+"')]")).click();
//		 			WebDriverWait wait1=new WebDriverWait(driver,20);
//		 			Thread.sleep(10000);
//		 			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncreatefolderpopup")));
//		 			driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
//		 			Thread.sleep(2000);
//		 			  WebDriverWait wait2=new WebDriverWait(driver,20);
//		 			  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btncreatefolder']")));
//		 			  driver.findElement(By.id("btncreatefolder")).click();
//		 			  Thread.sleep(2000);
//		 			  WebDriverWait wait12=new WebDriverWait(driver,20);
//		 			  wait12.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='admfoldername']")));
//		 			  driver.findElement(By.id("admfoldername")).sendKeys(folder1 + this.randomValue1);
//		 			  Thread.sleep(2000);
//		 			  WebDriverWait wait13=new WebDriverWait(driver,20);
//		 			  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='ADD']")));
//		 			  driver.findElement(By.xpath("//button[text()='ADD']")).click();
//		 			  Thread.sleep(4000);
//		 			
//		 	  }
//		       
//		       
////		       
//		      @Test(priority=10, groups="createFolder")
//		 	   @Parameters({"URL", "folder1"})
//		 	   public void newsubFolderPermissions(String URL, String folder1) throws InterruptedException         //Software folder is created at 3rd
//		 	   {  
//		 	       driver.get(URL);
//		 	       JavascriptExecutor js = (JavascriptExecutor) driver;
//		 	       driver.findElement(By.xpath("//a[@title = 'Admin and User Permissions']")).click();
//		 	       Thread.sleep(2000);
//		 	       driver.findElement(By.xpath("//a[@id = 'permission_module']/i")).click();
//		 	       Thread.sleep(2000);	
//		 	       SwitchCurrentWindow.GetNewWindow();
//		 	       Thread.sleep(2000);	
//		 	       driver.findElement(By.xpath("//li[contains(text(),'"+ folder1 + this.randomValue1 + "')]")).click();
//		 	       Thread.sleep(2000);
//		 	       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//		 	       Thread.sleep(2000);	
//		 	       boolean selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//		 	       Thread.sleep(2000);
//		            System.out.println(selected);
//		            Assert.assertEquals(selected, true);
//		            for(int j = 3 ; j<=10 ; j++) {
//		     		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//		                System.out.println(selected);
//		 		       Thread.sleep(1000);
//		 		       Assert.assertEquals(selected, true);
//		     		   
//		     	   }
//		            driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//		     	   Thread.sleep(1000);
//		     	  selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//   		          Assert.assertEquals(selected, true);
//		     	  try {
//		     		 driver.findElement(By.xpath("//li[contains(text(),'"+ folder1 + this.randomValue1 + "')]")).click();
//	            	   } catch(Exception e) {          		 
//	            		   driver.findElement(By.xpath("//li[contains(text(),'"+ folder1 + this.randomValue1 + "')]")).sendKeys(Keys.PAGE_DOWN);
//	            		   Thread.sleep(2000);
//	            		   js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//li[contains(text(),'"+ folder1 + this.randomValue1 + "')]")));
//	            		   Thread.sleep(5000);
//	            		   driver.findElement(By.xpath("//li[contains(text(),'"+ folder1 + this.randomValue1 + "')]")).click();
//	            	   }
//	    		       Thread.sleep(2000);
//	    		       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//	    		       Thread.sleep(2000);
//	    		       selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//		 		       Thread.sleep(2000);
//		                System.out.println(selected);
//		                Assert.assertEquals(selected, true);
//	            	   for(int j = 3 ; j<=10 ; j++) {
//	            		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//	                       System.out.println(selected);
//	        		       Thread.sleep(1000); 
//		    		       Assert.assertEquals(selected, true);
//	            	   }
//	            	   driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//	            	   Thread.sleep(1000);
//		 }
//		      
//		           @Test(priority=11, groups="uploadFolder")
//		 		  @Parameters({"URL"})
//		 		  public void newSubFolderUpload(String URL) throws InterruptedException
//		 		  {
//		 			    driver.get(URL);
//		 			   String destinationDir = System.getProperty("user.dir") + "/src/main/java/config/TestData" + this.randomValue1;
//		 				Thread.sleep(4000);
//						WebDriverWait wait3=new WebDriverWait(driver,20);
//						wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'TestData" + this.randomValue1 + "')]")));
//						driver.findElement(By.xpath("//a[contains(text(),'TestData" + this.randomValue1 + "')]")).click();
//		 				WebDriverWait wait1=new WebDriverWait(driver,20);
//		 				wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncreatefolderpopup")));
//		 				driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
//		 				  WebDriverWait wait2=new WebDriverWait(driver,20);
//		 				  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuploadfolder']")));
//		 				  driver.findElement(By.id("btnuploadfolder")).click();
//		 				  WebDriverWait wait13=new WebDriverWait(driver,20);
//		 				  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='adm_fileuploadId']")));
//		                 Thread.sleep(4000);
//		 				  driver.switchTo().frame("adm_fileuploadId");
//
//		 				  WebElement folderInput = driver.findElement(By.id("uploadFolderId"));
//		 				  folderInput.sendKeys(destinationDir);
//		 				  Thread.sleep(10000);
//		                    driver.switchTo().parentFrame();						
//		 		  }
//		        
//		 	       @Test(priority=12,groups="uploadFolder")
//		 		   @Parameters({"URL", "name1"})
//		 		   public void uploadSubFoldersPermissions(String URL, String name1) throws InterruptedException         //Software folder is created at 3rd
//		 		   {  
//		 		       driver.get(URL);
//		 		       JavascriptExecutor js = (JavascriptExecutor) driver;
//		 		       driver.findElement(By.xpath("//a[@title = 'Admin and User Permissions']")).click();
//		 		       Thread.sleep(2000);
//		 		       driver.findElement(By.xpath("//a[@id = 'permission_module']/i")).click();
//		 		       Thread.sleep(2000);	
//		 		       SwitchCurrentWindow.GetNewWindow();
//		 		       Thread.sleep(2000);	
//		 		       driver.findElement(By.xpath("//li[contains(text(),'TestData" + this.randomValue1 + "')]")).click();
//		 		       Thread.sleep(2000);
//		 		       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//		 		       Thread.sleep(2000);	
//		 		       boolean selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//		 		       Thread.sleep(2000);
//		                System.out.println(selected);
//		                Assert.assertEquals(selected, true);
//		                for(int j = 3 ; j<=10 ; j++) {
//		         		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//		                    System.out.println(selected);
//		     		       Thread.sleep(1000);
//		     		       Assert.assertEquals(selected, true);
//		         		   
//		         	   }
//		                driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//		         	   Thread.sleep(1000);
//		         	   selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//		 		       Thread.sleep(2000);
//		                System.out.println(selected);
//		                Assert.assertEquals(selected, true);
//		         	  try {
//		         		 driver.findElement(By.xpath("//li[contains(text(),'TestData" + this.randomValue1 + "')]")).click();
//			            	   } catch(Exception e) {          		 
//			            		   driver.findElement(By.xpath("//li[contains(text(),'TestData" + this.randomValue1 + "')]")).sendKeys(Keys.PAGE_DOWN);
//			            		   Thread.sleep(2000);
//			            		   js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//li[contains(text(),'TestData" + this.randomValue1 + "')]")));
//			            		   Thread.sleep(5000);
//			            		   driver.findElement(By.xpath("//li[contains(text(),'TestData" + this.randomValue1 + "')]")).click();
//			            	   }
//			    		       Thread.sleep(2000);
//			    		       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//			    		       Thread.sleep(2000);
//			    		       selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//			    		       Assert.assertEquals(selected, true);
//			    		       selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//			            	   for(int j = 3 ; j<=10 ; j++) {
//			            		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//			                       System.out.println(selected);
//			        		       Thread.sleep(1000);    
//				    		       Assert.assertEquals(selected, true);
//			            	   }
//			            	   driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//			            	   Thread.sleep(1000);
//		         	   
//		 }
//		       
//	       @Test(priority=13, groups="uploadFolder")
//	       @Parameters({"URL","folder1"})
//			  public void subshareFolderCreateCollaborate(String URL, String folder1) throws InterruptedException
//			  {
//				    driver.get(URL);
//					Thread.sleep(2000);
//					this.randommail = 1 + random.nextInt(1000000000);
//					JavascriptExecutor js = (JavascriptExecutor) driver;
//     				js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//*[@data-foldername='"+ folder1 + this.randomValue1 + "']/div[7]/ul/li/img")) );
//					driver.findElement(By.xpath("//*[@data-foldername='" + folder1 + this.randomValue1 + "']/div[7]/ul/li/img")).click();
//					Thread.sleep(2000);
//					driver.findElement(By.linkText("Share")).click();
//					Thread.sleep(2000);
//					
//					driver.findElement(By.id("admsharesubject")).sendKeys("Folder Shared to Collaborate" + this.randommail);
//					driver.findElement(By.xpath("//*[@id='lblallowupload']/span")).click();
//					driver.findElement(By.id("admaddreceiver")).click();
//					driver.findElement(By.id("adm_mobileemail_txt1_0")).sendKeys("user1prod@protonmail.com");
//					driver.findElement(By.id("btnshareemaildone")).click();
//					driver.findElement(By.id("admsharesend")).click();
//					WebDriverWait wait=new WebDriverWait(driver,20);
//					wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
//					String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
//					System.out.println(Popupmsg);
//					Assert.assertEquals("Collaboration link shared successfully", Popupmsg);
//					driver.get("https://mail.protonmail.com/login");
//					driver.findElement(By.id("username")).sendKeys("user1prod@protonmail.com");
//					driver.findElement(By.name("password")).sendKeys("Test@123");
//					Thread.sleep(2000);
//					driver.findElement(By.id("login_btn")).click();
//					Thread.sleep(2000);
//					WebDriverWait wait4=new WebDriverWait(driver,20);
//					wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Folder Shared to Collaborate" + this.randommail+ "']")));
//					driver.findElement(By.xpath("//span[text()='Folder Shared to Collaborate" + this.randommail + "']")).click();
//					Thread.sleep(4000);
//					driver.findElement(By.linkText("View File(s)/Folder")).click();
//					SwitchCurrentWindow.GetNewWindow();
//					Thread.sleep(4000);
//					this.randomValue2 = 1 + random.nextInt(1000000000);
//		 			WebDriverWait wait3=new WebDriverWait(driver,20);
////					wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'" + folder1 +  this.randomValue1+"')]")));
////					driver.findElement(By.xpath("//a[contains(text(),'" + folder1 +  this.randomValue1+"')]")).click();
//					WebDriverWait wait1=new WebDriverWait(driver,20);
//					wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncreatefolderpopup")));
//					driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
//					  WebDriverWait wait2=new WebDriverWait(driver,20);
//					  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btncreatefolder']")));
//					  driver.findElement(By.id("btncreatefolder")).click();
//					  WebDriverWait wait12=new WebDriverWait(driver,20);
//					  wait12.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='admfoldername']")));
//					  driver.findElement(By.id("admfoldername")).sendKeys(folder1 + this.randomValue2);
//					  WebDriverWait wait13=new WebDriverWait(driver,20);
//					  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='ADD']")));
//					  driver.findElement(By.xpath("//p[text()='ADD']")).click();
//					  Thread.sleep(4000);
//					 			
//			  }
//		      
//		       @Test(priority=14,groups="createFolder")
//			   @Parameters({"URL", "folder1"})
//			   public void subCreateSharedFoldersPermissions(String URL, String folder1) throws InterruptedException         //Software folder is created at 3rd
//			   {  
//			       driver.get(URL);
//			       JavascriptExecutor js = (JavascriptExecutor) driver;
//			       driver.findElement(By.xpath("//a[@title = 'Admin and User Permissions']")).click();
//			       Thread.sleep(2000);
//			       driver.findElement(By.xpath("//a[@id = 'permission_module']/i")).click();
//			       Thread.sleep(2000);	
//			       SwitchCurrentWindow.GetNewWindow();
//			       Thread.sleep(2000);	
//			       driver.findElement(By.xpath("//li[contains(text(),'"+ folder1 + this.randomValue1 + "')]")).click();
//			       Thread.sleep(2000);
//			       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//			       Thread.sleep(2000);	
//			       boolean selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//			       Thread.sleep(2000);
//	               System.out.println(selected);
//	               Assert.assertEquals(selected, true);
//	               for(int j = 3 ; j<=10 ; j++) {
//	        		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//	                   System.out.println(selected);
//	    		       Thread.sleep(1000); 
//	    		       Assert.assertEquals(selected, true);
//	        	   }
//	               driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//            	   Thread.sleep(1000);
//            	   selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//            	   try {
//            	   driver.findElement(By.xpath("//li[contains(text(),'"+ folder1 + this.randomValue2 + "')]")).click();
//            	   } catch(Exception e) {          		 
//            		   driver.findElement(By.xpath("//li[contains(text(),'"+ folder1 + this.randomValue2 + "')]")).sendKeys(Keys.PAGE_DOWN);
//            		   Thread.sleep(2000);
//            		   js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//li[contains(text(),'"+ folder1 + this.randomValue2 + "')]")));
//            		   Thread.sleep(5000);
//            		   driver.findElement(By.xpath("//li[contains(text(),'"+ folder1 + this.randomValue2 + "')]")).click();
//            	   }
//    		       Thread.sleep(2000);
//    		       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//    		       Thread.sleep(2000);
//    		       selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//    		       Assert.assertEquals(selected, true);
//            	   for(int j = 3 ; j<=10 ; j++) {
//            		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//                       System.out.println(selected);
//        		       Thread.sleep(1000);  
//        		       Assert.assertEquals(selected, true);
//            	   }
//            	   driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//            	   Thread.sleep(1000);
//            	   
//            	   
//	}
//		          @Test(priority=15,groups="uploadFolder")
//				  @Parameters({"URL","folder1"})
//				  public void subShareFolderUploadCollaborate(String URL, String folder1) throws InterruptedException
//				  {
//					    driver.get(URL);
//						Thread.sleep(2000);
//						this.randommail = 1 + random.nextInt(1000000000);
//						String sourceDir = System.getProperty("user.dir") + "/src/main/java/config/TestData";
//						String destinationDir = System.getProperty("user.dir") + "/src/main/java/config/TestData" + this.randomValue2;
//		                this.cpDirectory(sourceDir, destinationDir);
//						
//						JavascriptExecutor js = (JavascriptExecutor) driver;
//						js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@data-foldername='TestData" + this.randomValue1 + "']/div[7]/ul/li/img")));
//						driver.findElement(By.xpath("//*[@data-foldername='TestData" + this.randomValue1 + "']/div[7]/ul/li/img")).click();  
//						Thread.sleep(2000);
//						driver.findElement(By.linkText("Share")).click();
//						Thread.sleep(2000);
//						
//						driver.findElement(By.id("admsharesubject")).sendKeys("Folder Shared to Collaborate" + this.randommail);
//						driver.findElement(By.xpath("//*[@id='lblallowupload']/span")).click();
//						driver.findElement(By.id("admaddreceiver")).click();
//						driver.findElement(By.id("adm_mobileemail_txt1_0")).sendKeys("user1prod@protonmail.com");
//						driver.findElement(By.id("btnshareemaildone")).click();
//						driver.findElement(By.id("admsharesend")).click();
//						WebDriverWait wait=new WebDriverWait(driver,20);
//						wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
//						String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
//						System.out.println(Popupmsg);
//						Assert.assertEquals("Collaboration link shared successfully", Popupmsg);
//						driver.get("https://mail.protonmail.com/login");
//						driver.findElement(By.id("username")).sendKeys("user1prod@protonmail.com");
//						driver.findElement(By.name("password")).sendKeys("Test@123");
//						Thread.sleep(2000);
//						driver.findElement(By.id("login_btn")).click();
//						Thread.sleep(2000);
//						WebDriverWait wait4=new WebDriverWait(driver,20);
//						wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Folder Shared to Collaborate" + this.randommail+ "']")));
//						driver.findElement(By.xpath("//span[text()='Folder Shared to Collaborate" + this.randommail + "']")).click();
//						Thread.sleep(4000);
//						driver.findElement(By.linkText("View File(s)/Folder")).click();
//						SwitchCurrentWindow.GetNewWindow();
//		 				Thread.sleep(4000);
////						WebDriverWait wait3=new WebDriverWait(driver,20);
////						wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'TestData" + this.randomValue1 + "')]")));
////						driver.findElement(By.xpath("//a[contains(text(),'TestData" + this.randomValue1 + "')]")).click();
//						Thread.sleep(4000);
//						WebDriverWait wait1=new WebDriverWait(driver,20);
//						wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncreatefolderpopup")));
//						driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
//						  WebDriverWait wait2=new WebDriverWait(driver,20);
//						  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuploadfolder']")));
//						  driver.findElement(By.id("btnuploadfolder")).click();
//						  WebDriverWait wait13=new WebDriverWait(driver,20);
//						  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='adm_fileuploadId']")));
//                          Thread.sleep(4000);
//						  driver.switchTo().frame("adm_fileuploadId");
//						  
//						  WebElement folderInput = driver.findElement(By.id("uploadFolderId"));
//
//						  folderInput.sendKeys(destinationDir);
//						  Thread.sleep(10000);
//						  driver.switchTo().parentFrame();						
//				  }
////			      
//			       @Test(priority=16,groups="uploadFolder")
//				   @Parameters({"URL", "name1"})
//				   public void subUploadSharedFoldersPermissions(String URL, String name1) throws InterruptedException         //Software folder is created at 3rd
//				   {  
//				       driver.get(URL);
//				       JavascriptExecutor js = (JavascriptExecutor) driver;
//				       driver.findElement(By.xpath("//a[@title = 'Admin and User Permissions']")).click();
//				       Thread.sleep(2000);
//				       driver.findElement(By.xpath("//a[@id = 'permission_module']/i")).click();
//				       Thread.sleep(2000);	
//				       SwitchCurrentWindow.GetNewWindow();
//				       Thread.sleep(2000);	
//				       driver.findElement(By.xpath("//li[contains(text(),'TestData" + this.randomValue1 + "')]")).click();
//				       Thread.sleep(2000);
//				       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//				       Thread.sleep(2000);	
//				       boolean selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//				       Thread.sleep(2000);
//		               System.out.println(selected);
//		               Assert.assertEquals(selected, true);
//		               for(int j = 3 ; j<=10 ; j++) {
//		        		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//		                   System.out.println(selected);
//		    		       Thread.sleep(1000);
//		    		       Assert.assertEquals(selected, true);
//		        	   }
//		               driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//		               selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//		               Assert.assertEquals(selected, true);
//	            	   Thread.sleep(1000);
//	            	   try {
//	            	   driver.findElement(By.xpath("//li[contains(text(),'TestData" + this.randomValue2 + "')]")).click();
//	            	   } catch(Exception e) {          		 
//	            		   driver.findElement(By.xpath("//li[contains(text(),'TestData" + this.randomValue2 + "')]")).sendKeys(Keys.PAGE_DOWN);
//	            		   Thread.sleep(2000);
//	            		   js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//li[contains(text(),'TestData" + this.randomValue2 + "')]")));
//	            		   Thread.sleep(5000);
//	            		   driver.findElement(By.xpath("//li[contains(text(),'TestData" + this.randomValue2 + "')]")).click();
//	            	   }
//	    		       Thread.sleep(2000);
//	    		       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//	    		       selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();
//	    		       Assert.assertEquals(selected, true);
//	    		       Thread.sleep(2000);
//	            	   for(int j = 3 ; j<=10 ; j++) {
//	            		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//	                       System.out.println(selected);
//	        		       Thread.sleep(1000); 
//	        		       Assert.assertEquals(selected, true);
//	            	   }
//	            	   driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//	            	   Thread.sleep(1000);
//	            	  
//		}
//      
//	  
//	   
////	   @Test(priority=17,groups="createFolder")
////	   @Parameters({"name1", "numberOfSubFolders"})
////	   public void create20thNewSubFolders(String name1, int numberOfSubFolders ) throws InterruptedException         //Software folder is created at 3rd
////	   {   Integer numberOfSubFoldersINT = new Integer(numberOfSubFolders);
////		   for(int i = 0; i < numberOfSubFolders ; i++) {
////			   if( i == 0 ) {
////		    driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click(); 
////		    Thread.sleep(2000);
////		    driver.findElement(By.id("btncreatefolder")).click();
////		    Thread.sleep(2000);
////		    driver.findElement(By.id("admfoldername")).sendKeys(name1 + String.valueOf(i));
////		    Thread.sleep(2000);
////		    
////		    driver.findElement(By.xpath("//*[@id='btncreatefolderdone']")).click();
////		    WebDriverWait wait2=new WebDriverWait(driver,20);
////			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
////		    String txt= driver.findElement(By.className("top-alert-message")).getText();
////		    System.out.println(txt);
////		   // String actualtxt= "Folder created successfully. Please give appropriate permissions to make the Folder visible to others.";
////		   // Assert.assertEquals(actualtxt,txt,"Folder Exists");
////            System.out.println("createNewFolder executed");
////            Thread.sleep(15000);
////			   } else {
////				    driver.findElement(By.xpath("//h2[@title = '" + name1 + String.valueOf(i-1) + "']/a")).click();
////				    Thread.sleep(15000);
////				    driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click(); 
////				    Thread.sleep(2000);
////				    driver.findElement(By.id("btncreatefolder")).click();
////				    Thread.sleep(2000);
////				    driver.findElement(By.id("admfoldername")).sendKeys(name1 + String.valueOf(i));
////				    Thread.sleep(2000);		    
////				    driver.findElement(By.xpath("//*[@id='btncreatefolderdone']")).click();
////				    WebDriverWait wait2=new WebDriverWait(driver,20);
////					wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
////				    String txt= driver.findElement(By.className("top-alert-message")).getText();
////				    System.out.println(txt);
////				   // String actualtxt= "Folder created successfully. Please give appropriate permissions to make the Folder visible to others.";
////				    //Assert.assertEquals(actualtxt,txt,"Folder Exists");
////		            System.out.println("createNewFolder executed");
////		            Thread.sleep(30000);
////					   }
////			   }
////	   }
//		   
//		   @Test(priority=17,groups="createFolder")
//		   @Parameters({"URL", "name3", "numberOfSubFolders"})
//		   public void create20thNewSubFoldersPermissions(String URL, String name3, int numberOfSubFolders ) throws InterruptedException         //Software folder is created at 3rd
//		   {   Integer numberOfSubFoldersINT = new Integer(numberOfSubFolders);
//		       JavascriptExecutor js = (JavascriptExecutor) driver;
//		       driver.get(URL);
//		       driver.findElement(By.xpath("//a[@title = 'Admin and User Permissions']")).click();
//		       Thread.sleep(2000);
//		       driver.findElement(By.xpath("//a[@id = 'permission_module']/i")).click();
//		       Thread.sleep(2000);	
//		       SwitchCurrentWindow.GetNewWindow();
//		       Thread.sleep(2000);	
//		       driver.findElement(By.xpath("//li[contains(text(),'"+ name3 + "')]")).click();
//		       Thread.sleep(2000);
//		       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//		       Thread.sleep(2000);	
//		       boolean selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//		       Thread.sleep(2000);
//               System.out.println(selected);
//               for(int j = 3 ; j<=10 ; j++) {
//        		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//                   System.out.println(selected);
//    		       Thread.sleep(1000);
//        		   
//        	   }
//               for(int i = 1 ; i < numberOfSubFolders ; i++) {
//            	   driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//            	   Thread.sleep(1000);
//            	   try {
//            	   driver.findElement(By.xpath("//li[contains(text(),'" + name3 + String.valueOf(i) + "')]")).click();
//            	   } catch(Exception e) {          		 
//            		   driver.findElement(By.xpath("//li[contains(text(),'" + name3 + String.valueOf(i) + "')]")).sendKeys(Keys.PAGE_DOWN);
//            		   Thread.sleep(2000);
//            		   js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//li[contains(text(),'" + name3 + String.valueOf(i) + "')]")));
//            		   Thread.sleep(5000);
//            		   driver.findElement(By.xpath("//li[contains(text(),'" + name3 + String.valueOf(i) + "')]")).click();
//            	   }
//    		       Thread.sleep(2000);
//    		       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//    		       Thread.sleep(2000);
//            	   for(int j = 3 ; j<=10 ; j++) {
//            		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//                       System.out.println(selected);
//        		       Thread.sleep(1000);         		   
//            	   }
//               }
//		       
//		}
//		   
//		      @Test(priority=18, groups="createFolder")
//			  @Parameters({"URL","folder1"})
//			  public void shareFolderCreateCollaborate(String URL, String folder1) throws InterruptedException
//			  {
//				    driver.get(URL);
//					Thread.sleep(2000);
//					this.randommail = 1 + random.nextInt(1000000000);
//					this.randomValue3 = 1 + random.nextInt(1000000000);
//					this.randomValue4 = 1 + random.nextInt(1000000000);
//					JavascriptExecutor js = (JavascriptExecutor) driver;
//     				js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//*[@data-foldername='"+ folder1 + this.randomValue1 + "']/div[7]/ul/li/img")) );
//					driver.findElement(By.xpath("//*[@data-foldername='" + folder1 + this.randomValue1 + "']/div[7]/ul/li/img")).click();
//					Thread.sleep(2000);
//					driver.findElement(By.linkText("Share")).click();
//					Thread.sleep(2000);
//					
//					driver.findElement(By.id("admsharesubject")).sendKeys("Folder Shared to Collaborate" + this.randommail);
//					driver.findElement(By.xpath("//*[@id='lblallowupload']/span")).click();
//					driver.findElement(By.id("admaddreceiver")).click();
//					driver.findElement(By.id("adm_mobileemail_txt1_0")).sendKeys("user1prod@protonmail.com");
//					driver.findElement(By.id("btnshareemaildone")).click();
//					driver.findElement(By.id("admsharesend")).click();
//					WebDriverWait wait=new WebDriverWait(driver,20);
//					wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
//					String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
//					System.out.println(Popupmsg);
//					Assert.assertEquals("Collaboration link shared successfully", Popupmsg);
//					driver.get("https://mail.protonmail.com/login");
//					driver.findElement(By.id("username")).sendKeys("user1prod@protonmail.com");
//					driver.findElement(By.name("password")).sendKeys("Test@123");
//					Thread.sleep(2000);
//					driver.findElement(By.id("login_btn")).click();
//					Thread.sleep(2000);
//					WebDriverWait wait4=new WebDriverWait(driver,20);
//					wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Folder Shared to Collaborate" + this.randommail+ "']")));
//					driver.findElement(By.xpath("//span[text()='Folder Shared to Collaborate" + this.randommail + "']")).click();
//					Thread.sleep(4000);
//					driver.findElement(By.linkText("View File(s)/Folder")).click();
//					SwitchCurrentWindow.GetNewWindow();
//					Thread.sleep(4000);
//
//					WebDriverWait wait1=new WebDriverWait(driver,20);
//					wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncreatefolderpopup")));
//					driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
//					  WebDriverWait wait2=new WebDriverWait(driver,20);
//					  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btncreatefolder']")));
//					  driver.findElement(By.id("btncreatefolder")).click();
//					  WebDriverWait wait12=new WebDriverWait(driver,20);
//					  wait12.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='admfoldername']")));
//					  driver.findElement(By.id("admfoldername")).sendKeys(folder1 + this.randomValue3);
//					  WebDriverWait wait13=new WebDriverWait(driver,20);
//					  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='ADD']")));
//					  driver.findElement(By.xpath("//p[text()='ADD']")).click();
//					  Thread.sleep(4000);
//					  WebDriverWait wait5=new WebDriverWait(driver,20);
//					  wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + folder1 + this.randomValue3 + "')]")));
//					  driver.findElement(By.xpath("//*[contains(text(),'" + folder1 + this.randomValue3 + "')]")).click();
//					  WebDriverWait wait6=new WebDriverWait(driver,20);
//						wait6.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncreatefolderpopup")));
//						driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
//						  WebDriverWait wait7=new WebDriverWait(driver,20);
//						  wait7.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btncreatefolder']")));
//						  driver.findElement(By.id("btncreatefolder")).click();
//						  WebDriverWait wait18=new WebDriverWait(driver,20);
//						  wait18.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='admfoldername']")));
//						  driver.findElement(By.id("admfoldername")).sendKeys(folder1 + this.randomValue4);
//						  WebDriverWait wait19=new WebDriverWait(driver,20);
//						  wait19.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='ADD']")));
//						  driver.findElement(By.xpath("//p[text()='ADD']")).click();
//						  Thread.sleep(4000);
//					
//			  }
//		      
//		       @Test(priority=19,groups="createFolder")
//			   @Parameters({"URL", "folder1"})
//			   public void createSharedFoldersPermissions(String URL, String folder1) throws InterruptedException         //Software folder is created at 3rd
//			   {  
//			       driver.get(URL);
//			       JavascriptExecutor js = (JavascriptExecutor) driver;
//			       driver.findElement(By.xpath("//a[@title = 'Admin and User Permissions']")).click();
//			       Thread.sleep(2000);
//			       driver.findElement(By.xpath("//a[@id = 'permission_module']/i")).click();
//			       Thread.sleep(2000);	
//			       SwitchCurrentWindow.GetNewWindow();
//			       Thread.sleep(2000);	
//			       driver.findElement(By.xpath("//li[contains(text(),'" + folder1 + this.randomValue1+ "')]")).click();
//			       Thread.sleep(2000);
//			       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//			       Thread.sleep(2000);	
//			       boolean selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//			       Thread.sleep(2000);
//	               System.out.println(selected);
//	               Assert.assertEquals(selected, true);
//	               for(int j = 3 ; j<=10 ; j++) {
//	        		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//	                   System.out.println(selected);
//	    		       Thread.sleep(1000);
//	    		       Assert.assertEquals(selected, true);
//	        		   
//	        	   }
//	               driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//            	   Thread.sleep(1000);
//            	   selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//            	   Assert.assertEquals(selected, true);
//            	   try {
//            	   driver.findElement(By.xpath("//li[contains(text(),'" + folder1 + this.randomValue3+ "')]")).click();
//            	   } catch(Exception e) {          		 
//            		   driver.findElement(By.xpath("//li[contains(text(),'" + folder1 + this.randomValue3+ "')]")).sendKeys(Keys.PAGE_DOWN);
//            		   Thread.sleep(2000);
//            		   js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//li[contains(text(),'" + folder1 + this.randomValue3+ "')]")));
//            		   Thread.sleep(5000);
//            		   driver.findElement(By.xpath("//li[contains(text(),'" + folder1 + this.randomValue3+ "')]")).click();
//            	   }
//    		       Thread.sleep(2000);
//    		       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//    		       Thread.sleep(2000);
//    		       selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();
//    		       Assert.assertEquals(selected, true);
//            	   for(int j = 3 ; j<=10 ; j++) {
//            		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//                       System.out.println(selected);
//        		       Thread.sleep(1000);
//        		       Assert.assertEquals(selected, true);
//            	   }
//            	   driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//            	   Thread.sleep(1000);
//            	   selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//            	   Assert.assertEquals(selected, true);
//            	   try {
//            	   driver.findElement(By.xpath("//*[contains(text(),'" + folder1 + this.randomValue4+ "')]")).click();
//            	   } catch(Exception e) {          		 
//            		   driver.findElement(By.xpath("//*[contains(text(),'" + folder1 + this.randomValue4+ "')]")).sendKeys(Keys.PAGE_DOWN);
//            		   Thread.sleep(2000);
//            		   js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[contains(text(),'" + folder1 + this.randomValue4 + "')]")));
//            		   Thread.sleep(5000);
//            		   driver.findElement(By.xpath("//*[contains(text(),'" + folder1 + this.randomValue4 + "')]")).click();
//            	   }
//    		       Thread.sleep(2000);
//    		       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//    		       Thread.sleep(2000);
//    		       selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//    		       Assert.assertEquals(selected, true);
//            	   for(int j = 3 ; j<=10 ; j++) {
//            		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//                       System.out.println(selected);
//        		       Thread.sleep(1000); 
//        		       Assert.assertEquals(selected, true);
//            	   }
//	}
//		       
//		          @Test(priority=20, groups="uploadFolder")
//				  @Parameters({"URL","folder3"})
//				  public void shareFolderUploadCollaborate(String URL, String folder3) throws InterruptedException
//				  {
//					    driver.get(URL);
//						Thread.sleep(2000);
//						this.randommail = 1 + random.nextInt(1000000000);
//						this.randomValue3 = 1 + random.nextInt(1000000000);;
//						this.randomValue4 = 1 + random.nextInt(1000000000);
//						String sourceDir = System.getProperty("user.dir") + "/src/main/java/config/TestData";
//						String destinationDir1 = System.getProperty("user.dir") + "/src/main/java/config/TestData" + this.randomValue3;
//						String destinationDir2 = System.getProperty("user.dir") + "/src/main/java/config/TestData" + this.randomValue4;
//		                this.cpDirectory(sourceDir, destinationDir1);
//		                this.cpDirectory(sourceDir, destinationDir2);
//						JavascriptExecutor js = (JavascriptExecutor) driver;
//						js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@data-foldername='"+folder3 + this.randomValue1 + "']/div[7]/ul/li/img")));
//						driver.findElement(By.xpath("//*[@data-foldername='"+ folder3 + this.randomValue1 + "']/div[7]/ul/li/img")).click();  
//						Thread.sleep(2000);
//						Thread.sleep(2000);
//						driver.findElement(By.linkText("Share")).click();
//						Thread.sleep(2000);
//						
//						driver.findElement(By.id("admsharesubject")).sendKeys("Folder Shared to Collaborate" + this.randommail);
//						driver.findElement(By.xpath("//*[@id='lblallowupload']/span")).click();
//						driver.findElement(By.id("admaddreceiver")).click();
//						driver.findElement(By.id("adm_mobileemail_txt1_0")).sendKeys("user1prod@protonmail.com");
//						driver.findElement(By.id("btnshareemaildone")).click();
//						driver.findElement(By.id("admsharesend")).click();
//						WebDriverWait wait=new WebDriverWait(driver,20);
//						wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
//						String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
//						System.out.println(Popupmsg);
//						Assert.assertEquals("Collaboration link shared successfully", Popupmsg);
//						driver.get("https://mail.protonmail.com/login");
//						driver.findElement(By.id("username")).sendKeys("user1prod@protonmail.com");
//						driver.findElement(By.name("password")).sendKeys("Test@123");
//						Thread.sleep(2000);
//						driver.findElement(By.id("login_btn")).click();
//						Thread.sleep(2000);
//						WebDriverWait wait4=new WebDriverWait(driver,20);
//						wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Folder Shared to Collaborate" + this.randommail + "']")));
//						driver.findElement(By.xpath("//span[text()='Folder Shared to Collaborate" + this.randommail + "']")).click();
//						Thread.sleep(4000);
//						driver.findElement(By.linkText("View File(s)/Folder")).click();
//						SwitchCurrentWindow.GetNewWindow();
//						Thread.sleep(4000);
//
//						WebDriverWait wait1=new WebDriverWait(driver,20);
//						wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncreatefolderpopup")));
//						driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
//						  WebDriverWait wait2=new WebDriverWait(driver,20);
//						  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuploadfolder']")));
//						  driver.findElement(By.id("btnuploadfolder")).click();
//						  WebDriverWait wait12=new WebDriverWait(driver,20);
//						  WebDriverWait wait13=new WebDriverWait(driver,20);
//						  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='adm_fileuploadId']")));
//                        Thread.sleep(4000);
//						  driver.switchTo().frame("adm_fileuploadId");
//
//						  WebElement folderInput = driver.findElement(By.id("uploadFolderId"));
//
//						  folderInput.sendKeys(destinationDir1);
//						  Thread.sleep(10000);
//						  WebDriverWait wait5=new WebDriverWait(driver,20);
//						  wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@data-foldername , '"+ folder3+ this.randomValue3 + "')]/div/h2/a")));
//						  driver.findElement(By.xpath("//div[contains(@data-foldername , '"+ folder3 + this.randomValue3 + "')]/div/h2/a")).click();
//						  WebDriverWait wait6=new WebDriverWait(driver,20);
//							wait6.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncreatefolderpopup")));
//							driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
//							  WebDriverWait wait7=new WebDriverWait(driver,20);
//							  wait7.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuploadfolder']")));
//							  driver.findElement(By.id("btnuploadfolder")).click();
//							  WebDriverWait wait8=new WebDriverWait(driver,20);
//							  wait8.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='adm_fileuploadId']")));
//	                        Thread.sleep(4000);
//							  driver.switchTo().frame("adm_fileuploadId");
//
//							  WebElement folderInput2 = driver.findElement(By.id("uploadFolderId"));
//
//							  folderInput2.sendKeys(destinationDir2);
//							  Thread.sleep(10000);
//                          driver.switchTo().parentFrame();						
//				  }
//			      
//			       @Test(priority=21, groups="uploadFolder")
//				   @Parameters({"URL", "folder3"})
//				   public void uploadSharedFoldersPermissions(String URL, String folder3) throws InterruptedException         //Software folder is created at 3rd
//				   {  
//				       driver.get(URL);
//				       JavascriptExecutor js = (JavascriptExecutor) driver;
//				       driver.findElement(By.xpath("//a[@title = 'Admin and User Permissions']")).click();
//				       Thread.sleep(2000);
//				       driver.findElement(By.xpath("//a[@id = 'permission_module']/i")).click();
//				       Thread.sleep(2000);	
//				       SwitchCurrentWindow.GetNewWindow();
//				       Thread.sleep(2000);	
//				       driver.findElement(By.xpath("//li[contains(text(),'" + folder3 + this.randomValue1 + "')]")).click();
//				       Thread.sleep(2000);
//				       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//				       Thread.sleep(2000);	
//				       boolean selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//				       Thread.sleep(2000);
//		               System.out.println(selected);
//		               Assert.assertEquals(selected, true);
//		               for(int j = 3 ; j<=10 ; j++) {
//		        		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//		                   System.out.println(selected);
//		    		       Thread.sleep(1000);
//		    		       Assert.assertEquals(selected, true);
//		        		   
//		        	   }
//		               driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//	            	   Thread.sleep(1000);
//	            	   selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();
//	            	   Assert.assertEquals(selected, true);
//	            	   try {
//	            	   driver.findElement(By.xpath("//li[contains(text(),'" + folder3 + this.randomValue3 + "')]")).click();
//	            	   } catch(Exception e) {          		 
//	            		   driver.findElement(By.xpath("//li[contains(text(),'" + folder3 + this.randomValue3 + "')]")).sendKeys(Keys.PAGE_DOWN);
//	            		   Thread.sleep(2000);
//	            		   js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//li[contains(text(),'" + folder3 + this.randomValue3 + "')]")));
//	            		   Thread.sleep(5000);
//	            		   driver.findElement(By.xpath("//li[contains(text(),'" + folder3 + this.randomValue3 + "')]")).click();
//	            	   }
//	    		       Thread.sleep(2000);
//	    		       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//	    		       Thread.sleep(2000);
//	    		       selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//	    		       Assert.assertEquals(selected, true);
//	            	   for(int j = 3 ; j<=10 ; j++) {
//	            		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//	                       System.out.println(selected);
//	        		       Thread.sleep(1000);      
//	        		       Assert.assertEquals(selected, true);
//	            	   }
//	            	   driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//	            	   Thread.sleep(1000);
//	            	   selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//	            	   Assert.assertEquals(selected, true);
//	            	   try {
//	            	   driver.findElement(By.xpath("//li[contains(text(),'" + folder3 + this.randomValue4 + "')]")).click();
//	            	   } catch(Exception e) {          		 
//	            		   driver.findElement(By.xpath("//li[contains(text(),'" + folder3 + this.randomValue4 + "')]")).sendKeys(Keys.PAGE_DOWN);
//	            		   Thread.sleep(2000);
//	            		   js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//li[contains(text(),'" + folder3 + this.randomValue4 + "')]")));
//	            		   Thread.sleep(5000);
//	            		   driver.findElement(By.xpath("//li[contains(text(),'" + folder3 + this.randomValue4 + "')]")).click();
//	            	   }
//	    		       Thread.sleep(2000);
//	    		       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//	    		       Thread.sleep(2000);
//	    		       Assert.assertEquals(selected, true);
//	    		       selected = driver.findElement(By.xpath("//input[@title='Reema De']")).isSelected();	
//	            	   for(int j = 3 ; j<=10 ; j++) {
//	            		   selected = driver.findElement(By.xpath("//input[@title='Reema De']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//	                       System.out.println(selected);
//	        		       Thread.sleep(1000); 
//	        		       Assert.assertEquals(selected, true);
//	            	   }
//		}
////			       
//					       @Test(priority=22,groups="createAdminUser")
//						   @Parameters({"URL", "name1", "name2", "email"})
//						   public void createAdminUser(String URL, String name1, String name2, String email) throws InterruptedException         //Software folder is created at 3rd
//						   {  
//						       driver.get(URL);
//						       JavascriptExecutor js = (JavascriptExecutor) driver;
//						       driver.findElement(By.xpath("//a[@title = 'Admin and User Permissions']")).click();
//						       Thread.sleep(2000);
//						       driver.findElement(By.xpath("//div[@title = 'Admin Controls']/a/img")).click();
//						       Thread.sleep(2000);	
//						       SwitchCurrentWindow.GetNewWindow();
//						       Thread.sleep(2000);	
//						       driver.findElement(By.xpath("//a[@title = 'Add users']/i")).click();
//						       Thread.sleep(2000);
//						       driver.findElement(By.xpath("//input[@id = 'txt_adduser_firstname']")).sendKeys(name1);
//						       Thread.sleep(2000);
//						       driver.findElement(By.xpath("//input[@id = 'txt_adduser_lastname']")).sendKeys(name2);
//						       Thread.sleep(2000);	
//						       driver.findElement(By.xpath("//input[@id = 'txt_adduser_loginid']")).sendKeys(email);
//						       Thread.sleep(2000);
//						       driver.findElement(By.xpath("//input[@id = 'chk_adduser_admin']")).click();
//						       Thread.sleep(2000);
//						       driver.findElement(By.xpath("//button[@id = 'adduserconfirm']")).click();
//						       Thread.sleep(2000);   
//				}
////					       
//					       @Test(priority=23, groups="createFolder")
//					       @Parameters({"URL", "folder1"})
//						   public void createNewFolder(String URL, String folder1) throws InterruptedException         //Software folder is created at 3rd
//						   {
//					    	    driver.get(URL);
//					    	    this.randomValue5 = 1 + random.nextInt(1000000000);
//							    driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click(); 
//							    driver.findElement(By.id("btncreatefolder")).click();
//							    driver.findElement(By.id("admfoldername")).sendKeys(folder1 + this.randomValue5);
//							    Thread.sleep(2000);
//							    driver.findElement(By.xpath("//*[@id='btncreatefolderdone']")).click();
//							    WebDriverWait wait2=new WebDriverWait(driver,20);
//								wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
//							    String txt= driver.findElement(By.className("top-alert-message")).getText();
//							    System.out.println(txt);
////							    String actualtxt= "Folder created successfully. Please give appropriate permissions to make the Folder visible to others.";
////							    Assert.assertEquals(actualtxt,txt,"Folder Exists");
//					            System.out.println("createNewFolder executed");
//						   }
//					       
//					       
//					       @Test(priority=24 ,groups="createFolder")
//						   @Parameters({"URL", "folder1", "name1"})
//						   public void createAdminFoldersPermissions(String URL, String folder1,String name1) throws InterruptedException         //Software folder is created at 3rd
//						   {  
//						       driver.get(URL);
//						       JavascriptExecutor js = (JavascriptExecutor) driver;
//						       driver.findElement(By.xpath("//a[@title = 'Admin and User Permissions']")).click();
//						       Thread.sleep(2000);
//						       driver.findElement(By.xpath("//a[@id = 'permission_module']/i")).click();
//						       Thread.sleep(2000);	
//						       SwitchCurrentWindow.GetNewWindow();
//						       Thread.sleep(2000);	
//						       driver.findElement(By.xpath("//li[contains(text(),'" + folder1 + this.randomValue5 + "')]")).click();
//						       Thread.sleep(2000);
//						       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//						       Thread.sleep(2000);	
//						       boolean selected = driver.findElement(By.xpath("//input[contains(@title,'" + name1 +"')]")).isSelected();	
//						       Thread.sleep(2000);
//				               System.out.println(selected);
//				               Assert.assertEquals(selected, true);
//				               for(int j = 3 ; j<=10 ; j++) {
//				        		   selected = driver.findElement(By.xpath("//input[@title='Rama Reddy']//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//				                   System.out.println(selected);
//				    		       Thread.sleep(1000);
//					               Assert.assertEquals(selected, true);		        		   
//				        	   }
//				               driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//			            	   Thread.sleep(1000);	            	   
//				}
//					       
//					      
//					          @Test(priority=25,groups="createFolder")
//							  @Parameters({"URL"})
//							  public void uploadAdminFolders(String URL) throws InterruptedException
//							  {
//								      driver.get(URL);
//	                                  Thread.sleep(4000);
//	                                  String sourceDir = System.getProperty("user.dir") + "/src/main/java/config/TestData";
//	            					  String destinationDir = System.getProperty("user.dir") + "/src/main/java/config/TestData" + this.randomValue5;
//	            	                  this.cpDirectory(sourceDir, destinationDir);
//								      WebDriverWait wait3=new WebDriverWait(driver,20);
//									  wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btncreatefolderpopup']")));
//									  driver.findElement(By.id("btncreatefolderpopup")).click();
//	                                  Thread.sleep(4000);
//									  WebDriverWait wait2=new WebDriverWait(driver,20);
//									  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuploadfolder']")));
//									  driver.findElement(By.id("btnuploadfolder")).click();
//	                                  Thread.sleep(4000);
//									  WebDriverWait wait13=new WebDriverWait(driver,20);
//									  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='adm_fileuploadId']")));
//	                                  Thread.sleep(4000);
//									  driver.switchTo().frame("adm_fileuploadId");
//	
//									  WebElement folderInput = driver.findElement(By.id("uploadFolderId"));
//	
//									  folderInput.sendKeys(destinationDir);
//									  Thread.sleep(10000);
//									  driver.switchTo().parentFrame();						
//							  }
//						      
//						       @Test(priority=26,groups="createFolder")
//							   @Parameters({"URL", "name1"})
//							   public void uploadAdminFoldersPermissions(String URL, String name1) throws InterruptedException         //Software folder is created at 3rd
//							   {  
//							       driver.get(URL);
//							       JavascriptExecutor js = (JavascriptExecutor) driver;
//							       driver.findElement(By.xpath("//a[@title = 'Admin and User Permissions']")).click();
//							       Thread.sleep(2000);
//							       driver.findElement(By.xpath("//a[@id = 'permission_module']/i")).click();
//							       Thread.sleep(2000);	
//							       SwitchCurrentWindow.GetNewWindow();
//							       Thread.sleep(2000);	
//							       driver.findElement(By.xpath("//li[contains(text(),'TestData"+ this.randomValue5 + "')]")).click();
//							       Thread.sleep(2000);
//							       driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//							       Thread.sleep(2000);	
//							       boolean selected = driver.findElement(By.xpath("//input[contains(@title,'"+ name1 + "')]")).isSelected();	
//							       Thread.sleep(2000);
//					               System.out.println(selected);
//					               Assert.assertEquals(selected, true);
//					               for(int j = 3 ; j<=10 ; j++) {
//					        		   selected = driver.findElement(By.xpath("//input[contains(@title,'"+ name1 + "')]//..//..//..//td['" + j + "']//div//input")).isSelected();		       
//					                   System.out.println(selected);
//					    		       Thread.sleep(1000);
//					    		       Assert.assertEquals(selected, true);
//					        		   
//					        	   }
//					               driver.findElement(By.xpath("//a[@id = 'btn_refresh']/i")).click();
//				            	   Thread.sleep(1000);
//				            	   
//					} 
////					     
//						       @Test(priority=27,groups="createFolder")
//								  @Parameters({"URL", "filename1"})
//								  public void uploadFileVisibility(String URL, String filename1) throws InterruptedException
//								  {
//									      driver.get(URL);
//		                                  Thread.sleep(4000);
//		                                  this.randomValue6 = 1 + random.nextInt(1000000000);
//		                                  String sourceDir = System.getProperty("user.dir") + "/src/main/java/config/TestDataUnique";
//		            					  String destinationDir = System.getProperty("user.dir") + "/src/main/java/config/TestDataUnique" + this.randomValue6;
//		            	                  this.cpDirectory(sourceDir, destinationDir);
//									      WebDriverWait wait3=new WebDriverWait(driver,20);
//										  wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btncreatefolderpopup']")));
//										  driver.findElement(By.id("btncreatefolderpopup")).click();
//		                                  Thread.sleep(4000);
//										  WebDriverWait wait2=new WebDriverWait(driver,20);
//										  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuploadfolder']")));
//										  driver.findElement(By.id("btnuploadfolder")).click();
//		                                  Thread.sleep(4000);
//										  WebDriverWait wait13=new WebDriverWait(driver,20);
//										  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='adm_fileuploadId']")));
//		                                  Thread.sleep(4000);
//										  driver.switchTo().frame("adm_fileuploadId");
//		
//										  WebElement folderInput = driver.findElement(By.id("uploadFolderId"));
//		
//										  folderInput.sendKeys(destinationDir);
//										  Thread.sleep(10000);
//										  driver.get(URL);
//										  WebDriverWait wait5=new WebDriverWait(driver,20);
//										  wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id = 'searchlink']/p")));
//										  driver.findElement(By.xpath("//a[@id = 'searchlink']/p")).click();
//		                                  Thread.sleep(10000);
//		                     
//		                                  WebDriverWait wait6=new WebDriverWait(driver,20);
//										  wait6.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id = 'searchfname']")));
//										  driver.findElement(By.xpath("//input[@id = 'searchfname']")).click();
//										  Thread.sleep(10000);
//										  driver.findElement(By.xpath("//input[@id = 'searchfname']")).sendKeys(filename1);
//		                                  Thread.sleep(10000);
//		                                  WebDriverWait wait7=new WebDriverWait(driver,20);
//										  wait7.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Search')]")));
//										  driver.findElement(By.xpath("//a[contains(text(),'Search')]")).click();
//		                                  Thread.sleep(4000);
//		                                  try {
//		                                	  WebDriverWait wait8=new WebDriverWait(driver,20);
//											  wait8.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + filename1 +"')]")));
//											  driver.findElement(By.xpath("//span[contains(text(),'" + filename1 +"')]")).click();
//											  Assert.assertEquals(true, true);
//			                                  Thread.sleep(4000);
//		                                  } catch ( Exception e ) {
//		                                	  Assert.assertEquals(false, true);
//		                                  }
//										  driver.switchTo().parentFrame();						
//								  }
//						       
//						       
//						       @Test(priority=28,groups="createFolder")
//								  @Parameters({"URL", "filename2", "filename1"})
//								  public void downloadFolder300mb(String URL, String filename2, String filename1) throws InterruptedException
//								  {
//									      driver.get(URL);
//		                                  Thread.sleep(4000);
//		                                  boolean complete = false;
//		                                  int loopCount = 10;
//		                                  this.randomValue6 = 1 + random.nextInt(1000000000);
//		                                  String sourceDir = System.getProperty("user.dir") + "/src/main/java/config/TestDataUnique";
//		            					  String destinationDir = System.getProperty("user.dir") + "/src/main/java/config/TestDataUnique" + this.randomValue6;
//		            	                  this.cpDirectory(sourceDir, destinationDir);
//									      WebDriverWait wait3=new WebDriverWait(driver,20);
//										  wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btncreatefolderpopup']")));
//										  driver.findElement(By.id("btncreatefolderpopup")).click();
//		                                  Thread.sleep(4000);
//										  WebDriverWait wait2=new WebDriverWait(driver,20);
//										  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuploadfolder']")));
//										  driver.findElement(By.id("btnuploadfolder")).click();
//		                                  Thread.sleep(4000);
//										  WebDriverWait wait13=new WebDriverWait(driver,20);
//										  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='adm_fileuploadId']")));
//		                                  Thread.sleep(4000);
//										  driver.switchTo().frame("adm_fileuploadId");
//		
//										  WebElement folderInput = driver.findElement(By.id("uploadFolderId"));
//		
//										  folderInput.sendKeys(destinationDir);
//										  Thread.sleep(10000);
//										  driver.get(URL);
//										  WebDriverWait wait5=new WebDriverWait(driver,20);
//										  wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id = 'searchlink']/p")));
//										  driver.findElement(By.xpath("//a[@id = 'searchlink']/p")).click();
//		                                  Thread.sleep(4000);
//		                     
//		                                  WebDriverWait wait6=new WebDriverWait(driver,20);
//										  wait6.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id = 'searchfname']")));
//										  driver.findElement(By.xpath("//input[@id = 'searchfname']")).click();
//										  Thread.sleep(10000);
//										  driver.findElement(By.xpath("//input[@id = 'searchfname']")).sendKeys(filename2);
//		                                  Thread.sleep(10000);
//		                                  WebDriverWait wait7=new WebDriverWait(driver,20);
//										  wait7.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Search')]")));
//										  driver.findElement(By.xpath("//a[contains(text(),'Search')]")).click();
//		                                  Thread.sleep(10000);
//		                                  try {
//		                          
//			                                  File file = new File(filename2);
//			                                  System.out.println(file.exists());
//			                                  if(file.exists()) {file.delete();}
//			                                  WebDriverWait wait8=new WebDriverWait(driver,20);
//											  wait8.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'" + filename2 +"')]")));
//											  driver.findElement(By.xpath("//span[contains(text(),'" + filename2 +"')]")).click();
//			                                  Thread.sleep(4000);
//											  
//			                                	  for( int i = 0 ; i <= loopCount && (!complete) ; i++ ) {
//			                                		  
//
//			                                	  if(file.exists()) {
//			                                		  complete = true;
//			                                		  break;
//			                                		  
//			                                	  }
//			                                	  }
//			                                	  Thread.sleep(20000);
//		                                  } catch ( Exception e ) {
//		                                	  Assert.assertEquals(false, true);
//		                                  }
//										  driver.switchTo().parentFrame();	
//										  if(complete) {Assert.assertEquals(true, true);}
//										  else {
//											  Assert.assertEquals(false, true);
//											  
//										  }
//										  }
						       @Test(priority=29)
						 	  @Parameters({"URL","filepath","folder1"})
						 	  public void requestFileDeposit(String URL,String filepath,String folder1) throws InterruptedException
						 	  { 
						 		driver.get(URL);
							    JavascriptExecutor js = (JavascriptExecutor) driver;
								WebDriverWait wait3=new WebDriverWait(driver,20);
								wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-foldername,'"+ folder1 + this.randomValue1+"')]/div[7]/ul/li/img")));
								js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[contains(@data-foldername,'"+ folder1 + this.randomValue1+"')]/div[7]/ul/li/img")));
								driver.findElement(By.xpath("//*[contains(@data-foldername,'"+ folder1 + this.randomValue1+"')]/div[7]/ul/li/img")).click();
								Thread.sleep(10000);
								wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-foldername,'"+ folder1 + this.randomValue1+"') and contains(text(), 'Request File Deposit')]")));
								driver.findElement(By.xpath("//*[contains(@data-foldername,'"+ folder1 + this.randomValue1+"') and contains(text(), 'Request File Deposit')]")).click();
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
						 		
						 		String handle=driver.getWindowHandle();
						 	//	System.out.println(handle);
						 		Set<String> handles=driver.getWindowHandles();
						 	//	System.out.println(handles);
						 		
						 		ArrayList<String> a1=new ArrayList<String> (handles);
						 		driver.switchTo().window(a1.get(1));
						 		WebDriverWait wait2=new WebDriverWait(driver,20);
						 		   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("adm_fileuploadId")));
						 		driver.switchTo().frame("adm_fileuploadId");
						 		WebElement fileInput = driver.findElement(By.id("uploadFileId"));
						 		  
						 	  fileInput.sendKeys(System.getProperty("user.dir") + "/src/main/java/config/TestData/" + filepath);
						 	 
						 	  driver.switchTo().parentFrame();
						 	  
						 	  WebDriverWait wait41=new WebDriverWait(driver,20);
						 	  wait41.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
						 	  
						 	  String txt= driver.findElement(By.className("top-alert-message")).getText();
						 	  System.out.println(txt);
						 	 
						 	  Assert.assertEquals("Your upload is completed successfully. this link is no longer valid", txt);

						 	  System.out.println("requestFiledeposit executed");
						 	  
						 	  } 
						 	  
	}
		   
		   
	





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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

	public class UserModule
	{
		
		   
		   public static  WebDriver driver;
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
		   
		   
		   @BeforeClass(groups= {"All"})
		   @Parameters({"id1","password","corporateID","URL"})
		   public void setUp(String id1,String password,String corporateID,String URL)
		   {
		  	 driver=new ChromeDriver();
		  	 driver.manage().window().maximize();
		  	 driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		  	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  	 driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 
			 driver.manage().deleteAllCookies();
		  	 driver.get(URL);
		     driver.findElement(By.linkText("Business User")).click();
			 driver.findElement(By.id("loginid")).sendKeys(id1);
			 driver.findElement(By.id("password")).sendKeys(password);
			 driver.findElement(By.id("corporateid")).sendKeys(corporateID);
			 String capchaval=JOptionPane.showInputDialog("Please enter the capcha value");
			 driver.findElement(By.id("logincaptcha")).sendKeys(capchaval);
			 driver.findElement(By.id("btnlogin")).click();
		   }
		   
		/*   @BeforeTest
		   public void prerequisite()
		   {
			   driver.findElement(By.xpath("//*[@data-foldername='All']/h2/a")).isDisplayed();
			   driver.findElement(By.xpath("//*[@data-foldername='Download']/h2/a")).isDisplayed();
			   driver.findElement(By.xpath("//*[@data-foldername='Upload']/h2/a")).isDisplayed();
			   driver.findElement(By.xpath("//*[@data-foldername='View']/h2/a")).isDisplayed();
			   
		   }*/
		   
		  
	      @AfterClass
		   public void tearDown() throws InterruptedException
		   {
			 
			   driver.quit();

		   }
		   
		   
		   
		   @Test(priority=1,groups= {"All"})
		   public void createFolderUser() throws InterruptedException
		   {
			   driver.findElement(By.id("btncreatefolderpopup")).click();
			   WebDriverWait wait2=new WebDriverWait(driver,20);
			   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
			   String txt= driver.findElement(By.className("top-alert-message")).getText();
			   System.out.println(txt);
			   String actualtxt="Sorry, you do not have right to perform the action. Please contact your administrator";
			   Assert.assertEquals(actualtxt,txt);
			  
			   System.out.println("createFolderUser executed");
		   }
		   
		   @Test(priority=3,groups= {"All"})
		   @Parameters({"foldername1","subFoldername","URL"})
		   public void subFolder(String foldername1, String subFoldername,String URL) throws InterruptedException   //creating subfolder inside All
		   {    
			   
			   driver.get(URL);
			 
			   driver.findElement(By.xpath("//*[@data-foldername='"+foldername1+"']/h2/a")).click(); 
			//   Alert alt=driver.switchTo().alert();
			//   alt.dismiss();
			   Thread.sleep(10000);
			   driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click();
			   Thread.sleep(2000);
			  // WebDriverWait wait2=new WebDriverWait(driver,20);
			//   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdownfolder1")));
			//   WebDriverWait wait3=new WebDriverWait(driver,20);
			//   wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btncreatefolder']")));
			   driver.findElement(By.xpath("//*[@id='btncreatefolder']")).click();
			   driver.findElement(By.xpath("//*[@id='admfoldername']")).sendKeys(subFoldername);
			   driver.findElement(By.xpath("//*[@id='btncreatefolderdone']")).click();
			   WebDriverWait wait5=new WebDriverWait(driver,20);
			   wait5.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
			   String txt= driver.findElement(By.className("top-alert-message")).getText();
			
			   if(txt.equals("Folder created successfully"))
			   {
				   String actualtxt= "Folder created successfully";
				   Assert.assertEquals(txt,actualtxt);
				   System.out.println("Sub-Folder created successfully");
			   }
			   else 
			   {
				   String actualtxt= "Folder Exists";
				   Assert.assertEquals(txt,actualtxt);
				   System.out.println(txt);
			   }
			 
			 
			   System.out.println("subFolder executed");
			 }
		   
		  

		      @Test(priority=4,groups= {"All"})
			  @Parameters({"URL","foldername1","subFoldername","renamedFolder"})
			  public void rename(String URL,String foldername1 ,String subFoldername,String renamedFolder) throws InterruptedException
			  {
				   driver.get(URL);
				   driver.findElement(By.xpath("//*[@data-foldername='"+foldername1+"']/h2/a")).click(); //*[@id="adm_foldername_0"]/h2/a
				   driver.findElement(By.xpath("//*[@data-foldername='"+subFoldername+"']/div[7]/ul/li/img")).click();
				   driver.findElement(By.linkText("Rename")).click();
				   driver.findElement(By.id("rename_admfoldername")).clear();
				   driver.findElement(By.id("rename_admfoldername")).sendKeys(renamedFolder);
				   driver.findElement(By.id("rename_btncreatefolderdone")).click();
				   WebDriverWait wait1=new WebDriverWait(driver,20);
				   wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
				   String txt= driver.findElement(By.className("top-alert-message")).getText();
				   System.out.println(txt);
				   String actualtxt= "Folder renamed successfully";
				   Assert.assertEquals(actualtxt,txt);
				   System.out.println("rename executed");
				 
			  }
		      
		      @Test(priority=5,groups= {"All"})
			  @Parameters({"URL","foldername1","renamedFolder"})
			  public void restore(String URL,String foldername1,String renamedFolder) throws InterruptedException   
			  {
				  driver.get(URL);
				  driver.findElement(By.xpath("//*[@data-foldername='"+foldername1+"']/h2/a")).click(); 
			      driver.findElement(By.xpath("//*[@data-foldername='"+renamedFolder+"']/div[7]/ul/li/img")).click();
			      driver.findElement(By.linkText("Delete")).click();
			      
			      Thread.sleep(4000);
			      driver.findElement(By.xpath("//*[@data-foldername='"+renamedFolder+"']/div[7]/ul/li/img")).click();
			      driver.findElement(By.linkText("Restore")).click();
			      Thread.sleep(4000);
			      System.out.println("restore executed");
			  }
			  
			  
		       
		       @Test(priority=11,groups= {"All"})
			   @Parameters({"URL","foldername1","renamedFolder"})
			   public void deleteFolder(String URL,String foldername1,String renamedFolder) throws InterruptedException
			   {
				 driver.get(URL);
				 driver.findElement(By.xpath("//*[@data-foldername='"+foldername1+"']/h2/a")).click();  
				 driver.findElement(By.xpath("//*[@data-foldername='"+renamedFolder+"']/div[7]/ul/li/img")).click();
				 driver.findElement(By.linkText("Delete")).click();
			     
			       Thread.sleep(5000);
			       driver.findElement(By.xpath("//*[@data-foldername='"+renamedFolder+"']/div[7]/ul/li/img")).click();//*[@id="adm_delete_folder_4"]
			       driver.findElement(By.linkText("Delete Permanently")).click();
			       driver.findElement(By.xpath("//*[@id='btndeleteconfirmdone']")).click();
			       
			       Thread.sleep(6000);
			  }
		      
		       @Test(priority=6,groups= {"All"})
			   @Parameters({"filepath","URL"})
			  public void uploadFile(String filepath,String URL) throws InterruptedException, IOException
			  {  
				   driver.get(URL);
				   driver.findElement(By.xpath("//*[@data-foldername='All']/h2/a")).click();
				   Thread.sleep(3000);
				   driver.findElement(By.xpath("//*[@data-foldername='Test01']/h2/a")).click();
				   
				    Thread.sleep(3000);
				    
				  driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click();
				  
				 Thread.sleep(2000);
					
				  driver.findElement(By.id("btnuploadfile")).click();
	
				 
				  WebDriverWait wait1=new WebDriverWait(driver,20);
					wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("adm_fileuploadId")));
				  driver.switchTo().frame("adm_fileuploadId");
				 
				  WebElement fileInput = driver.findElement(By.id("uploadFileId"));
				  
				  fileInput.sendKeys(System.getProperty("user.dir") + "/src/main/java/config/TestData/" + filepath);
	
				  driver.switchTo().parentFrame();
				  WebDriverWait wait2=new WebDriverWait(driver,20);
				  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
				  String txt= driver.findElement(By.className("top-alert-message")).getText();
				  System.out.println(txt);
				  Assert.assertEquals(txt,"Files uploaded. Processing the update..");
				  System.out.println("uploadFile executed");
			  }
		       
		      @Test(priority=7,groups= {"All"})
		      @Parameters({"URL"})
			  public void uploadFolder(String URL) throws InterruptedException, IOException
			  {  
				    driver.get(URL);
	        	    driver.findElement(By.xpath("//*[@data-foldername='All']/h2/a")).click();
	        	    Thread.sleep(3000);
				    driver.findElement(By.xpath("//*[@data-foldername='Test01']/h2/a")).click();
				    Thread.sleep(3000);
				    driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click();
				    Thread.sleep(2000);
				
				    driver.findElement(By.xpath("//*[@id='btnuploadfolder']")).click();
				    WebDriverWait wait1=new WebDriverWait(driver,20);
					wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("adm_fileuploadId")));
				
				    driver.switchTo().frame("adm_fileuploadId");
					 
				    WebElement folderInput = driver.findElement(By.id("uploadFolderId"));
					  
					 folderInput.sendKeys(System.getProperty("user.dir") + "/src/main/java/config/TestData");
				//	  Alert alt=driver.switchTo().alert();
				//	  alt.accept();
			
					  driver.switchTo().parentFrame();
					  WebDriverWait wait2=new WebDriverWait(driver,20);
					  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
					  
					  String txt= driver.findElement(By.className("top-alert-message")).getText();
					  System.out.println(txt);
					  Assert.assertEquals(txt,"Files uploaded. Processing the update..");
					  System.out.println("uploadFolder executed");
				   
				  }   
		      @Test(priority=12,groups= {"All"})
			  @Parameters({"URL","foldername1"})
			  public void downloadFolder(String URL,String foldername1) throws InterruptedException
			  {   
				  driver.get(URL);
				  String folderpath="//*[@data-foldername='"+foldername1+"']/div[6]/img[1]";
				  
				  driver.findElement(By.xpath(folderpath)).click();
				  WebDriverWait wait=new WebDriverWait(driver,60);
				  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='modalfolderdownload']/div[1]/div")));
			     
				  System.out.println("Folder downloaded");
				  System.out.println("downloadFolder executed");
				   
				}
		      
		      @Test(groups= {"All"},priority=13)
			  @Parameters({"URL","downloadfilename1","iteration_time"})
			  public void downloadFile(String URL,String downloadfilename1,int iteration_time ) throws InterruptedException           //Download 1st file of All folder
			  {   
				  driver.get(URL);
				  boolean exist=false;
				  
				  String file="//*[@data-filename='"+downloadfilename1+"']/div[6]/img[1]"; 
				  
				 
				 File f;
				 f=new File("C:/Users/" + System.getProperty("user.name") + "/Downloads/" + downloadfilename1 + ".pdf"); 
				 
				if( f.exists()) {
					f.delete();
					 driver.findElement(By.xpath("//*[@data-foldername='All']/h2/a")).click();
					  driver.findElement(By.xpath(file)).click();
					  Thread.sleep(5000);
					 String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
					 System.out.println(Popupmsg);
					 Assert.assertEquals("Download started. Your file(s) will be downloaded shortly", Popupmsg);
					 do
					    {
					          f=new File("C:/Users/" + System.getProperty("user.name") + "/Downloads/" + downloadfilename1 + ".pdf"); 
					   	      if(f.exists())
					   	     {
					   		  
					   		    exist= true;
					   		    break;
					   		 
					   	     }
					   	     Thread.sleep(iteration_time*1000);
					   	    iteration_time = iteration_time-1;
					   	  
					    }while(!(f.exists()));
					    
					     if(exist) 
					     {
					               System.out.println("downloadFile executed");
					     }
					     else 
					     {
					    	      System.out.println("downloadFile not executed");
					     }
				} else {
					  driver.findElement(By.xpath("//*[@data-foldername='All']/h2/a")).click();
					  driver.findElement(By.xpath(file)).click();
					  Thread.sleep(5000);
					 String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
					 System.out.println(Popupmsg);
					 Assert.assertEquals("Download started. Your file(s) will be downloaded shortly", Popupmsg);
					
			     do
			    {
			          f=new File("C:/Users/" + System.getProperty("user.name") + "/Downloads/" + downloadfilename1 + ".pdf"); 
			   	      if(f.exists())
			   	     {
			   		  
			   		    exist= true;
			   		    break;
			   		 
			   	     }
			   	     Thread.sleep(iteration_time*1000);
			   	    iteration_time = iteration_time-1;
			   	  
			    }while(!(f.exists()));
			    
			     if(exist) 
			     {
			               System.out.println("downloadFile executed");
			     }
			     else 
			     {
			    	      System.out.println("downloadFile not executed");
			     }
				}
			     
			     Assert.assertEquals(exist, true);
			  }
			  
			  
			  @Test(priority=14,groups= {"All"})
			  @Parameters({"URL"})
			  public void downloadMultipleFiles(String URL) throws InterruptedException
			  {     
				  driver.get(URL);
				  driver.findElement(By.xpath("//*[@data-foldername='All']/h2/a")).click();        //download 1st and 2nd file of All folder
				  driver.findElement(By.xpath("//*[@id='adm_doc_name_1']/label/span")).click();
				  driver.findElement(By.xpath("//*[@id='adm_doc_name_2']/label/span")).click();
				  driver.findElement(By.id("moreall")).click();
				  driver.findElement(By.id("adm_download_file")).click();
				  Thread.sleep(6000);
				  String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
				  System.out.println(Popupmsg);
				  Assert.assertEquals("Download started. You will receive a zip file", Popupmsg);
				  System.out.println("downloadMultipleFiles executed");
				  
			  }
			  @Test(priority=15,groups= {"All"})
			  @Parameters({"URL"})
			  public void downloadVersionList(String URL) throws InterruptedException
			  {  
				  driver.get(URL);
				  driver.findElement(By.xpath("//*[@data-foldername='All']/h2/a")).click();     //All folder contains subfolder... inside subfolder ,1st file have version list..download the recent version
				  driver.findElement(By.id("admfileversionname_1")).click();
				  Thread.sleep(4000);
				  driver.findElement(By.xpath("//*[@id='adm_download_Version_Popup_0']/div/img")).click();
				  Thread.sleep(5000);    
				  String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
				  System.out.println(Popupmsg);
				  Assert.assertEquals("Download started. Your file(s) will be downloaded shortly", Popupmsg);
				  System.out.println("downloadversionList executed");
			  }
		      
			  @Test(groups= {"All"},priority=16)
			  @Parameters({"URL","filepath"})
			  public void requestFileDeposit(String URL,String filepath) throws InterruptedException
			  { 
				driver.get(URL);  
				
				driver.findElement(By.xpath("//*[@data-foldername='All']/div[7]/ul/li/img")).click();
			//	driver.findElement(By.linkText("Request File Deposit")).click();
				driver.findElement(By.id("adm_inbound_folder_0")).click();  
				Thread.sleep(2000);
				 
				driver.findElement(By.id("inbound_admsharesubject")).sendKeys("Document required for testing");
				driver.findElement(By.id("inbound_admaddreceiver")).click();
				driver.findElement(By.id("adm_mobileemail_txt1_0")).sendKeys("admin1test@protonmail.com");
				driver.findElement(By.id("btnshareemaildone")).click();
				driver.findElement(By.id("inbound_admsharesend")).click();
				 WebDriverWait wait2=new WebDriverWait(driver,20);
				  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
				String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
		
				 System.out.println(Popupmsg);
				
				 Assert.assertEquals("File deposit request sent successfully", Popupmsg);
				driver.get("https://mail.protonmail.com/login");
				driver.findElement(By.id("username")).sendKeys("admin1test@protonmail.com");
				driver.findElement(By.name("password")).sendKeys("Test@123");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//button[@id='login_btn']")).click();
				Thread.sleep(3000);
				WebDriverWait wait1=new WebDriverWait(driver,20);
				wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Document required for testing']")));
			    
				driver.findElement(By.xpath("//span[text()='Document required for testing']")).click();
				Thread.sleep(3000);
				driver.findElement(By.linkText("Deposit File(s)/Folder")).click();
				
			//	String handle=driver.getWindowHandle();
			//	System.out.println(handle);
			//	Set<String> handles=driver.getWindowHandles();
			//	System.out.println(handles);
				
			//	ArrayList<String> a1=new ArrayList<String> (handles);
			//	driver.switchTo().window(a1.get(1));
				
				SwitchCurrentWindow.GetNewWindow();
				WebDriverWait wait4=new WebDriverWait(driver,20);
				wait4.until(ExpectedConditions.visibilityOfElementLocated(By.id("adm_fileuploadId")));
				driver.switchTo().frame("adm_fileuploadId");
				WebElement fileInput = driver.findElement(By.id("uploadFileId"));

			  fileInput.sendKeys(System.getProperty("user.dir") + "/src/main/java/config/TestData/" + filepath);
			 
			  driver.switchTo().parentFrame();
			 
			  
		       WebDriverWait wait5=new WebDriverWait(driver,20);
		      wait5.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		      String txt= driver.findElement(By.className("top-alert-message")).getText();
			  System.out.println(txt);
			  String actualtxt="Your upload is completed successfully. this link is no longer valid";
			  Assert.assertEquals(actualtxt,txt);

			  System.out.println("requestFileDeposit executed");
			}
			  
			
			  
			  @Test(priority=17,groups= {"Upload"})
			  @Parameters({"URL"})
			   public void createFolder_Upload(String URL) throws InterruptedException
			   {
				   driver.get(URL);
				   driver.findElement(By.id("btncreatefolderpopup")).click();
				   WebDriverWait wait2=new WebDriverWait(driver,20);
				   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
				   String txt= driver.findElement(By.className("top-alert-message")).getText();
				   System.out.println(txt);
				   String actualtxt="Sorry, you do not have right to perform the action. Please contact your administrator";
				   Assert.assertEquals(actualtxt,txt);
		
				   System.out.println("createFolder_Upload executed");
			   }
			   
			   @Test(priority=18,groups={"Upload"})
			   @Parameters({"subFoldername","URL"})
			   public void subFolder_Upload(String subFoldername,String URL) throws InterruptedException   //creating subfolder inside 2nd folder
			   {    
				   
				   driver.get(URL);
				 
				   driver.findElement(By.xpath("//*[@data-foldername='Upload']/h2/a")).click();
				 
				 //  WebDriverWait wait1=new WebDriverWait(driver,20);
				 //  wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btncreatefolderpopup']/i")));
				    Thread.sleep(2000);
				   driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click();
				   Thread.sleep(2000);
				//   WebDriverWait wait2=new WebDriverWait(driver,20);
				//   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdownfolder1")));
				//   WebDriverWait wait4=new WebDriverWait(driver,20);
				//   wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btncreatefolder']")));
				   driver.findElement(By.xpath("//*[@id='btncreatefolder']")).click();
				   driver.findElement(By.xpath("//*[@id='admfoldername']")).sendKeys(subFoldername);
				   driver.findElement(By.xpath("//*[@id='btncreatefolderdone']")).click();
				   WebDriverWait wait5=new WebDriverWait(driver,20);
				   wait5.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
				   String txt= driver.findElement(By.className("top-alert-message")).getText();
				
				   if(txt.equals("Folder created successfully"))
				   {
					   String actualtxt= "Folder created successfully";
					   Assert.assertEquals(txt,actualtxt);
					   System.out.println("Sub-Folder created successfully");
				   }
				   else 
				   {
					   String actualtxt= "Folder Exists";
					   Assert.assertEquals(txt,actualtxt);
					   System.out.println(txt);
				   }
				  
				
				   System.out.println("subFolder_Upload executed");
				 } 
			   
			      @Test(priority=19,groups= {"Upload"})
				  @Parameters({"URL","subFoldername","renamedFolder"})
				  public void rename_Upload(String URL,String subFoldername,String renamedFolder) throws InterruptedException
				  {
					   driver.get(URL);
					   driver.findElement(By.xpath("//*[@data-foldername='Upload']/h2/a")).click();
					   Thread.sleep(3000);
					   driver.findElement(By.xpath("//*[@data-foldername='"+subFoldername+"']/div[7]/ul/li/img")).click();
					   driver.findElement(By.linkText("Rename")).click();
					   driver.findElement(By.id("rename_admfoldername")).clear();
					   driver.findElement(By.id("rename_admfoldername")).sendKeys(renamedFolder);
					   driver.findElement(By.id("rename_btncreatefolderdone")).click();
					   WebDriverWait wait2=new WebDriverWait(driver,20);
					   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
					   String txt= driver.findElement(By.className("top-alert-message")).getText();
					   System.out.println(txt);
					   String actualtxt= "Folder renamed successfully";
					   Assert.assertEquals(actualtxt,txt);
					   System.out.println("rename_Upload executed");
				  }
			      
			      @Test(priority=20,groups= {"Upload","subFolderdefault"})
				  @Parameters({"URL","subFolderdefault"})
				  public void restore_Upload(String URL,String subFolderdefault) throws InterruptedException   
				  {
					   driver.get(URL);
					  driver.findElement(By.xpath("//*[@data-foldername='Upload']/h2/a")).click();
					  Thread.sleep(3000);
				      driver.findElement(By.xpath("//*[@data-foldername='"+subFolderdefault+"']/div[7]/ul/li/img")).click();
				      try
				      {
				          if(driver.findElement(By.linkText("Delete")).isDisplayed());
				         {
				        	 driver.findElement(By.linkText("Delete")).click();
				        	 WebDriverWait wait2=new WebDriverWait(driver,20);
							 wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
							 String txt= driver.findElement(By.className("top-alert-message")).getText();
							 System.out.println(txt);
				         }
				      
				      }
				     catch(Exception e)
				      {
				    	  System.out.println("No Delete Permisssions");
				      }
				
					   System.out.println("restore_Upload executed");
				  }
				  
			      @Test(priority=21,groups= {"Upload"})
				  @Parameters({"filepath","URL","subFolderdefault"})
				  public void uploadFile_Upload(String filepath,String URL, String subFolderdefault) throws InterruptedException, IOException
				  {  
					   driver.get(URL);
					   driver.findElement(By.xpath("//*[@data-foldername='Upload']/h2/a")).click();
					   Thread.sleep(3000);
					   driver.findElement(By.xpath("//*[@data-foldername='"+subFolderdefault+"']/h2/a")).click();
					   Thread.sleep(2000);
					//  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btncreatefolderpopup']/i")));
					  driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click();
					  WebDriverWait wait=new WebDriverWait(driver,20);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuploadfile']")));
				
					  driver.findElement(By.id("btnuploadfile")).click();
					  WebDriverWait wait1=new WebDriverWait(driver,20);
						wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("adm_fileuploadId")));
					  driver.switchTo().frame("adm_fileuploadId");
					 
					  WebElement fileInput = driver.findElement(By.id("uploadFileId"));
					  
					  fileInput.sendKeys(System.getProperty("user.dir") + "/src/main/java/config/TestData/" + filepath);
					 
					  driver.switchTo().parentFrame();
					  WebDriverWait wait2=new WebDriverWait(driver,20);
					  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
					  
					  String txt= driver.findElement(By.className("top-alert-message")).getText();
					  System.out.println(txt);
					  Assert.assertEquals(txt,"Files uploaded. Processing the update..");
			
					  System.out.println("uploadFile_Upload executed");
				   
				  }
			       
			      @Test(priority=22,groups= {"Upload"})
			      @Parameters({"URL","subFolderdefault"})
				  public void uploadFolder_Upload(String URL, String subFolderdefault) throws InterruptedException, IOException
				  {  
					    driver.get(URL);
		        	    driver.findElement(By.xpath("//*[@data-foldername='Upload']/h2/a")).click();
		        	    Thread.sleep(3000);
		        	    driver.findElement(By.xpath("//*[@data-foldername='"+subFolderdefault+"']/h2/a")).click();
		        	    Thread.sleep(2000);
					    driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click();
					    WebDriverWait wait=new WebDriverWait(driver,20);
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuploadfolder']")));
					    driver.findElement(By.xpath("//*[@id='btnuploadfolder']")).click();
					    WebDriverWait wait1=new WebDriverWait(driver,20);
						wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("adm_fileuploadId")));
					    driver.switchTo().frame("adm_fileuploadId");
						 
					    WebElement folderInput = driver.findElement(By.id("uploadFolderId"));
						  
						 folderInput.sendKeys(System.getProperty("user.dir") + "/src/main/java/config/TestData");
					//	  Alert alt=driver.switchTo().alert();
					//	  alt.accept();
					
						  driver.switchTo().parentFrame();
						  
						  WebDriverWait wait2=new WebDriverWait(driver,20);
						  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
						  
						  String txt= driver.findElement(By.className("top-alert-message")).getText();
						  System.out.println(txt);
						  Assert.assertEquals(txt,"Files uploaded. Processing the update..");
				
						  System.out.println("uploadFolder_Upload executed");
					   
					  }  
			      
			      @Test(groups= {"Upload"},priority=23)
				  @Parameters({"URL","downloadfilename2"})
				  public void downloadFile_upload(String URL,String downloadfilename2) throws InterruptedException           //Download 1st file of All folder
				  {   
					  driver.get(URL);
					  String file="//*[@data-filename='"+downloadfilename2+"']/div[6]/img[1]";
					  
					  driver.findElement(By.xpath("//*[@data-foldername='Upload']/h2/a")).click();
					  driver.findElement(By.xpath(file)).click();
					  Thread.sleep(5000);
					 String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
					 System.out.println(Popupmsg);
					 Assert.assertEquals("Download started. Your file(s) will be downloaded shortly", Popupmsg);
					 File f=new File("C:\\Users\\91863\\Downloads\\funny.zip");
				     if(f.exists())
				    {
				   	  System.out.println("File Downloaded");
				   	  
				    }
				     System.out.println("downloadFile_Upload executed");
				  }
				  
				  @Test(priority=24,groups= {"Upload"})
				  @Parameters({"URL"})
				  public void downloadMultipleFiles_upload(String URL) throws InterruptedException
				  {     
					  driver.get(URL);
					  driver.findElement(By.xpath("//*[@data-foldername='Upload']/h2/a")).click();        //download 1st and 2nd file of All folder
					  driver.findElement(By.xpath("//*[@id='adm_doc_name_0']/label/span")).click();
					  driver.findElement(By.xpath("//*[@id='adm_doc_name_1']/label/span")).click();//*[@id="adm_doc_name_0"]/label/span
					  driver.findElement(By.id("moreall")).click();
					  driver.findElement(By.id("adm_download_file")).click();
					  Thread.sleep(6000);
					  String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
					  System.out.println(Popupmsg);
					  Assert.assertEquals("Download started. You will receive a zip file", Popupmsg);
					  System.out.println("downloadMultipleFiles_Upload executed");
					  
				  }
				  @Test(priority=25,groups= {"Upload"})
				  @Parameters({"URL"})
				  public void downloadVersionList_upload(String URL) throws InterruptedException
				  {  
					  driver.get(URL);
					  driver.findElement(By.xpath("//*[@data-foldername='Upload']/h2/a")).click();     //All folder contains subfolder... inside subfolder ,1st file have version list..download the recent version
					  driver.findElement(By.id("admfileversionname_0")).click();
					  Thread.sleep(4000);
					  driver.findElement(By.xpath("//*[@id='adm_download_Version_Popup_0']/div/img")).click();
					  Thread.sleep(5000);    
					  String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();//*[@id="adm_download_Version_Popup_0"]/div/img
					  System.out.println(Popupmsg);
					  Assert.assertEquals("Download started. Your file(s) will be downloaded shortly", Popupmsg);
					  System.out.println("downloadVersionList_Upload executed");
				  }
			     
				  @Test(groups= {"Upload"},priority=28)
				  @Parameters({"URL","filepath","subFolderdefault"})
				  public void RequestFileDeposit_upload(String URL,String filepath,String subFolderdefault ) throws InterruptedException
				  { 
					driver.get(URL);
					
					driver.findElement(By.xpath("//*[@data-foldername='Upload']/div[7]/ul/li/img")).click();  
					driver.findElement(By.id("adm_inbound_folder_2")).click();  
					Thread.sleep(2000);
					driver.findElement(By.id("inbound_admsharesubject")).sendKeys("Document required for upload folder");
					driver.findElement(By.id("inbound_admaddreceiver")).click();
					driver.findElement(By.id("adm_mobileemail_txt1_0")).sendKeys("admin1test@protonmail.com");
					driver.findElement(By.id("btnshareemaildone")).click();
					driver.findElement(By.id("inbound_admsharesend")).click();
					
					 WebDriverWait wait2=new WebDriverWait(driver,20);
					  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
					String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
					 System.out.println(Popupmsg);
					
					 Assert.assertEquals("File deposit request sent successfully", Popupmsg);
					driver.get("https://mail.protonmail.com/login");
					driver.findElement(By.id("username")).sendKeys("admin1test@protonmail.com");
					driver.findElement(By.name("password")).sendKeys("Test@123");
					Thread.sleep(2000);
					driver.findElement(By.id("login_btn")).click();
					WebDriverWait wait=new WebDriverWait(driver,20);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Document required for testing']")));
					driver.findElement(By.xpath("//span[text()='Document required for upload folder']")).click();
					Thread.sleep(3000);
					driver.findElement(By.linkText("Deposit File(s)/Folder")).click();
					
					
				//	driver.switchTo().frame(driver.findElement(By.id("frameId")));
					
					
					
				//	String handle=driver.getWindowHandle();
				//	System.out.println(handle);
				//	Set<String> handles=driver.getWindowHandles();
				//	System.out.println(handles);
					
				//	ArrayList<String> a1=new ArrayList<String> (handles);
				//	driver.switchTo().window(a1.get(2));
					
					SwitchCurrentWindow.GetNewWindow();
					WebDriverWait wait6=new WebDriverWait(driver,20);
					   wait6.until(ExpectedConditions.visibilityOfElementLocated(By.id("adm_fileuploadId")));
					driver.switchTo().frame("adm_fileuploadId");
					WebElement fileInput = driver.findElement(By.id("uploadFileId"));
					  
				  fileInput.sendKeys(System.getProperty("user.dir") + "/src/main/java/config/TestData/" + filepath);
				 
				  driver.switchTo().parentFrame();
				  
				  WebDriverWait wait3=new WebDriverWait(driver,20);
				  wait3.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
				  
				  String txt= driver.findElement(By.className("top-alert-message")).getText();
				  System.out.println(txt);
				
				  Assert.assertEquals("Your upload is completed successfully. this link is no longer valid", txt);
				 
				  System.out.println("requestFiledeposit_Upload executed");
				}
				  
			  
				  @Test(priority=29,groups= {"Upload"})
				  @Parameters({"URL","foldername2"})
				  public void downloadFolder_upload(String URL,String foldername2) throws InterruptedException
				  {   
					  driver.get(URL);
					  String folderpath="//*[@data-foldername='"+foldername2+"']/div[6]/img[1]";
					  
					  driver.findElement(By.xpath(folderpath)).click();
					  WebDriverWait wait=new WebDriverWait(driver,60);
					  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='modalfolderdownload']/div[1]/div")));
				     
					  System.out.println("Folder downloaded");
					  System.out.println("downloadFolder_upload executed");
				
					   
				  }
				  
				  @Test(priority=30,groups= {"Upload"})
				  @Parameters({"URL","subFolderdefault"})
				   public void deleteFolder_upload(String URL,String subFolderdefault) throws InterruptedException
				   {
					 driver.get(URL);
					 driver.findElement(By.xpath("//*[@data-foldername='Upload']/h2/a")).click(); 
					 Thread.sleep(3000);
					 driver.findElement(By.xpath("//*[@data-foldername='"+subFolderdefault+"']/div[7]/ul/li/img")).click();
					 try
				      {
				         if(driver.findElement(By.linkText("Delete")).isDisplayed());
				         {
				        	 driver.findElement(By.linkText("Delete")).click();
				        	 Thread.sleep(3000);
						     driver.findElement(By.xpath("//*[@data-foldername='"+subFolderdefault+"']/div[7]/ul/li/img")).click();
						     driver.findElement(By.linkText("Delete Permanently")).click();
						     driver.findElement(By.xpath("//*[@id='btndeleteconfirmdone']")).click();
						     Thread.sleep(3000);
				         }
				      
				      }
				     catch(Exception e)
				      {
				    	  System.out.println("No Delete Permisssions");
				      }
			
					  System.out.println("deleteFolder_upload executed");
					  
				  }
				  
				  
		      	  @Test(priority=31,groups= {"Download"})
			      @Parameters({"URL"})
			 
				   public void createFolder_Download(String URL) throws InterruptedException
				   {
				       driver.get(URL);
					   driver.findElement(By.id("btncreatefolderpopup")).click();
					   WebDriverWait wait2=new WebDriverWait(driver,20);
					   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
					   String txt= driver.findElement(By.className("top-alert-message")).getText();
					   System.out.println(txt);
					   String actualtxt="Sorry, you do not have right to perform the action. Please contact your administrator";
					   Assert.assertEquals(actualtxt,txt);
			
					   System.out.println("createFolder_Download executed");
				   }
				   
				   @Test(priority=32,groups={"Download"})
				   @Parameters({"subFoldername","URL"})
				   public void subFolder_Download(String subFoldername,String URL) throws InterruptedException   //creating subfolder inside 2nd folder
				   {    
					   
					   driver.get(URL);
					 
					   driver.findElement(By.xpath("//*[@data-foldername='Download']/h2/a")).click(); // finding Parent folder
					//   Alert alt=driver.switchTo().alert();
					//   alt.dismiss();
					   Thread.sleep(2000);
					   driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
					//   driver.findElement(By.xpath("//*[@id='btncreatefolder']")).click();
					   WebDriverWait wait2=new WebDriverWait(driver,20);
					   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
					   String txt= driver.findElement(By.className("top-alert-message")).getText();
					   System.out.println(txt);
					   String actualtxt="Sorry, you do not have right to perform the action. Please contact your administrator";
					   Assert.assertEquals(actualtxt,txt);

					   System.out.println("subFolder_Download executed");
					  
					 } 
				   @Test(priority=34,groups= {"Download"})
					  @Parameters({"URL"})
					  public void rename_Download(String URL) throws InterruptedException
					  {
						   driver.get(URL);					   
						   driver.findElement(By.xpath("//*[@data-foldername='Download']/h2/a")).click(); 
						   Thread.sleep(3000);
						   driver.findElement(By.xpath("//*[@data-foldername='Test02']/div[7]/ul/li/img")).click();
						      try
						      {
						         if(driver.findElement(By.linkText("Rename")).isDisplayed());
						         {
						        	 driver.findElement(By.linkText("Rename")).click();
						        	 driver.findElement(By.id("rename_admfoldername")).clear();
									 driver.findElement(By.id("rename_admfoldername")).sendKeys("Rename");
									 driver.findElement(By.id("rename_btncreatefolderdone")).click();
						         }
						      
						      }
						     catch(Exception e)
						      {
						    	  System.out.println("No Rename Permisssions");
						      }
						 
						   System.out.println("rename_Download executed");
					  }
				      
				      @Test(priority=35,groups= {"Download"})
					  @Parameters({"URL"})
					  public void restore_Download(String URL) throws InterruptedException   
					  {
						  driver.get(URL);
						  driver.findElement(By.xpath("//*[@data-foldername='Download']/h2/a")).click(); 
						  Thread.sleep(3000);
					      driver.findElement(By.xpath("//*[@data-foldername='Test02']/div[7]/ul/li/img")).click();
					      try
					      {
					         if(driver.findElement(By.linkText("Delete")).isDisplayed());
					         {
					        	 driver.findElement(By.linkText("Delete")).click();
					        	 Thread.sleep(3000);
							     driver.findElement(By.xpath("//*[@data-foldername='Test02']/div[7]/ul/li/img")).click();
							     driver.findElement(By.linkText("Delete Permanently")).click();
							     driver.findElement(By.xpath("//*[@id='btndeleteconfirmdone']")).click();
							     Thread.sleep(3000);
					         }
					      
					      }
					     catch(Exception e)
					      {
					    	  System.out.println("No Delete Permisssions");
					      }
						
						   System.out.println("restore_Download executed");
					  }
				      
				      @Test(priority=36,groups= {"Download"})
					  @Parameters({"filepath","URL"})
					  public void uploadFile_Download(String filepath,String URL) throws InterruptedException, IOException
					  {  
						   driver.get(URL);
						   driver.findElement(By.xpath("//*[@data-foldername='Download']/h2/a")).click();
						   Thread.sleep(3000);
						   driver.findElement(By.xpath("//*[@data-foldername='Test02']/h2/a")).click();
						   Thread.sleep(3000);
		
						  driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click();
					//	  driver.findElement(By.id("btnuploadfile")).click();
						  WebDriverWait wait2=new WebDriverWait(driver,20);
						  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
						  String txt= driver.findElement(By.className("top-alert-message")).getText();
						   System.out.println(txt);
						   String actualtxt="Sorry, you do not have right to perform the action. Please contact your administrator";
						   Assert.assertEquals(actualtxt,txt);
						 
						   System.out.println("uploadFile_Download executed");
					   
					  }
				       
				      @Test(priority=37,groups= {"Download"})
				      @Parameters({"URL"})
					  public void uploadFolder_Download(String URL) throws InterruptedException, IOException
					  {  
						    driver.get(URL);
			        	    driver.findElement(By.xpath("//*[@data-foldername='Download']/h2/a")).click();
			        	    Thread.sleep(3000);
			        	    driver.findElement(By.xpath("//*[@data-foldername='Test02']/h2/a")).click();
							Thread.sleep(3000);
						    driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click();
					
						    WebDriverWait wait2=new WebDriverWait(driver,20);
							   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
						    String txt= driver.findElement(By.className("top-alert-message")).getText();
							System.out.println(txt);
		
							String actualtxt="Sorry, you do not have right to perform the action. Please contact your administrator";
							Assert.assertEquals(actualtxt,txt);
			
							System.out.println("uploadFolder_Download executed");
						   
					  }  
				      @Test(groups= {"Download"},priority=38)
					  @Parameters({"URL","downloadfilename3"})
					  public void downloadFile_Download(String URL, String downloadfilename3) throws InterruptedException           //Download 1st file of All folder
					  {   
						  driver.get(URL);
						  String file="//*[@data-filename='"+downloadfilename3+"']/div[6]/img[1]";
						  driver.findElement(By.xpath("//*[@data-foldername='Download']/h2/a")).click();
						
						  driver.findElement(By.xpath(file)).click();
						  Thread.sleep(5000);
						  WebDriverWait wait2=new WebDriverWait(driver,20);
						   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
						 String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
						 System.out.println(Popupmsg);
						 Assert.assertEquals("Download started. Your file(s) will be downloaded shortly", Popupmsg);
						 File f=new File("C:\\Users\\91863\\Downloads\\funny.zip");
					     if(f.exists())
					    {
					   	  System.out.println("File Downloaded");
					    }
					     System.out.println("downloadFile_Download executed");
					     
					  }
					  
					  @Test(priority=39,groups= {"Download"})
					  @Parameters({"URL"})
					  public void downloadMultipleFiles_Download(String URL) throws InterruptedException
					  {     
						  driver.get(URL);
						  driver.findElement(By.xpath("//*[@data-foldername='Download']/h2/a")).click();        //download 1st and 2nd file of All folder
						  driver.findElement(By.xpath("//*[@id='adm_doc_name_0']/label/span")).click();
						  driver.findElement(By.xpath("//*[@id='adm_doc_name_1']/label/span")).click();//*[@id="adm_doc_name_0"]/label/span
						  driver.findElement(By.id("moreall")).click();
						  driver.findElement(By.id("adm_download_file")).click();
						  Thread.sleep(6000);
						  WebDriverWait wait2=new WebDriverWait(driver,20);
						   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
						  String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
						  System.out.println(Popupmsg);
						  Assert.assertEquals("Download started. You will receive a zip file", Popupmsg);
						  System.out.println("downloadMultipleFiles_Download executed");
						  
					  }
					  @Test(priority=40,groups= {"Download"})
					  @Parameters({"URL"})
					  public void downloadVersionList_Download(String URL) throws InterruptedException
					  {  
						  driver.get(URL);
						  driver.findElement(By.xpath("//*[@data-foldername='Download']/h2/a")).click();     //All folder contains subfolder... inside subfolder ,1st file have version list..download the recent version
						  driver.findElement(By.id("admfileversionname_0")).click();
						  Thread.sleep(4000);
						  driver.findElement(By.xpath("//*[@id='adm_download_Version_Popup_0']/div/img")).click();
						  Thread.sleep(5000);  
						  WebDriverWait wait2=new WebDriverWait(driver,20);
						   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
						  String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
						  System.out.println(Popupmsg);
						  Assert.assertEquals("Download started. Your file(s) will be downloaded shortly", Popupmsg);
						  System.out.println("downloadVersionList_Download executed");
					  }
					  
					  
					  @Test(priority=41,groups= {"Download"})
					  @Parameters({"URL","foldername3"})
					  public void downloadFolder_Download(String URL,String foldername3) throws InterruptedException
					  {   
						  driver.get(URL);
						  String folderpath="//*[@data-foldername='"+foldername3+"']/div[6]/img[1]";
						  driver.findElement(By.xpath(folderpath)).click();
						  Thread.sleep(30000);
						  WebDriverWait wait=new WebDriverWait(driver,30);//*[@id="adm_download_folder_1"]
						  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='modalfolderdownload']/div[1]/div")));
					 //     String progress=driver.findElement(By.xpath("//*[@id='downloadingperc_2']")).getText();
						  System.out.println("Folder downloaded ");
					//	  Assert.assertEquals("100%",progress);
						  System.out.println("downloadFolder_Download executed");
						   
					  }
					  
					  @Test(priority=42,groups= {"Download"})
					  @Parameters({"URL","foldername3"})
					   public void deleteFolder_Download(String URL,String foldername3) throws InterruptedException
					   {
						 driver.get(URL);
						 driver.findElement(By.xpath("//*[@data-foldername='"+foldername3+"']/h2/a")).click(); 
						 Thread.sleep(3000);
						 driver.findElement(By.xpath("//*[@data-foldername='Test02']/div[7]/ul/li/img")).click();
						 try
					      {
					         if(driver.findElement(By.linkText("Delete")).isDisplayed());
					         {
					        	 driver.findElement(By.linkText("Delete")).click();
					        	 Thread.sleep(3000);
							     driver.findElement(By.xpath("//*[@data-foldername='Test02']/div[7]/ul/li/img")).click();
							     driver.findElement(By.linkText("Delete Permanently")).click();
							     driver.findElement(By.xpath("//*[@id='btndeleteconfirmdone']")).click();
							     Thread.sleep(3000);
					         }
					      
					      }
					     catch(Exception e)
					      {
					    	  System.out.println("No Delete Permisssions");
					      }
					
						  System.out.println("deleteFolder_Download executed");
					  }
					  @Test(groups= {"Download"},priority=44)
					  @Parameters({"URL","filepath","foldername3"})
					  public void RequestFileDeposit_Download(String URL,String filepath,String foldername3 ) throws InterruptedException
					  { 
						driver.get(URL);
						driver.findElement(By.xpath("//*[@data-foldername='"+foldername3+"']/div[7]/ul/li/img")).click(); 
						 
						 try
					      {
					         if(driver.findElement(By.id("adm_inbound_folder_1")).isDisplayed());
					         {
					        	 driver.findElement(By.id("adm_inbound_folder_1")).click();
					        	 driver.findElement(By.id("inbound_admsharesubject")).sendKeys("Document required for testing");
								 driver.findElement(By.id("inbound_admaddreceiver")).click();
								 driver.findElement(By.id("adm_mobileemail_txt1_0")).sendKeys("admin1test@protonmail.com");
								 driver.findElement(By.id("btnshareemaildone")).click();
								 driver.findElement(By.id("inbound_admsharesend")).click();
								 WebDriverWait wait4=new WebDriverWait(driver,20);
								 wait4.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
								 String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
							     System.out.println(Popupmsg);
					         }
					      
					      }
					     catch(Exception e)
					      {
					    	  System.out.println("No Request File Deposit Permisssions");
					      }
					
						System.out.println("RequestFileDeposit_Download executed");
					}
					  
					  @Test(priority=45,groups= {"View"})
				      @Parameters({"URL"})
					  
					  public void createFolder_View(String URL) throws InterruptedException
					   {
					       driver.get(URL);
						   driver.findElement(By.id("btncreatefolderpopup")).click();
						   WebDriverWait wait2=new WebDriverWait(driver,20);
						   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
						   String txt= driver.findElement(By.className("top-alert-message")).getText();
						   System.out.println(txt);
						   String actualtxt="Sorry, you do not have right to perform the action. Please contact your administrator";
						   Assert.assertEquals(actualtxt,txt);
				
						   System.out.println("createFolder_View executed");
					   }
					  
					  
					  @Test(priority=46,groups={"View"})
					   @Parameters({"subFoldername","URL"})
					   public void subFolder_View(String subFoldername,String URL) throws InterruptedException   //creating subfolder inside 2nd folder
					   {    
						   
						   driver.get(URL);
						 
						   driver.findElement(By.xpath("//*[@data-foldername='View']/h2/a")).click(); // finding Parent folder
					
					
						   Thread.sleep(2000);
						   driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
					
						   WebDriverWait wait2=new WebDriverWait(driver,20);
						   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
						   String txt= driver.findElement(By.className("top-alert-message")).getText();
						   System.out.println(txt);
						   String actualtxt="Sorry, you do not have right to perform the action. Please contact your administrator";
						   Assert.assertEquals(actualtxt,txt);

						   System.out.println("subFolder_View executed");
						  } 
					  
					  
					   @Test(priority=47,groups= {"View"})
					   @Parameters({"URL"})
					   public void rename_View(String URL) throws InterruptedException
						  {
							   driver.get(URL);
							   driver.findElement(By.xpath("//*[@data-foldername='View']/h2/a")).click();
							   Thread.sleep(3000);
							   driver.findElement(By.xpath("//*[@data-foldername='Test04']/div[7]/ul/li/img")).click();//*[@id="adm_foldername_2"]/h2/a
							   try
							      {
							         if(driver.findElement(By.linkText("Rename")).isDisplayed());
							         {
							        	 driver.findElement(By.linkText("Rename")).click();
							        	 driver.findElement(By.id("rename_admfoldername")).clear();
										 driver.findElement(By.id("rename_admfoldername")).sendKeys("Rename");
										 driver.findElement(By.id("rename_btncreatefolderdone")).click();
							         }
							      
							      }
							     catch(Exception e)
							      {
							    	  System.out.println("No Rename Permisssions");
							      }
							 
							   System.out.println("rename_View executed");
						  }
					      
					      @Test(priority=48,groups= {"View"})
						  @Parameters({"URL"})
						  public void restore_View(String URL) throws InterruptedException   
						  {
							  driver.get(URL);
							  driver.findElement(By.xpath("//*[@data-foldername='View']/h2/a")).click(); 
							  Thread.sleep(3000);
						      driver.findElement(By.xpath("//*[@data-foldername='Test04']/div[7]/ul/li/img")).click();
						      try
						      {
						         if(driver.findElement(By.linkText("Delete")).isDisplayed());
						         {
						        	 driver.findElement(By.linkText("Delete")).click();
						        	 Thread.sleep(3000);
								     driver.findElement(By.xpath("//*[@data-foldername='Test04']/div[7]/ul/li/img")).click();
								     driver.findElement(By.linkText("Delete Permanently")).click();
								     driver.findElement(By.xpath("//*[@id='btndeleteconfirmdone']")).click();
								     Thread.sleep(3000);
						         }
						      
						      }
						     catch(Exception e)
						      {
						    	  System.out.println("No Delete Permisssions");
						      }
							
							   System.out.println("restore_View executed");
						  }
					      
					      @Test(priority=49,groups= {"Download"})
						  @Parameters({"filepath","URL"})
						  public void uploadFile_View(String filepath,String URL) throws InterruptedException, IOException
						  {  
							   driver.get(URL);
							   driver.findElement(By.xpath("//*[@data-foldername='View']/h2/a")).click();
							   Thread.sleep(3000);
							   driver.findElement(By.xpath("//*[@data-foldername='Test04']/h2/a")).click();
							   Thread.sleep(3000);
			
							  driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click();
						//	  driver.findElement(By.id("btnuploadfile")).click();
							  WebDriverWait wait2=new WebDriverWait(driver,20);
							  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
							  String txt= driver.findElement(By.className("top-alert-message")).getText();
							   System.out.println(txt);
							   String actualtxt="Sorry, you do not have right to perform the action. Please contact your administrator";
							   Assert.assertEquals(actualtxt,txt);
							 
							   System.out.println("uploadFile_View executed");
						   
						  }
					       
					      @Test(priority=50,groups= {"Download"})
					      @Parameters({"URL"})
						  public void uploadFolder_View(String URL) throws InterruptedException, IOException
						  {  
							    driver.get(URL);
				        	    driver.findElement(By.xpath("//*[@data-foldername='View']/h2/a")).click();
				        	    Thread.sleep(3000);
				        	    driver.findElement(By.xpath("//*[@data-foldername='Test04']/h2/a")).click();
								Thread.sleep(3000);
							    driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click();
						//	    driver.findElement(By.xpath("//*[@id='btnuploadfolder']")).click();
							    WebDriverWait wait2=new WebDriverWait(driver,20);
								   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
							    String txt= driver.findElement(By.className("top-alert-message")).getText();
								System.out.println(txt);
			
								String actualtxt="Sorry, you do not have right to perform the action. Please contact your administrator";
								Assert.assertEquals(actualtxt,txt);
				
								System.out.println("uploadFolder_View executed");
							   
						  }  
					      
					      @Test(groups= {"Download"},priority=51)
						  @Parameters({"URL"})
						  public void RequestFileDeposit_View(String URL ) throws InterruptedException
						  { 
							driver.get(URL);
							driver.findElement(By.xpath("//*[@data-foldername='View']/div[7]/ul/li/img")).click(); 
							  
							 try
						      {
						         if(driver.findElement(By.id("adm_inbound_folder_3")).isDisplayed());
						         {
						        	 driver.findElement(By.id("adm_inbound_folder_3")).click();
						        	 driver.findElement(By.id("inbound_admsharesubject")).sendKeys("Document required for testing");
									 driver.findElement(By.id("inbound_admaddreceiver")).click();
									 driver.findElement(By.id("adm_mobileemail_txt1_0")).sendKeys("admin1test@protonmail.com");
									 driver.findElement(By.id("btnshareemaildone")).click();
									 driver.findElement(By.id("inbound_admsharesend")).click();
									 WebDriverWait wait4=new WebDriverWait(driver,20);
									 wait4.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
									 String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
								     System.out.println(Popupmsg);
						         }
						      
						      }
						     catch(Exception e)
						      {
						    	  System.out.println("No Request File Deposit Permisssions");
						      }
						
							 System.out.println("RequestFileDeposit_View executed");
						}
					  
					      @Test(priority=52,groups= {"Download"})
						  @Parameters({"URL","foldername3"})
						   public void deleteFolder_View(String URL,String foldername3) throws InterruptedException
						   {
							 driver.get(URL);
							 driver.findElement(By.xpath("//*[@data-foldername='View']/h2/a")).click(); 
							 Thread.sleep(3000);
							 driver.findElement(By.xpath("//*[@data-foldername='Test04']/div[7]/ul/li/img")).click();
							 try
						      {
						         if(driver.findElement(By.linkText("Delete")).isDisplayed());
						         {
						        	 driver.findElement(By.linkText("Delete")).click();
						        	 Thread.sleep(3000);
								     driver.findElement(By.xpath("//*[@data-foldername='Test04']/div[7]/ul/li/img")).click();
								     driver.findElement(By.linkText("Delete Permanently")).click();
								     driver.findElement(By.xpath("//*[@id='btndeleteconfirmdone']")).click();
								     Thread.sleep(3000);
						         }
						      
						      }
						     catch(Exception e)
						      {
						    	  System.out.println("No Delete Permisssions");
						      }
						
							  System.out.println("deleteFolder_View executed");
						  }
					      
				/*	      @Test(priority=53,groups= {"View"})
						  @Parameters({"URL"})
						  public void downloadFolder_View(String URL) throws InterruptedException
						  {   
							  driver.get(URL);
							  String folderpath="//*[@data-foldername='View']/div[6]/img[1]";
							  driver.findElement(By.xpath(folderpath)).isDisplayed();
							  
							  System.out.println("Folder can't be downloaded ");
						
							  System.out.println("downloadFolder_View executed");
							   
						  }
					      
					      @Test(groups= {"View"},priority=54)
						  @Parameters({"URL","downloadfilename4"})
						  public void downloadFile_View(String URL, String downloadfilename4) throws InterruptedException           //Download 1st file of All folder
						  {   
							  driver.get(URL);
							  String file="//*[@data-filename='"+downloadfilename4+"']/div[6]/img[1]";
							  driver.findElement(By.xpath("//*[@data-foldername='View']/h2/a")).click();
							
							  driver.findElement(By.xpath(file)).isDisplayed();
							 
							 
							 System.out.println("File cannot be downloaded");
							
						     System.out.println("downloadFile_View executed");
						     
						  }
						  */
						
					  
				  
}

package main.java.com.dcirrus.login;

import java.util.List ;   
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Set;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import main.java.com.dcirrus.login.UserModule.SwitchCurrentWindow;

	public class DcirrusAutomation 
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
	   
/*	   @Test
	   @Parameters({"id","password","corporateID"})
	   public void login(String id,String password,String corporateID)
	   {
	     driver.findElement(By.linkText("Business User")).click();
		 driver.findElement(By.id("loginid")).sendKeys(id);
		 driver.findElement(By.id("password")).sendKeys(password);
		 driver.findElement(By.id("corporateid")).sendKeys(corporateID);
		 String capchaval=JOptionPane.showInputDialog("Please enter the capcha value");
		 driver.findElement(By.id("logincaptcha")).sendKeys(capchaval);
		 driver.findElement(By.id("btnlogin")).click();
	   }
	   
	   
	   @Test
	   @Parameters()
	   public void Changepassword()
	   {
		   
		   driver.findElement(By.id("username")).click();
		   driver.findElement(By.id("adm_changepass")).click();
		   WebDriverWait wait2=new WebDriverWait(driver,40);
		   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("tbldatarows")));
		   driver.findElement(By.id("newpassword")).sendKeys("Test@1234");
		   driver.findElement(By.id("confirmpassword")).sendKeys("Test@1234");
		   driver.findElement(By.id("btnchangepass")).click();
		   WebDriverWait wait3=new WebDriverWait(driver,20);
		   wait3.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		   String txt= driver.findElement(By.className("top-alert-message")).getText();
		   System.out.println(txt);
		   
	   }
	   
	   @Test(priority=17)
		  @Parameters({"URL","id","corporateID"})
		  public void forgetPassword(String URL, String id, String corporateID)
		  {
			  driver.get(URL);
			  driver.findElement(By.linkText("Forgot Password")).click();
			  driver.findElement(By.id("loginidforgot")).sendKeys(id);
			  driver.findElement(By.id("corporateidforgot")).sendKeys(corporateID);
			  driver.findElement(By.id("btnforgot")).click();
			  WebDriverWait wait1=new WebDriverWait(driver,20);
			  wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
			  String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
			  System.out.println(Popupmsg);
			//  Assert.assertEquals("", Popupmsg);
			  
			  
		  }
	   
	   */
	   
      @AfterClass
	   @Parameters({"URL"})
	   public void tearDown(String URL) throws InterruptedException
	   {
		  
		  driver.quit();

	   }
      
      
	   @Test(priority=15)
	   @Parameters({"URL","renamedFolder1","subFoldername","renamedFolder"})
	   public void cleanup(String URL,String renamedFolder1,String subFoldername,String renamedFolder ) throws InterruptedException
		   {
			   driver.get(URL);
			   driver.findElement(By.xpath("//*[@data-foldername='"+renamedFolder1+"']/div[7]/ul/li/img")).click();
			   driver.findElement(By.linkText("Rename")).click();
			   driver.findElement(By.id("rename_admfoldername")).clear();
			   driver.findElement(By.id("rename_admfoldername")).sendKeys("All"); 
			   driver.findElement(By.id("rename_btncreatefolderdone")).click();
			   Thread.sleep(3000);
			   driver.findElement(By.xpath("//*[@data-foldername='All']/h2/a")).click();
			   driver.findElement(By.xpath("//*[@data-foldername='"+subFoldername+"']/div[7]/ul/li/img")).click();
			   driver.findElement(By.linkText("Delete")).click();
			   Thread.sleep(4000);
			   driver.findElement(By.xpath("//*[@data-foldername='"+subFoldername+"']/div[7]/ul/li/img")).click();
			   driver.findElement(By.linkText("Delete Permanently")).click();
		       driver.findElement(By.xpath("//*[@id='btndeleteconfirmdone']")).click();
		       Thread.sleep(6000);
			   driver.get(URL);
			   driver.findElement(By.xpath("//*[@data-foldername='Upload']/h2/a")).click();
			   driver.findElement(By.xpath("//*[@data-foldername='"+renamedFolder+"']/div[7]/ul/li/img")).click(); 
			   driver.findElement(By.linkText("Delete")).click();
			   Thread.sleep(4000);
			   driver.findElement(By.xpath("//*[@data-foldername='"+renamedFolder+"']/div[7]/ul/li/img")).click();
			   driver.findElement(By.linkText("Delete Permanently")).click();
		       driver.findElement(By.xpath("//*[@id='btndeleteconfirmdone']")).click();//*[@id="btndeleteconfirmdone"]
		       Thread.sleep(6000);
	   }
	   
	   @Test(priority=1,groups="createFolder")
	   @Parameters({"name1"})
	   public void createNewFolder(String name1) throws InterruptedException         //Software folder is created at 3rd
	   {
		    driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click(); 
		    driver.findElement(By.id("btncreatefolder")).click();
		    driver.findElement(By.id("admfoldername")).sendKeys(name1);
		    Thread.sleep(2000);
		    driver.findElement(By.xpath("//*[@id='btncreatefolderdone']")).click();
		    WebDriverWait wait2=new WebDriverWait(driver,20);
			wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		    String txt= driver.findElement(By.className("top-alert-message")).getText();
		    System.out.println(txt);
		    String actualtxt= "Folder created successfully. Please give appropriate permissions to make the Folder visible to others.";
		    Assert.assertEquals(actualtxt,txt,"Folder Exists");
            System.out.println("createNewFolder executed");
	   }
	   
	 
	   
	   @Test(groups="createFolder",priority=2)
	   @Parameters({"name","URL"})
	   public void existingFolder(String name,String URL) throws InterruptedException
	   {
		   driver.get(URL);
		   driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click();
		   driver.findElement(By.id("btncreatefolder")).click();
		   driver.findElement(By.id("admfoldername")).sendKeys(name);
		   Thread.sleep(2000);
		   driver.findElement(By.xpath("//*[@id='btncreatefolderdone']")).click();//*[@id="btncreatefolderdone"]
		   WebDriverWait wait2=new WebDriverWait(driver,20);
		   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		   String txt= driver.findElement(By.className("top-alert-message")).getText();

		   System.out.println(txt);
		  
		   String actualtxt= "Folder exists";
		   Assert.assertEquals(actualtxt,txt);
		   System.out.println("existingFolder executed");
	   }
	   
	   
	   
	   @Test(groups="createFolder",priority=3)
	   @Parameters({"URL"})
	   public void enterFolderName(String URL) throws InterruptedException 
	   {   
		   driver.get(URL);
		   driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click();
		   driver.findElement(By.id("btncreatefolder")).click();
		   Thread.sleep(2000);
		   driver.findElement(By.xpath("//*[@id='btncreatefolderdone']")).click();
		   WebDriverWait wait2=new WebDriverWait(driver,20);
		   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		   String txt= driver.findElement(By.className("top-alert-message")).getText();
		   System.out.println(txt);
		   
		   String actualtxt= "Please enter folder name";
		   Assert.assertEquals(txt,actualtxt);
		   System.out.println("enterFolderName executed");
	   }
	   
	   @Test(priority=4)
	   @Parameters({"subFoldername","URL"})
	   public void subFolder(String subFoldername,String URL) throws InterruptedException   //creating subfolder inside All folder ,named as list
	   {    
		
	       driver.get(URL);
		   driver.findElement(By.xpath("//*[@data-foldername='All']/h2/a")).click(); // clicked on All folder
		
		   Thread.sleep(10000);
		   driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
		   Thread.sleep(2000);
		   driver.findElement(By.xpath("//*[@id='btncreatefolder']")).click();
		   driver.findElement(By.xpath("//*[@id='admfoldername']")).sendKeys(subFoldername);
		   driver.findElement(By.xpath("//*[@id='btncreatefolderdone']")).click();
		   WebDriverWait wait1=new WebDriverWait(driver,20);
		   wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
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
	   
	  
	   
	   @Test(priority=12)
	   @Parameters({"name1","URL"})
	   public void deleteFolder(String name1,String URL) throws InterruptedException
	   {
		   driver.get(URL);
		   driver.findElement(By.xpath("//*[@data-foldername='"+name1+"']/div[7]/ul/li/img")).click();
			  
		   driver.findElement(By.linkText("Delete")).click();
		   Thread.sleep(4000);
		   
	       driver.findElement(By.xpath("//*[@data-foldername='"+name1+"']/div[7]/ul/li/img")).click();
	       driver.findElement(By.linkText("Delete Permanently")).click();
	       driver.findElement(By.xpath("//*[@id='btndeleteconfirmdone']")).click();
	       Thread.sleep(5000);
	       System.out.println("deleteFolder executed");
	  }
	  
	  @Test(priority=5)
      @Parameters({"URL","foldername1","renamedFolder1"})
	  public void rename(String URL,String foldername1 ,String renamedFolder1) throws InterruptedException
	  {
		   driver.get(URL);
		  
		   driver.findElement(By.xpath("//*[@data-foldername='"+foldername1+"']/div[7]/ul/li/img")).click();
		   driver.findElement(By.linkText("Rename")).click();
		   driver.findElement(By.id("rename_admfoldername")).clear();
		   driver.findElement(By.id("rename_admfoldername")).sendKeys(renamedFolder1);
		   driver.findElement(By.id("rename_btncreatefolderdone")).click();
		   WebDriverWait wait2=new WebDriverWait(driver,20);
		   wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		   String txt= driver.findElement(By.className("top-alert-message")).getText();
		   System.out.println(txt);
		   String actualtxt= "Folder renamed successfully";
		   Assert.assertEquals(actualtxt,txt);
		   System.out.println("rename executed");
		 
	  }
	  
	                      

	  @Test(priority=6)
	  @Parameters({"URL","renamedFolder1"})
	  public void restore(String URL,String renamedFolder1) throws InterruptedException   // Delete and restore All folder
	  {
		  driver.get(URL);
	      driver.findElement(By.xpath("//*[@data-foldername='"+renamedFolder1+"']/div[7]/ul/li/img")).click();
	      driver.findElement(By.linkText("Delete")).click();
	      Thread.sleep(4000);
	      driver.findElement(By.xpath("//*[@data-foldername='"+renamedFolder1+"']/div[7]/ul/li/img")).click();
	      driver.findElement(By.linkText("Restore")).click();
	      Thread.sleep(4000);
	      System.out.println("restore executed");
	  }
	  
	  
	  @Test(priority=7)
	  @Parameters({"URL","filepath"})
	  public void uploadFile(String URL, String filepath) throws InterruptedException, IOException
	  {  

		  driver.get(URL);
		  driver.findElement(By.xpath("//*[@data-foldername='Download']/h2/a")).click();
		  Thread.sleep(3000);
		  driver.findElement(By.xpath("//*[@data-foldername='Test02']/h2/a")).click();
		  Thread.sleep(3000);

		  driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click();
		  WebDriverWait wait=new WebDriverWait(driver,20);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuploadfile']")));
		  driver.findElement(By.id("btnuploadfile")).click();
		  WebDriverWait wait1=new WebDriverWait(driver,20);
		  wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='adm_fileuploadId']")));
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
	    

	  @Test(priority=8)
	  @Parameters({"URL"})
	  public void uploadFolder(String URL) throws InterruptedException, IOException
	  {  
		  driver.get(URL);
		  driver.findElement(By.xpath("//*[@data-foldername='Download']/h2/a")).click();
		  Thread.sleep(3000);
		  driver.findElement(By.xpath("//*[@data-foldername='Test02']/h2/a")).click();
		  Thread.sleep(3000);
		  driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']/i")).click();

		  WebDriverWait wait=new WebDriverWait(driver,20);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuploadfolder']")));

		  driver.findElement(By.xpath("//*[@id='btnuploadfolder']")).click();

		  WebDriverWait wait1=new WebDriverWait(driver,20);
		  wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='adm_fileuploadId']")));

		  driver.switchTo().frame("adm_fileuploadId");

		  WebElement folderInput = driver.findElement(By.id("uploadFolderId"));

		  folderInput.sendKeys(System.getProperty("user.dir") + "/src/main/java/config/TestData");
		  driver.switchTo().parentFrame();
		  WebDriverWait wait2=new WebDriverWait(driver,20);
		  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		  String txt= driver.findElement(By.className("top-alert-message")).getText();
		  System.out.println(txt);
		  Assert.assertEquals(txt,"Files uploaded. Processing the update..");
		  System.out.println("uploadFolder executed");

	  }   
	  
	  @Test(priority=12)
	  @Parameters({"URL","foldername3"})
	  public void downloadFolder(String URL,String foldername3) throws InterruptedException
	  {   
		  driver.get(URL);
		  String folderpath="//*[@data-foldername='"+foldername3+"']/div[6]/img[1]";
		  
		  driver.findElement(By.xpath(folderpath)).click();
		  Thread.sleep(2000);
		  WebDriverWait wait=new WebDriverWait(driver,50);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='modalfolderdownload']/div[1]/div")));
	      
		  System.out.println("Folder downloaded");
		   System.out.println("downloadFolder executed");
		   
		   
	  }

	  

	  
	  @Test(groups="DownloadFile",priority=9)
	  @Parameters({"URL","xlsfile"})
	  public void downloadFile(String URL,String xlsfile) throws InterruptedException           //Download 1st file of 6th folder
	  {   
		  driver.get(URL);
		  String file="//*[@data-filename='"+xlsfile+"']/div[6]/img[1]";
		  
		  
		  driver.findElement(By.xpath("//*[@data-foldername='View']/h2/a")).click();
		  driver.findElement(By.xpath(file)).click();
		
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
	     System.out.println("downloadFile executed");
	     
	  }
	  
	  @Test(groups="DownloadFile",priority=10)
	  @Parameters({"URL"})
	  public void downloadMultipleFiles(String URL) throws InterruptedException
	  {     
		  driver.get(URL);
		  driver.findElement(By.xpath("//*[@data-foldername='View']/h2/a")).click();        //download 1st and 2nd file of 5th folder
		  driver.findElement(By.xpath("//*[@id='adm_doc_name_0']/label/span")).click();
		  driver.findElement(By.xpath("//*[@id='adm_doc_name_1']/label/span")).click();
		  driver.findElement(By.id("moreall")).click();
		  driver.findElement(By.id("adm_download_file")).click();
		  Thread.sleep(6000);
		  
		  String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
		  System.out.println(Popupmsg);
		  Assert.assertEquals("Download started. You will receive a zip file", Popupmsg);
		  System.out.println("downloadMultipleFiles executed");
		  
	  }
	  @Test(groups="DownloadFile",priority=11)
	  @Parameters({"URL"})
	  public void downloadVersionList(String URL) throws InterruptedException
	  {  
		  driver.get(URL);
		  driver.findElement(By.xpath("//*[@data-foldername='View']/h2/a")).click();     //5th folder contains subfolder... inside subfolder ,1st file have version list..download the recent version
		  driver.findElement(By.id("admfileversionname_0")).click();
		 
		  driver.findElement(By.xpath("//*[@id='adm_download_Version_Popup_0']/div/img")).click();
		  Thread.sleep(4000);    
		  String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();//*[@id="adm_download_Version_Popup_0"]/div/img
		  System.out.println(Popupmsg);
		  Assert.assertEquals("Download started. Your file(s) will be downloaded shortly", Popupmsg);
		  System.out.println("downloadVersionList executed");
	  }
	  
	  @Test(priority=14)
	  @Parameters({"URL","filepath"})
	  public void requestFileDeposit(String URL,String filepath) throws InterruptedException
	  { 
		driver.get(URL);
		driver.findElement(By.xpath("//*[@data-foldername='View']/div[7]/ul/li/img")).click();  
		driver.findElement(By.linkText("Request File Deposit")).click();//*[@id="adm_folderrow_3"]/div[7]/ul/li/img
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
	  
	  WebDriverWait wait3=new WebDriverWait(driver,20);
	  wait3.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
	  
	  String txt= driver.findElement(By.className("top-alert-message")).getText();
	  System.out.println(txt);
	 
	  Assert.assertEquals("Your upload is completed successfully. this link is no longer valid", txt);

	  System.out.println("requestFiledeposit executed");
	  
	  } 
	  
	  
	  
	  
	  
	  @Test(priority=24)
	  @Parameters({"URL","docfile","pptfile","pdffile","xlsfile"})
	  public void downloadFileFunctionality(String URL, String docfile, String pptfile, String pdffile , String xlsfile) throws InterruptedException
	  {
		  
     	driver.get(URL);
		 String parent=driver.getWindowHandle(); 
		driver.findElement(By.cssSelector("#dropdownMenuLink > svg > g > path")).click();
		driver.findElement(By.xpath("//*[@id='admin_module']/img")).click();
		SwitchCurrentWindow.GetNewWindow();
		WebDriverWait wait1=new WebDriverWait(driver,20);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("img_corp_details")));
		driver.findElement(By.xpath("//*[@id='img_corp_details']")).click();
		WebDriverWait wait2=new WebDriverWait(driver,30);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnsavecorp")));
		driver.findElement(By.id("btnsavecorp")).click();
		//WebDriverWait wait7=new WebDriverWait(driver,30);
	//	wait7.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin_chk_watermark_view")));
		if(driver.findElement(By.id("admin_chk_watermark_view")).isSelected() && driver.findElement(By.id("admin_chk_watermark_download")).isSelected())
		{
			WebDriverWait wait6=new WebDriverWait(driver,30);
			wait6.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnsavecorp")));
		    driver.findElement(By.id("btnsavecorp")).click();
		}
		
		else
		{
			
			WebDriverWait wait3=new WebDriverWait(driver,30);
			wait3.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin_chk_watermark_view")));
			driver.findElement(By.id("admin_chk_watermark_view")).click();
			WebDriverWait wait4=new WebDriverWait(driver,30);
			wait4.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin_chk_watermark_download")));
			driver.findElement(By.id("admin_chk_watermark_download")).click();
		
			WebDriverWait wait5=new WebDriverWait(driver,30);
			wait5.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnsavecorp")));
			driver.findElement(By.id("btnsavecorp")).click();
			
		}
		WebDriverWait wait3=new WebDriverWait(driver,20);
		wait3.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		String txt= driver.findElement(By.className("top-alert-message")).getText();
		System.out.println(txt);
		Assert.assertEquals("Settings saved successfully.", txt);
		
	    driver.switchTo().window(parent);
	    String docfile1="//*[@data-filename='"+docfile+"']/div[6]/img[1]";
		String pptfile2= "//*[@data-filename='"+pptfile+"']/div[6]/img[1]"; 
		String pdffile3= "//*[@data-filename='"+pdffile+"']/div[6]/img[1]"; 
		String xlsfile4= "//*[@data-filename='"+xlsfile+"']/div[6]/img[1]"; 
		  
		  driver.findElement(By.xpath("//*[@data-foldername='View']/h2/a")).click();
		  
		 
		  driver.findElement(By.xpath(docfile1)).click();
		  Thread.sleep(5000);
		  WebDriverWait wait9=new WebDriverWait(driver,20);
		  wait9.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		  String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
		  System.out.println(Popupmsg);
		  Assert.assertEquals("Download started. Your file(s) will be downloaded shortly", Popupmsg);
		  
		  
		
	//	  WebDriverWait wait4=new WebDriverWait(driver,20);
	//	 wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pptfile2)));//*[@id="admdownloadfile_15"] /html/body/div[2]/div[2]/div[4]/div/div[4]/div[9]/div[6]/img[1]
		  driver.findElement(By.xpath(pptfile2)).click();
		  Thread.sleep(5000);
		  WebDriverWait wait10=new WebDriverWait(driver,20);
		  wait10.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		  String Popupmsg1= driver.findElement(By.className("top-alert-message")).getText();
		  System.out.println(Popupmsg1);
		  Assert.assertEquals("Download started. Your file(s) will be downloaded shortly", Popupmsg1);
		 
		 
		  WebDriverWait wait5=new WebDriverWait(driver,20);
		  wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pdffile3)));
		  driver.findElement(By.xpath(pdffile3)).click();
		  Thread.sleep(5000);
		  WebDriverWait wait11=new WebDriverWait(driver,20);
		  wait11.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		  String Popupmsg2= driver.findElement(By.className("top-alert-message")).getText();
		  System.out.println(Popupmsg2);
		  Assert.assertEquals("Download started. Your file(s) will be downloaded shortly", Popupmsg2);
		 
		  WebDriverWait wait6=new WebDriverWait(driver,20);
		  wait6.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xlsfile4)));
          driver.findElement(By.xpath(xlsfile4)).click();
          Thread.sleep(5000);
		  WebDriverWait wait12=new WebDriverWait(driver,20);
		  wait12.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		  String Popupmsg3= driver.findElement(By.className("top-alert-message")).getText();
		  System.out.println(Popupmsg3);
		  Assert.assertEquals("Download started. Your file(s) will be downloaded shortly", Popupmsg3);
		  
		 
		 
	    }   
	  
	  
	  
      @Test(priority=25)
	  @Parameters({"URL", "docfile"})
	  public void fileviewDOC(String URL, String docfile) throws InterruptedException
	  {
		 
		  driver.get(URL);
		  driver.findElement(By.xpath("//*[@data-foldername='View']/h2/a")).click();
          
		  Thread.sleep(2000);
	      driver.findElement(By.xpath("//span[text()='"+docfile+"']")).click();
	      Thread.sleep(2000);
		  driver.findElement(By.id("radio_viewer_type_2")).click();
		  Thread.sleep(2000);
	      WebDriverWait wait12=new WebDriverWait(driver,20);
	      wait12.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='adm_viewer_type_open']")));
		  driver.findElement(By.xpath("//*[@id='adm_viewer_type_open']")).click();
		 
		  Thread.sleep(3000);
		  SwitchCurrentWindow.GetNewWindow();
		  String geturl= driver.getCurrentUrl();
		  
		  System.out.println("File view DOC" + geturl);
		  Assert.assertTrue(geturl.contains(".pdf"));// verifying doc file in pdf format
		}
      
      @Test(priority=26)
	  @Parameters({"URL", "xlsfile"})
	  public void fileviewXLS(String URL, String xlsfile) throws InterruptedException
	  {
		  
		  driver.get(URL);
		  driver.findElement(By.xpath("//*[@data-foldername='View']/h2/a")).click();
          
		  Thread.sleep(2000);
	      driver.findElement(By.xpath("//span[text()='"+xlsfile+"']")).click();
	      Thread.sleep(2000);
		  driver.findElement(By.id("radio_viewer_type_1")).click();
		  Thread.sleep(2000);
	      WebDriverWait wait12=new WebDriverWait(driver,20);
	      wait12.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='adm_viewer_type_open']")));
		  driver.findElement(By.xpath("//*[@id='adm_viewer_type_open']")).click();
		 
		  Thread.sleep(5000);
		  SwitchCurrentWindow.GetNewWindow();
		  String geturl= driver.getCurrentUrl();
		  
		  System.out.println(geturl);
		  Assert.assertTrue(geturl.contains(".xlsx"));
		}
      
	  
	  @Test(priority=16)
	  @Parameters({"URL"})
	  public void userCreation(String URL) throws InterruptedException
	  {
		  driver.get(URL);
		  driver.findElement(By.cssSelector("#dropdownMenuLink > svg > g > path")).click();
		  driver.findElement(By.xpath("//*[@id='admin_module']/img")).click();
		  SwitchCurrentWindow.GetNewWindow();
		  WebDriverWait wait11=new WebDriverWait(driver,20);
		  wait11.until(ExpectedConditions.visibilityOfElementLocated(By.id("manage_user_bg")));
		  driver.findElement(By.id("manage_user_bg")).click();
		  WebDriverWait wait1=new WebDriverWait(driver,40);
		  wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuser']")));
		  Thread.sleep(4000);
		  driver.findElement(By.xpath("//*[@id='btnuser']")).click();
		  driver.findElement(By.id("txt_adduser_firstname")).sendKeys("Reema");
		  driver.findElement(By.id("txt_adduser_lastname")).sendKeys("De");
		  driver.findElement(By.id("txt_adduser_loginid")).sendKeys("admintestautomation@protonmail.com");
		  WebDriverWait wait2=new WebDriverWait(driver,20);
		  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("adduserconfirm")));
		  driver.findElement(By.id("adduserconfirm")).click();
		  WebDriverWait wait12=new WebDriverWait(driver,20);
		  wait12.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		  String Popupmsg3= driver.findElement(By.className("top-alert-message")).getText();
		  System.out.println(Popupmsg3);
		  Assert.assertEquals("User details saved successfully", Popupmsg3);
		  
		  
		}
	  @Test(priority=17)
	  @Parameters({"URL"})
	  public void resendActivation(String URL) throws InterruptedException
	  {
		  driver.get(URL);                                                                
		  driver.findElement(By.cssSelector("#dropdownMenuLink > svg > g > path")).click();
		  driver.findElement(By.xpath("//*[@id='admin_module']/img")).click();
		  SwitchCurrentWindow.GetNewWindow();                                              
		  WebDriverWait wait11=new WebDriverWait(driver,20);                               
		  wait11.until(ExpectedConditions.visibilityOfElementLocated(By.id("manage_user_bg")));
		  driver.findElement(By.id("manage_user_bg")).click();
		
		  try
		  {
		      if(driver.findElement(By.xpath("//div[contains(text(),'admintestresend@protonmail.com')]")).isDisplayed() )
		  {	  
		  
		//   WebDriverWait wait3=new WebDriverWait(driver,20);
		//  wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'admintestresend@protonmail.com')]/../div[5]/div/img")));
		  driver.findElement(By.xpath("//div[contains(text(),'admintestresend@protonmail.com')]/../div[5]/div/img")).click(); 
		  driver.findElement(By.linkText("Resend Activation Mail")).click();
		  WebDriverWait wait12=new WebDriverWait(driver,20);
		  wait12.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		  String Popupmsg3= driver.findElement(By.className("top-alert-message")).getText();
		  System.out.println(Popupmsg3);
		//  Assert.assertEquals("Successfully resend the activation mail", Popupmsg3);
		  }
		  }
		  
		  catch(Exception e)
		  {
			  WebDriverWait wait1=new WebDriverWait(driver,40);
			  wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuser']")));
			//Thread.sleep(4000);
			  driver.findElement(By.xpath("//*[@id='btnuser']")).click();
			  driver.findElement(By.id("txt_adduser_firstname")).sendKeys("Reema");
			  driver.findElement(By.id("txt_adduser_lastname")).sendKeys("De");
			  driver.findElement(By.id("txt_adduser_loginid")).sendKeys("admintestresend@protonmail.com");
			  WebDriverWait wait2=new WebDriverWait(driver,20);
			  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("adduserconfirm")));
			  driver.findElement(By.id("adduserconfirm")).click();
			  WebDriverWait wait13=new WebDriverWait(driver,20);
			  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
			  String Popupmsg4= driver.findElement(By.className("top-alert-message")).getText();
		      System.out.println(Popupmsg4);
		    //Assert.assertEquals("User details saved successfully", Popupmsg4);
			  driver.findElement(By.className("top-alert-close")).click();
			  Thread.sleep(2000);
			  
			  WebDriverWait wait3=new WebDriverWait(driver,20);
			  wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'admintestresend@protonmail.com')]/../div[5]/div/img")));
			  driver.findElement(By.xpath("//div[contains(text(),'admintestresend@protonmail.com')]/../div[5]/div/img")).click(); 
			  driver.findElement(By.linkText("Resend Activation Mail")).click();
			  WebDriverWait wait12=new WebDriverWait(driver,20);
			  wait12.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
			  String Popupmsg3= driver.findElement(By.className("top-alert-message")).getText();
			  System.out.println(Popupmsg3);
			  Assert.assertEquals("Successfully resend the activation mail", Popupmsg3);
		  
		  }
		   driver.get("https://mail.protonmail.com/login");
		    driver.findElement(By.id("username")).sendKeys("admintestresend@protonmail.com");
			driver.findElement(By.name("password")).sendKeys("Test@123");
			Thread.sleep(2000);
			driver.findElement(By.id("login_btn")).click();
			Thread.sleep(3000);
			WebDriverWait wait4=new WebDriverWait(driver,20);
			wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Activate your DCirrus Account']")));
			driver.findElement(By.xpath("//span[text()='Activate your DCirrus Account']")).click();
			Thread.sleep(3000);
			String msg2= driver.findElement(By.xpath("//span[text()='Activate your DCirrus Account']")).getText();
			System.out.println(msg2);
			Assert.assertEquals("Activate your DCirrus Account", msg2);
		  
	  }
	  
	  @Test(priority=18)
	  public void useractivationmail() throws InterruptedException
	  {
		    driver.get("https://mail.protonmail.com/login");
		    driver.findElement(By.id("username")).sendKeys("admintestautomation@protonmail.com");
			driver.findElement(By.name("password")).sendKeys("Test@123");
			driver.findElement(By.id("login_btn")).click();
			WebDriverWait wait4=new WebDriverWait(driver,20);
			wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Activate your DCirrus Account']")));
			driver.findElement(By.xpath("//span[text()='Activate your DCirrus Account']")).click();
			Thread.sleep(4000);
			driver.findElement(By.linkText("Activate Account")).click();
			SwitchCurrentWindow.GetNewWindow();
			 String geturl= driver.getCurrentUrl();
			 System.out.println(geturl);
			 
			  Assert.assertTrue(geturl.contains("useractivate.html"));
			  Thread.sleep(2000);
	  }
	  
	  @Test(priority=19)
	  public void userWelcomemail() throws InterruptedException
	  {
		    driver.get("https://mail.protonmail.com/login");
		    driver.findElement(By.id("username")).sendKeys("admintestautomation@protonmail.com");
			driver.findElement(By.name("password")).sendKeys("Test@123");
			Thread.sleep(2000);
			driver.findElement(By.id("login_btn")).click();
			WebDriverWait wait4=new WebDriverWait(driver,20);
			wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Welcome to DCirrus']")));
			driver.findElement(By.xpath("//span[text()='Welcome to DCirrus']")).click();
			String msg= driver.findElement(By.xpath("//span[text()='Welcome to DCirrus']")).getText();
			Assert.assertEquals("Welcome to DCirrus", msg);
			
	  }
	  
	  
	  
	  
	  @Test(priority=20)
	  @Parameters({"URL"})
	  public void userDeletion(String URL)
	  {
		 driver.get(URL);                                                                
		  driver.findElement(By.cssSelector("#dropdownMenuLink > svg > g > path")).click();
		  driver.findElement(By.xpath("//*[@id='admin_module']/img")).click();
		  SwitchCurrentWindow.GetNewWindow();                                              
		  WebDriverWait wait11=new WebDriverWait(driver,20);                               
		  wait11.until(ExpectedConditions.visibilityOfElementLocated(By.id("manage_user_bg")));
		  driver.findElement(By.id("manage_user_bg")).click();
		  WebDriverWait wait1=new WebDriverWait(driver,20);
		  wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'admintestautomation@protonmail.com')]/../div[5]/div/img")));
		  driver.findElement(By.xpath("//div[contains(text(),'admintestautomation@protonmail.com')]/../div[5]/div/img")).click();
		  WebDriverWait wait2=new WebDriverWait(driver,20);
		  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Delete User")));
		  driver.findElement(By.linkText("Delete User")).click();
		  WebDriverWait wait4=new WebDriverWait(driver,20);
		  wait4.until(ExpectedConditions.visibilityOfElementLocated(By.id("deleteuserconfirm")));
		  
		  driver.findElement(By.id("deleteuserconfirm")).click();
		  WebDriverWait wait13=new WebDriverWait(driver,20);
		  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		  String Popupmsg3= driver.findElement(By.className("top-alert-message")).getText();
		  System.out.println(Popupmsg3);
		  Assert.assertEquals("User deleted successfully", Popupmsg3);
		  
		  
	  }
	  
	  @Test(priority=21)
	  @Parameters({"URL","id1"})
	  public void ResetPasswordUser(String URL, String id1) throws InterruptedException
	  { 
		  driver.get(URL);  
		  driver.findElement(By.cssSelector("#dropdownMenuLink > svg > g > path")).click();
		  driver.findElement(By.xpath("//*[@id='admin_module']/img")).click();
		  SwitchCurrentWindow.GetNewWindow();                                              
		  WebDriverWait wait11=new WebDriverWait(driver,20);                               
		  wait11.until(ExpectedConditions.visibilityOfElementLocated(By.id("manage_user_bg")));
		  driver.findElement(By.id("manage_user_bg")).click();
		  WebDriverWait wait1=new WebDriverWait(driver,20);
		  wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'"+id1+"')]/../div[5]/div/img")));
		  driver.findElement(By.xpath("//div[contains(text(),'"+id1+"')]/../div[5]/div/img")).click();
		  WebDriverWait wait2=new WebDriverWait(driver,20);
		  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Reset Password")));
		  driver.findElement(By.linkText("Reset Password")).click();
		  WebDriverWait wait3=new WebDriverWait(driver,20);
		  wait3.until(ExpectedConditions.visibilityOfElementLocated(By.id("newpassword")));
		  driver.findElement(By.id("newpassword")).sendKeys("Test@123");
		  WebDriverWait wait4=new WebDriverWait(driver,20);
		  wait4.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirmpassword")));
		  driver.findElement(By.id("confirmpassword")).sendKeys("Test@123");
		  driver.findElement(By.id("resetpassconfirm")).click();
		  WebDriverWait wait13=new WebDriverWait(driver,20);
		  wait13.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		  String Popupmsg3= driver.findElement(By.className("top-alert-message")).getText();
		  System.out.println(Popupmsg3);
		  Assert.assertEquals("New Password can not be same as old password", Popupmsg3);
		 
	  }
	  @Test(priority=22)
	  @Parameters({"URL"})
	  public void shareFolderDownload(String URL) throws InterruptedException
	  { 
		driver.get(URL);
		driver.findElement(By.xpath("//*[@data-foldername='All']/div[7]/ul/li/img")).click();  
		driver.findElement(By.linkText("Share")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.id("admsharesubject")).sendKeys("Folder Shared to download");
		driver.findElement(By.id("admaddreceiver")).click();
		driver.findElement(By.id("adm_mobileemail_txt1_0")).sendKeys("user1prod@protonmail.com");
		driver.findElement(By.id("btnshareemaildone")).click();
		driver.findElement(By.id("admsharesend")).click();
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
		String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
		System.out.println(Popupmsg);
		Assert.assertEquals("Document list shared successfully", Popupmsg);
		driver.get("https://mail.protonmail.com/login");
		driver.findElement(By.id("username")).sendKeys("user1prod@protonmail.com");
		driver.findElement(By.name("password")).sendKeys("Test@123");
		driver.findElement(By.id("login_btn")).click();
		WebDriverWait wait4=new WebDriverWait(driver,20);
		wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Folder Shared to download']")));
		driver.findElement(By.xpath("//span[text()='Folder Shared to download']")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("View File(s)/Folder")).click();
		SwitchCurrentWindow.GetNewWindow();
		WebDriverWait wait1=new WebDriverWait(driver,20);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-filename='Test_XLS1.xlsx']/div[5]/img[1]")));
		driver.findElement(By.xpath("//*[@data-filename='Test_XLS1.xlsx']/div[5]/img[1]")).click();
		Thread.sleep(3000);
		
	  
	  }  
	  
	  @Test(priority=23)
	  @Parameters({"URL","filepath"})
	  public void shareFolderCollaborate(String URL, String filepath) throws InterruptedException
	  {
		    driver.get(URL);
			driver.findElement(By.xpath("//*[@data-foldername='All']/div[7]/ul/li/img")).click();  
			driver.findElement(By.linkText("Share")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.id("admsharesubject")).sendKeys("Folder Shared to Collaborate");
			driver.findElement(By.xpath("//*[@id='lblallowupload']/span")).click();
			driver.findElement(By.id("admaddreceiver")).click();
			driver.findElement(By.id("adm_mobileemail_txt1_0")).sendKeys("user1prod@protonmail.com");
			driver.findElement(By.id("btnshareemaildone")).click();
			driver.findElement(By.id("admsharesend")).click();
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("top-alert-message")));
			String Popupmsg= driver.findElement(By.className("top-alert-message")).getText();
			System.out.println(Popupmsg);
			Assert.assertEquals("Collaboration link shared successfully", Popupmsg);
			driver.get("https://mail.protonmail.com/login");
			driver.findElement(By.id("username")).sendKeys("user1prod@protonmail.com");
			driver.findElement(By.name("password")).sendKeys("Test@123");
			driver.findElement(By.id("login_btn")).click();
			WebDriverWait wait4=new WebDriverWait(driver,20);
			wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Folder Shared to Collaborate']")));
			driver.findElement(By.xpath("//span[text()='Folder Shared to Collaborate']")).click();
			Thread.sleep(4000);
			driver.findElement(By.linkText("View File(s)/Folder")).click();
			SwitchCurrentWindow.GetNewWindow();
			Thread.sleep(4000);
			driver.findElement(By.xpath("//*[@data-foldername='Test01']/h2/a")).click();
			Thread.sleep(3000);

			WebDriverWait wait1=new WebDriverWait(driver,20);
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("btncreatefolderpopup")));
			driver.findElement(By.xpath("//*[@id='btncreatefolderpopup']")).click();
			  WebDriverWait wait2=new WebDriverWait(driver,20);
			  wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='btnuploadfile']")));
			  driver.findElement(By.id("btnuploadfile")).click();
			  WebDriverWait wait12=new WebDriverWait(driver,20);
			  wait12.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='adm_fileuploadId']")));
			  driver.switchTo().frame("adm_fileuploadId");

			  WebElement fileInput = driver.findElement(By.id("uploadFileId"));
			  fileInput.sendKeys(System.getProperty("user.dir") + "/src/main/java/config/TestData/" + filepath);
			  Thread.sleep(4000);
			
	  }
	/*  @DataProvider(name="dataproviderfiles")
	     public Object[] dataprovider() 
	     {

	    	 //	    	 Object data[][]= new Object[3][2];
	    	 //	    	 data[0][0]= "C:\\Users\\91863\\Documents\\selenium.docx";
	    	 //	    	 data[0][1]= 20;
	    	 //	    	 data[1][0]="C:\\Users\\91863\\Documents\\CAPTCHA.docx";
	    	 //	    	 data[1][1]=20;
	    	 //	    	 data[2][0]="C:\\Users\\91863\\Documents\\CAPTCHA.docx";
	    	 //	    	 data[2][1]=30;
	    	 //              return data;

	    	 String fName = System.getProperty("FILENAME");
	    	 String cSeparator = System.getProperty("COMMONSEPARATOR");
	    	 String thisLine;
	    	 FileInputStream fis = null;
	    	 BufferedReader myInput = null;
	    	 Object[][] retValue = null;
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
	    				 // TODO Auto-generated catch block
	    				 e.printStackTrace();
	    			 }
	    	 }
	    	 return retValue;


	     }  */
	  
	}




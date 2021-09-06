package testingOnRediff;
import java.io.IOException;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import excelOperations.ReadWriteFromExcel;

public class OperationOnRediff {
	
	static WebDriver driver = new FirefoxDriver();
	public static boolean pfchck = false;
	public static String pathCheckPortfolio = "portfolio.xlsx";
	public static String pathSignIn = "LoginCredentials.xlsx";
	

	
	  static String result = " "; 
	  static String moduleName = " "; 
	  static String comment = " ";
	  
	 

	//visiting the site
	public static void gotoRediff() throws IOException {
		ReadWriteFromExcel.read(pathSignIn);
		String url=ReadWriteFromExcel.readList.get(0);
		driver.get(url);
		driver.manage().window().maximize();
	}

	// Log In with Credentials
	public static boolean SignIn() throws Exception {
		moduleName = "SignIn";
		String FirstUrl =" ";
		String SecondUrl =" ";
		String userId = null;
		String pass = null;
		
		//click on sign-In on anchor tag
		driver.findElement(By.xpath("//a[contains(text(),'Sign In')]")).click();		
		
		
		//System.out.println(ReadWriteFromExcel.readwrite);
		//Reading UserId and password from Excel file
		for (int i = 1; i < ReadWriteFromExcel.readList.size(); i++) {
			userId = ReadWriteFromExcel.readList.get(i);
			//System.out.println(userId);
			i++;
			pass = ReadWriteFromExcel.readList.get(i);
			//System.out.println(pass);
		}
		
		driver.findElement(By.xpath("//input[@name='email-id']")).sendKeys(userId);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='passwd']")).sendKeys(pass);
		Thread.sleep(1000);
		// before click on submit
		 FirstUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		Thread.sleep(2000);
		// After click on submit
		SecondUrl = driver.getCurrentUrl();
		
		//writing test case for login pass or fail
		if (FirstUrl.contentEquals(SecondUrl)) {
			
			  result = "Fail"; 
			  comment = "SignIn failed";
			 
			pfchck = false;
			System.out.println("SignIn Module -> Fail -> Sign In Failed");
		} else {
			
			  result = "Pass"; 
			  comment = "SignIn Successful";
			  System.out.println("SignIn Module -> Pass -> Sign In Successful");
			 
			pfchck = true;
		}
		
		
		System.out.println("-----------------------------------------------------------------");
		System.out.println("  Url before login: "+FirstUrl);
		System.out.println("  Url After login: "+SecondUrl);
		System.out.println("  Page Title: "+driver.getTitle());
		System.out.println("--------------***********---------------------------------------");
		
		//passing values to moduleCheck method 
		ReadWriteFromExcel.writeTestCases("D:\\selenium1\\mini_Project_rediff\\testCasees.txt", moduleName, result, comment);
		//It  clears array list 
		ReadWriteFromExcel.readList.clear();
	
		return pfchck;
	}

	// Check portfolio if not exist Create
	// CreatePortfolio
	public static  boolean createPortfolio() throws Exception {
		boolean crPort = false;
		
		//passing portfolio List to read method of ReadWriteFromExcel class
		ReadWriteFromExcel.read(pathCheckPortfolio);
	
		//reading values from Excel
		for (int i = 0; i <ReadWriteFromExcel.readList.size(); i++) {
			
			//data from Excel file
			String element1 = ReadWriteFromExcel.readList.get(i);		
			Thread.sleep(3000);
			System.out.println("-------------------------------------------------");
			//before creating portfolio it checks portfolio is exist or not
			if (OperationOnRediff.checkPortfolio(element1)) {
				
				System.out.println("Result -> "+element1 + " " + "Portfolio Exist");

				String portfolio1 = element1 + " " + "Portfolio Exist";
				
				//write to excel file
				ReadWriteFromExcel.writeText("D:\\selenium1\\mini_Project_rediff\\Result.txt", portfolio1);
				moduleName = "Create Portfolio";
				
				crPort = false;
				

			} else {
				
				//Thread.sleep(2000);
				//click on button of create portfolio
				driver.findElement(By.xpath("//a[@id='createPortfolio']")).click();
				Thread.sleep(2000);

				driver.findElement(By.xpath("//input[@name='create']")).clear();
				Thread.sleep(2000);
				
				//input for assigning portfolio name 
				driver.findElement(By.xpath("//input[@name='create']")).sendKeys(element1);
				Thread.sleep(2000);
				//click on button of create portfolio
				driver.findElement(By.xpath("//input[@value='Create Portfolio']")).click();//
				
				crPort = true;
				
				System.out.println("Result -> "+element1 + " " + "Portfolio Created");

			}
			
			//writing test case for CreatePortfolio method
			
			  if(crPort) {
			  
			  result = "Pass"; 
			  comment = "Portfolio Created Successfully";
			  System.out.println("CreatPortfolio Module ");
			  System.out.println(element1+" "+"Portfolio Created"); 
			  }else {
			  
			  result = "Pass"; 
			  comment = "Portfolio Already Exist";
			  System.out.println(element1+" "+"Portfolio Already Exist"); 
			  }
			 
			  
			//passing values to moduleCheck method
			ReadWriteFromExcel.writeTestCases("D:\\selenium1\\mini_Project_rediff\\testCasees.txt", moduleName, result,comment);
		}
		
		return crPort;

	}

	//It checks  Portfolio is exist or not
	public static boolean checkPortfolio(String pf1) throws Exception {
		
		Thread.sleep(2000);

		
		  moduleName = "checkPortfolio"; 
		  comment = "";
		 
		pfchck = false;
		//getting list of existing elements of portfolio
		Thread.sleep(2000);
		WebElement pf = driver.findElement(By.xpath("//select[@id='portfolioid']"));
		Select pfTitle = new Select(pf);
		List<WebElement> pfTitle_options = pfTitle.getOptions();

		//compare existing elements of portfolio to excel data
		for (int i = 0; i < pfTitle_options.size(); i++) {
			if (pfTitle_options.get(i).getText().toString().equals(pf1)) {
				
				 //System.out.println(pfTitle_options.get(i).getText());
				pfchck = true;

			}
			
		}
		//test case for check CheckPortfolio module run successfully or not
		
		  if (pfchck) { 
			  result = "Pass"; 
			  comment = "CheckPortfolio module run successfully";
		  System.out.println("CheckPortfolio Module -> Pass ->  Portfolio Exist "); 
		  }
		  else
		  { 
			  //System.out.println(pfchck);
			  result = "Fail"; 
			  comment = "CheckPortfolio module run successfully";
		  System.out.println("CheckPortfolio Module -> Pass ->  Portfolio not exist ");
		  }
		 
		//passing values to moduleCheck method
		ReadWriteFromExcel.writeTestCases("D:\\selenium1\\mini_Project_rediff\\testCasees.txt", moduleName, result, comment);
		return pfchck;
		

	}
}

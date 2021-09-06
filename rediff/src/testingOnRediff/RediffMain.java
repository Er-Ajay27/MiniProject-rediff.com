package testingOnRediff;



public class RediffMain {

	static String url= "https://money.rediff.com/index.html";
	public static void main(String[] args) throws Exception {
		
		  System.setProperty("webdriver.gecko.driver", "D:\\fire\\geckodriver.exe");
		  
		  
		  //OperationOnRediff op = new OperationOnRediff();
		 
		  OperationOnRediff.gotoRediff();
		  OperationOnRediff.SignIn();
		 // ReadWriteFromExcel.checkCreateModule();
		  OperationOnRediff.createPortfolio();
		 // ReadWriteFromExcel.checkSignModule();
		 // ReadWriteFromExcel.checkCheckPortfolioModule();
		  
		  //ReadWriteFromExcel.moduleCheck("D:\\selenium1\\mini_Project_rediff\\testCasees.txt");
		
		  
	}

}

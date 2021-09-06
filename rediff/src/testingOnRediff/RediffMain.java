package testingOnRediff;



public class RediffMain {

	public static void main(String[] args) throws Exception {
		
		  System.setProperty("webdriver.gecko.driver", "D:\\fire\\geckodriver.exe");
		  
		 
		 
		  OperationOnRediff.gotoRediff();
		  OperationOnRediff.SignIn();
		 
		  OperationOnRediff.createPortfolio();
		 
		
		  
		  
	}

}

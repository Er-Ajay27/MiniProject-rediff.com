package excelOperations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ReadWriteFromExcel {
	
	static DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	static Date date = java.util.Calendar.getInstance().getTime();
	static String reportDate = df.format(date);
	static String dateToPrintToFile = reportDate;
	public static ArrayList<String> readList= new ArrayList<>();
	public static boolean creatPortfolio = false;
	public static boolean checkPortfolio= false;
	public static boolean signInCheck = false;
	public static String moduleName = " ";
	public static String result = " ";
	public static String comment =" ";
	
	//Reading Excel file
	public static void read(String excelfile) throws IOException  {
		//ArrayList<String> ar=new ArrayList<String>();
		File src = new File(System.getProperty("user.dir")+"\\src\\excel\\"+excelfile);
		
		try {
			//read data from file
			FileInputStream  fis = new FileInputStream(src);
			XSSFWorkbook xsf = new XSSFWorkbook(fis);
			//Object of excel Sheet
			XSSFSheet sheet = xsf.getSheetAt(0);
			
			//Iterate through each row one by one
			 Iterator<Row> itr = sheet.iterator();
			 //return true if the given list iterator contains more number
		        while (itr.hasNext()) {
		        	//iterate on next row
		            Row row = itr.next();
		            
		            //reading rows from excel
		            Iterator<Cell> cellIterator = row.cellIterator();
		            //returns true if the iteration has more elements
		            while (cellIterator.hasNext()) {
		            	//iterate on next cell
		                Cell cell = cellIterator.next();
		                //set cell type(numeric,formula,string). If the cell currently contains
		                switch (cell.getCellType()) {
		                case STRING:
		                    //geting the values from excel and stored into variable
		                    String get = cell.getStringCellValue();
		                    //Add  data into array List 
		                    readList.add(get);
		                    break;
		                    
		                default:

		                
		                }
		            }
		              xsf.close();        
		            }
			  //String entrySheet = sheet.getRow(1).getCell(0).getStringCellValue();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		  System.out.println(readList);
		  //System.out.println("Entry : "+entrySheet);
		 	
		
		
	}

	//write to text file
	public static void writeText(String filePath, String dataToWrite) throws IOException {

		FileWriter fw = new FileWriter(filePath, true);
		//BufferedWriter bw = new BufferedWriter(fw);
		String str = dataToWrite;
	
		
		
		
		fw.write("\r\n");
		fw.write("------------------------------");
		fw.write("\r\n");
		fw.write(dateToPrintToFile);
		fw.write("\r\n");
		fw.write("------------------------------");
		fw.write("\r\n");
		fw.write(str);
		fw.write("\r\n");
		fw.write("----------**********----------");
		fw.write("\r\n");
	

		

		// System.out.println("check file");
		fw.close();
	}
	
	//Check Each Module
public static void writeTestCases(String filePath,String moduleName,String result,String comment) throws IOException {
		
	
	
	FileWriter FW = new FileWriter(filePath, true);
	BufferedWriter BW = new BufferedWriter(FW);
	BW.newLine();
	BW.write(dateToPrintToFile);
	BW.newLine();
	BW.write(moduleName+ "Module");
	BW.newLine();
	BW.write("--------------------------------------------------------------------");
	BW.newLine();
	BW.write("Module Name || "); // Writing In To File.
	BW.write("Test Result || "); // Writing In To File.
	BW.write("Comments"); // Writing In To File.

	// To write next string on new line.
	BW.newLine();
	BW.write("--------------------------------------------------------------------");
	BW.newLine();
	BW.append(moduleName + " || ");
	BW.append(result + " || ");
	BW.append(comment);	
	BW.newLine();
	BW.write("--------------------------------------------------------------------");
	BW.newLine();
	
	BW.close();
	FW.close();
	}
}

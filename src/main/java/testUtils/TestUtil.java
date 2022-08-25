package testUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ClassPathUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openxml4j.exceptions.InvalidFormatException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import base.BaseClass;

public class TestUtil extends BaseClass {

	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	public static String TESTDATA_SHEET_PATH = "./src/main/java/inputFiles/TransformerAuto.xlsx";

	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;
	static File f;
	
	@DataProvider
	public static Object[][] getTestData(String sheetName) throws InvalidFormatException {
		
		FileInputStream file = null;
		
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum();i++) {
		for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
//			data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			Cell cell = sheet.getRow(i + 1).getCell(k,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			
			if (cell.getCellType().equals(CellType.NUMERIC)) {
				data[i][k] = new Integer((int)cell.getNumericCellValue()).toString();
			}
			if (cell.getCellType().equals(CellType.STRING)) {
				System.out.println(cell.getStringCellValue());
				try {
				data[i][k] = Integer.parseInt(cell.getStringCellValue().substring(0, cell.getStringCellValue().indexOf('.')));
				} catch(Exception e) {
					data[i][k] = cell.getStringCellValue();
				}
				
			}
			
			if (cell==null || cell.getCellTypeEnum()== CellType.BLANK) {
				data[i][k] = "";
			}
			
			}
		
		}
		
		for (int i = 0; i < data.length;i++) {
			for (int k = 0; k < data[0].length; k++) {
				
				 System.out.print(" data -" + data[i][k]);
				
				}
			
			}
		
		return data;
	}
	
	public static String takeScreenshotAtEndOfTest() throws IOException {
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("/screenshots"+ System.currentTimeMillis() + ".png"));
		return TESTDATA_SHEET_PATH ;

	}
	
	public static List<Map<String,String>> getTestDataInMap() throws IOException{
		
		List<Map<String,String>> testDataAllRows=null;
		  
		  Map<String,String> testData=null;

		  try {
		   
			  FileInputStream fileInputStream=new FileInputStream(TESTDATA_SHEET_PATH);
		   Workbook workbook=new XSSFWorkbook(fileInputStream);
		   Sheet sheet=workbook.getSheetAt(1);
		   int lastRowNumber=sheet.getLastRowNum();
		   
		   System.out.println(lastRowNumber);
		   
		   int lastColNumber=sheet.getRow(0).getLastCellNum();
		   
		   System.out.println(lastColNumber);
		   
		   List list=new ArrayList();
		   for (int i = 0; i < lastColNumber; i++) {
		    Row row=sheet.getRow(0);
		    Cell cell=row.getCell(i);
		    String rowHeader=cell.getStringCellValue().trim();
		    
		    System.out.println(rowHeader);
		    
		    list.add(rowHeader);
		   }
		   
		    testDataAllRows=new ArrayList<Map<String,String>>();
		    
		    for(int j=1;j<=lastRowNumber;j++) {
		    	
		    	Row row=sheet.getRow(j);
		    	testData= new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER);
		    	for(int k=0;k<lastColNumber;k++) {
		    		Cell cell = row.getCell(k);
		    		String colvalue=cell.getStringCellValue().trim();
		    		testData.put((String) list.get(k), colvalue);
		    		
		    	}
		    	
		    	testDataAllRows.add(testData);
		    }
		   
		   
		  } catch (FileNotFoundException e) {
		   e.printStackTrace();
		  }
		  return testDataAllRows;
		  }
	
public String getData(String sheetName ,int rowNo, int cellNo) throws IOException {
	
	
	try {
		f = new File(System.getProperty("user.dir")+File.separator+"AutoTransformer.xlsx");
	} catch (Exception e) {
		System.out.println("could not access input data file");
		e.printStackTrace();
	}
	
	FileInputStream fis = new FileInputStream(f);
	
//	System.out.println(f);
//	System.out.println(fis);
//	System.out.println(fis==null);
	
	XSSFWorkbook w = new XSSFWorkbook(fis);
	
//	System.out.println(ClassLoader.getSystemResourceAsStream("\\AutoTransformer.xlsx")!=null);
//	InputStream Is =this.getClass().getResourceAsStream("/AutoTransformer.xlsx");
//	
//
//	
//	System.out.println(Is);
//
//	System.out.println(Is!=null);
//	
//	XSSFWorkbook w = new XSSFWorkbook(Is);	
//	System.out.println(w);
	
//		Workbook w = WorkbookFactory.create(Is);
		 Sheet sheet = w.getSheet(sheetName);
		 Row r = sheet.getRow(rowNo);
		 Cell c =r.getCell(cellNo ,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//		 String value = c.getStringCellValue();
		String value;
		CellType cellType = c.getCellType();
		if (c.getCellTypeEnum()== CellType.STRING) {
			value=c.getStringCellValue();
		}else if (DateUtil.isCellDateFormatted(c)) {
			
			Date d = c.getDateCellValue();
			SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
			value = s.format(d);

		}else {
			
			double d = c.getNumericCellValue();
			long l =(long) d;
			value = String.valueOf(l);
			
		}
		
			 if (c==null ||c.getCellTypeEnum()== CellType.BLANK) {
					value = "";
				}
	
		return value;
	
	}

public static String getScreenshot() {
	
	TakesScreenshot ts = (TakesScreenshot) driver;
	File src = ts.getScreenshotAs(OutputType.FILE); 
	String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
	File destination = new File(path);
	try {
		FileUtils.copyFile(src, destination);
	} catch (IOException e) {
		e.printStackTrace();
	}
	return path;
	
}
	
}

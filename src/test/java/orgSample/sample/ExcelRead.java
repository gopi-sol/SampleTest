package orgSample.sample;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;



public class ExcelRead {
	
	public String login(String sheetName) throws IOException, URISyntaxException {
		
		File f = new File("./excel/TransformerAuto.xlsx");
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook w = new XSSFWorkbook(fis);
		 XSSFSheet sheet = w.getSheet(sheetName);
		 Row r = sheet.getRow(1);
		 Cell c =r.getCell(1 ,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
		 String value = c.getStringCellValue();
		 
		 if (c==null ||c.getCellType()== CellType.BLANK) {
				value = "";
			}
	System.out.println(value);
		return value;
	
	}
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		ExcelRead er = new ExcelRead();
		er.login("Logindata");
	}

}


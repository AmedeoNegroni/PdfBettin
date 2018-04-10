package it.ariadne;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static Map<String,String> readXLSFile(String excelFile) throws IOException
	{
	
		//File myFile = new File("/home/amedeo/Documenti/provaCedolini.xlsx");
		File myFile = new File(excelFile);
        FileInputStream fis = new FileInputStream(myFile);

        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
       
        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
       
        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = mySheet.iterator();
        
        List<String> nomi = new ArrayList<>();
        List<String> passwordList = new ArrayList<>();
        Map<String,String> mappa = new HashMap<>();
        String nome = "";
        String cognome = "";
        String password = "";
        int counter = 0;
        
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            
            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();
                
                if (cell.getColumnIndex() == 2 && cell.getRowIndex() != 0) {
                	nome = cell.getStringCellValue();
                } else if(cell.getColumnIndex() == 1 && cell.getRowIndex() != 0) {
                	cognome = cell.getStringCellValue();
                } else if(cell.getColumnIndex() == 3 && cell.getRowIndex() != 0) {
                	if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                		Double cellValue = cell.getNumericCellValue();
                		password = Integer.toString(cellValue.intValue());
                	} else {
                		password = cell.getStringCellValue();
                	}
                }
            }
           
            if(counter > 0) {
            	nomi.add(cognome + " " + nome);   
            	passwordList.add(password);
            	mappa.put(cognome + " " + nome, password);
            }
            
            counter++;
        }
        
        return mappa;
	}

}

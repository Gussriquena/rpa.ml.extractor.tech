package tech.talents.controller;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tech.talents.model.Product;

public class ExcelController {
	
	public void writeOutputFile(List<Product> products) {
		try {
			// xlsx
			XSSFWorkbook workbook = new XSSFWorkbook(); 
			XSSFSheet sheet = workbook.createSheet("All Products");
			
			int indexRow = 0;
			for (Product product : products) {
				Row row = sheet.createRow(indexRow++);
				
				Cell cell = row.createCell(0);
				cell.setCellValue(product.getNome());
				
				cell = row.createCell(1);
				cell.setCellValue(product.getPreco());
			}
			
			FileOutputStream fileOutput = new FileOutputStream("C:\\Arquivos\\tech\\output_products.xlsx");
			workbook.write(fileOutput);
			workbook.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}

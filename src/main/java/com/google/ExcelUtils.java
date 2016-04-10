package com.google;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;
import java.util.Iterator;

/**
 * @author ajay.kg created on 06/04/16.
 */

@Slf4j
public class ExcelUtils {

	private XSSFSheet excelWSheet;
	private XSSFWorkbook excelWBook;

	// Constructor to connect to the Excel with sheetname and Path
	public ExcelUtils(String path, String sheetName) throws Exception {

		try {
			// Open the Excel file
			FileInputStream excelFile = new FileInputStream(path);

			// Access the required test data sheet
			excelWBook = new XSSFWorkbook(excelFile);
			excelWSheet = excelWBook.getSheet(sheetName);
		}
		catch (Exception e){
			throw (e);
		}
	}

	// This method is to set the rowcount of the excel.
	public int excelGetRows() throws Exception {

		try{
			return excelWSheet.getPhysicalNumberOfRows();
		}
		catch (Exception e){
			throw (e);
		}
	}

	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		}

		return null;
	}

//	private void writeBook(Book aBook, Row row) {
//		Cell cell = row.createCell(0);
//		cell.setCellValue(aBook.getTitle());
//
//		cell = row.createCell(1);
//		cell.setCellValue(aBook.getAuthor());
//
//		cell = row.createCell(2);
//		cell.setCellValue(aBook.getPrice());
//	}
//
//	public void writeToExcel(List<Book> listBook) throws IOException {
//
//		XSSFWorkbook workbook = new XSSFWorkbook();
//		XSSFSheet sheet = workbook.createSheet();
//
//		int rowCount = 0;
//
//		for (Book aBook : listBook) {
//			Row row = sheet.createRow(rowCount);
//			writeBook(aBook, row);
//			rowCount += 1;
//		}
//
//		try (FileOutputStream outputStream = new FileOutputStream(this.excelFilePath)) {
//			workbook.write(outputStream);
//		}
//	}

	// This method to get the data and get the value as strings.
	public String getCellDataAsString(int rowNum, int colNum) throws Exception {

		try {
			String cellData = excelWSheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			log.info("The value of CellData " + cellData);
			return cellData;
		}
		catch (Exception e) {
			log.error("EXCEL::GET::CELL::DATA::AS::STRING::FAILED: ROW: {}, COL: {}", rowNum, colNum);
			return null;
		}
	}

	// This method to get the data and get the value as number.
	public double getCellDataAsNumber(int rowNum, int colNum) throws Exception {

		try {
			double cellData = excelWSheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
			log.info("The value of CellData " + cellData);
			return cellData;
		}
		catch (Exception e){
			log.error("EXCEL::GET::CELL::DATA::AS::NUMBER::FAILED: ROW: {}, COL: {}", rowNum, colNum);
			return 000.00;
		}
	}

	public String[][] getExcelData() throws Exception {

		int colCount = excelWSheet.getRow(1).getLastCellNum();
		int rowCount = excelGetRows();
		int row = 0;
		log.info("GET EXCEL DATA ROWS: {}, COLS: {}", rowCount, colCount);
		Iterator<Row> iterator = excelWSheet.iterator();
		String str[][] = new String[rowCount][colCount];
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			if (row > 0) {

				Iterator<Cell> cellIterator = nextRow.cellIterator();

				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					int type = nextCell.getCellType();
					Object res = "";
					switch (type) {
					case Cell.CELL_TYPE_NUMERIC: // 0
						nextCell.setCellType(Cell.CELL_TYPE_STRING);
						res = nextCell.getStringCellValue().trim();
						break;
					case Cell.CELL_TYPE_STRING: // 1
						res = nextCell.getStringCellValue().trim();
						break;
					case Cell.CELL_TYPE_BLANK: // 3
						res = "No value in cell";
						break;
					case Cell.CELL_TYPE_BOOLEAN: // 4
						res = nextCell.getBooleanCellValue();
						break;
					default:
						throw new RuntimeException("We don't support this cell type: " + type);
					}
					str[row - 1][nextCell.getColumnIndex()] = String.valueOf(res);
				}
			}
			row += 1;
		}
		return str;
	}
}
package q_application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	public static void main(String[] args) throws Exception {

		// fun3();
//		customer() ;
	}

	public static void customer() throws Exception {
		XSSFWorkbook book = new XSSFWorkbook(new FileInputStream("【导出】ecs系统-客户信息（2017-12-21）.xlsx"));
		XSSFSheet sheet = book.getSheetAt(0);
		int lastRowNum = sheet.getLastRowNum();
		File file = new File("customer.sql");
		XSSFRow firstRow = sheet.getRow(0);
		List<String> fields = getFields(firstRow);
		FileWriter fr = new FileWriter(file);
		for (int i = 1; i < lastRowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			
			Map<String,Object> map=new HashMap<>();
			 for (int j = 0; j < fields.size(); j++) {
				 String key=fields.get(j);
				 row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
				 String value = row.getCell(j).getStringCellValue();
				 map.put(key, value);
			}
			 String insertSql = Util.getInsertSql(map, "plt_ecs_customer");
//			 System.out.println(insertSql);
			 fr.write(insertSql+";\n");
			 
		}
		fr.flush();
		fr.close();
	}

	public static List<String> getFields(XSSFRow row) {
		List<String> list = new ArrayList<String>();
		Iterator<Cell> cellIterator = row.cellIterator();
		for (Cell cell : row) {
			list.add(cell.getStringCellValue());
		}
		return list;
	}

}

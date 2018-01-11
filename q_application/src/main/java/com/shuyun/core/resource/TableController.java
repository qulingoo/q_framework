package com.shuyun.core.resource;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shuyun.core.bize.IFieldBize;
import com.shuyun.core.bize.ITableModelBize;
import com.shuyun.core.entity.Field;
import com.shuyun.core.entity.TableModel;

@Controller
@RequestMapping("/table")
public class TableController {
	@Autowired
	ITableModelBize iTableModelBize;
	@Autowired
	IFieldBize iFieldBize;

	@ResponseBody
	@RequestMapping("/addTable")
	public void addTable(TableModel table) {
		iTableModelBize.insert(table);
	}

	@ResponseBody
	@RequestMapping("/updateTable")
	public void updateTable(TableModel table) {
		iTableModelBize.update(table);
	}

	@ResponseBody
	@RequestMapping("/deleteTable")
	public void deleteTable(String id) {
		iTableModelBize.delete(id);
		;
	}

	@ResponseBody
	@RequestMapping("/getTableById")
	public TableModel getTableById(String id) {
		return iTableModelBize.findById(id);
	}

	@ResponseBody
	@RequestMapping("/getTableByPackId")
	public List<TableModel> findByPackId(String id) {
		return iTableModelBize.findByPackId(id);
	}

	@ResponseBody
	@RequestMapping("/findByPage")
	public List<TableModel> findByPage(int page) {
		return iTableModelBize.findByPage(page);
	}

	@ResponseBody
	@RequestMapping("/count")
	public int count() {
		return iTableModelBize.count();
	}

	@ResponseBody()
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@Transactional
	public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		Map<String, Integer> typeMap = new HashMap<>();
		typeMap.put("INTEGER", 4);
		typeMap.put("INT", 4);
		typeMap.put("DECIMAL", 3);
		typeMap.put("CHAR", 1);
		typeMap.put("VARCHAR", 12);
		typeMap.put("TIMESTAMP", 93);
		typeMap.put("DATETIME", 94);
		typeMap.put("CLOB", 2005);
		if (!file.isEmpty()) {
			try {

				HSSFWorkbook book = new HSSFWorkbook(file.getInputStream());
				int sheets = book.getNumberOfSheets();
				for (int i = 0; i < sheets; i++) {
					HSSFSheet sheet = book.getSheetAt(i);
					if (sheet == null) {
						continue;
					}
					String tableComment = sheet.getSheetName();
					HSSFRow row = sheet.getRow(0);
					HSSFCell cell = row.getCell(1);
					String table_name = cell.getStringCellValue();
					TableModel table = new TableModel();
					table.setId(UUID.randomUUID().toString());
					table.setComment(tableComment);
					table.setTable_name(table_name);
					table.setIs_exist(0);
					iTableModelBize.insert(table);
					int lastRowNum = sheet.getLastRowNum();
					for (int j = 2; j <= lastRowNum; j++) {
						HSSFRow row2 = sheet.getRow(j);
						Field field = new Field();
						field.setId(UUID.randomUUID().toString());
						field.setField_name(row2.getCell(1) == null ? "" : row2.getCell(1).toString());
						field.setComment(row2.getCell(0) == null ? "" : row2.getCell(0).toString());
						field.setTypes(
								row2.getCell(2) == null ? -1 : typeMap.get(row2.getCell(2).toString().toUpperCase()));
						field.setAccuracy(row2.getCell(3) != null ? row2.getCell(3).toString() : "");
						field.setDindex(j - 2);
						field.setTable_id(table.getId());
						iFieldBize.insert(field);
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			return "/index.html";
		} else {
			return "上传失败，因为文件是空的.";
		}
	}

	@ResponseBody
	@RequestMapping("/xml")
	public String xml(String id) throws Exception {
		TableModel table = iTableModelBize.findById(id);
		StringBuffer sb = new StringBuffer();
		sb.append("<table id=\"");
		sb.append("db@").append(table.getTable_name()).append("\" code=\"").append(table.getTable_name()).append("\"");
		sb.append(" comment=\"").append(table.getComment()).append("\">");
		 List<Field> fields = iFieldBize.findByTableId(table.getId());
		sb.append("<fields>");
		 for (Field field : fields) {
			sb.append("<field id=\"db@"+table.getTable_name()+"."+field.getField_name()+"\"");
			sb.append(" code=\""+field.getField_name()+"\"");
			sb.append(" type=\""+field.getType()+"\"");
			if("3".equals(field.getType())) {
				sb.append(" length=\"0\"");
				sb.append(" decimals=\""+field.getAccuracy()+"\"");
			}else {
				sb.append(" length=\""+field.getAccuracy()+"\"");
				sb.append(" decimals=\"0\"");
			}
			sb.append(" allownull=\"true\"");
			sb.append(" iskey=\"false\"");
			sb.append(" comment=\""+field.getComment()+"\"");
			sb.append("/>");
		}
		 sb.append("</fields>");
		 sb.append("</table>");
		return formatXml(sb.toString());
	}
	 public static String formatXml(String str) throws Exception {
	        Document document = null;
	        document = DocumentHelper.parseText(str);
	        // 格式化输出格式
	        OutputFormat format = OutputFormat.createPrettyPrint();
	        format.setEncoding("gb2312");
	        StringWriter writer = new StringWriter();
	        // 格式化输出流
	        XMLWriter xmlWriter = new XMLWriter(writer, format);
	        // 将document写入到输出流
	        xmlWriter.write(document);
	        xmlWriter.close();
	        return writer.toString();
	    }

}

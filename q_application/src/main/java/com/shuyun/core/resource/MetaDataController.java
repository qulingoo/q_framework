package com.shuyun.core.resource;

import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/metadata")
public class MetaDataController {

	@ResponseBody
	@RequestMapping("/parseToXML")
	public String getSegmentation(String json) throws Exception {
		System.out.println(json);
		JSONObject jsonObject = JSON.parseObject(json);
		return parase(jsonObject);

	}

	private String parase(JSONObject json) throws Exception {
		StringBuffer sb = new StringBuffer("<root>");
		sb.append(parseDatabase(json.getJSONObject("database")));
		sb.append(parseFunctions(json.getJSONArray("functions")));
		sb.append(parseSegmentation(json.getJSONObject("segmentation")));
		sb.append(parseQueryItems(json.getJSONArray("queryitems")));
		JSONArray features = json.getJSONArray("features");
		if (features.size() >= 2) {

			sb.append("<features>");
			sb.append(parseFeature1(features.getJSONObject(0)));
			sb.append(parseFeature2(features.getJSONObject(1)));
			sb.append("</features>");
		}
		return formatXml(sb.append("</root>").toString());
	}

	private StringBuffer parseDatabase(JSONObject database) {
		StringBuffer sb = new StringBuffer();
		sb.append("<database id=\"" + database.getString("id") + "\">");
		sb.append("<tables>");
		JSONArray tables = database.getJSONArray("tables");
		for (int i = 0; i < tables.size(); i++) {
			JSONObject table = tables.getJSONObject(i);
			sb.append("<table id=\"" + table.getString("id") + "\" code=\"" + table.getString("code") + "\" comment=\""
					+ table.getString("comment") + "\">");
			JSONArray fields = table.getJSONArray("fields");
			sb.append("<fields>");
			for (int j = 0; fields != null && j < fields.size(); j++) {
				JSONObject field = fields.getJSONObject(j);
				sb.append("<field id=\"" + field.getString("id") + "\" code=\"" + field.getString("code") + "\" type=\""
						+ field.getString("type") + "\" length=\"" + field.getString("length") + "\" decimals=\""
						+ field.getString("decimals") + "\" " + "allownull=\"" + field.getString("allownull")
						+ "\" iskey=\"" + field.getString("iskey") + "\" " + "comment=\"" + field.getString("comment")
						+ "\" />");

			}
			sb.append("</fields>");
			sb.append("</table>");
		}
		sb.append("</tables>");
		sb.append("</database>");
		return sb;
	}

	private StringBuffer parseFunctions(JSONArray functions) {
		StringBuffer sb = new StringBuffer("<functions>");
		for (int i = 0; i < functions.size(); i++) {
			JSONObject function = functions.getJSONObject(i);
			sb.append("<function id=\"" + function.getString("id") + "\" returnDataType=\""
					+ function.getString("returnDataType") + "\" " + "paramJson=\"" + paramParams(JSON.parseArray(function.getString("paramJson")))
					+ "\" expression=\"" + function.getString("expression").replace("\n", "") + "\"  >");
			sb.append("</function>");
		}
		sb.append("</functions>");
		return sb;
	}
	public String paramParams(JSONArray array) {
		StringBuffer sb=new StringBuffer( );
		sb.append("[");
		for (int i = 0; i < array.size(); i++) {
			sb.append("&quot;").append(array.getString(i)).append("&quot;").append(",");
		}
		return sb.deleteCharAt(sb.length()-1).append("]").toString();
		
		
	}
	private StringBuffer parseSegmentation(JSONObject seg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<segmentation id=\"" + seg.getString("id") + "\" display=\"" + seg.getString("display") + "\">");
		JSONArray subjects = seg.getJSONArray("subjects");

		for (int i = 0; subjects != null && i < subjects.size(); i++) {
			JSONObject subject = subjects.getJSONObject(i);
			sb.append("<subject id=\"" + subject.getString("id") + "\" display=\"" + subject.getString("display")
					+ "\">");
			JSONArray attributeCollections = subject.getJSONArray("attributeCollections");
			sb.append("<attribute-collections>");
			for (int j = 0; j < attributeCollections.size(); j++) {
				JSONObject attrcolls = attributeCollections.getJSONObject(j);
				sb.append(" <attribute-collection id=\"" + attrcolls.getString("id") + "\" display=\""
						+ attrcolls.getString("id") + "\" " + "table=\"" + attrcolls.getString("table")
						+ "\" ismaster=\"" + attrcolls.getString("ismaster") + "\" refsubject=\""
						+ attrcolls.getString("refsubject") + "\">");
				JSONArray normalAttributes = attrcolls.getJSONArray("normalAttributes");
				for (int k = 0; k < normalAttributes.size(); k++) {
					JSONObject nomal = normalAttributes.getJSONObject(k);
					sb.append(" <normal-attribute id=\"" + nomal.getString("id") + "\" display=\""
							+ nomal.getString("display") + "\" field=\"" + nomal.getString("field") + "\" ismaster=\""
							+ nomal.getString("ismaster") + "\" refsubject=\"" + nomal.getString("refsubject")
							+ "\" />");

				}
				sb.append(" </attribute-collection>");
			}
			sb.append("</attribute-collections>");
			JSONArray functionAttributes = subject.getJSONArray("functionAttributes");
			sb.append("<function-attributes>");
			for (int j = 0; j < functionAttributes.size(); j++) {
				JSONObject function = functionAttributes.getJSONObject(j);
				sb.append("  <function-attribute id=\"" + function.getString("id") + "\" display=\""
						+ function.getString("display") + "\" function=\"" + function.getString("function") + "\">");
				JSONArray params = function.getJSONArray("param");
				for (int k = 0; params != null && k < params.size(); k++) {
					JSONObject param = params.getJSONObject(k);
					sb.append("<param name=\"" + param.getString("name") + "\" attribute-type=\""
							+ param.getString("attributeType") + "\" attribute=\"" + param.getString("attribute")
							+ "\" />");

				}
				sb.append("</function-attribute>");
			}
			sb.append("</function-attributes>");
			JSONArray composeAttributes = subject.getJSONArray("composeAttributes");
			sb.append("<compose-attributes>");
			for (int j = 0; j < composeAttributes.size(); j++) {
				JSONObject comp = composeAttributes.getJSONObject(j);
				sb.append("<compose-attribute id=\"" + comp.getString("id") + "\" display=\""
						+ comp.getString("display") + "\">");
				JSONArray mapping = comp.getJSONArray("mapping");
				for (int k = 0; k < mapping.size(); k++) {
					JSONObject map = mapping.getJSONObject(k);
					sb.append("<mapping name=\"" + map.getString("name") + "\" attribute-type=\""
							+ map.getString("attributeType") + "\" attribute=\"" + map.getString("attribute") + "\"/>");
				}
				sb.append("</compose-attribute>");
			}
			sb.append("</compose-attributes>");
			sb.append("<dynamic-attributes>");
			JSONArray dycAttrs = subject.getJSONArray("dycAttrs");
			for (int j = 0; j < dycAttrs.size(); j++) {
				JSONObject dyc = dycAttrs.getJSONObject(j);
				sb.append("<dynamic-attribute id=\"" + dyc.getString("id") + "\" dispaly=\"" + dyc.getString("dispaly")
						+ "\" default-attribute=\"" + dyc.getString("default-attribute") + "\" attribute-type=\""
						+ dyc.getString("attribute-type") + "\">");
				 JSONObject dynamic = dyc.getJSONObject("dynamic");
					sb.append(" <dynamic param-attribute-collection=\""
							+ dynamic.getString("param-attribute-collection")
							+ "\" attribute-type=\"normal\" attribute=\"" + dynamic.getString("attribute") + "\" />");
				sb.append("</dynamic-attribute>");
			}
			
			sb.append("</dynamic-attributes>");
			sb.append("<quota-attributes>");
			JSONArray quotaAttrs = subject.getJSONArray("quotaAttrs");
			for (int j = 0; j < quotaAttrs.size(); j++) {
				JSONObject quota = quotaAttrs.getJSONObject(j);
				sb.append(" <quota-attribute id=\""+quota.getString("id")+"\" display=\""+quota.getString("display")+"\" function=\""+quota.getString("function")+"\" group-subject=\""+quota.getString("group-subject")+"\">");
					JSONArray params = quota.getJSONArray("param");
					for (int k = 0; k < params.size(); k++) {
						JSONObject param = params.getJSONObject(k);
						sb.append("<param name=\""+param.getString("name")+"\" attribute-type=\""+param.getString("attribute-type")+"\" attribute=\""+param.getString("attribute")+"\" />");
						
						
					}
				 sb.append("</quota-attribute>");
				 
			}
			sb.append("</quota-attributes>");
			sb.append("</subject>");
		}
		sb.append("</segmentation>");
		return sb;
	}

	private StringBuffer parseQueryItems(JSONArray queryitems) {
		StringBuffer sb = new StringBuffer("<queryitems>");
		for (int i = 0; i < queryitems.size(); i++) {
			JSONObject queryitem = queryitems.getJSONObject(i);
			sb.append(" <queryitem id=\"" + queryitem.getString("id") + "\" display=\"" + queryitem.getString("display")
					+ "\" " + "attribute=\"" + queryitem.getString("attribute") + "\" attribute-type=\""
					+ queryitem.getString("attributeType") + "\" " + "query-type=\"" + queryitem.getString("queryType")
					+ "\" " + "order=\"" + i + "\" compute-by-result=\"" + queryitem.getString("compute-by-result")
					+ "\" tips=\"" + queryitem.getString("tips") + "\">");
			sb.append("<properties>");
			JSONArray properties = queryitem.getJSONArray("properties");
			for (int j = 0; j < properties.size(); j++) {
				JSONObject prop = properties.getJSONObject(j);
				if (j == 0) {
					sb.append(" <property id=\"" + prop.getString("id") + "\" value=\""
							+ arrayToString(prop.getJSONArray("value")) + "\"/>");

				} else {
					sb.append(" <property id=\"" + prop.getString("id") + "\" value=\"" + prop.getString("value")
							+ "\"/>");
				}
			}
			sb.append("</properties>");
			sb.append("</queryitem>");
		}
		sb.append("</queryitems>");
		return sb;
	}

	private StringBuffer parseFeature1(JSONObject feature) {
		StringBuffer sb = new StringBuffer("");
		sb.append(" <feature id=\"querynode@basic\" display=\"属性查询\" type=\"querynode\">");
		JSONObject subject = feature.getJSONObject("subject");
		sb.append("<subject id=\"" + subject.getString("id").replace("\n", "").replace("\t", "") + "\" display=\""
				+ subject.getString("id").replace("\n", "").replace("\t", "") + "\">");
		JSONArray categorys = subject.getJSONArray("category");
		JSONArray groupbys = subject.getJSONArray("groupbys");
		sb.append("<groupbys>");
		for (int i = 0; groupbys != null && i < groupbys.size(); i++) {
			JSONObject groupby = groupbys.getJSONObject(i);
			sb.append("<groupby attribute=\"" + groupby.getString("attribute") + "\" attribute-type=\"normal\" dic=\""
					+ groupby.getString("dic") + "\" order=\"" + i + "\"/>");
		}
		sb.append("</groupbys>");
		for (int i = 0; i < categorys.size(); i++) {
			JSONObject category = categorys.getJSONObject(i);
			sb.append("<category id=\"" + category.getString("id") + "\" display=\"" + category.getString("display")
					+ "\" type=\"" + category.getString("type") + "\" typename=\"" + category.getString("typename")
					+ "\" order=\"" + i + "\">");
			JSONArray queryitems = category.getJSONArray("queryitems");
			sb.append("<category-queryitems>");
			for (int j = 0; j < queryitems.size(); j++) {
				JSONObject queryitem = queryitems.getJSONObject(j);
				sb.append("<category-queryitem id=\"" + queryitem.getString("id") + "\" display=\""
						+ queryitem.getString("display") + "\" />");
			}
			sb.append("</category-queryitems>");
			sb.append("</category>");
		}
		sb.append("</subject>");
		sb.append("</feature>");
		return sb;
	}

	private StringBuffer parseFeature2(JSONObject feature) {
		StringBuffer sb = new StringBuffer("");
		sb.append("<feature id=\"querynode@order\" display=\"订单查询\" type=\"querynode\">");
		JSONObject subject = feature.getJSONObject("subject");
		sb.append("<subject id=\"" + subject.getString("id").replace("\n", "").replace("\t", "") + "\" display=\""
				+ subject.getString("id").replace("\n", "").replace("\t", "") + "\">");
		JSONObject category = subject.getJSONObject("category");
		sb.append(" <category id=\"" + category.getString("id")
				+ "\" display=\"订单信息\" type=\"3\"  typename=\"行为自定义\" order=\"6\">");
		JSONObject behavior = category.getJSONObject("behavior");
		sb.append(" <behavior id=\"" + behavior.getString("id") + "\" display=\"" + behavior.getString("display")
				+ "\" behavior-subject=\"" + behavior.getString("behaviorSubject")
				+ "\" type=\"3\" typename=\"行为自定义\">");
		JSONArray items = behavior.getJSONArray("item");
		for (int i = 0; i < items.size(); i++) {
			JSONObject item = items.getJSONObject(i);
			sb.append(" <behavior-queryitem id=\"" + item.getString("id") + "\" display=\"" + item.getString("display")
					+ "\"  x=\"" + item.getString("x") + "\" y=\"" + item.getString("y") + "\"/>");
		}
		;
		sb.append("</behavior>");
		sb.append("</category>");

		sb.append("</subject>");
		sb.append("</feature>");
		return sb;
	}

	public String arrayToString(JSONArray array) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.size(); i++) {
			sb.append(array.get(i) + ",");

		}
		if(sb.length()>0) {
			
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();

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

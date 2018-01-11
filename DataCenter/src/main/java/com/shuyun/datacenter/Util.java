package com.shuyun.datacenter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Util {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static String formatDate(Date date) {
		return sdf.format(date);
	}

	public static String formatNowDate() {
		return sdf.format(new Date());
	}

	public static Date parse(String date) {
		try {
			Date parse = sdf.parse(date);
			return parse;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 时间分割
	 * @param beginStr
	 * @param endStr
	 * @param split
	 * @return
	 */
	public static List<String> splitDate(String beginStr, String endStr, int split) {
		List<String> list=new ArrayList<>();
		Date begin = parse(beginStr);
		Date end = parse(endStr);
		long beginTime = begin.getTime();
		long endTime = end.getTime();
		long differ = endTime - beginTime;
		long actualDiffer = differ - differ % split;
		long distance=actualDiffer/split;
		for (int i = 0; i <= split; i++) {
			if(i!=split) {
				list.add(formatDate(new Date(begin.getTime()+i*distance)));
			}else {
				list.add(formatDate(end));
			}
		}
		return list;
	}
	public static String readAll(String file) {
		StringBuffer sb=new StringBuffer();
		FileReader fr=null;
		BufferedReader br=null;
		try {
			  fr=new FileReader(file);
			  br=new BufferedReader(fr);
			String line=null;
			while((line=br.readLine())!=null) {
				sb.append(line+"\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}

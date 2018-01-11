package com.shuyun.core.mybatis;

import java.lang.reflect.Field;

import org.apache.ibatis.jdbc.SQL;

public class Provider {
	public String insert(final Object obj) {

		return new SQL() {
			{
				Class<? extends Object> class1 = obj.getClass();
				System.out.println(class1.getName());
				Table tableAnn = class1.getAnnotation(Table.class);
				System.out.println(tableAnn);
				String tableName = tableAnn.value();
				INSERT_INTO(tableName);
				Field[] fields = class1.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					try {
						if (field.get(obj) != null)
							VALUES(field.getName(), "#{" + field.getName() + "}");
						else {
							DefaultProc defaultProc = field.getAnnotation(DefaultProc.class);
							if(defaultProc!=null) {
								VALUES(field.getName(), defaultProc.value());
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
				System.out.println(this.toString());
			}
		
		}.toString();

	}

	public String update(final Object obj) {

		return new SQL() {
			{
				Class<? extends Object> class1 = obj.getClass();
				Table tableAnn = class1.getAnnotation(Table.class);
				String tableName = tableAnn.value();
				UPDATE(tableName);
				Field[] fields = class1.getDeclaredFields();
				try {
					for (Field field : fields) {
						field.setAccessible(true);
						PrimaryKey primary = field.getAnnotation(PrimaryKey.class);
						if (primary != null) {
							WHERE(field.getName() + "=#{" + field.getName() + "}");
						} else {
							if (field.get(obj) != null)
								SET(field.getName() + "=#{" + field.getName() + "}");
						}

					}
				} catch (Exception e) {
				}
				System.out.println(obj);
				System.out.println(this.toString());
			}
		}.toString();
	}
}

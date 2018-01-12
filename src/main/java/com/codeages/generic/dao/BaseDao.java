package com.codeages.generic.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Date;

import com.codeages.generic.annotation.Table;
import com.codeages.generic.entity.BaseEntity;
import com.codeages.generic.util.ConnectionFactory;

public class BaseDao<T extends BaseEntity> {

	private Class<T> clazz;

	public BaseDao() {
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType pType = (ParameterizedType) type;
			Type claz = pType.getActualTypeArguments()[0];
			if (claz instanceof Class) {
				this.clazz = (Class) claz;
			}
		}
	}

	protected <Y> Y[] concat(Y[] first, Y[] second) {
		Y[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}

	protected String underscoreName(String camelCaseName) {
		StringBuilder result = new StringBuilder();
		if (camelCaseName != null && camelCaseName.length() > 0) {
			result.append(camelCaseName.substring(0, 1).toLowerCase());
			for (int i = 1; i < camelCaseName.length(); i++) {
				char ch = camelCaseName.charAt(i);
				if (Character.isUpperCase(ch)) {
					result.append("_");
					result.append(Character.toLowerCase(ch));
				} else {
					result.append(ch);
				}
			}
		}
		return result.toString();
	}

	protected String captureName(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	protected <T extends BaseEntity> T mapper(ResultSet rs) throws Exception {
		T t = (T) clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		Field[] superClassFields = clazz.getSuperclass().getDeclaredFields();
		Field[] allFields = concat(fields, superClassFields);

		for (Field field : allFields) {
			String dbField = underscoreName(field.getName());
			if (field.getGenericType().getTypeName() == String.class.getName()) {
				Method method = clazz.getMethod("set" + captureName(field.getName()), String.class);
				method.invoke(t, rs.getString(dbField));
			}

			if (field.getGenericType().getTypeName() == Integer.class.getName()) {
				Method method = clazz.getMethod("set" + captureName(field.getName()), Integer.class);
				method.invoke(t, rs.getInt(dbField));
			}

			if (field.getGenericType().getTypeName() == Long.class.getName()) {
				Method method = clazz.getMethod("set" + captureName(field.getName()), Long.class);
				method.invoke(t, rs.getLong(dbField));
			}

			if (field.getGenericType().getTypeName() == Date.class.getName()) {
				Method method = clazz.getMethod("set" + captureName(field.getName()), Date.class);
				method.invoke(t, rs.getDate(dbField));
			}
		}
		return t;
	}

	public T get(Long id) throws Exception {
		T entity = null;
		Connection con = ConnectionFactory.connection();
		PreparedStatement stat = con.prepareStatement("select * from " + getTable() + " where id = ?");
		stat.setLong(1, id.longValue());
		ResultSet rs = stat.executeQuery();

		if (rs.next()) {
			entity = mapper(rs);
		}

		rs.close();
		stat.close();
		con.close();
		return entity;
	}

	protected String getTable() {
		Table[] ts = this.clazz.getDeclaredAnnotationsByType(Table.class);
		return ts[0].value();
	}
}

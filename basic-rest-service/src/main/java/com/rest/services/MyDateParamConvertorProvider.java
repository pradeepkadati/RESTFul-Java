package com.rest.services;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

import javax.ws.rs.ext.ParamConverter;
import javax.ws.rs.ext.ParamConverterProvider;
import javax.ws.rs.ext.Provider;

@Provider
public class MyDateParamConvertorProvider implements ParamConverterProvider {

	@Override
	public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
		
		if (rawType.getName().equals(MyDate.class.getName())) {
			
			return new ParamConverter<T>() {

				@Override
				public T fromString(String value) {
					
					Calendar cal = Calendar.getInstance();
					
					if (value.equals("tomorrow")) {
						cal.add(Calendar.DATE, 1);
					}else if(value.equals("yesterday")) {
						cal.add(Calendar.DATE, -1);
					}
					MyDate date = new MyDate();
					date.setDay(cal.get(Calendar.DATE));
					date.setMonth(cal.get(Calendar.MONTH));
					date.setYeay(cal.get(Calendar.YEAR));
					return rawType.cast(date);
				}

				@Override
				public String toString(T value) {
					
					return value.toString();
				}
				
			};
		}
			
		return null;
	}

}

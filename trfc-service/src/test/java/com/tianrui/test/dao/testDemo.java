package com.tianrui.test.dao;


import java.sql.Date;
import java.util.Calendar;

public class testDemo {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		Date now = new Date(System.currentTimeMillis());
		Date last = new Date(1484270339346l);
		c.setTime(last);
		System.out.println(c.get(Calendar.YEAR));
		System.out.println(c.get(Calendar.DATE));
		System.out.println(c.get(Calendar.MONTH));
		System.out.println(last);
	}
}

package cn.zdsoft.common;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class DateUtilTest {

	@Test
	public void testAddDay() {
		Date d = DateUtil.AddDay(new Date(), -180);
		System.out.println(DateUtil.Format(d,"yyyy-MM-01"));
	}

}

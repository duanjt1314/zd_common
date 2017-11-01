package cn.zdsoft.common;

import static org.junit.Assert.*;

import org.junit.Test;

public class PinYinUtilTest {

	@Test
	public void testGetPinYin() {
		String hanzi="重庆很重要";
		System.out.println(PinYinUtil.GetPinYin(hanzi));
	}

}

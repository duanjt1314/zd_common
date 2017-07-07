package cn.zdsoft.common;

import static org.junit.Assert.*;

import org.junit.Test;

public class RegexUtilTest {

	@Test
	public void testRegex() {
		boolean result = RegexUtil.Regex("\\d{2}", "1");
		assertEquals(result, false);
		result = RegexUtil.Regex("\\d{2}", "12");
		assertEquals(result, true);
	}

}

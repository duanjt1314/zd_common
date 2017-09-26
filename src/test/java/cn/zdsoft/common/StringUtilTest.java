package cn.zdsoft.common;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testReplaceLastStr() {
		String content="10100";
		String a= StringUtil.ReplaceLastStr(content, '0');
		assertEquals(a, "101");
	}

}

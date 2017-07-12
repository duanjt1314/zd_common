package cn.zdsoft.common;

import static org.junit.Assert.*;

import org.junit.Test;

public class DirectoryUtilTest {

	@Test
	public void testCreateDir() {
		DirectoryUtil.CreateDir("f:\\result1.txt");
	}

}

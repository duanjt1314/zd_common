package cn.zdsoft.common;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class FileUtilTest {

	@Test
	public void testGetFiles() {
		File[] fs = FileUtil.GetFiles("F:\\工作软件", "Office");
		for (File file : fs) {
			System.out.println(file.getName());
		}
		assertTrue(fs.length == 2);
		fs = FileUtil.GetFiles("F:\\工作软件", "office");
		assertTrue(fs.length == 0);
		fs = FileUtil.GetFiles("F:\\工作软件", "office", true);// 忽略大小写
		assertTrue(fs.length == 2);
	}

	@Test
	public void testGetFilesRegex() {
		File[] fs = FileUtil.GetFilesRegex("F:\\工作软件", "^.*Office.*$");
		for (File file : fs) {
			System.out.println(file.getName());
		}
		
	}
}

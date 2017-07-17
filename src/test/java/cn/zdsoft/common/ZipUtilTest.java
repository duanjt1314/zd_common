package cn.zdsoft.common;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ZipUtilTest {


	@Test
	public void testZip() throws IOException {
		File file1=new File("F:\\360云盘\\20160511_101608.jpg");
		File file2=new File("F:\\360云盘\\Github帐号密码.txt");
		
		List<File> list=new ArrayList<File>();
		list.add(file1);
		list.add(file2);
		
		ZipUtil.Zip(list, "f:\\", "duanjt.zip", true);
		
//		System.out.println(file1.getParent());
//		System.out.println(file1.getPath());
//		System.out.println(file1.getAbsolutePath());
	}

}

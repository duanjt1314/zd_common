package cn.zdsoft.common;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MD5UtilTest {


	@Test
	public void testEncrypt() throws IOException {
		String str="admin";
		System.out.println(MD5Util.Encrypt(str));
		
	}

}

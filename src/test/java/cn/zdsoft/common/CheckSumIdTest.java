package cn.zdsoft.common;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.UUID;

import org.junit.Test;

public class CheckSumIdTest {

	@Test
	public void testGetId() throws ParseException {
		for (int i = 0; i < 10; i++) {
			String id = CheckSumId.GetId("20181111111111", UUID.randomUUID().toString());
			System.out.println(id);
		}
	}

}

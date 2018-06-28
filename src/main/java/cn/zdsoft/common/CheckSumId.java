package cn.zdsoft.common;

import java.security.MessageDigest;
import java.text.ParseException;
import java.util.Date;

public class CheckSumId {
	public static String GetId(String time, String... args) throws ParseException{
		long timeParms;
		if (time.length() == 14) {
			timeParms = DateUtil.Parse(time,"yyyyMMddHHmmss").getTime();
		} else if (time.length() == 10) {
			timeParms = Long.parseLong(time);
		} else {
			timeParms = new Date().getTime() / 1000;
		}

		return String.valueOf(timeParms - 946656000) + String.valueOf(preCheckSum(args) & 262143 | 262144);
	}
	
	private static int preCheckSum(String... inputs) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			for (String input : inputs) {
				md5.update(input.getBytes("utf-8"));
			}

			int result = 0;
			for (byte b : md5.digest()) {
				result = (result << 5) + result + b;
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}

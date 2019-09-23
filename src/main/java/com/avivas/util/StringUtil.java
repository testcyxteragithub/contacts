package com.avivas.util;

public final class StringUtil {
	
	private StringUtil() {
	}
	
	public static boolean isLowerCaseAndAscii(String str) {
		if (str == null || str.equals("")) {
			return false;
		}
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!(ch >= 'a' && ch <= 'z')) {
				return false;
			}
		}
		return true;
	}
}

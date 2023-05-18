package main.java.Client;

import java.util.Locale;
import java.util.ResourceBundle;

public class InterText {
	public static String lan = "es";
	
	private static String SYSTEM_MESSAGES = "SystemMessages";
	private static ResourceBundle res = ResourceBundle.getBundle(SYSTEM_MESSAGES,	Locale.forLanguageTag(InterText.lan));
	
	public static void setlan(String a) {
		lan = a;
		res = ResourceBundle.getBundle(SYSTEM_MESSAGES,	Locale.forLanguageTag(InterText.lan));
	}
	public static String getString(String a) {
		return res.getString(a);
	}
}

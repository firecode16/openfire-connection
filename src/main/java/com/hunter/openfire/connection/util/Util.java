package com.hunter.openfire.connection.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Fredi Hernandez
 *
 */
public class Util {

	public static final Integer TIME_OUT = 10;
	public static final Integer XMPP_PORT = 5222;
	public static final Object SECURITY_MODE = "ifpossible";
	public static final String XMPP_DOMAIN = "15.80.110.110";
	public static final String OK_RESPONSE = "OK";
	public static final String ERROR_RESPONSE = "ERROR";

	public static String modifyWildcards(String resource) {
		if (resource == null) {
			return "";
		}

		resource = resource.replace("%random%", "" + Math.round((Math.random() * 1000)));
		resource = resource.replace("%system%", System.getProperty("os.name").replace(" ", ""));
		resource = resource.replace("%version%", System.getProperty("os.version").replace(" ", ""));

		final String DATE_FORMAT_NOW = "HH:mm";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		resource = resource.replace("%time%", sdf.format(cal.getTime()));

		while (resource.contains("%property{")) {
			if (resource.contains("%property{") && resource.contains("}/%")) {
				int indexofbeginning = resource.indexOf("%property{");
				int indexofending = resource.indexOf("}/%");

				String begin = resource.substring(0, indexofbeginning);
				String middle = System.getProperty(resource.substring(indexofbeginning + 10, indexofending));
				String end = resource.substring(indexofending + 3);
				resource = begin + middle + end;
			}
		}

		return resource;
	}
}

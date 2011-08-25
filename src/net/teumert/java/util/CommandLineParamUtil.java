package net.teumert.java.util;

import java.util.Hashtable;

public class CommandLineParamUtil {
	
	/**
	 * Puts command line parameters in the table.
	 * Allowed syntax: --key <value> | --key --key | value 
	 * @param args string array passed on from main() method
	 * @param map Hashtable to put the values in
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void storeParams(String[] args, Hashtable map) {
		String key = null;
		for (String string : args) {
			if (string.startsWith("--")) { // found key
				if (key != null)  // allow arguments without parameter
					map.put(key, key);
				
				key = string.substring(3); // set new key
			}
			else if (key != null) { // found param
				map.put(key, string); // add param with key to map
				key = null; // reset key
			}
			else // key is null, value without -- found, so add it
				map.put(string.substring(3) , string.substring(3));
		}
		if(key != null) //there is a param left
			map.put(key, key);
	}
}

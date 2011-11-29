package net.teumert.java.util;

import java.util.StringTokenizer;

public class ApplicationUtil {
	
	
	/**
	 * Returns an byte indicating whether v1 is greater, equal or lesser than v2.
	 * Version numbers are compared from left to right and might be separated by '.' or ' '.
	 * A greater number will be considered higher. The following string are (case insensitive) allowed
	 * in the following order:
	 * Release = Final > RC = Release Candidate = Gamma > Beta > Alpha > Dev = Development = pre-Alpha > any other string
	 * Numbers are considered greater then strings
	 * Negative version numbers were not supported
	 * @param v1 First version string
	 * @param v2 Version string to compare against
	 * @return 0 if equal, 1 if greater, -1 if lesser
	 */
	public static byte compareVersion(String v1, String v2) {
		if(v1.equals(v2)) return 0; //equal = 0
		v1 = v1.toLowerCase();
		v2 = v2.toLowerCase();
		v1.replace('-', ' ');
		v2.replace('-', ' ');
		v1.replaceAll("release", "-1");
		v1.replaceAll("final", "-1");
		v2.replaceAll("release", "-1");
		v2.replaceAll("final", "-1");
		v1.replaceAll("rc", "-2");
		v1.replaceAll("release candidate", "-2");
		v2.replaceAll("rc", "-2");
		v2.replaceAll("release candidate", "-2");
		v1.replaceAll("gamma", "-2");
		v2.replaceAll("gamma", "-2");
		v1.replaceAll("beta", "-3");
		v2.replaceAll("beta", "-3");
		v1.replaceAll("alpha", "-4");
		v2.replaceAll("alpha", "-4");
		v1.replaceAll("dev", "-5");
		v2.replaceAll("dev", "-5");
		v1.replaceAll("development", "-5");
		v2.replaceAll("development", "-5");
		v1.replaceAll("pre-alpha", "-5");
		v2.replaceAll("pre-alpha", "-5");
		StringTokenizer t1 = new StringTokenizer(v1, ". ", false);
		StringTokenizer t2 = new StringTokenizer(v2, ". ", false);		
		
		while(t1.hasMoreTokens()) {
			String s1 = t1.nextToken();
			if(!t2.hasMoreTokens())
				return 1;
			String s2 = t2.nextToken();
			int i1 = 0, i2= 0;
			
			// compare tokens
			try {
				i1 = Integer.parseInt(s1);				
			}
			catch (NumberFormatException nfe) {
				i1 = -9;
			}
			
			try {
				i2 = Integer.parseInt(s2);				
			}
			catch (NumberFormatException nfe) {
				i2 = -9;
			}
			
			System.out.println(i1 + " ? "  + i2);
			if(i1 > i2)
				return 1;
			else if(i1 < i2)
				return -1;
			
		}
		
		//if versions were equal until now (no return)
		//and t2 still has tokens, t2 is greater 
		if(t2.hasMoreTokens())
			return -1;
		
		//if they both have the same number of tokens
		//and no number is greater or lesser, then they're equal
		return 0;
	}	
}

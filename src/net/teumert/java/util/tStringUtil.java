package net.teumert.java.util;

import java.util.ArrayList;
import java.util.Random;

// TODO add LGPL license hint here

public class tStringUtil {
	
	/**
	 * @param source Array containing the objects that should be imploded in string representation
	 * @param delimiter Separator for the strings
	 * @return String containing all objects in string representation separated by delimiter
	 */
	public static String implode(Object[] source, String delimiter) {
		StringBuilder sb = new StringBuilder(source[0].toString());
		for (int i = 1; i < source.length; i++) {
			if (source[i] == null) break;
			sb.append(delimiter);
			sb.append(source[i].toString());
		}
		return sb.toString();
	}
	
	/**
	 * Generates random words, idea found on stackoverflow:
	 * <a href="http://stackoverflow.com/questions/4951997/generating-random-words-in-java">
	 * http://stackoverflow.com/questions/4951997/generating-random-words-in-java</a>
	 * 
	 * @param numberOfWords
	 * @return
	 * 
	 */
	public static String[] generateRandomWords(int numberOfWords) {
		String[] randomStrings = new String[numberOfWords];
		Random random = new Random();
		for (int i = 0; i < numberOfWords; i++) {
			char[] word = new char[random.nextInt(8) + 3]; // words of length 3 through 10. (1 and 2 letter
									// words are boring.)
			for (int j = 0; j < word.length; j++) {
				word[j] = (char) ('a' + random.nextInt(26));
			}
			randomStrings[i] = new String(word);
		}
		return randomStrings;
	}
	
	/**
	 * Justifies a given paragraph of text. Only works with mono-spaced fonts.
	 * 
	 * @param message the text to be justified
	 * @param letters the amount of letters that should be placed on one line.
	 * @return array of strings, each element of the array is one justified line.
	 */
	public static String[] justifyParagraph(String message, int letters) {
		
		// remove line breaks
		message = message.replace('\n', ' ');
		
		// remove double whitespace
		String replaced = message.replace("  ", " ");
		while (!message.equals(replaced)) {
			message = replaced;
			replaced = message.replace("  ", " ");
		}
		
		ArrayList<String> lineWords = new ArrayList<String>();
		ArrayList<String> currentLine = new ArrayList<String>();
		
		String[] words = message.split(" ");
		
		// wrap lines
		String lineBuffer = words[0];
		currentLine.add(words[0]);
		// start with second word
		for (int i = 1; i < words.length; i++) {
			String word = words[i];
			if ((lineBuffer + word).length() < letters) {
				lineBuffer += (" " + word);
				currentLine.add(word);
			}
			else {
				lineBuffer = justifyLine(currentLine, (letters - lineBuffer.length()));
				lineWords.add(lineBuffer);
				lineBuffer = word;
				currentLine = new ArrayList<String>();
				currentLine.add(word);
			}
			
		}
		
		String[] lines = new String[lineWords.size()];
		return lineWords.toArray(lines);
	}
	
	/**
	 * Method internally used by justifyParagraph() in order to justify one line
	 * 
	 * @param currentLine words that shall be placed on that line
	 * @param plusWhite number of additional whitespace needed.
	 * @return String containing the justified line.
	 */
	private static String justifyLine(ArrayList<String> currentLine, int plusWhite) {
		
		String lineBuffer = "";				// contains the line
		
		int holes = currentLine.size() - 1; 		// there is one empty space between the words less then there are words
		int add = plusWhite;				// we need to add all whitespace
		
		lineBuffer = currentLine.get(0);		// initialise buffer with first word (no leading whitespace!)
		for (int i = 1; i < currentLine.size(); i++) {  // iterate over all words, starting with second word
								// (offset 1)
			lineBuffer += " ";			// add at least one whitespace before inserting next word.
			while (add - holes >= 0) {		// now comes a mathematical trick avoiding the use of rational numbers
				lineBuffer += " ";		// add whitespace
				add -= holes;			// do the trick again ;) 
			}
			lineBuffer += currentLine.get(i);	// add word
			add += plusWhite;			// and the last step of the math trick
		}
		return lineBuffer;
		
		//if you are interested in the math behind it, checkout readme-string-util.txt in this folder
	}	
}

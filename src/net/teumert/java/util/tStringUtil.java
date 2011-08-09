package net.teumert.java.util;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;

import de.lessvoid.nifty.tools.StringHelper;

public class tStringUtil {
	
	/**
	 * Currently unused	 
	 * @param source Array containing the objects that should be imploded in string representation
	 * @param delimiter Separator for the strings
	 * @return String containing all objects in string representation separated by delimiter
	 */
	public static String implode(Object[] source, String delimiter) {
		StringBuilder sb = new StringBuilder(source[0].toString());
		for (int i = 1; i < source.length; i++) {
			if(source[i] == null)
				break;
			sb.append(delimiter);
			sb.append(source[i].toString());
		}		
		return sb.toString();
	}
	
	/**
	 * {@link}http://stackoverflow.com/questions/4951997/generating-random-words-in-java
	 * @param numberOfWords
	 * @return
	 */
	public static String[] generateRandomWords(int numberOfWords)
	{
	    String[] randomStrings = new String[numberOfWords];
	    Random random = new Random();
	    for(int i = 0; i < numberOfWords; i++)
	    {
	        char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
	        for(int j = 0; j < word.length; j++)
	        {
	            word[j] = (char)('a' + random.nextInt(26));
	        }
	        randomStrings[i] = new String(word);
	    }
	    return randomStrings;
	}
	
	
	public static void main(String[] args) {
		String lipsum ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus elementum hendrerit mauris, sit amet egestas quam rhoncus eu. Aenean quis est dui, ac tempus lacus. Aliquam sollicitudin dapibus ipsum, id egestas nulla commodo ac. Praesent pretium libero fringilla ante facilisis at rutrum ante interdum. Vestibulum vitae fringilla urna. Phasellus interdum mauris sed purus bibendum quis dignissim risus rhoncus. Aliquam risus dui, luctus id dignissim ac, tincidunt at mauris. Donec tincidunt, massa sed tempus ultricies, urna diam facilisis leo, volutpat venenatis est mauris vel metus. Nulla venenatis ultricies sagittis. Mauris pharetra nunc vitae mi placerat sit amet pellentesque tortor tristique. Sed pellentesque sagittis dolor non consectetur. Pellentesque id nunc metus, sed scelerisque risus. Nam fringilla ornare justo vitae placerat. Donec sit amet nisl ac augue pellentesque pretium at in metus. Donec faucibus, est eu dapibus porta, ligula ante fermentum diam, in mollis justo urna nec ipsum. Etiam nulla tellus, vestibulum et rutrum quis, faucibus a nisi. Ut consequat venenatis quam, ac porttitor quam vulputate sit amet. Nam id nisi eget odio sagittis condimentum in ac nisi. Phasellus venenatis tempor justo, id semper mauris facilisis vestibulum. Donec facilisis tellus sed orci viverra vehicula.";
		justifyParagraph(lipsum, 40);
	}
	
	public static String[] justifyParagraph(String message, int letters) {
		
		//remove line breaks
		message = message.replace('\n', ' ');
		
		//remove double whitespace
		String replaced = message.replace("  ", " ");
		while(!message.equals(replaced)){
			message = replaced;
			replaced = message.replace("  ", " ");
		}
				
		ArrayList<ArrayList<String>> lineWords = new ArrayList<ArrayList<String>>();
		ArrayList<String> currentLine = new ArrayList<String>();
		
		String[] words = message.split(" ");
		
		// wrap lines
		String lineBuffer = words[0];
		currentLine.add(words[0]);
		//start with second word
		for (int i = 1; i < words.length; i++) {
			String word = words[i];	
			if((lineBuffer + word).length() < letters) {
				lineBuffer += (" " + word);
				currentLine.add(word);
			}
			else {
				lineBuffer = justifyLine(currentLine, letters, (letters - lineBuffer.length()));
				lineBuffer = word;
				lineWords.add(currentLine);
				currentLine = new ArrayList<String>();
				currentLine.add(word);
			}
			
		}
		lineWords.add(currentLine);
			
		String[] lines = new String[lineWords.size()];		
		return lineWords.toArray(lines);
	}

	private static String justifyLine(ArrayList<String> currentLine, int letters, int plusWhite) {
		
		String lineBuffer = "";		
		
		int holes = currentLine.size() - 1;
		int add = plusWhite;
		
		for (int i = 1; i < currentLine.size(); i++) {
			lineBuffer += " ";
			while(add - holes >= 0) {
				lineBuffer += " ";
				add -= holes;			
			}
			lineBuffer += currentLine.get(i);
			add += plusWhite;
		}
		
		return lineBuffer;
	}
	
	
	
	
}

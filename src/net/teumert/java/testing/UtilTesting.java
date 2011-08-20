package net.teumert.java.testing;

import net.teumert.java.util.tStringUtil;

public class UtilTesting {
	
	public static void main(String[] args) {
		
		String lipsum ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus elementum hendrerit mauris, sit amet egestas quam rhoncus eu. Aenean quis est dui, ac tempus lacus. Aliquam sollicitudin dapibus ipsum, id egestas nulla commodo ac. Praesent pretium libero fringilla ante facilisis at rutrum ante interdum. Vestibulum vitae fringilla urna. Phasellus interdum mauris sed purus bibendum quis dignissim risus rhoncus. Aliquam risus dui, luctus id dignissim ac, tincidunt at mauris. Donec tincidunt, massa sed tempus ultricies, urna diam facilisis leo, volutpat venenatis est mauris vel metus. Nulla venenatis ultricies sagittis. Mauris pharetra nunc vitae mi placerat sit amet pellentesque tortor tristique. Sed pellentesque sagittis dolor non consectetur. Pellentesque id nunc metus, sed scelerisque risus. Nam fringilla ornare justo vitae placerat. Donec sit amet nisl ac augue pellentesque pretium at in metus. Donec faucibus, est eu dapibus porta, ligula ante fermentum diam, in mollis justo urna nec ipsum. Etiam nulla tellus, vestibulum et rutrum quis, faucibus a nisi. Ut consequat venenatis quam, ac porttitor quam vulputate sit amet. Nam id nisi eget odio sagittis condimentum in ac nisi. Phasellus venenatis tempor justo, id semper mauris facilisis vestibulum. Donec facilisis tellus sed orci viverra vehicula.";
		
		
		for (int i = 0; i < 12; i++) {
			lipsum += ("" + lipsum);
		}
		
		//benchmark
		long start = System.currentTimeMillis();
		
		int runs = 100;
		int stepsize = 10;	
		
		for (int i = 0; i < runs; i += stepsize) {
			long start2 = System.currentTimeMillis();
			for(int j = 0; j < stepsize; j++)
				tStringUtil.justifyParagraph(lipsum, 40);
			long end = System.currentTimeMillis();
			System.out.println("Needed " + (end-start2) +" for "+ stepsize +" runs with " + lipsum.length() +" letters");
		}	
		
		long end = System.currentTimeMillis();		
		
		System.out.println("Needed " + (end-start) +" for "+ runs +" runs with " + lipsum.length() +" letters");
		
	}
	
}

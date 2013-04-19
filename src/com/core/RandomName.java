package com.core;

import java.util.Random;

public class RandomName {
	public static StringBuffer getName(){
		String chars = "abcdefghijklmnopqrstuvwxyz";
	    Random r = new Random();
	    int limit = 5;
	    StringBuffer buf = new StringBuffer();
	    buf.append(chars.charAt(r.nextInt(26)));
	    for (int i = 0; i < limit ; i++) {
	       buf.append(chars.charAt(r.nextInt(chars.length())));
	    }
	    
	    return buf;
	}
}

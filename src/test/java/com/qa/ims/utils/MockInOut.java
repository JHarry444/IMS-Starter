package com.qa.ims.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class MockInOut {

	    private PrintStream orig;
	    private InputStream irig;
	    private ByteArrayOutputStream os;
	    private ByteArrayInputStream is;
	    
	    private final static Charset charset;
	    static {
	        // Our source and tests files are UTF-8, so we assert against UTF-8 strings
	        // and don't want MockInOut to convert to the native charset.
	        if (Charset.availableCharsets().containsKey("UTF-8")) {
	            charset = Charset.forName("UTF-8");
	        } else {
	            charset = Charset.defaultCharset();
	        }
	    }

	    public MockInOut(String input) {
	        orig = System.out;
	        irig = System.in;

	        os = new ByteArrayOutputStream();
	        try {
	            System.setOut(new PrintStream(os, false, charset.name()));
	        } catch (UnsupportedEncodingException ex) {
	            throw new RuntimeException(ex);
	        }

	        is = new ByteArrayInputStream(input.getBytes());
	        System.setIn(is);
	    }
	    
	    public void close() {
	        os = null;
	        is = null;
	        System.setOut(orig);
	        System.setIn(irig);
	    }
	}

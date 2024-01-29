package pobj.multiset.test;

import pobj.multiset.*;

public class MultiSetParserTest {

    public static void main(String[] args) {
    	    	
        try {
        	
            MultiSet<String> multiSet = MultiSetParser.parse("data/toParse.txt"); 
            System.out.println("MultiSet contents:");
            System.out.println(multiSet);
            
        } catch (InvalidMultiSetFormat e) {
        	
            System.err.println("Error: " + e.getMessage());
            Throwable exception = e.getCause();
            if (exception != null) {
                System.err.println("Cause: " + exception.getMessage());
            }
            
        }
        
    }
}

package pobj.multiset.test;
import pobj.multiset.*;

public class HashTests {
	public static void main(String[] args) {
		MultiSet<String> m = new HashMultiSet<>();
		m.add("a");
		m.add("a",5);
		m.add("b");
		m.add("b",4);
		m.add("c");
		m.add("c",3);
		
		System.out.println(m);
 
	}
}


package pobj.multiset.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pobj.multiset.*;

public class HashMultiSetTest2 {
	private MultiSet<String> m;
	
	@Before
	public void before() {
		m = new HashMultiSet<>();
		m.add("a");
		m.add("a",5);
		m.add("b",3);
	}
	
	@After
	public void after() {
		assertTrue(m.isConsistent());
	}
	
	@Test
	public void testAdd1() {
		assertEquals(m.count("a"), 6);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAdd2() {
		m.add("a");
		m.add("a",-1);
	}

	@Test
	public void testRemove1() {
		assertTrue(m.remove("a"));
		assertEquals(m.count("a"), 5);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testRemove2() {
		m.remove("b", -5);
	}

	@Test
	public void testToString() {
		assertEquals(m.toString(), "[a:6;b:3]");
	}

	@Test
	public void testClear() {
		m.clear();
		assertEquals(m.size(), 0);	
	}
	
	@Test
	public void testsZero() {
		assertFalse(m.add("e",0));
		assertFalse(m.remove("b",0));
	}
	
	@Test
	public void testsN() {
		assertTrue(m.add("e",8));
		assertEquals(m.size(),17);
		assertTrue(m.remove("e",2));
		assertEquals(m.count("e"), 6);
		assertEquals(m.count("i"),0);
	}
	
}

package pobj.multiset;
import java.util.*;

public interface MultiSet<T> extends Iterable<T> {
	public boolean add(T e, int count);
	public boolean add(T e);
	public boolean remove(Object e);
	public boolean remove(Object e, int count);
	public int count(T o);
	public void clear();	
	public int size();
	public List<T> elements();
	public boolean isConsistent();
}
 
package pobj.multiset;

import java.util.*;

public class MultiSetDecorator<T> implements MultiSet<T>{
	MultiSet<T> decorated;
	
	public MultiSetDecorator(MultiSet<T> decorated) {
		this.decorated = decorated;
	}
	
	public MultiSet<T> getDecorator() {
		return decorated;
	}
	
	public boolean add(T e, int count) {
		boolean res = decorated.add(e, count);
		if (!decorated.isConsistent()) {
            throw new InternalError("MultiSet is inconsistent");
        }
		return res;
	}
	
	public boolean add(T e) {
		boolean res = decorated.add(e);
		if (!decorated.isConsistent()) {
            throw new InternalError("MultiSet is inconsistent");
        }
		return res;
	}
	
	public boolean remove(Object e, int count) {
		boolean res = decorated.remove(e, count);
		if (!decorated.isConsistent()) {
            throw new InternalError("MultiSet is inconsistent");
        }
		return res;
	}
	
	public boolean remove(Object e) {
		boolean res = decorated.remove(e);
		if (!decorated.isConsistent()) {
            throw new InternalError("MultiSet is inconsistent");
        }
		return res;
	}
	
	public int count(T o) {
		return decorated.count(o);
	}
	
	public void clear() {
		decorated.clear();
	}
	
	public int size() {
		return decorated.size();
	}
	
	public List<T> elements() {
		 return decorated.elements();
	}
	
	public boolean isConsistent() {
		return decorated.isConsistent();
	}
	
	@Override
	public String toString() {
		return decorated.toString();
	}
	
	@Override
	public Iterator<T> iterator() {
		return decorated.iterator();
	}
}

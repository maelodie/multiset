package pobj.multiset;

import java.util.*;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>, Iterable<T> {
	private Map<T, Integer> set;
	private int size;
	public HashMultiSet() {
		set = new HashMap<T, Integer>();
		size = 0;
	}
	
	public HashMultiSet(Collection<T> c) {
		HashMultiSet<T> copy = new HashMultiSet<T>();
		for(T e : c) {
			copy.add(e);
		}	
	}
	
	public Map<T, Integer> getSet() { return set; }
	
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		int count = size;
		for(Map.Entry<T, Integer> element : set.entrySet()) {
			count -= element.getValue();
			sb.append(element.getKey());
			sb.append(":");
			sb.append(element.getValue());
			if(count > 0) sb.append(";");
		}
		sb.append("]");
		return sb.toString();
	}
	
	//Changer l'algo pour ne pas utiliser get et set en meme temps
	public boolean add(T e, int count) {
		if(count < 0) throw new IllegalArgumentException();
		if(count != 0) {
			Integer val = set.get(e);
			if(val == null) {
				set.put(e, count);
			}
			else {
				set.put(e, val+count);
			}
			size += count;
			return true;
		}
		return false;
	}
	
	public boolean add(T e) {
		boolean res = this.add(e, 1);
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public boolean remove(Object e, int count) {
		if(count < 0) throw new IllegalArgumentException();
		if(count != 0) {
			Integer val = set.get(e);
			if(val == null) {
				return false;
			}
			else {
				if(val - count <= 0) {
					set.remove(e);
					size -= val;
				}
				else {
					set.put((T)e, val-count);
					size -= count;
				}
			}
			return true;
		}
		assert(this.isConsistent());
		return false;
	}
			
	@SuppressWarnings("unchecked")
	public boolean remove(Object e) {
		boolean res = this.remove((T)e, 1);
		return res;
	}
	
	public int count(T o) {
		Integer val = set.get(o);
		if(val == null) return 0;
		return val;
	}
	
	public int size() {
		return this.size;
	}
	
	public void clear() {
		size = 0;
		set.clear();
	}

	public boolean removeAll(AbstractCollection<T> collect) {
		boolean modified = false;
		for(T element : collect) {
			if(this.contains(element)) {
				this.remove(element);
				modified = true;
			}
		}
		return modified;
	}
	
	@Override
	public List<T> elements() {
		List<T> res = new ArrayList<>();
		for(T e: set.keySet()) {
			res.add(e);
		}
		return res;
	}
	
	public boolean isConsistent() {
		int sizeTest = 0;
		List<Integer> liste = new ArrayList<>(set.values());
		for(Integer i : liste) {
			if(i <= 0) return false;
			sizeTest += i;
		}
		return sizeTest == size;
		
	}
	
	private class HashMultiSetIterator implements Iterator<T> {
		private Iterator<Map.Entry<T, Integer>> iterator;
		private int reste;
		private T current;
		
		public HashMultiSetIterator(Iterator<Map.Entry<T, Integer>> iterator) {
			this.iterator = iterator;
			current = null;
			reste = 0;
		}
		
		public boolean hasNext() {
			return iterator.hasNext() || reste > 0;
		}
		
		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			if(reste == 0) {
				Map.Entry<T, Integer> entry = iterator.next();
                current = entry.getKey();
                reste = entry.getValue();
			}
			reste--;
            return current;
         }
		
	}
	
	public HashMultiSetIterator iterator() {
		return new HashMultiSetIterator(set.entrySet().iterator());
	}

}

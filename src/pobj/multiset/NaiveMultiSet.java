package pobj.multiset;

import java.util.*;
;
public class NaiveMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>{
	private List<T> elements;
	private List<Integer> freq;
	private int size;
	
	public NaiveMultiSet() {
		this.elements = new ArrayList<>();
		this.freq = new ArrayList<>();
	}

	public boolean add(T e, int count) {
		if(count > 0) {
			int ind = elements.indexOf(e);
			int freqE = 0;
			
			//si l'élément est déjà dans les listes, on incrémente sa fréquence à la valeur correspondante
			if(ind != -1) {
				freqE = freq.get(ind);
				freq.set(ind, freqE + count);
				
			//sinon, on ajoute un nouvel élément dans chaque liste
			} else {
				elements.add(e);
				freq.add(count);
			}
			size += count;
			return true;
		}
		return false;
	}
	
	public boolean add(T e) {
		return add(e,1);
	}
	
	public boolean remove(Object e, int count) {
		if(count > 0) {
			int index = elements.indexOf(e);
			
			//L'élément n'existe pas et il n'y a rien à supprimer
			if(index == -1) {
				return false;
			}
			
			//sinon on enlève l'élémént
			int freqE = freq.get(index);
			if(freqE > count) {
				freq.set(index, freqE-count);
			} else {
				elements.remove((index));
				freq.remove(index);
			}
			size -= count;
			return true;
		}
		return false;
	}
	
	public boolean remove(Object e) {
		if(e == null) {
			return false;
		}
		return remove(e, 1);
	}
	
	public int count(T o) {
		if(o==null) return 0;
		int ind = elements.indexOf(o);
		if(ind == -1) return 0;
		return freq.get(ind);		
	}
	
	public void clear() {
		elements.clear();
		freq.clear();
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public List<T> elements(){
		return elements;
	}
	
	public List<T> sort(){
		
		List<T> res = new ArrayList<>(); //liste triée
		List<Integer> tmpFrequence = new ArrayList<>(); //liste de traitement des frequences
		List<T> tmpElements = new ArrayList<>(); //liste de traitement des valeurs
		
		//initialisations de liste 
		for(Integer i: freq) {
			tmpFrequence.add(i);
		}
		for(T t:elements) {
			tmpElements.add(t);
		}
		
		int ite = elements.size();
		
		while(ite > 0) {
			
			Integer fmax = tmpFrequence.get(0);
			T elmax = tmpElements.get(0);
			
			//recherche des plus grands éléments
			for(int i = 1; i < tmpFrequence.size(); i++) {
				if(tmpFrequence.get(i) > fmax) {
					elmax = tmpElements.get(i);
					fmax = tmpFrequence.get(i);
				}
			}
			
			//retraits du max et ajout dans le résultat
			tmpFrequence.remove(fmax);
			tmpElements.remove(elmax);
			res.add(elmax);
			
			ite--;
		}
		
		return res;
	}
	
	public boolean isConsistent() {
		//vérification de la taille
		int realSize = 0;
		for(Integer i : freq) {
			if(i <= 0) {
				return false;
			}
			realSize += i;
		}
		return realSize == size;	
	}

	@Override
	public String toString() {
		return "NaiveMultiSet [elements=" + elements + ", freq=" + freq + "]";
	}
	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
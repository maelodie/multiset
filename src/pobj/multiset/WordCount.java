package pobj.multiset;

import java.io.*;
import java.util.*;
import pobj.util.Chrono;

public class WordCount {
	public static void wordcount(MultiSet<String> ms) throws IOException {
		String file ="data/WarAndPeace.txt";
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;

		while ((line = br.readLine()) != null) {
		    for (String word : line.split("\\P{L}+")) {
		        if (word.equals("")) continue; 
		        ms.add(word);
		    }
		}
		br.close();
		
		System.out.println(ms);
		
		List<String> elements = new ArrayList<>();
		elements = ms.elements();
		
		Collections.sort(elements, (e1, e2) -> ms.count(e2) - ms.count(e1));
		
		for(int i = 0; i < 10; i++) {
			System.out.println(elements.get(i));
		}
	}
	
	public static void wordcount2(MultiSet<String> ms) throws IOException {
		String file = "data/WarAndPeace.txt"; 
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while((line = br.readLine()) != null) {
			for(String word: line.split("\\P{L}+")) {
				if(word.equals("")) continue;
				ms.add(word);
			}
		}
		br.close();
		
		List<String> elements = new ArrayList<>();
		if(ms instanceof MultiSetDecorator) {
			MultiSetDecorator<String> msd = (MultiSetDecorator<String>) ms;
			elements = ((NaiveMultiSet<String>) msd.getDecorator()).sort();
		} else {
			elements = ((NaiveMultiSet<String>) ms).sort();
		}
		
		for(int i = 0; i < 10; i++) {
			System.out.println(elements.get(i));
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		Chrono chrono1= new Chrono();
		MultiSet<String> hset = new HashMultiSet<>();
		MultiSet<String> hset2 = new MultiSetDecorator<>(hset);
		wordcount(hset2);
		chrono1.stop();
		
		System.out.println("\n\n");
		
		Chrono chrono2 = new Chrono();
		MultiSet<String> nset = new NaiveMultiSet<>();
		MultiSet<String> nset2 = new MultiSetDecorator<>(nset);
		wordcount2(nset2);
		chrono2.stop();
	}
		
	
}
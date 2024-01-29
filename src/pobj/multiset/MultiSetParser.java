package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class MultiSetParser {

	
    public static MultiSet<String> parse(String fileName) throws InvalidMultiSetFormat {
        MultiSet<String> multiSet = new HashMultiSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parties = line.split(":");
                if (parties.length != 2) {
                    throw new InvalidMultiSetFormat("Le format de la ligne " + line + " n'est pas valide");
                }

                String element = parties[0];
                
                try {
                    int freq = Integer.decode(parties[1]);
                    multiSet.add(element, freq);
                    
                } catch (NumberFormatException e) {
                    throw new InvalidMultiSetFormat("Fréquence invalide sur la ligne :  " + line + ", il faut donner un entier", e);
                }
            }
            
        } catch (IOException e) {
            throw new InvalidMultiSetFormat("Erreur d'ouverture du fichier", e);
        }

        return multiSet;
    }

}

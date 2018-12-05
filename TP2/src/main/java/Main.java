import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        long x = System.nanoTime();
        //String faute = "waggon";
        List<String> dico = readFile("dico.txt");
        List<String> fautes = readFile("fautes.txt");
        TrigramsDictionnary trigramsDictionnary = new TrigramsDictionnary();
        List<String> suggestionList = new ArrayList<>();
        trigramsDictionnary.trigramsDictionnary(dico);
        for(String faute: fautes) {
            Levenshtein levenshtein = new Levenshtein(faute);
            System.out.println("\nLe mot à corriger est : "+faute);
            if (!dico.contains(faute)) {
                //System.out.println("Mots avec le plus de trigrammes en commun :");
                List<String> communWords = trigramsDictionnary.search(faute, 100);
                //printList(communWords);
                //System.out.println("-----------------");
                System.out.println("Mots suggérés :");
                suggestionList = levenshtein.bestWords(communWords, 5);
                printList(suggestionList);
            } else {
                System.out.println("Le mot est correct");
            }
        }
        System.out.println("Temps d'exécution :"+ TimeUnit.NANOSECONDS.toMillis(System.nanoTime()-x));
    }

    /**
     * Lit le fichier passer en paramètre et retourne une liste contenant le mots du fichier
     *
     * @param fileName le fichier à lire
     * @return la liste des mots du fichier
     */

    public static List<String> readFile(String fileName) {

        Path formulaPath = Paths.get(fileName);
        List<String> list = null;
        try {
            list = Files.readAllLines(formulaPath);
        } catch (IOException e) {
            System.out.println("Impossible de lire le fichier");
        }
        return list;


    }

    public static <K> void printList(List<K> list){
        for (K element : list) System.out.println(element);
    }
}

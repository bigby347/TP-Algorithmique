import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        long x = System.nanoTime();
        String word = "enticonstitucionele";
        List<String> dico = readFile("dico.txt");
        TrigramsDictionnary trigramsDictionnary = new TrigramsDictionnary();
        List<String> suggestionList = new ArrayList<>();
        Levenshtein levenshtein = new Levenshtein(word);
        trigramsDictionnary.trigramsDictionnary(dico);
        if(!dico.contains(word)){
            System.out.println("Mots avec le plus de trigrammes en commun :");
            List<String> communWords = trigramsDictionnary.search(word,10000);
            printList(communWords);
            System.out.println("-----------------");
            System.out.println("Mots suggérés :");
            suggestionList = levenshtein.bestWords(communWords,5);
            printList(suggestionList);
        }
        else{
            System.out.println("Le mot est correct");
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> dico = readFile("dico.txt");
        //for (String word : dico) System.out.println(word);

    }

    /**
     * Lit le fichier passer en paramètre et retourne une liste contenant le mots du fichier
     * @param fileName le fichier à lire
     * @return la liste des mots du fichier
     */

    public static List<String> readFile(String fileName){

        Path formulaPath = Paths.get(fileName);
        List<String> list = null;
        try {
            list = Files.readAllLines(formulaPath);
        } catch (IOException e) {
            System.out.println("Impossible de lire le fichier");
        }
        return list;


    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionnary {

    Map<String, ArrayList<String>> dictionary;

    public Dictionnary(){
        this.dictionary = new HashMap<>();
    }


    public void trigramsDictionnary(List<String> words){

    }

    public List<String> trigrams(String word){
        List<String> listTrigrams = new ArrayList<>();

        for (int i=0;i<word.length()-2;i++){
            listTrigrams.add(word.toLowerCase().substring(i, i + 3));
        }
        return listTrigrams;
    }


}

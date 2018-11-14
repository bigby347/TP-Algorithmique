import java.util.*;

public class TrigramsDictionnary {

    Map<String, ArrayList<String>> dictionnary;

    public TrigramsDictionnary() {
        this.dictionnary = new HashMap<>();
    }

    /**
     * Remplit le dictionnaire de trigrammes à partir d'une liste de mots
     * Pour chaque trigramme , une liste de mots contenant celui-ci lui est associé à l'aide d'un Map
     *
     * @param words liste de mots
     */
    public void trigramsDictionnary(List<String> words) {
        for (String word : words) {
            List<String> trigramsList = setTrigramsList(word);

            //Pour chaque trigrammes, de la liste de trigramme d'un mot,
            // on vérifie si le trigramme n'est pas déjà dans le dictionnaire.

            for (String trigram : trigramsList) {
                ArrayList<String> array;
                if (dictionnary.containsKey(trigram)) {
                    //si le trigram est déjà présent dans le dictionnaire, on récupere sa liste de mots
                    array = dictionnary.get(trigram);
                } else {
                    //sinon on créer un liste vide
                    array = new ArrayList<>();
                }
                // on ajoute le mot a la liste du trigramme
                array.add(word);
                //on ajout le trigram et sa liste à la map
                dictionnary.put(trigram, array);
            }
        }
    }

    /**
     * Retourne la liste des trigrammes d'un mot
     *
     * @param word le mot dont on cherche les trigrammes
     * @return listTrigrams la liste des trigrammes de word
     */

    public List<String> setTrigramsList(String word) {
        List<String> trigramsList = new ArrayList<>();

        for (int i = 0; i < word.length() - 2; i++) {
            trigramsList.add(word.toLowerCase().substring(i, i + 3));
        }
        return trigramsList;
    }

    /**
     * Retourne la liste des mots ayant le plus de trigrame en communs
     *
     * @param word       le mot dont on cherche les trigrammes
     * @param wordNumber nombre de mots à retourner
     * @return list la liste de mots ayant le plus de trigrammes en communs avec word
     */
    public List<String> search(String word, int wordNumber) {

        List<String> returnList = new ArrayList<>();
       Map<String,Integer> triMap = new HashMap<>();
        List<String> trigrams = setTrigramsList(word);

        for (String trigram : trigrams) {
            if (dictionnary.containsKey(trigram)) {
                for (String communWord : dictionnary.get(trigram)) {
                    if (triMap.containsKey(communWord)) {
                        triMap.put(communWord,triMap.get(communWord)+1);
                    } else {
                        triMap.put(communWord,1);
                    }
                }
            }
        }
        //on trie la map par value dans un ordre décroissant
        Map<String,Integer> sortMap = MapTools.sortByValueDescending(triMap);
        //MapTools.printMap(sortMap);
        // on choisi les n premier mots que l'on stock dans une list

        for (Map.Entry<String,Integer> mapEntry : sortMap.entrySet()){
            if(returnList.size()<wordNumber) returnList.add(mapEntry.getKey());
            else break;
        }

        return returnList;
    }

}

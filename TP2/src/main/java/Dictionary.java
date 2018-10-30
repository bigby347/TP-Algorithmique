public class Dictionary {


    public int levenshteinDistance(String word1, String word2) {

        int distance[][] = new int[word1.length() + 1][word2.length() + 1];
        // i et j itèrent sur word1 et word2
        int i,j;
        int edit; //coût de substitution
        //initialisation de la distance

        for (i=0 ; i<word1.length();i++){
            distance[i][0] = i;
        }
        for (j=0 ; j<word1.length();j++){
            distance[0][j] = j;
        }

        for(i=1;i<word1.length();i++){
            for (j=1;j<word2.length();j++){

                int db = distance[i-1][j-1]; // diagonal box
                int lb = distance[i][j-1]; // left box
                int tb = distance[i-1][j]; // top box

                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    edit =0;
                }
                else {
                    edit = 1;
                    distance[i][j] = Math.min(distance[i-1][j-1])
                }
            }
        }

        return 0;
    }
}

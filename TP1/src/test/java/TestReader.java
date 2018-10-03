import java.io.*;
import java.util.ArrayList;

public class TestReader {

    public static void main (String[] argv) throws IOException
    {
        ArrayList<Graph> graphs;
        Graph graph;
        Graph transpose ;
        GraphConstructor graphConstructor = new GraphConstructor("src/formula1.txt");

        System.out.println("nb var : " + graphConstructor.readVarCount());
        graphs = graphConstructor.createGraph();
        graph= graphs.get(0);
        transpose= graphs.get(1);
        System.out.println("Le graphe G : ");
        System.out.println(graph.toString());
        System.out.println("----------------------------");
        System.out.println("Le graphe tranposé G : ");
        System.out.println(transpose.toString());
        System.out.println("----------------------------");
        graph.parcoursProfondeur();

    }
}

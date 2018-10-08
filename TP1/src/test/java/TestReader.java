import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestReader {

    public static void main(String[] argv) throws IOException {
        ArrayList<Graph> graphs;
        Graph graph;
        Graph transpose;
        ArrayList<LinkedList<Integer>> components;


        GraphConstructor graphConstructor = new GraphConstructor("src/formula1.txt");
        graphs = graphConstructor.createGraph();
        graph = graphs.get(0);
        transpose = graphs.get(1);

        System.out.println("Le graphe G : ");
        System.out.println(graph.toString());
        System.out.println("----------------------------");
        System.out.println("Le graphe tranposé G : ");
        System.out.println(transpose.toString());
        System.out.println("----------------------------");
        SCCKosaraju scc = new SCCKosaraju(graph, transpose);
        components = scc.scc();

        System.out.println("Les composentes fortement connexes : ");
        System.out.println(components);

    }
}

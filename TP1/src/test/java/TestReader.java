import java.io.*;

public class TestReader {

    public static void main (String[] argv) throws IOException
    {
        Graph graph;
        GraphConstructor graphConstructor = new GraphConstructor("src/formula1.txt");

        System.out.println("nb var : " + graphConstructor.readVarCount());
        graph = graphConstructor.createGraph();
        System.out.println(graph.toString());

    }
}

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;

public class TestReader {

    public static void main (String[] argv) throws IOException
    {
        Graph graph;
        Reader file = new Reader("src/formula1.txt");

        System.out.println("nb var : " + file.readVarCount());
        graph = file.createGraph();
        System.out.println(graph.toString());

    }
}

import java.io.*;
import java.util.*;
import java.util.Vector;

public class Reader {

    private BufferedReader input;
    private String fileName;
    private File file;

    public Reader(String fileName) {
        this.fileName = fileName;
        this.file = new File(fileName);
    }

    public int readVarCount() throws IOException {
        int varCount;
        input = new BufferedReader(new FileReader(file));

        varCount = Character.getNumericValue(input.read());

        return varCount;
    }

    public Graph createGraph() throws IOException {
        String line;
        Graph graph = new Graph(2*readVarCount());
        int[] array;
        int x,y;
        input = new BufferedReader(new FileReader(file));
        array = createTab(readVarCount());
        while ((line = input.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            if (st.countTokens() > 1) {
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                graph.addArc(getIndex(-x,array),getIndex(y,array),1);
                graph.addArc(getIndex(-y,array),getIndex(x,array),1);
            }
        }
        return graph;
    }

    public int getIndex(int litteral,int[] tab) {
        int i=0;
        int absLit = Math.abs(litteral);
        while (tab[i] != absLit){
            i++;
        }
        if( litteral < 0){
            return i + tab.length;
        }
        else {
            return i;
        }
    }

    public int[] createTab(int size) {
        int[] tab;
        tab = new int[size];

        for (int i = 0; i < size; i++) {
            tab[i] = i + 1;
        }

        return tab;
    }
}

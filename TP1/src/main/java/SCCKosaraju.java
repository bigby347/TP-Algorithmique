import java.io.IOException;
import java.util.*;

public class SCCKosaraju {

    private Graph graph;
    private Graph reverseGraph;


    public SCCKosaraju(Graph graph, Graph reverseGraph) {
        this.graph = graph;
        this.reverseGraph = reverseGraph;
    }

    public ArrayList<LinkedList<Integer>> scc() throws IOException {

        int n = graph.order();
        int C[] = new int[n];
        boolean[] marked = new boolean[n];
        LinkedList<Integer> order = new LinkedList<Integer>();
        ArrayList<LinkedList<Integer>> compo = new ArrayList<LinkedList<Integer>>();

        for (int i = 0; i < n; i++) {
            marked[i] = false;
        }
        for (int i = 0; i < n; i++) {
            if (!marked[i]) {
                graph.dfs(i, marked,order);
            }
        }

        System.out.println(order);
        Collections.reverse(order);
        System.out.println(order);
        Arrays.fill(marked, false);
        LinkedList<Integer> component = new LinkedList<Integer>();

        for(int u : order){
            if(!marked[u]){
                component = reverseGraph.dfsOnReverseGraph(u,marked,component,C);
                compo.add(component);
            }
        }

        for (int j=0;j<n;j++){
            System.out.print(j);
            System.out.print(C[j]);
            System.out.print("\n");
        }
        return compo;
    }
}

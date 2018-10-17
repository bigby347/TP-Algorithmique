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




        ArrayList<LinkedList<Integer>> component = new ArrayList<LinkedList<Integer>>();
        for (int i = 0; i < graph.order(); i++) {
            component.add(i, new LinkedList<Integer>());
        }


        int k = 0;
        for(int u : order){
            if(!marked[u]){
                reverseGraph.dfsOnReverseGraph(k,u,marked,component,C);
            }
            k+=1;
        }

        return component;
    }
}
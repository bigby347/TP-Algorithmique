import java.io.IOException;
import java.util.*;

public class SCCKosaraju {

    private Graph graph;
    private Graph reverseGraph;
    int n;
    int[] C;


    public SCCKosaraju(Graph graph, Graph reverseGraph) {
        this.graph = graph;
        this.reverseGraph = reverseGraph;
        n = graph.order();
    }

    public ArrayList<LinkedList<Integer>> scc() throws IOException {

        boolean[] marked = new boolean[n];
        C = new int[n];
        LinkedList<Integer> order = new LinkedList<Integer>();

        for (int i = 0; i < n; i++) {
            marked[i] = false;
        }
        for (int i = 0; i < n; i++) {
            if (!marked[i]) {
                graph.dfs(i, marked, order);
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
        for (int u : order) {
            if (!marked[u]) {
                reverseGraph.dfsOnReverseGraph(k, u, marked, component, C);
            }
            k += 1;
        }
        return component;
    }

    public boolean isSatisfiable(ArrayList<LinkedList<Integer>> components) {
        boolean s = true;
        LinkedList<Integer> stronglyConnectedComponent;
        for (int iterator = 0; iterator < components.size(); iterator++) {
            stronglyConnectedComponent = components.get(iterator);
            for (int iterator2 = 0; iterator2 < stronglyConnectedComponent.size(); iterator2++) {
                if (stronglyConnectedComponent.get(iterator2) >= n/2) {
                    stronglyConnectedComponent.set(iterator2, -(stronglyConnectedComponent.get(iterator2) - n/2+ 1));
                } else {
                    stronglyConnectedComponent.set(iterator2, stronglyConnectedComponent.get(iterator2) + 1);
                }
            }
            for (int literal = 0; literal < n/2; literal++) {
                if (stronglyConnectedComponent.contains(literal) && stronglyConnectedComponent.contains(-literal)) {
                    s = false;
                }
            }

        }
        return s;
    }

}
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Graph<Label> {

    private class Edge {
        public int source;
        public int destination;
        public Label label;

        public Edge(int from, int to, Label label) {
            this.source = from;
            this.destination = to;
            this.label = label;
        }
    }

    private int cardinal;
    private ArrayList<LinkedList<Edge>> incidency;


    public Graph(int size) {
        cardinal = size;
        incidency = new ArrayList<LinkedList<Edge>>(size + 1);
        for (int i = 0; i < cardinal; i++) {
            incidency.add(i, new LinkedList<Edge>());
        }
    }

    public int order() {
        return cardinal;
    }

    public void addArc(int source, int dest, Label label) {
        incidency.get(source).addLast(new Edge(source, dest, label));
    }

    public String toString() {
        String result = new String("");
        result = result.concat(cardinal + "\n");
        for (int i = 0; i < cardinal; i++) {
            for (Edge e : incidency.get(i)) {
                result = result.concat(e.source + " " + e.destination + " "
                        + e.label.toString() + "\n");
            }
        }
        return result;

    }
    public interface ArcFunction<Label, K> {
        public K apply(int source, int dest, Label label, K accu);
    }

    public interface ArcConsumer<Label> {
        public void apply(int source, int dest, Label label);
    }

    public <K> K foldEdges(ArcFunction<Label, K> f, K init) {
        for (LinkedList<Edge> adj : this.incidency) {
            for (Edge e : adj) {
                init = f.apply(e.source, e.destination, e.label, init);
            }
        }
        return init;
    }

    public void iterEdges(ArcConsumer<Label> f) {
        for (LinkedList<Edge> adj : this.incidency) {
            for (Edge e : adj) {
                f.apply(e.source, e.destination, e.label);
            }
        }
    }

    public void dfs(int u, boolean[] marked, LinkedList<Integer> order) {
        marked[u] = true;
        for (Edge e : incidency.get(u)) {
            if (!marked[e.destination]) {
                dfs(e.destination, marked,order);
            }
        }
        order.add(u);
    }

    public void dfsOnReverseGraph(int k, int u, boolean[] marked, ArrayList<LinkedList<Integer>> order,int C[]) {

        marked[u] = true;

        for (Edge e : incidency.get(u)) {
            C[e.destination]=u;
            if (!marked[e.destination]) {
                dfsOnReverseGraph(k,e.destination,marked,order,C);
            }
        }
        order.get(k).add(u);
    }
}
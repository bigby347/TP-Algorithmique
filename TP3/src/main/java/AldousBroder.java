import java.util.*;

public class AldousBroder {

    Graph graph;
    boolean[] reached;
    ArrayList<ArrayList<Arc>> tree;
    int currentVertex;
    Random random= new Random();

    public AldousBroder(Graph graph) {
        this.graph = graph;
        this.reached = new boolean[graph.order];
        this.currentVertex = random.nextInt(graph.order);
        reached[currentVertex] = true;
        this.tree= new ArrayList<>();
    }

    public boolean allVertexesReached(){
        for(Boolean bool :reached){
            if(!bool) return bool;
        }
        return true;
    }
    public void makeList(){
        tree = new ArrayList<>();
        for(int i = 0; i < graph.order ; i++) {
            this.tree.add(new ArrayList<Arc>());
        }
    }

    private void parcours(){

        while(!allVertexesReached()){
            List<Arc> neighbours = graph.outNeighbours(currentVertex);
            int randomNeighbours = random.nextInt(neighbours.size());
            currentVertex = neighbours.get(randomNeighbours).getDest();
            if(!reached[currentVertex]){
                tree.get(neighbours.get(randomNeighbours).getSource()).add(neighbours.get(randomNeighbours));
            }
            reached[currentVertex]=true;
        }

    }
    public ArrayList<Arc> generateTree() {
        makeList();
        parcours();
        ArrayList<Arc> result = new ArrayList<>();
        for(ArrayList<Arc> arcs: tree) {
            if(!arcs.isEmpty())
                result.addAll(arcs);
        }
        return result;
    }
}

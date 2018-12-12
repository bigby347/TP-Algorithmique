import java.util.*;

public class AldousBroder {

    Graph graph;
    BitSet reached;
    ArrayList<ArrayList<Arc>> tree;
    int currentVertex;

    public AldousBroder(Graph graph) {
        this.graph = graph;
        this.reached = new BitSet(graph.order);
        Random rand = new Random();
        this.currentVertex = rand.nextInt(graph.order);
        this.tree= new ArrayList<>();
        for(int i = 0; i <= graph.order ; i++) {
            tree.add(new ArrayList<Arc>());
        }
    }

    public boolean allVertexesReached(){
        for(int i =0 ; i<=reached.size();i++){
            if(!reached.get(i)) return false;
        }
        return true;
    }

    public void parcours(){

        reached.set(currentVertex);
        Random random= new Random();
        int randomNeighbours = random.nextInt(graph.order);
        List<Arc> neighbours = graph.outNeighbours(currentVertex);
        while(!allVertexesReached()){
            currentVertex = neighbours.get(randomNeighbours).getDest();
            if(!reached.get(currentVertex)){
                tree.get(neighbours.get(randomNeighbours).getSource()).add(neighbours.get(randomNeighbours));
                reached.set(currentVertex);
            }
        }

    }

    public ArrayList<Arc> generateTree() {

        Random random = new Random();
        int randomVertex = random.nextInt(graph.order);
        parcours();

        ArrayList<Arc> result = new ArrayList<>();
        for(ArrayList<Arc> arcs: tree) {
            if(!arcs.isEmpty())
                result.add(arcs.get(0));
        }
        return result;

    }
}

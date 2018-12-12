import java.util.*;

public class AldousBroder {

    Graph graph;
    BitSet reached;
    ArrayList<ArrayList<Arc>> tree;

    public AldousBroder(Graph graph) {
        this.graph = graph;
        this.reached = new BitSet(graph.order);
        this.tree = makeList(graph.order);
    }

    public boolean allVertexesReached(){
        for(int i =0 ; i<reached.size();i++){
            if(!reached.get(i)) return false;
        }
        return true;
    }

    public boolean unreachedNeighbours(int sommet){
        for(Arc arc : graph.outNeighbours(sommet))
            if(!reached.get(arc.getDest()))
                return true;
        return false;
    }

    public <T> ArrayList<ArrayList<T>> makeList(int size) {
        ArrayList<ArrayList<T>> res = new ArrayList<>(size);
        for(int i = 0; i <= size; i++) {
            res.add(null);
        }
        return res;
    }

    public void parcours(int startingVertex){

        reached.set(startingVertex);
        while(unreachedNeighbours(startingVertex) && !allVertexesReached()){
            Random rand = new Random();
            List<Arc> neighbours = graph.outNeighbours(startingVertex);
            int randomNeighbour = rand.nextInt(neighbours.size());
            int vertexReached = neighbours.get(randomNeighbour).getDest();
            if(!reached.get(vertexReached)) {
                reached.set(vertexReached);
                tree.get(neighbours.get(randomNeighbour).getDest()).add(neighbours.get(randomNeighbour));
            }
            parcours(vertexReached);
        }

    }

    public ArrayList<Arc> generateTree() {

        Random random = new Random();
        int randomVertex = random.nextInt(graph.order);
        parcours(randomVertex);

        ArrayList<Arc> result = new ArrayList<>();
        for(ArrayList<Arc> arcs: tree) {
            if(!arcs.isEmpty())
                result.add(arcs.get(0));
        }
        return result;

    }
}

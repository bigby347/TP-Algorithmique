import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Graph implements Iterable<Edge>{
    // classe de graphe non orientés permettant de manipuler
    // en même temps des arcs (orientés)
    // pour pouvoir stocker un arbre couvrant, en plus du graphe
    
	int order;
	int edgeCardinality;
	
	ArrayList<LinkedList<Edge>> adjacency; //graphe non orientés
	ArrayList<LinkedList<Arc>> inAdjacency;
	ArrayList<LinkedList<Arc>> outAdjacency;
	
	public boolean isVertex(int index) {
			for(LinkedList Edge : adjacency){
				return (Edge.contains(index));
			}

			for(LinkedList ArcIn : inAdjacency) {
				return (ArcIn.contains(index));
			}

			for(LinkedList ArcOut : outAdjacency){
				return (ArcOut.contains(index));
			}
			return false;
	}
	
	public <T> ArrayList<LinkedList<T>> makeList(int size) {
		ArrayList<LinkedList<T>> res = new ArrayList<LinkedList<T>>(size);
		for(int i = 0; i <= size; i++) {
			res.add(null);			
		}
		return res;
	}
	
	public Graph(int upperBound) {
	    order = upperBound;
	    adjacency = makeList(order);
	    inAdjacency = makeList(order);
	    outAdjacency = makeList(order);
	}
	
	public void addVertex(int indexVertex) {
		adjacency.add(indexVertex, new LinkedList<Edge>());
		inAdjacency.add(indexVertex, new LinkedList<Arc>());
		outAdjacency.add(indexVertex, new LinkedList<Arc>());
		order++;
	}
	
	public boolean ensureVertex(int indexVertex) {
	    if(!isVertex(indexVertex)){
	    	addVertex(indexVertex);
	    	return true;
		}
	    return false;
	}	
	
	public void addArc(Arc arc) {
		inAdjacency.get(inAdjacency.size()).add(arc);
		outAdjacency.get(outAdjacency.size()).add(arc);
		order++;
	}
	
	public void addEdge(Edge e) {
		edgeCardinality++;
		if(ensureVertex(e.source) && !hasEdge(e)){
			adjacency.get(e.source).add(e);
		}

		Edge inversedEdge  = new Edge(e.dest,e.source,e.weight);
		if(ensureVertex(inversedEdge.source) && !hasEdge(inversedEdge)){
			adjacency.get(inversedEdge.source).add(inversedEdge);
		}

		addArc(new Arc(e,false));
		addArc(new Arc(inversedEdge,true));
	}
	/*
		Verifie si l'arête n'est pas déjà présente dans le graph
	 */
	public boolean hasEdge(Edge e){
		for(Edge edge : adjacency.get(e.source))
			if(edge.dest == e.dest){
				return true;
			}

		return false;
	}

	public Arc outNeighbours(int vertex){
		//TODO à remplir
		return null;
	}
	
}

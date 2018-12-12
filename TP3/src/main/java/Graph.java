import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/*
	CODE DE BAPTISTE BUSNOULT
 */

public class Graph implements Iterable<Edge> {
	// classe de graphe non orientés permettant de manipuler
	// en même temps des arcs (orientés)
	// pour pouvoir stocker un arbre couvrant, en plus du graphe

	public int order;
	public int edgeCardinality;

	ArrayList<LinkedList<Edge>> adjacency;
	ArrayList<LinkedList<Arc>> inAdjacency; //Arcs qui arrivent vers le sommet
	ArrayList<LinkedList<Arc>> outAdjacency;  //Arcs qui partent du sommet


	public boolean isVertex(int index) {
		return index < adjacency.size() && index>=0;
	}

	public void ensureVertex(int indexVertex) {
		if (!isVertex(indexVertex)) {

			addVertex(indexVertex);
		}

	}

	public Graph(int upperBound) {
		order = upperBound;
		adjacency = new ArrayList<LinkedList<Edge>>();
		inAdjacency = new ArrayList<LinkedList<Arc>>();
		outAdjacency = new ArrayList<LinkedList<Arc>>();
	}


	public void addArc(Arc arc) {
		inAdjacency.get(arc.getDest()).add(arc);
		outAdjacency.get(arc.getSource()).add(arc);
	}

	public void addVertex(int indexVertex) {
		adjacency.ensureCapacity(indexVertex);
		order += indexVertex;
		for (int i=adjacency.size(); i<=indexVertex;i++) {
			adjacency.add(new LinkedList<Edge>());
			inAdjacency.add(new LinkedList<Arc>());
			outAdjacency.add(new LinkedList<Arc>());
		}
	}

	public void addEdge(Edge e) {
		addVertex(e.dest);
		adjacency.get(e.dest).add(e);
		addVertex(e.source);
		adjacency.get(e.source).add(e);
		addArc(new Arc(e,false));
		addArc(new Arc(e,true));
	}

	public List<Arc> outNeighbours(int vertex) {
		return outAdjacency.get(vertex);
	}

	@Override
	public Iterator<Edge> iterator() {
		// TODO Auto-generated method stub
		return null;
	}


}
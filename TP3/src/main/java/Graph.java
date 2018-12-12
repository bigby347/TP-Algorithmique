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
		//return index < adjacency.size() && index>=0;
		return index < adjacency.size() && index>=0 && adjacency.get(index) != null;
	}

	public void ensureVertex(int indexVertex) {
		if (!isVertex(indexVertex)) {

			addVertex(indexVertex);
		}

	}

	public Graph(int upperBound) {
		order = 0;
		//order = upperBound;
		adjacency = new ArrayList<LinkedList<Edge>>();
		inAdjacency = new ArrayList<LinkedList<Arc>>();
		outAdjacency = new ArrayList<LinkedList<Arc>>();

		for(int i = 0; i <= upperBound ; i++) {
			adjacency.add(null);
			inAdjacency.add(null);
			outAdjacency.add(null);
		}

	}


	public void addArc(Arc arc) {
		inAdjacency.get(arc.getDest()).add(arc);
		outAdjacency.get(arc.getSource()).add(arc);
	}

	public void addVertex(int indexVertex) {
		adjacency.ensureCapacity(indexVertex);
		if (isVertex(indexVertex)){
			return;
		}

		order += 1;
		adjacency.set(indexVertex,new LinkedList<Edge>());
		inAdjacency.set(indexVertex,new LinkedList<Arc>());
		outAdjacency.set(indexVertex,new LinkedList<Arc>());
	}

	public void addEdge(Edge e) {
		ensureVertex(e.dest);
		ensureVertex(e.source);

		adjacency.get(e.dest).add(e);
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
package graph;

import graph.Edge;
import graph.Graph;
import java.util.Iterator;

public interface IGraph<E> {
    
    Vertex[] endVertices(Edge e);

    Vertex opposite(Vertex v, Edge e);

    boolean areAdjacent(Vertex w, Vertex v);

    boolean replace(Vertex v, E x);

    boolean replace(Edge e, E x);

    boolean insertVertex(E o);
    
    boolean insertVertexReference(Vertex v);

    boolean insertEdge(Vertex v, Vertex w, E o);
    
    //boolean insertDirectedEdge(Vertex v, Vertex w, E o);
    
    boolean insertEdgeReference(Vertex v, Vertex w, Edge e);
    
    //boolean insertDirectedEdgeReference(Vertex v, Vertex w, Edge e);

    boolean removeVertex(Vertex v);

    boolean removeEdge(Edge e);

    Iterator vertices();

    Iterator edges();
    
    void DFS(Graph G);
    
    void BFS(Graph G);
    
}

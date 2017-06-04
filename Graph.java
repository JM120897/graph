package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Graph<E> implements IGraph<E>{
    
    /*ATRBS*/
    private List<Vertex> vertices;
    
    public Graph(){
        vertices = new ArrayList<>();
    }
    
    /*S/G*/
    public List<Vertex> getVertices() {
        return vertices;
    }

    /*MÉTODOS PROPIOS*/
    public boolean insertVertex(E o) {
        boolean added = false;
        Vertex<E> newVert = new Vertex<>(o);
        if(!vertices.contains(newVert)){
            getVertices().add(newVert);
            added = true;
        }
        return added;
    }
    
    public boolean insertVertexReference(Vertex v){
        return getVertices().add(v);
    }
 
    public boolean insertEdge(Vertex v, Vertex w, E o) {
        boolean added = false;
        //This way we can limit 1 connection per 2 vertices.
        if(getVertices().contains(v) && getVertices().contains(w) && !v.getAdjacentVertices().contains(w) && !w.getAdjacentVertices().contains(v)){
            //Edge<E> newEdge = new Edge<>(o);
            Edge<E> newEdge = new Edge<>(o);
            v.getAdjacentVertices().add(w);
            w.getAdjacentVertices().add(v);
            v.getEdges().add(newEdge);
            w.getEdges().add(newEdge);
            added = true;
        }
        return added;
    }


   
    

    public boolean insertEdgeReference(Vertex v, Vertex w, Edge o) {
        boolean added = false;
        //This way we can limit 1 connection per 2 vertices.
        if(getVertices().contains(v) && getVertices().contains(w) && !v.getAdjacentVertices().contains(w) && !w.getAdjacentVertices().contains(v)){
            v.getAdjacentVertices().add(w);
            w.getAdjacentVertices().add(v);
            v.getEdges().add(o);
            w.getEdges().add(o);
            added = true;
        }
        return added;
    }
    

   
 
    public Vertex[] endVertices(Edge e) {
        Vertex[] vertArray = new Vertex[2];
        int occurrence = 0;
        Iterator<Vertex> it = getVertices().iterator();
        Vertex<E> vert = null;
        while(it.hasNext()){
            vert = it.next();
            if(vert.getEdges().contains(e)){
                vertArray[occurrence++] = vert;
            }
        }
        return vertArray;
    }
 
    public Vertex opposite(Vertex v, Edge e) {
        boolean found = false;
        Vertex<E> vert = null;
        Iterator<Vertex> it = getVertices().iterator();
        if(v.getEdges().contains(e)){
            while(it.hasNext() && !found){
                vert = it.next();
                if(vert.getEdges().contains(e) && vert.getAdjacentVertices().contains(v)){
                    found = true;
                }
            }

            if(!found){
                vert = null;
            }
        }
        return vert;
    }
 
    //Condition is OR in order to work with DIRECTED EDGES
    public boolean areAdjacent(Vertex w, Vertex v) {
        return w.getAdjacentVertices().contains(v) || v.getAdjacentVertices().contains(w);
    }
 
    public boolean replace(Vertex v, E x) {
        boolean replaced = false;
        if(getVertices().contains(v)){
            Vertex<E> vert = getVertices().get(getVertices().indexOf(v));
            vert.setId(x);
            replaced = true;
        }
        return replaced;
    }
 
    public boolean replace(Edge e, E x) {
        boolean replaced = false, found = false;
        Vertex<E> vert = null;
        Edge<E> edge = null;
        Iterator<Vertex> it1 = getVertices().iterator();
        while(it1.hasNext() && !found){
            vert = it1.next();
            if(vert.getEdges().contains(e)){
                edge = vert.getEdges().get(vert.getEdges().indexOf(e));
                edge.setId(x);
                replaced = true;
            }
        }
        return replaced;
    }
 
    public boolean removeVertex(Vertex v) {
        Vertex w = null;
        Edge e = null;
        Iterator<Vertex> it1 = v.getAdjacentVertices().iterator();
        Iterator<Edge> it2 = null;
        while(it1.hasNext()){
            w = it1.next();
            it2 = w.getEdges().iterator();
            while(it2.hasNext()){
                e = it2.next();
                if(v == this.opposite(w, e)){
                    w.getEdges().remove(e);
                }
            }
            w.getAdjacentVertices().remove(v);
        }
        return getVertices().remove(v);
    }
 
    public boolean removeEdge(Edge e) {
        boolean found = false;
        Iterator<Vertex> it = getVertices().iterator();
        Vertex<E> vert = null;
        Vertex<E> oppositeVert = null;
        while(it.hasNext() && !found){
            vert = it.next();
            if(vert.getEdges().contains(e)){
                oppositeVert = this.opposite(vert, e);
                vert.getEdges().remove(e);
                oppositeVert.getEdges().remove(e);
                vert.getAdjacentVertices().remove(oppositeVert);
                oppositeVert.getAdjacentVertices().remove(vert);
                found = true;
            }
        }
        return found;
    }
 
    public Iterator vertices() {
        return getVertices().iterator();
    }
 
    public Iterator edges() {
        List<Edge> edgeList = new ArrayList<>();
        Iterator<Vertex> it1 = getVertices().iterator();
        Iterator<Edge> it2 = null;
        Vertex<E> vert = null;
        Edge<E> edge = null;
        while(it1.hasNext()){
            vert = it1.next();
            it2 = vert.getEdges().iterator();
            while(it2.hasNext()){
                edge = it2.next();
                if(!edgeList.contains(edge)){
                    edgeList.add(edge);
                }
            }
        }
        return edgeList.iterator();
    }
    
    public void DFS(Graph g){
        int i = 0;
        Iterator<Vertex> itV = g.getVertices().iterator();
        Vertex<E> v;
        Iterator<Edge> itE;
        while(itV.hasNext()){
                v = itV.next();
                v.setLabel("UNEXPLORED");
                itE = v.getEdges().iterator();
            while(itE.hasNext()){
                itE.next().setLabel("UNEXPLORED");  
            }
        }
        
        itV = g.getVertices().iterator();
        while(itV.hasNext()){
            v = itV.next();
            if(v.getLabel().equals("UNEXPLORED")){
                System.out.println("\nBosque " + i++);
                DFS(g,v);
            }
        }
    }
 
    public void DFS(Graph g,Vertex v){
        Vertex<E> w;
        Edge<E> ea;
        List<Edge> e = v.getEdges();
        Iterator<Edge> itE= e.iterator();

        v.setLabel("EXPLORED");
        System.out.println(v);
        while(itE.hasNext()){
            ea = itE.next();
            if(ea.getLabel().equals("UNEXPLORED")){
                w = opposite(v,ea);
                if(w != null && w.getLabel().equals("UNEXPLORED")){
                    ea.setLabel("DISCOVERY"); 
                    System.out.println(ea);
                    DFS(g,w);
                }else{
                    ea.setLabel("BACK");
                    System.out.println(ea);
                }
            }
        }
    }
    
    public void BFS(Graph g){
        int i = 0;
        Iterator<Vertex> itV = g.getVertices().iterator();
        Vertex<E> v;
        Iterator<Edge> itE;
        while(itV.hasNext()){
                v = itV.next();
                v.setLabel("UNEXPLORED");
                itE = v.getEdges().iterator();
            while(itE.hasNext()){
                itE.next().setLabel("UNEXPLORED");  
            }
        }
        
        itV = g.getVertices().iterator();
        while(itV.hasNext()){
            v = itV.next();
            if(v.getLabel().equals("UNEXPLORED")){
                System.out.println("\nBosque " + i++);
                BFS(g,v);
            }
        }        
    }

    private void BFS(Graph g, Vertex s) {
        List<Vertex> returnList = new ArrayList<>();                            //Just in case later on we wanted to return the elements.
        
        List<Vertex> current = new ArrayList<>();
        current.add(s);
        s.setLabel("VISITED");
        Vertex v, w;
        Edge e;
        
        Iterator<Vertex> it1 = current.iterator();
        int i = 0;
        
        while(it1.hasNext()){
            List<Vertex> next = new ArrayList<>();
            System.out.println("\nNivel " + i +  ":\n");
            v = it1.next();
            System.out.println(v + "\n\nTour a partir de este vertex:\n");
            Iterator<Edge> it2 = v.getEdges().iterator();
                
            while(it2.hasNext()){
                e = it2.next();

                if(e.getLabel().equals("UNEXPLORED")){
                    w = g.opposite(v, e);

                    if(w != null && w.getLabel().equals("UNEXPLORED")){
                        w.setLabel("VISITED");
                        e.setLabel("DISCOVERY");
                        System.out.println(w);
                        System.out.println(e);
                        next.add(w);
                        returnList.add(w);

                    }else{
                        e.setLabel("CROSS");
                        System.out.println(e);

                    }
                }
            }

            //i <- i+1
            //Preparing the outer loop for the next nodes
            if(it1.hasNext() == false){
                it1= next.iterator();
                i++;
            }
            
        }
        
    }
    
}

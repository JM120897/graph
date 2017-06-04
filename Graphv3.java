package graph;

import java.util.Iterator;

public class Graphv3 {

    public static void main(String[] args) {
        //DECLARATION OF EDGES/VERTICES
        Edge edge1= new Edge(2);
        Edge edge2= new Edge(13);
        Edge edge3 = new Edge(3);
        Edge edge4= new Edge(7);
        Edge edge5 = new Edge(11);
        Edge edge6 = new Edge(10);
        Edge edge7 = new Edge(9);

        Vertex vertex1 = new Vertex("A");
        Vertex vertex2 = new Vertex("B");
        Vertex vertex3 = new Vertex("C");
        Vertex vertex4 = new Vertex("D");
        Vertex vertex5 = new Vertex("G");
        Vertex vertex6 = new Vertex("OwO");

        //INSERTION OF VERTICES IN NEW GRAPH
        IGraph g = new Graph();
        g.insertVertexReference(vertex1);
        g.insertVertexReference(vertex2);
        g.insertVertexReference(vertex3);
        g.insertVertexReference(vertex4);
        g.insertVertexReference(vertex5);
        g.insertVertexReference(vertex6);
       
        
        System.out.println("Prueba de inserción de Edges");
        if(g.insertEdgeReference(vertex1, vertex2, edge1)){
            System.out.println("Se ha añadido un edge entre vertices  " + vertex1 + " y " + vertex2);
        }
        if(!g.insertEdgeReference(vertex2, vertex1, edge1)){
           System.out.println("No se puede insertar porque ya estaba insertado!");
        }
        g.insertEdgeReference(vertex1, vertex5, edge2);
        g.insertEdgeReference(vertex2, vertex3, edge3);
        g.insertEdgeReference(vertex3, vertex4, edge4);
        g.insertEdgeReference(vertex3, vertex5, edge6);
        g.insertEdgeReference(vertex4, vertex5, edge5);
        g.insertEdgeReference(vertex5, vertex6, edge7);

        System.out.println("\n\nPrueba de iterador de vértices:\n");
        Iterator<Vertex> l1 = g.vertices();
        Vertex v2;
        while(l1.hasNext()){
            v2 = l1.next();
            System.out.println(v2 + "Nº DE ARISTAS: " +  v2.getEdges().size());
        }        

        /**
          * En esta prueba tiene que devolver A y G
          *//*
        Vertex[] v1 = new Vertex[2];
        v1 = g.endVertices(edge2);
        System.out.println("\n\nPrueba de la funcion endVertices de "+ edge2 +", deberían ser A y G");
        for(int i=0;i<v1.length;i++){
            System.out.println(v1[i]);
        }
        */
        System.out.println("\n\nPrueba de las funciones de adyacencia entre " + vertex2 + " y " + vertex3);
        if(g.areAdjacent(vertex2, vertex3)){
            System.out.println("\nB y C son adyacentes");
        }
        System.out.println("\nEntre " + vertex2 + " y " + vertex6);
        if(!g.areAdjacent(vertex2, vertex6)){
            System.out.println("B y G no son adyacentes");

        }
        /**
         * Tiene que devolver el C
         */
        System.out.println("\n\nPrueba de opuesto de B respecto edge 3: " + g.opposite(vertex2, edge3));

        /*Prueba de quitar el vertex D
        Tiene que salir false 2 veces tras C y 2 veces tras G para que esté bien y no aparecer D*/
        g.removeVertex(vertex4);
        Iterator<Vertex> it = g.vertices();
        Vertex vr = null;
        while (it.hasNext()){
            vr = it.next();
            System.out.println(vr);
            if(vr == vertex3){
                System.out.println(vr.getEdges().contains(vertex4));
                System.out.println(vr.getAdjacentVertices().contains(edge4));
            }
            if(vr == vertex5){
                System.out.println(vr.getEdges().contains(vertex4));
                System.out.println(vr.getAdjacentVertices().contains(edge5));
            }
        }
        
        /*
        Al borrar el EDGE7 tiene que desaparecer de la lista de edges de G y O y
        G y O deben desaparecer de sus respectivas listas de vértices adyacentes
        */
        System.out.println("\n\nPrueba de borrando del " + edge7);
        g.removeEdge(edge7);
        if(!vertex5.getEdges().contains(edge7) && !vertex6.getEdges().contains(edge7)
                && !vertex5.getAdjacentVertices().contains(vertex6) && !vertex6.getAdjacentVertices().contains(vertex5)){
            System.out.println("BORRADO EDGE7 CON ÉXITO!");
        }
        
        System.out.println("\n\nPrueba de iterador de edges");
        Iterator<Edge> it4 = g.edges();
        while(it4.hasNext()){
            System.out.println(it4.next());
        }
        
        System.out.println("\n\nPrueba DFS:");
        g.DFS((Graph) g);
        
        System.out.println("\n\nPrueba BFS:");
        g.BFS((Graph) g);

        
        
    }        
}
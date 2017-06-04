/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import graph.Edge;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex<E> {
    
    /*ATRBS*/
    private E id;
    private List<Vertex> adjacentVertices;
    private List<Edge> adjacentEdges;
    private String label;

    public Vertex(E ele) {
        this.id = ele;
        this.adjacentVertices = new ArrayList<>();
        this.adjacentEdges = new ArrayList<>();
        this.label = "";
    }       
    
    /*S/G*/
    public E getId() {
        return id;
    }

    public void setId(E id) {
        this.id = id;
    }

    public List<Vertex> getAdjacentVertices() {
        return adjacentVertices;
    }

    public void setAdjacentVertices(List<Vertex> adjacentVertices) {
        this.adjacentVertices = adjacentVertices;
    }

    public List<Edge> getEdges() {
        return adjacentEdges;
    }

    public void setAdjacentEdges(List<Edge> adjacentEdges) {
        this.adjacentEdges = adjacentEdges;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    /*MÉTODOS OBJECT*/
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertex<?> other = (Vertex<?>) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vertex{" + "id=" + id + ", label=" + label + '}';
    }
}

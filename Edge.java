package graph;

import java.util.Objects;

public class Edge<E> {
    
    /*ATRBS*/
    private E id;    
    private String label;

    public Edge(E id) {
        this.setId(id);
        this.label = "";
    }

    /*S/G*/
    public void setId(E id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public E getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
    
    /*MÉTODOS OBJECT*/
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
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
        final Edge<?> other = (Edge<?>) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "Edge{" + "id=" + id + ", label=" + label + '}';
    }
}

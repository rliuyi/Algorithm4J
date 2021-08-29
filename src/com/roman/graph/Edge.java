package com.roman.graph;

import java.util.Objects;

public class Edge {

    final int u;
    final int v;

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }

    @Override
    public int hashCode() {
        return Objects.hash(u, v);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge other = (Edge) obj;
        return u == other.u && v == other.v;
    }

}

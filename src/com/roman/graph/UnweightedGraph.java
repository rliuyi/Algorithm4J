package com.roman.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class UnweightedGraph<V> implements Graph<V> {

    protected List<V> vertices = new ArrayList<>();
    protected List<List<Edge>> neighbors = new ArrayList<>();

    /** Construct an empty graph */
    protected UnweightedGraph() {
        super();
    }

    /** Construct a graph from vertices and edges stored in arrays */
    protected UnweightedGraph(V[] vertices, int[][] edges) {
        for (V v : vertices) {
            this.addVertex(v);
        }

        this.createAdjacencyLists(edges, vertices.length);
    }

    /** Construct a graph from vertices and edges stored in List */
    protected UnweightedGraph(List<V> vertices, List<Edge> edges) {
        for (V v : vertices) {
            this.addVertex(v);
        }

        this.createAdjacencyLists(edges, vertices.size());
    }

    /** Construct a graph for integer vertices 0, 1, 2 and edge list */
    @SuppressWarnings("unchecked")
    protected UnweightedGraph(List<Edge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            this.addVertex((V) Integer.valueOf(i));
        }

        this.createAdjacencyLists(edges, numberOfVertices);
    }

    /** Construct a graph for integer vertices 0, 1, 2 and edge array */
    @SuppressWarnings("unchecked")
    protected UnweightedGraph(int[][] edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            this.addVertex((V) Integer.valueOf(i));
        }

        this.createAdjacencyLists(edges, numberOfVertices);
    }

    /** Create adjacency lists for each vertex */
    private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
        for (int[] edge : edges) {
            this.addEdge(edge[0], edge[1]);
        }
    }

    /** Create adjacency list for each vertex */
    private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
        for (Edge edge : edges) {
            this.addEdge(edge);
        }
    }

    @Override
    public int getSize() {
        return this.vertices.size();
    }

    @Override
    public List<V> getVertices() {
        return this.vertices;
    }

    @Override
    public V getVertex(int index) {
        return this.vertices.get(index);
    }

    @Override
    public int getIndex(V v) {
        return this.vertices.indexOf(v);
    }

    @Override
    public List<Integer> getNeighbors(int index) {
        List<Edge> edges = this.neighbors.get(index);
        List<Integer> neighborList = new ArrayList<>(edges.size());
        for (Edge edge : edges) {
            neighborList.add(edge.v);
        }

        return neighborList;
    }

    @Override
    public int getDegree(int v) {
        return this.neighbors.get(v).size();
    }

    @Override
    public void printEdges() {
        for (int i = 0, length = this.neighbors.size(); i < length; i++) {
            System.out.print(this.getVertex(i) + " (" + i + "): ");
            for (Edge edge : this.neighbors.get(i)) {
                System.out.print("(" + getVertex(edge.u) + ", " + getVertex(edge.v) + ") ");
            }

            System.out.println();
        }

    }

    @Override
    public void clear() {
        this.vertices.clear();
        this.neighbors.clear();
    }

    /**
     * Add a vertex to the graph.
     */
    @Override
    public boolean addVertex(V vertex) {
        if (!this.vertices.contains(vertex)) {
            this.vertices.add(vertex);
            this.neighbors.add(new ArrayList<Edge>());
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     */
    @Override
    public boolean addEdge(int u, int v) {
        return this.addEdge(new Edge(u, v));
    }

    @Override
    public boolean addEdge(Edge edge) {
        if (edge.u < 0 || edge.u > this.getSize() - 1) {
            throw new IllegalArgumentException("No such index: " + edge.u);
        }

        if (edge.v < 0 || edge.v > this.getSize() - 1) {
            throw new IllegalArgumentException("No such index: " + edge.v);
        }

        if (!this.neighbors.get(edge.u).contains(edge)) {
            this.neighbors.get(edge.u).add(edge);
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public boolean remove(V v) {
        int index = this.getIndex(v);
        if (index == -1) {
            return false;
        } else {
            this.vertices.remove(index);
            this.neighbors.remove(index);
            return true;
        }
    }

    @Override
    public boolean remove(int u, int v) {
        if (u < 0 || u > this.getSize() - 1) {
            throw new IllegalArgumentException("No such index: " + u);
        }

        if (v < 0 || v > this.getSize() - 1) {
            throw new IllegalArgumentException("No such index: " + v);
        }

        List<Edge> edges = this.neighbors.get(u);
        return edges.remove(new Edge(u, v));
    }
    
    @Override
    public UnweightedGraph<V>.SearchTree dfs(int v) {
        List<Integer> searchOrder = new ArrayList<Integer>();
        int[] parent = new int[vertices.size()];
        for(int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        
        // Mark visited vertices
        boolean[] isVisited = new boolean[vertices.size()];
        
        // Recursively search
        this.dfs(v, parent, searchOrder, isVisited);
        
        // Return a search tree
        return new SearchTree(v, parent, searchOrder);
    }
    
    /** Recursive method for DFS search */
    private void dfs(int v, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {
        searchOrder.add(v);
        isVisited[v] = true;
        
         
        List<Integer> neighbors = this.getNeighbors(v);
        for(int neighbor : neighbors) {
            if(!isVisited[neighbor]) {
                parent[neighbor] = v;
                dfs(neighbor, parent, searchOrder, isVisited);
            }
        }
    }
    
    @Override
    public UnweightedGraph<V>.SearchTree bfs(int v) {
        List<Integer> searchOrder = new ArrayList<Integer>();
        int[] parent = new int[vertices.size()];
        for(int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        
        // Mark visited vertices
        boolean[] isVisited = new boolean[vertices.size()];
        
        isVisited[v] = true;
        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(v);
        while(!q.isEmpty()) {
            int current = q.poll();
            searchOrder.add(current);
            List<Integer> neighbors = this.getNeighbors(current);
            for(int n : neighbors) {
                if(!isVisited[n]) {
                    isVisited[n] = true;
                    parent[n] = current;
                    q.offer(n);
                }
            }
        }
        
        return new SearchTree(v, parent, searchOrder);
    }

    /**
     * Tree inner class inside the UnweightedGraph class.
     */
    public class SearchTree {
        private final int root; // The root of the tree
        private final int[] parent; // Store the parent of each vertex
        private final List<Integer> searchOrder; // Store the search order

        /** Construct a tree with root, parent, and searchOrder */
        public SearchTree(int root, int[] parent, List<Integer> searchOrder) {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        /** Return the root of the tree */
        public int getRoot() {
            return this.root;
        }

        /** Return the parent of the vertex v */
        public int getParent(int v) {
            return this.parent[v];
        }

        /** Return an array representing search order */
        public List<Integer> getSearchOrder() {
            return this.searchOrder;
        }

        /** Return number of vertices found */
        public int getNumberOfVerticesFound() {
            return this.searchOrder.size();
        }

        /** Return the path of vertices from a vertex to the root */
        public List<V> getPath(int index) {
            List<V> path = new ArrayList<>();

            int currentIndex = index;
            do {
                path.add(vertices.get(currentIndex));
                currentIndex = this.parent[currentIndex];
            } while (currentIndex != -1);

            return path;
        }

        /** Print a path from the root to vertex v */
        public void printPath(int index) {
            List<V> reversePath = this.getPath(index);
            System.out.print("A path from " + vertices.get(root) + " to " + vertices.get(index) + ": ");
            for (int i = reversePath.size() - 1; i >= 0; i--) {
                System.out.print(reversePath.get(i) + " ");
            }
        }

        /** Print the whole tree */
        public void printTree() {
            System.out.println("Root is: " + vertices.get(root));
            System.out.println("Edges: ");
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] != -1) {
                    // Display an edge
                    System.out.print("(" + vertices.get(parent[i]) + ", " + vertices.get(i) + ") ");
                }
            }
            System.out.println();
        }
    }

}

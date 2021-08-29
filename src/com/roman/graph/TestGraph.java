package com.roman.graph;

import java.util.List;

public class TestGraph {
    
    private static String[] vertices = {"Seattle", "San Francisco", "Los Angeles", 
                                        "Denver",  "Kansas City",   "Chicago", 
                                        "Boston",  "New York",      "Atlanta", 
                                        "Miami",   "Dallas",        "Houston"};
    
    private static int[][] edges = {
        {0, 1}, {0, 3}, {0, 5},
        {1, 0}, {1, 2}, {1, 3},
        {2, 1}, {2, 3}, {2, 4}, {2, 10},
        {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
        {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
        {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
        {6, 5}, {6, 7},
        {7, 4}, {7, 5}, {7, 6}, {7, 8}, 
        {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11}, 
        {9, 8}, {9, 11},
        {10, 2}, {10, 4}, {10, 8}, {10, 11}, 
        {11, 8}, {11, 9}, {11, 10},
    };
    
    private static Graph<String> graph = new UnweightedGraph<String>(vertices, edges);
    
    private static void dfs() {
        UnweightedGraph<String>.SearchTree dfs = graph.dfs(graph.getIndex("Chicago"));
        List<Integer> searchOrders = dfs.getSearchOrder();
        System.out.println(dfs.getNumberOfVerticesFound() + " vertices are searched in thi DFS order:");
        for (int i = 0, size = searchOrders.size(); i < size; i++) {
            System.out.print(graph.getVertex(searchOrders.get(i)) + " ");
        }
        System.out.println();

        for (int i = 0, size = searchOrders.size(); i < size; i++) {
            if (dfs.getParent(searchOrders.get(i)) != -1) {
                System.out.println("parent of " + graph.getVertex(searchOrders.get(i)) + " is "
                        + graph.getVertex(dfs.getParent(searchOrders.get(i))));
            }
        }
    }
    
    private static void bfs() {
        UnweightedGraph<String>.SearchTree bfs = graph.bfs(graph.getIndex("Chicago"));
        List<Integer> bSearchOrders = bfs.getSearchOrder();
        System.out.println(bfs.getNumberOfVerticesFound() + " vertices are searched in thi BFS order:");
        for (int i = 0, size = bSearchOrders.size(); i < size; i++) {
            System.out.print(graph.getVertex(bSearchOrders.get(i)) + " ");
        }
        System.out.println();

        for (int i = 0, size = bSearchOrders.size(); i < size; i++) {
            if (bfs.getParent(bSearchOrders.get(i)) != -1) {
                System.out.println("parent of " + graph.getVertex(bSearchOrders.get(i)) + " is "
                        + graph.getVertex(bfs.getParent(bSearchOrders.get(i))));
            }
        }
    }
    
    public static void main(String[] args) {
        dfs();
        System.out.println();
        bfs();
    }
                
}

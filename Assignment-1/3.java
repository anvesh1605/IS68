package DSA_Assignment_01;

import java.util.*;

public class ques3 {
    public static void main(String[] args) {
        int V = 4;
        List<List<Integer>> E = Arrays.asList(
            Arrays.asList(0, 1),
            Arrays.asList(1, 2),
            Arrays.asList(2, 0),
            Arrays.asList(1, 3)
        );
        TarjansAlgo obj = new TarjansAlgo();
        List<List<Integer>> ans = obj.criticalConnections(V, E);
        System.out.println("The critical connections in the given graph are:");
        for (List<Integer> bridge : ans) {
            System.out.println(bridge.get(0) + " " + bridge.get(1));
        }
    }
}

class TarjansAlgo {

    private int timer = 1;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
       //create adjacency list
        List<Integer>[] adj = new ArrayList[n];
        for (int i=0; i<n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (List<Integer> edge : connections) {
            int u=edge.get(0), v=edge.get(1);
            adj[u].add(v);
            adj[v].add(u);
        }

        int[] vis = new int[n];
        int[] tin = new int[n]; //time of insertion
        int[] lowest = new int[n]; //lowest time of insertion achievable 

        List<List<Integer>> bridges = new ArrayList<>();
        dfs(0, -1, adj, vis, tin, lowest, bridges);
        return bridges;
    }

    private void dfs(int node, int parent, List<Integer>[] adj, int[] vis,
                     int[] tin, int[] lowest, List<List<Integer>> bridges) {
        
        vis[node] = 1; 
        tin[node] = lowest[node] = timer;
        timer++;

        for (int neighbor : adj[node]){
            if (neighbor == parent) continue;

            if (vis[neighbor] == 0){ // unvisited neighboring node
                dfs(neighbor, node, adj, vis, tin, lowest, bridges);
                lowest[node] = Math.min(lowest[node], lowest[neighbor]);
                if (tin[node] < lowest[neighbor]){ 
                    bridges.add(Arrays.asList(neighbor, node));
                }
            }else{ // alr visited neighboring node
                lowest[node] = Math.min(lowest[node], tin[neighbor]);
            }
        }
    }


    
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.projekt.graph;
/**
 *
 * @author Can
 */

public class Properties {
    
    
    private static int nodes;
    private static int max_capacity;
    private static int instances;
    private static String algorithm = "";

    public static void setNodes(int new_nodes) {
        nodes = new_nodes;
    }

    public static void setMax_capacity(int new_max_capacity) {
        max_capacity = new_max_capacity;
    }

    public static void setInstances(int new_instances) {
        instances = new_instances;
    }

    public static int getNodes() {
        return nodes;
    }

    public static int getMax_capacity() {
        return max_capacity;
    }

    public static int getInstances() {
        return instances;
    }
    
    public static void setAlgorithm(String new_algorithm) {
        switch (new_algorithm) {
            case "Ford-Fulkerson":
                algorithm = "FORD_FULK";
                break;
            case "Edmonds-Karp":
                algorithm = "ED_KARP";
                break;
            case "Dinic":
                algorithm = "DINIC";
                break;
            case "Goldberg-Tarjan":
                algorithm = "GOLD_TAR";
                break;
        }        
    }
    
    public static String getAlgorithm() {
        return algorithm;
    }
}

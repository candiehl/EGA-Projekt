/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.project.graph;
/**
 *
 * @author Can
 */

enum Algorithm{
    FORD_FULK, ED_KARP, DINIC, GOLD_TAR
};
public class Properties {
    
    
    private static int nodes;
    private static int max_capacity;
    private static int instances;
    private static Algorithm algorithm;
    

    public Properties() {
        this.nodes = 0;
        this.max_capacity = 0;
        this.instances = 0;
    }

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
                algorithm = Algorithm.FORD_FULK;
                break;
            case "Edmonds-Karp":
                algorithm = Algorithm.ED_KARP;
                break;
            case "Dinic":
                algorithm = Algorithm.DINIC;
                break;
            case "Goldberg-Tarjan":
                algorithm = Algorithm.GOLD_TAR;
                break;
        }        
    }
    
    public static Algorithm getAlgorithm() {
        return algorithm;
    }
}

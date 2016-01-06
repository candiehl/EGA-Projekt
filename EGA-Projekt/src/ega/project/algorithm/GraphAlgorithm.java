/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.project.algorithm;

import ega.project.utility.Protocoller;
import ega.projekt.graph.Graph;

/**
 *
 * @author Can
 */
public class GraphAlgorithm {
    
    private Graph graph;
    private Protocoller protocoller;

    public Graph getGraph() {
        return graph;
    }
    
    public GraphAlgorithm(Graph init_graph) {
        graph = init_graph;
    }
    
    public void initialize(){
    }
    
    public boolean iteration(){
        if(!break_condition()){
            //Do Something
            return false;
        }
        //Else return true
        return true;
    }
    
    private boolean break_condition(){
        //Checks if Algorithm terminates
        return false;
    }
}

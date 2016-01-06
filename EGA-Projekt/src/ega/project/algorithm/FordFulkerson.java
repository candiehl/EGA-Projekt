/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.project.algorithm;

import ega.projekt.graph.Graph;

/**
 *
 * @author Can
 */
public class FordFulkerson extends GraphAlgorithm{
    
    public FordFulkerson(Graph init_graph) {
        super(init_graph);
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

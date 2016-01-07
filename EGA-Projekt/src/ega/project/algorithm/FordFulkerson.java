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
public class FordFulkerson implements GraphAlgorithm{
    
    Graph graph;
    
    public FordFulkerson(Graph rnd_graph) {
        graph = rnd_graph;
    }

    @Override
    public Graph getGraph() {
        return graph;
    }

    @Override
    public void initialize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean iteration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean break_condition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

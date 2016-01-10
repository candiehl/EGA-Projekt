/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.project.algorithm;

import ega.projekt.graph.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Can
 */
public class FordFulkerson implements GraphAlgorithm{
    
    Graph graph;
    Node start;
    Node end;
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    
    public FordFulkerson(Graph rnd_graph) {
        graph = rnd_graph;
        initialize();
    }

    public Graph getGraph() {
        return graph;
    }

    public void initialize() {
        ArrayList<Node> nodes = graph.getNodes();
        start = nodes.get(0);
        end = nodes.get(nodes.size()-1);
        System.out.println(start.getID());
        System.out.println(end.getID());
        for(int i=0; i< nodes.size(); i++){
            map.put(nodes.get(i).getID(), 0);
        }
    }

    public boolean iteration() {
        Node curNode = start;
        int min_cap = Integer.MAX_VALUE;
        while(curNode != end){
            int edgeCount = map.get(curNode.getID());
            Edge outEdge = start.getOutEdge(edgeCount);
            map.put(curNode.getID(), (edgeCount+1));
            int cap = outEdge.getCapacity();
            if(cap < min_cap)
                min_cap = cap;
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean break_condition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

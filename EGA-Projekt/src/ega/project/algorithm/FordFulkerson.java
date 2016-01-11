/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.project.algorithm;

import ega.projekt.graph.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

/**
 *
 * @author Can
 */
public class FordFulkerson implements GraphAlgorithm{
    
    Graph graph;
    Node start;
    Node end;
    Node curNode;
    HashMap<Integer,Integer> map = new HashMap<>();
    Deque<Node> stack = new ArrayDeque<>();
    
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
        for(int i=0; i< nodes.size(); i++){
            map.put(nodes.get(i).getID(), 0);
        }
        //Initialize stack
        curNode = start;
        curNode.setSeen(true);
        stack.push(curNode);
    }

    public boolean iteration() {
        int min_cap = Integer.MAX_VALUE;
        while(!stack.isEmpty()){
            int edgeCount = map.get(curNode.getID());
            System.out.println(curNode.getID());
            //If edges are still available
            if(curNode.getOutEdges().size() > edgeCount){
                Edge outEdge = curNode.getOutEdge(edgeCount);
                map.put(curNode.getID(), (edgeCount+1));
                curNode = outEdge.getToNode();
                if (!curNode.isSeen()){
                    curNode.setSeen(true);
                    stack.push(curNode);
                }
                int cap = outEdge.getCapacity();
                if(cap < min_cap)
                    min_cap = cap;
            }
            else {
                curNode.isFinished();
                stack.pop();
            }
        }
        return true;
    }

    public boolean break_condition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

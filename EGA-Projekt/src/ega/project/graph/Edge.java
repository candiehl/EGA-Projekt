/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.project.graph;

import java.util.Random;

/**
 *
 * @author Mike Demele
 */
public class Edge {
    private Node from, to;
    private int capacity;
    
    public Edge(Node from, Node to, int capacity){
        this.from=from;
        this.to=to;
        this.capacity=capacity;
    }

    public Node getFromNode(){
        return from;
    }
    
    public Node getToNode(){
        return to;
    }
    
    public int getCapacity(){
        return capacity;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.projekt.graph;

/**
 *
 * @author Mike Demele
 */
public class Edge {
    private static int idCounter=0;
    private final int id;
    private final Node from, to;
    private int capacity;
    
    public Edge(Node from, Node to, int capacity){
        this.id=fetchID();
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
    
    public void setCapacity(int newCapacity){
        this.capacity=newCapacity;
    }
    
    private int fetchID(){
        idCounter++;
        return idCounter;
    }
    
    public int getID(){
        return this.id;
    }
}

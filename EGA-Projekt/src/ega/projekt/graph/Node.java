/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.projekt.graph;

import java.util.ArrayList;

/**
 *
 * @author Mike Demele
 */
public class Node {
    private final int xCoord,yCoord;
    private final int id;
    private static int idCounter=0;
    private ArrayList<Edge> inEdges;
    private ArrayList<Edge> outEdges;
        
    public Node(int x, int y, ArrayList<Edge> inEdges, ArrayList<Edge> outEdges){
        this.id=fetchID();
        this.inEdges=new ArrayList<>();
        this.outEdges=new ArrayList<>();
        this.xCoord=x;
        this.yCoord=y;
    }
    
    public Node(int x, int y){
        this.id=fetchID();
        this.inEdges=new ArrayList<>();
        this.outEdges=new ArrayList<>();
        this.xCoord=x;
        this.yCoord=y;
    }
    
    public int getID(){
        return this.id;
    }
    
    public int getX(){
        return this.xCoord;
    }
    
    public int getY(){
        return this.yCoord;
    }
    
    public void addOutEdge(Node to, int capacity){
        this.outEdges.add(new Edge(this,to, capacity));
    }
    
    public void addInEdge(Node from, int capacity){
        this.inEdges.add(new Edge(from,this, capacity));
    }
    
    public void addEdge(Node to, int capacity){
        this.addOutEdge(to,capacity);
        this.addInEdge(to, capacity);
        to.addInEdge(this, capacity);
        to.addOutEdge(this, capacity);
    }
    
    public ArrayList<Edge> getAllEdges(){
        ArrayList<Edge> edges=new ArrayList<>();
        int size=this.inEdges.size()+this.outEdges.size();
        edges.ensureCapacity(size);
        edges.addAll(inEdges);
        edges.addAll(outEdges);
        return edges;
    }
    
    public ArrayList<Edge> getInEdges(){
        return this.inEdges;
    }
    
    public ArrayList<Edge> getOutEdges(){
        return this.outEdges;
    }
    
    public Edge getInEdge(int num){
        return this.inEdges.get(num);
    }
    
    public Edge getOutEdges(int num){
        return this.outEdges.get(num);
    }
    
    public boolean inside(Node a,Node b,Node c){
        //calculate circle around triangle
        float yDelta_a = b.getY() - a.getY();
        float xDelta_a = b.getX() - a.getX();
        float yDelta_b = c.getY() - b.getY();
        float xDelta_b = c.getX() - b.getX();
        float aSlope = yDelta_a/xDelta_a;
        float bSlope = yDelta_b/xDelta_b;
        float centerX = (aSlope*bSlope*(a.getY() - c.getY()) + bSlope*(a.getX()
                + b.getX())- aSlope*(b.getX()+c.getX()) )/(2* (bSlope-aSlope) );
        float centerY = -1*(centerX - (a.getX()+b.getX())/2)/aSlope + (a.getY()
                + b.getY())/2;
        
        float xd = a.getX()-centerX;
	float yd = a.getY()-centerY;
	double radius =(float)Math.sqrt(xd*xd + yd*yd);
        
        //calculate distance of node position to circle center
        xd = this.getX()-centerX;
	yd = this.getY()-centerY;
        double distance = (float)Math.sqrt(xd*xd + yd*yd);
        
        return distance >radius;
    }
    
    private int fetchID(){
        idCounter++;
        return idCounter;
    }
    
    
    public String toString(){
        String result=Integer.toString(this.id);
        return result;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.project.graph;

import java.util.ArrayList;

/**
 *
 * @author Mike Demele
 */
public class Node {
    private final int xCoord,yCoord;
    private ArrayList<Edge> edges;
    
    public Node(int x, int y, ArrayList<Edge> edges){
        this.edges=edges;
        this.xCoord=x;
        this.yCoord=y;
    }
    
    public Node(int x, int y){
        this.edges.clear();
        this.xCoord=x;
        this.yCoord=y;
    }
    
    public int getX(){
        return this.xCoord;
    }
    
    public int getY(){
        return this.yCoord;
    }
    
    public void addEdge(Node to, int capacity){
        this.edges.add(new Edge(this,to, capacity));
    }
    
    public ArrayList<Edge> getEdges(){
        return this.edges;
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
}

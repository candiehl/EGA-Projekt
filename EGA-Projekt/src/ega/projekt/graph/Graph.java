/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.projekt.graph;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Mike Demele
 */
public class Graph {    
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private static final int MAX_WIDTH=300;
    private static final int MAX_HEIGHT=200;
    
    public ArrayList<Node> getNodes(){
        return this.nodes;
    }
    
    public Node getNode(int num){
        return this.nodes.get(num);
    }
    
    public ArrayList<Edge> getEdges(){
        return this.edges;
    }
    
    public Edge getEdge(int num){
        return this.edges.get(num);
    }
    
    public Graph(int numNodes, int maxCapacity){
        Random rand=new Random();   
        this.nodes=new ArrayList<>();
        nodes.ensureCapacity(numNodes);
        this.edges=new ArrayList<>();
        for(int i=0;i<numNodes;i++){
            
            int x,y;
            x=rand.nextInt(MAX_WIDTH);
            y=rand.nextInt(MAX_HEIGHT);
            for(int j=0;j<i;j++){
                while(this.nodes.get(j).getX()==x && this.nodes.get(j).getY()==y){
                    x=rand.nextInt(MAX_WIDTH);
                    y=rand.nextInt(MAX_HEIGHT);
                }
            }
            this.nodes.add(i, new Node(x,y));
        }
        //get edges via Delauney Triangulation
        for (int i = 0; i < numNodes; i++) {
            for (int j = i+1; j < numNodes; j++) {
                for (int k = j+1; k < numNodes; k++) {
                    boolean isTriangle = true;
                    for (int a = 0; a < numNodes; a++) {
                        if (a == i || a == j || a == k) continue;
                        if (this.nodes.get(a).inside(this.nodes.get(i),
                                this.nodes.get(j), this.nodes.get(k))) {
                           isTriangle = false;
                           break;
                        }
                    }
                    if (isTriangle) {
                        //new triangle so add edges, if not already present
                        if(!this.hasEdge(i,j)){
                        this.nodes.get(i).addEdge(this.nodes.get(j),
                                rand.nextInt(maxCapacity));
                        }
                        if(!this.hasEdge(i,k)){
                            this.nodes.get(i).addEdge(this.nodes.get(k),
                                rand.nextInt(maxCapacity));
                        }
                        if(!this.hasEdge(j,k)){
                            this.nodes.get(j).addEdge(this.nodes.get(k),
                                rand.nextInt(maxCapacity));
                        }
                    }
                }
            }
        }
        for(int i=0;i<this.nodes.size();i++){
            this.edges.addAll(nodes.get(i).getAllEdges());
        }
    }
    
    public boolean hasEdge(int from,int to){
        for(Edge curEdge : this.edges){
            if(curEdge.getFromNode().equals(this.nodes.get(from))){
                if(curEdge.getToNode().equals(this.nodes.get(to)))
                return true;
            }
        }
        return false;
    }
    
    public String toString(){
        String result=new String();
        return result;
    }
}

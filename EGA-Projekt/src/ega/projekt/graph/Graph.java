/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.projekt.graph;

import ega.projekt.graph.triangulation.Delaunay;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Mike Demele
 */
public class Graph {    
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private static final int MAX_WIDTH=1000;
    private static final int MAX_HEIGHT=1000;
    
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
    
    public Graph(int numNodes,int maxCapacity){
        this(numNodes,maxCapacity,Graph.MAX_WIDTH,Graph.MAX_HEIGHT);
    }
    
    public Graph(int numNodes, int maxCapacity, int maxWidth, int maxHeight){
        Random rand=new Random();   
        this.nodes=new ArrayList<>();
        nodes.ensureCapacity(numNodes);
        this.edges=new ArrayList<>();
        for(int i=0;i<numNodes;i++){
            double x,y;
            x=rand.nextDouble()*maxWidth;
            y=rand.nextDouble()*maxHeight;
            //avoid that nodes are to close
            for(int j=0;j<i;j++){
                final int DRAW_OFFSET=50;
                while(this.nodes.get(i-1).distance(new Node(x,y))<DRAW_OFFSET){
                    x=rand.nextDouble()*maxWidth;
                    y=rand.nextDouble()*maxHeight;
                }
            }
            this.nodes.add(new Node(x,y));
        }
        //get edges via Delauney Triangulation
        Node[] triNodes = new Node[numNodes];
        this.nodes.toArray(triNodes);
        Delaunay triangulator=new Delaunay(triNodes);
        triangulator.compute();
        //add edges to graph and nodes
        for(int i=0;i<triangulator.delEdges.size();i++){
            Node from=triangulator.delEdges.get(i).orig();
            Node to=triangulator.delEdges.get(i).dest();
            int capacity=(rand.nextInt(maxCapacity-1)+1);
            this.edges.add(new Edge(from, to, capacity));
            from.addOutEdge(to,capacity);
            to.addInEdge(from,capacity);
            rand.nextInt(maxCapacity);
            this.edges.add(new Edge(to, from, rand.nextInt(maxCapacity)));
            to.addOutEdge(from,capacity);
            from.addInEdge(to,capacity);
        }
    }
}
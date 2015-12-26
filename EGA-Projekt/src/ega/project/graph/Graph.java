/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.project.graph;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Mike Demele
 */
public class Graph {    
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    
    public Graph(int numNodes, int maxCapacity){
        
        this.nodes.ensureCapacity(numNodes);
        for(int i=0;i<numNodes;i++){
            Random rand=new Random();
            int x,y;
            x=rand.nextInt(maxCapacity);
            y=rand.nextInt(maxCapacity);
            for(int j=0;j<i;j++){
                while(this.nodes.get(j).getX()==x && this.nodes.get(j).getY()==y){
                    x=rand.nextInt(maxCapacity);
                    y=rand.nextInt(maxCapacity);
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
                        Random rand=new Random();
                        
                        //new triangle so add forward and backwards edges
                        this.nodes.get(i).addEdge(this.nodes.get(j),
                                rand.nextInt(maxCapacity));
                        this.nodes.get(j).addEdge(this.nodes.get(i),
                                rand.nextInt(maxCapacity));
                        
                        this.nodes.get(i).addEdge(this.nodes.get(k),
                                rand.nextInt(maxCapacity));
                        this.nodes.get(k).addEdge(this.nodes.get(i),
                                rand.nextInt(maxCapacity));
                        
                        this.nodes.get(j).addEdge(this.nodes.get(k),
                                rand.nextInt(maxCapacity));
                        this.nodes.get(k).addEdge(this.nodes.get(j),
                                rand.nextInt(maxCapacity));
                    }
                }
            }
        }
        for(int i=0;i<this.nodes.size();i++){
            this.edges.addAll(nodes.get(i).getEdges());
        }
    }
    
    
}

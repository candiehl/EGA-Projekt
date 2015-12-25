/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.project.graph;

/**
 *
 * @author Can
 */
public class Properties {
    private int nodes;
    private int max_capacity;
    private int instances;

    public Properties() {
        this.nodes = 0;
        this.max_capacity = 0;
        this.instances = 0;
    }

    public void setNodes(int nodes) {
        this.nodes = nodes;
    }

    public void setMax_capacity(int max_capacity) {
        this.max_capacity = max_capacity;
    }

    public void setInstances(int instances) {
        this.instances = instances;
    }

    public int getNodes() {
        return nodes;
    }

    public int getMax_capacity() {
        return max_capacity;
    }

    public int getInstances() {
        return instances;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.projekt.graph.triangulation;

/**
 *
 * @author Mike Demele
 */
public class HalfEdge {

    /**  */
    public HalfEdge elLeft;

    /**  */
    public HalfEdge elRight;

    /**  */
    public VorEdge elEdge;

    /**  */
    public boolean deleted;

    /**  */
    public int elPm;

    /**  */
    public Site vertex;

    /**  */
    public double ystar;

    /** the next half-edge in the hash table */
    public HalfEdge pqNext;

    /**
     * Constructs a new <code>Halfedge</code>.
     */
    public HalfEdge() {

        this.pqNext = null;
    }

    /**
     * Constructs a new <code>Halfedge</code>.
     *
     * @param  edge  ???
     * @param  pm    ???
     */
    public HalfEdge(final VorEdge edge, final int pm) {

        this();

        this.elEdge = edge;
        this.elPm = pm;
        this.vertex = null;
    }

}

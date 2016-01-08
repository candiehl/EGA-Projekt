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
    private final double xCoord,yCoord;
    private final int id;
    private static int idCounter=0;
    private ArrayList<Edge> inEdges;
    private ArrayList<Edge> outEdges;
        
    public Node(double x, double y, ArrayList<Edge> inEdges, ArrayList<Edge> outEdges){
        this.id=fetchID();
        this.inEdges=new ArrayList<>();
        this.outEdges=new ArrayList<>();
        this.xCoord=x;
        this.yCoord=y;
        this.isFake=false;
    }
    
    public Node(double x, double y, boolean fake){
        this.id=fetchID();
        this.inEdges=new ArrayList<>();
        this.outEdges=new ArrayList<>();
        this.xCoord=x;
        this.yCoord=y;
        this.isFake=fake;
    }
    
    public Node(double x, double y){
        this.id=fetchID();
        this.inEdges=new ArrayList<>();
        this.outEdges=new ArrayList<>();
        this.xCoord=x;
        this.yCoord=y;
        this.isFake=false;
    }
    
    public int getID(){
        return this.id;
    }
    
    public double getX(){
        return this.xCoord;
    }
    
    public double getY(){
        return this.yCoord;
    }
    
    public void addOutEdge(Node to, int capacity){
        this.outEdges.add(new Edge(this,to, capacity));
    }
    
    public void addInEdge(Node from, int capacity){
        this.inEdges.add(new Edge(from,this, capacity));
    }
    
    public void addEdge(Edge e){
        if(e.getFromNode().equals(this)){
            this.addOutEdge(e.getToNode(), e.getCapacity());
        }else if(e.getToNode().equals(this)){
            this.addInEdge(e.getFromNode(), e.getCapacity());
        }
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
    
    public Edge getOutEdge(int num){
        return this.outEdges.get(num);
    }

    private int fetchID(){
        idCounter++;
        return idCounter;
    }

    //////////// ONLY FOR TRIANGULATION /////////////////////
    
    private final boolean isFake;
    
    /** distance within which we consider a node to fall on an edge */
    public static final double EPSILON = 1e-6;

    /**
     * Returns the square of the distance of this node from the origin.
     *
     * @return  the distance from the origin squared
     */
    public double lengthSquared() {

        return (this.xCoord * this.xCoord) + (this.yCoord * this.yCoord);
    }

    /**
     * Tests whether a point lies to the right of an edge.
     *
     * @param   edge  the edge
     * @return  <code>true</code> if the point lies to the right of the edge; <code>false</code>
     *          otherwise
     */
    public boolean isRightOf(final Edge edge) {

        return isCcw(this, edge.dest(), edge.orig());
    }

    /**
     * Tests whether a point lies to the left of an edge.
     *
     * @param   edge  the edge
     * @return  <code>true</code> if the point lies to the left of the edge; <code>false</code>
     *          otherwise
     */
    public boolean isLeftOf(final Edge edge) {

        return isCcw(this, edge.orig(), edge.dest());
    }

    /**
     * Tests whether this point lies on an edge (within some small epsilon).
     *
     * @param   edge  the edge
     * @return  <code>true</code> if the point lies on the edge; <code>false</code> otherwise
     */
    public boolean isOnEdge(final Edge edge) {

        Node orig;
        Node dest;
        double tx;
        double ty;
        double len;
        double lineA;
        double lineB;
        double lineC;
        boolean onEdge;

        orig = edge.orig();
        dest = edge.dest();

        tx = dest.xCoord - orig.xCoord;
        ty = dest.yCoord - orig.yCoord;
        len = Math.sqrt((tx * tx) + (ty * ty));

        lineA = ty / len;
        lineB = -tx / len;
        lineC = -((lineA * orig.xCoord) + (lineB * orig.yCoord));

        onEdge = Math.abs((lineA * this.xCoord) + (lineB * this.yCoord) + lineC) < EPSILON;

        return onEdge;
    }

    /**
     * Tests whether this point lies within the circle circumscribing a triangle.
     *
     * @param   pt1  the first point defining the triangle
     * @param   pt2  the second point defining the triangle
     * @param   pt3  the third point defining the triangle
     * @return  <code>true</code> if this point is inside the circumscribing circle; <code>
     *          false</code> if not
     */
    public boolean isInCircle(final Node pt1, final Node pt2, final Node pt3) {

        return ((pt1.lengthSquared() * triArea(pt2, pt3, this))
                - (pt2.lengthSquared() * triArea(pt1, pt3, this))
                + (pt3.lengthSquared() * triArea(pt1, pt2, this))
                - (this.lengthSquared() * triArea(pt1, pt2, pt3))) > 0;
    }

    /**
     * Computes twice the area of the oriented triangle <code>(pt1, pt2, pt3)</code> (the area is
     * positive if the triangle is oriented counterclockwise).
     *
     * @param   pt1  the first point
     * @param   pt2  the second point
     * @param   pt3  the third point
     * @return  twice the oriented area
     */
    public static double triArea(final Node pt1, final Node pt2, final Node pt3) {

        return ((pt2.xCoord - pt1.xCoord) * (pt3.yCoord - pt1.yCoord))
            - ((pt2.yCoord - pt1.yCoord) * (pt3.xCoord - pt1.xCoord));
    }

    /**
     * Tests whether the points of a triangle are in counterclockwise order.
     *
     * @param   pt1  the first point
     * @param   pt2  the second point
     * @param   pt3  the third point
     * @return  <code>true</code> if triangle (pt1, pt2, pt3) is in counterclockwise order
     */
    public static boolean isCcw(final Node pt1, final Node pt2, final Node pt3) {

        return triArea(pt1, pt2, pt3) > 0;
    }

    /**
     * Creates a node that lies at the center of the circle that circumscribes a triangle.
     *
     * @param   pt1  the first node of the triangle
     * @param   pt2  the second node of the triangle
     * @param   pt3  the third node of the triangle
     * @return  the node at the circumcenter
     */
    public static Node circumcenter(final Node pt1, final Node pt2, final Node pt3) {

        double len1Sq;
        double len2Sq;
        double len3Sq;
        double denom;
        double xNumer;
        double yNumer;
        Node center;

        len1Sq = pt1.lengthSquared();
        len2Sq = pt2.lengthSquared();
        len3Sq = pt3.lengthSquared();

        denom = 2
            * ((pt1.xCoord * (pt2.yCoord - pt3.yCoord)) + (pt2.xCoord * (pt3.yCoord - pt1.yCoord))
                + (pt3.xCoord * (pt1.yCoord - pt2.yCoord)));

        xNumer = (len1Sq * (pt2.yCoord - pt3.yCoord)) + (len2Sq * (pt3.yCoord - pt1.yCoord))
            + (len3Sq * (pt1.yCoord - pt2.yCoord));
        yNumer = (len1Sq * (pt3.xCoord - pt2.xCoord)) + (len2Sq * (pt1.xCoord - pt3.xCoord))
            + (len3Sq * (pt2.xCoord - pt1.xCoord));

        center = new Node(xNumer / denom, yNumer / denom);

        return center;
    }
    
    public boolean isFake(){
        return this.isFake;
    }
    
    public double distance(Node other){
        double x=this.xCoord-other.getX();
        double y=this.yCoord-other.getY();
        return x*x+y*y;
    }
}

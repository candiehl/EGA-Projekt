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

import ega.projekt.graph.Edge;
import ega.projekt.graph.Node;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * A subdivision of the plane by edges to form a Delaunay triangulation.
 */
public class Delaunay{

    /** the number of points to generate in the random set */
    private static final int NUM_POINTS = 250;

    /** a visualizer that will display results as triangulation proceeds */
    //private final DelaunayVisualizerInt visualizer;

    /** the original list of points */
    private final Node[] origPoints;

    /** a starting edge for the subdivision */
    private Edge startingEdge;

    /** the list of all edges in the triangulation */
    public final List<Edge> delEdges;

    /** the list of all edges in the Voronoi diagram */
    public final List<GraphEdge> vorEdges;

    /** a map from node to the Voronoi region containing it */
    public Map<Node, Path2D> vorMap;

    public Delaunay(final Node[] points) {

        double max;
        double xPos;
        double yPos;

        max = 0;

        for (int i = 0; i < points.length; i++) {
            xPos = points[i].getX();
            yPos = points[i].getY();

            if (xPos > max) {
                max = xPos;
            }

            if (yPos > max) {
                max = yPos;
            }
        }

        this.origPoints = points.clone();
        this.delEdges = new ArrayList<>(points.length + 10);
        this.vorEdges = new ArrayList<>(points.length + 10);

        // We mark these three points as with asPoint = false so we can
        // recognize which points belong to this surrounding triangle later
        // Note: we use 3.1 rather than 3.0 since 3.0 admits the possibility
        // that a point falls on the edge of the surrounding triangle, and
        // we can have all points inside at no cost.
        set(new Node(3000 * max, 0, true), new Node(0, 3000 * max, true),
            new Node(-3000 * max, -3000 * max, true));
    }

    /**
     * Initializes the subdivision to contain a single triangle formed by <code>point1</code>,
     * <code>point2</code>, and <code>point3</code>.
     *
     * @param  point1  the first point
     * @param  point2  the second point
     * @param  point3  the third point
     */
    private void set(final Node point1, final Node point2, final Node point3) {

        Edge ea;
        Edge eb;
        Edge ec;

        ea = new QuadEdge().edge;
        ea.setEndPoints(point1, point2);

        eb = new QuadEdge().edge;
        Edge.splice(ea.sym(), eb);
        eb.setEndPoints(point2, point3);

        ec = new QuadEdge().edge;
        Edge.splice(eb.sym(), ec);
        ec.setEndPoints(point3, point1);

        Edge.splice(ec.sym(), ea);
        this.startingEdge = ea;

        this.delEdges.add(ea);
        this.delEdges.add(eb);
        this.delEdges.add(ec);
    }

    /**
     * Computes the delaunay triangulation of the points used when constructing the subdivision.
     */
    public void compute() {

        Edge edge;
        Voronoi vor;

        for (Node node : this.origPoints) {
            this.insertSite(node);
        }

        // Remove all edges that touch a bogus node
        for (int i = this.delEdges.size() - 1; i >= 0; i--) {
            edge = this.delEdges.get(i);

            if (edge.orig().isFake() || edge.dest().isFake()) {
                edge.delete();
                this.delEdges.remove(i);
            }
        }
        vor = new Voronoi(1e-6);
//        this.vorEdges.addAll(vor.generateVoronoi(this.origPoints));
        this.vorMap = vor.makeVoronoiPolygons(this.origPoints, this.vorEdges);

    }

    /**
     * Inserts a new node into a subdivision representing a Delaunay triangulation, and fixes the
     * affected edges so the result is still a Delaunay triangulation.
     *
     * @param  node  the node to insert
     */
    public void insertSite(final Node node) {

        Edge edge;
        Edge base;
        Edge test;
        Node first;

        edge = locate(node);

        if ((node != edge.orig()) && (node != edge.dest())) {

            if (node.isOnEdge(edge)) {
                test = edge.oPrev();
                edge.delete();
                this.delEdges.remove(edge);
                edge = test;
            }

            // Connect the new point to the nodeices of the containing triangle
            // (or quadrilateral, if the new point fell on an existing edge)
            base = new QuadEdge().edge;
            this.delEdges.add(base);
            first = edge.orig();
            base.setEndPoints(first, node);
            Edge.splice(base, edge);

            base = edge.connect(base.sym());
            this.delEdges.add(base);
            edge = base.oPrev();

            while (edge.dest() != first) {
                base = edge.connect(base.sym());
                this.delEdges.add(base);
                edge = base.oPrev();
            }

            // Examine suspect edges to ensure that the Delaunay condition is
            // satisfied
            for (;;) {
                test = edge.oPrev();

                if (test.dest().isRightOf(edge)
                        && node.isInCircle(edge.orig(), test.dest(), edge.dest())) {
                    edge.swap();

                    // edge = test; // Documented bug
                    edge = edge.oPrev();
                } else if (edge.orig() == first) {
                    break;
                }

                edge = edge.oNext().lPrev();
            }
        }
    }

    /**
     * Returns an edge <code>e</code> such that either <code>point</code> is on <code>e</code> or
     * <code>e</code> is an edge of a triangle containing <code>point</code>. The search starts
     * from <code>startingEdge</code> and proceeds in the general direction of <code>point</code>.
     *
     * @param   point  the point
     * @return  the edge
     */
    public Edge locate(final Node point) {

        Edge edge;

        edge = this.startingEdge;

        for (;;) {

            if ((point == edge.orig()) || (point == edge.dest())) {
                break;
            }

            if (point.isRightOf(edge)) {
                edge = edge.sym();
            } else if (point.isLeftOf(edge.oNext())) {
                edge = edge.oNext();
            } else if (point.isLeftOf(edge.dPrev())) {
                edge = edge.dPrev();
            } else {
                break;
            }
        }

        return edge;
    }

    /**
     * Main method that generates a random set of points, builds a visualizer and a Delaunay
     * triangulator, then steps through the process asking the user for a button press after each
     * step to move on.
     *
     * @param  args  command-line arguments
     */
    public static void main(final String... args) {

        Random rand;
        Node[] points;
        Delaunay triangulator;
        double xPos;
        double yPos;

        rand = new Random(System.currentTimeMillis());
        points = new Node[NUM_POINTS];

        for (int i = 0; i < NUM_POINTS; i++) {
            xPos = (rand.nextDouble() - 0.5);
            yPos = (rand.nextDouble() - 0.5);
            points[i] = new Node(xPos, yPos); // NOPMD SRB
        }

        try {
            triangulator = new Delaunay(points);
            triangulator.compute();
        } catch (Exception e) {
            System.err.println("tiangulation failed!");
        }
    }
}

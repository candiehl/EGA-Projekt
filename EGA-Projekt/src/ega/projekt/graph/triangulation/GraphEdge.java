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

/**
 * An edge in the Voronoi graph.
 */
public class GraphEdge {

    /** The start X coordinate */
    public double xPos1;

    /** The start Y coordinate */
    public double yPos1;

    /** The end X coordinate */
    public double xPos2;

    /** The endY coordinate */
    public double yPos2;

    /** the index of the site to the left of the edge */
    public int site1;

    /** the index of the site to the right of the edge */
    public int site2;
}


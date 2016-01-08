/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.projekt.graph.triangulation;

import ega.projekt.graph.Node;

/**
 *
 * @author Mike Demele
 */
/**
 * A site or vertex in the Voronoi diagram.
 */
public class Site extends Node implements Comparable<Site> {

    /** the index of the site */
    public int sitenbr;

    /**
     * Constructs a new <code>Site</code>.
     *
     * @param  xCoord  the X coordinate
     * @param  yCoord  the Y coordinate
     * @param  num     the site number
     */
    public Site(final double xCoord, final double yCoord, final int num) {

        super(xCoord, yCoord);

        this.sitenbr = num;
    }

    /**
     * Compares this object with the specified object for order. Returns a negative integer, zero,
     * or a positive integer as this object is less than, equal to, or greater than the specified
     * object.
     *
     * @param   obj  the object to be compared
     * @return  a negative integer, zero, or a positive integer as this object is less than, equal
     *          to, or greater than the specified object.
     */
    public int compareTo(final Site obj) {

        int result;

        if (this.getY() < obj.getY()) {
            result = -1;
        } else if (this.getY() > obj.getY()) {
            result = 1;
        } else if (this.getX() < obj.getX()) {
            result = -1;
        } else if (this.getX() > obj.getX()) {
            result = 1;
        } else {
            result = 0;
        }

        return result;
    }

    /**
     * Tests this object for equality with another object. To be equal, the other object must be a
     * site, and must have the same X and Y coordinates. The site number is not used in comparison.
     *
     * @param  obj  the object to compare
     */
    @Override public boolean equals(final Object obj) {

        Site site;
        boolean equal;

        if (obj instanceof Site) {
            site = (Site) obj;
            equal = (site.getX() == this.getX()) && (site.getY() == this.getY());
        } else {
            equal = false;
        }

        return equal;
    }

    /**
     * Generates the hash code of the site.
     *
     * @return  the hash code
     */
    @Override public int hashCode() {

        return (int) Double.doubleToLongBits(this.getX() + this.getY());
    }
}

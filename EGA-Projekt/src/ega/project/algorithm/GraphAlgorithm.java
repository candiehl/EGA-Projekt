/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.project.algorithm;

import ega.project.utility.Protocoller;
import ega.projekt.graph.Graph;

/**
 *
 * @author Can
 */
public interface GraphAlgorithm {
    Graph graph = null;
    Protocoller protocoller = null;
    
    Graph getGraph();
    void initialize();
    boolean iteration();
    boolean break_condition();
}

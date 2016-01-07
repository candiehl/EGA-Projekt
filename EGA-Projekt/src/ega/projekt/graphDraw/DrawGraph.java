/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.projekt.graphDraw;

import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import javax.swing.JFrame;
import org.apache.commons.collections15.Transformer;

import ega.projekt.graph.*; 
import java.awt.geom.Point2D.Double;

/**
 *
 * @author Mike Demele
 */
public class DrawGraph {
    public Graph<Node, Edge> drawGraph;
    private Graph<Integer,String> g; //for testing purposes via default constructor, will be deleted later on
    /** Creates a new instance of SimpleGraphView */
    public DrawGraph(ega.projekt.graph.Graph graph) {
       drawGraph = new DirectedSparseMultigraph<>();
        for(Node node : graph.getNodes()){
            drawGraph.addVertex(node);
            
        }
        for(Edge edge : graph.getEdges()){
            drawGraph.addEdge(edge,edge.getFromNode(), edge.getToNode());
        }
    }
    //default constructor to test integration, will be deleted later on
    public DrawGraph() {
        // Graph<V, E> where V is the type of the vertices and E is the type of the edges
        // Note showing the use of a SparseGraph rather than a SparseMultigraph
        g = new SparseGraph<Integer, String>();
        // Add some vertices. From above we defined these to be type Integer.
        g.addVertex((Integer)1);
        g.addVertex((Integer)2);
        g.addVertex((Integer)3); 
        // g.addVertex((Integer)1);  // note if you add the same object again nothing changes
        // Add some edges. From above we defined these to be of type String
        // Note that the default is for undirected edges.
        g.addEdge("Edge-A", 1, 2); // Note that Java 1.5 auto-boxes primitives
        g.addEdge("Edge-B", 2, 3);  
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ega.projekt.graph.Graph dataGraph=new ega.projekt.graph.Graph(5,100,295,295);
        if(dataGraph.getEdges().isEmpty()) System.out.println("Error initializing graph");
        DrawGraph graphView = new DrawGraph(dataGraph); // This builds the graph
        // Layout<V, E>, VisualizationComponent<V,E>
        Layout<Node, Edge> layout = new StaticLayout(graphView.drawGraph);
        for(Node n:graphView.drawGraph.getVertices()){
            layout.setLocation(n,new java.awt.geom.Point2D.Double(n.getX(),n.getY()));
        }
        layout.setSize(new Dimension(300,300));
        BasicVisualizationServer<Node,Edge> vv = new BasicVisualizationServer<>(layout);
        vv.setPreferredSize(new Dimension(350,350));       
        // Setup up a new vertex to paint transformer...
        Transformer<Node,Paint> vertexPaint = new Transformer<Node,Paint>() {
            public Paint transform(Node i) {
                return Color.GREEN;
            }
        };
        
        // Set up a new stroke Transformer for the edges
        //float dash[] = {10.0f};
        final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
             BasicStroke.JOIN_MITER);
        Transformer<Edge, Stroke> edgeStrokeTransformer = new Transformer<Edge, Stroke>() {
            public Stroke transform(Edge e) {
                if(e.isMarked()){
                    final Stroke modStroke = new BasicStroke(5.0f, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_MITER);
                    return modStroke;
                }
                return edgeStroke;
            }
        };
    Transformer<Edge, Paint> edgePaint = new Transformer<Edge, Paint>() {
    public Paint transform(Edge e) {
        if(e.isMarked()){
            return Color.RED;
        }
        return Color.BLACK;
    }
};
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.QuadCurve<Node,Edge>());
        vv.getRenderContext().setEdgeLabelTransformer(new Transformer<Edge, String>() {
                public String transform(Edge e) {
                    return (e.getFlowString() + "/" + Integer.toString(e.getCapacity()));
                }
            });
        vv.getRenderContext().setVertexLabelTransformer(new Transformer<Node, String>(){
        public String transform(Node n) {
                    return (Integer.toString(n.getID()));
                }
            });
        //vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);        
        
        JFrame frame = new JFrame("Simple Graph View 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);     
    }
    
    public static BasicVisualizationServer<Node,Edge> generatePanel(ega.projekt.graph.Graph dataGraph, int panelWidth, int panelHeight){
        if(dataGraph.getEdges().isEmpty()) System.out.println("Error initializing graph");
        DrawGraph graphView = new DrawGraph(dataGraph); // This builds the graph
        // Layout<V, E>, VisualizationComponent<V,E>
        Layout<Node, Edge> layout = new StaticLayout(graphView.drawGraph);
        for(Node n:graphView.drawGraph.getVertices()){
            layout.setLocation(n,new java.awt.geom.Point2D.Double(n.getX(),n.getY()));
        }
        layout.setSize(new Dimension(panelWidth,panelHeight));
        BasicVisualizationServer<Node,Edge> vv = new BasicVisualizationServer<>(layout);
        vv.setPreferredSize(new Dimension(panelWidth,panelHeight));       
        // Setup up a new vertex to paint transformer...
        Transformer<Node,Paint> vertexPaint = new Transformer<Node,Paint>() {
            public Paint transform(Node i) {
                return Color.GREEN;
            }
        };  
        final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
             BasicStroke.JOIN_MITER);
        Transformer<Edge, Stroke> edgeStrokeTransformer = new Transformer<Edge, Stroke>() {
            public Stroke transform(Edge e) {
                return edgeStroke;
            }
        };
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.QuadCurve<Node,Edge>());
        vv.getRenderContext().setEdgeLabelTransformer(new Transformer<Edge, String>() {
                public String transform(Edge e) {
                    return (e.getFlowString() + "/" + Integer.toString(e.getCapacity()));
                }
            });
        vv.getRenderContext().setVertexLabelTransformer(new Transformer<Node, String>(){
        public String transform(Node n) {
                    return (Integer.toString(n.getID()));
                }
            });
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
        
        return vv;
    }
    
    //Dummy function
    public static BasicVisualizationServer<Node,Edge> generatePanel(int panelWidth, int panelHeight){
        DrawGraph graphView = new DrawGraph();
        Layout<Node, Edge> layout = new StaticLayout(graphView.drawGraph);
        for(Node n:graphView.drawGraph.getVertices()){
            layout.setLocation(n,new java.awt.geom.Point2D.Double(n.getX(),n.getY()));
        }
        layout.setSize(new Dimension(panelWidth,panelHeight));
        BasicVisualizationServer<Node,Edge> vv = new BasicVisualizationServer<>(layout);
        vv.setPreferredSize(new Dimension(panelWidth,panelHeight));       
        Transformer<Node,Paint> vertexPaint = new Transformer<Node,Paint>() {
            public Paint transform(Node i) {
                return Color.GREEN;
            }
        };  
        final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
             BasicStroke.JOIN_MITER);
        Transformer<Edge, Stroke> edgeStrokeTransformer = new Transformer<Edge, Stroke>() {
            public Stroke transform(Edge e) {
                return edgeStroke;
            }
        };
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
        vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.QuadCurve<Node,Edge>());
        vv.getRenderContext().setEdgeLabelTransformer(new Transformer<Edge, String>() {
                public String transform(Edge e) {
                    return (e.getFlowString() + "/" + Integer.toString(e.getCapacity()));
                }
            });
        vv.getRenderContext().setVertexLabelTransformer(new Transformer<Node, String>(){
        public String transform(Node n) {
                    return (Integer.toString(n.getID()));
                }
            });
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
        return vv;
    }
            
}

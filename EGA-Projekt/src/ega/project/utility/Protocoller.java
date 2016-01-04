/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.project.utility;
import ega.projekt.graph.Edge;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Can
 */
public class Protocoller {
    
    File file;
    int iteration = 0;
    PrintWriter out = null;
    
    public Protocoller(int instance, String algo) {
        int max = Misc.getMaximumExperiment("protocols");
        int max2 = Misc.getMaximumExperiment("protocols/"+max);
        file = new File("protocols/"+max+"/"+max2+"/"+algo+"/"+instance+".csv");
        try {
            out = new PrintWriter(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Protocoller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveIteration(ArrayList<Edge> edges){
        Timestamp timestamp = getTimestamp();
        out.print(iteration++);
        out.print(","+timestamp);
        String edges_string = "'";
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            edges_string+=edge.getID()+" ";
            edges_string+=edge.getFromNode()+" ";
            edges_string+=edge.getToNode()+",";
        }
        edges_string = edges_string.substring(0,edges_string.length()-1);
        edges_string += "'\n";
        out.print(","+edges_string);
        out.flush();
    }
    
    private Timestamp getTimestamp(){
        return (new Timestamp(new Date().getTime()));
    }
    
    public void close(){
        out.close();
    }
}

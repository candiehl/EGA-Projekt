/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.project.utility;

import java.io.File;

/**
 *
 * @author diehl
 */
public class Misc {
    
    public static int getMaximumExperiment(String foldername){
        int max = 0;
        File folder = new File(foldername);
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                int value = Integer.parseInt(fileEntry.getName());
                if (max < value)
                    max = value;
            }
        }
        return max;
    }
    
    public static int createExperimentFolder(String foldername){
       int max = getMaximumExperiment(foldername) + 1;
       String exp_foldername = foldername+"/"+max;
       createFolder(exp_foldername);
       return max;
    }
    
    public static void createTupleFolder(String foldername){
       int max = getMaximumExperiment(foldername) + 1;
       String exp_foldername = foldername+"/"+max;
       createFolder(exp_foldername);
       createSubFolder(exp_foldername);
    }
    
    public static void createFolder(String foldername) {
        new File(foldername).mkdir();
    }
    
     public static void createSubFolder(String foldername){
       createFolder(foldername+"/FORD_FULK");
       createFolder(foldername+"/ED_KARP");
       createFolder(foldername+"/DINIC");
       createFolder(foldername+"/GOLD_TAR");
     }
}

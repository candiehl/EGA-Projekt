/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.project.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Can
 */
public class TupleReader {
    
    ArrayList<int[]> tuples = new ArrayList<int[]>();
    int pointer = 0;
    
    public TupleReader(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNext()){
            String[] line = scanner.next().split(",");
            int tuple[] = new int[]{Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2])};
            tuples.add(tuple);
        }
        scanner.close();
    }
    
    public int[] getTuple(){
        if(pointer < tuples.size()){
            int[] tuple = tuples.get(pointer);
            pointer++;
            return tuple;
        }
        else
            return null;
    }
    
    public boolean hasNextTuple(){
        if(pointer < tuples.size())
            return true;
        else
            return false;
    }
}

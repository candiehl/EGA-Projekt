/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.project.utility;
import java.sql.Timestamp;
import java.util.Date;
/**
 *
 * @author Can
 */
public class Protocoller {
    
    public void saveIteration(){
        Timestamp timestamp = getTimestamp();
    }
    
    private Timestamp getTimestamp(){
        return (new Timestamp(new Date().getTime()));
    }
}

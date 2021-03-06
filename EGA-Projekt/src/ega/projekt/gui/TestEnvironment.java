/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.projekt.gui;

import ega.project.utility.Misc;
import ega.project.utility.Protocoller;
import ega.project.utility.TupleReader;
import ega.projekt.graph.Graph;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Can
 */
public class TestEnvironment extends javax.swing.JFrame {
    
    TupleReader reader;

    /**
     * Creates new form TestEnvironment
     */
    public TestEnvironment() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jCustomTuples = new javax.swing.JTextArea();
        jtupel = new javax.swing.JButton();
        jsmall = new javax.swing.JButton();
        jbig = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jCustomTuples.setColumns(20);
        jCustomTuples.setRows(5);
        jScrollPane1.setViewportView(jCustomTuples);

        jtupel.setText("Submit Tupels");
        jtupel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtupelActionPerformed(evt);
            }
        });

        jsmall.setText("Small Test");
        jsmall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsmallActionPerformed(evt);
            }
        });

        jbig.setText("Big Test");
        jbig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbigActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jsmall, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbig, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtupel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jsmall)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbig)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jtupel))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jsmallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsmallActionPerformed
        runTestEnvironment("small.csv");
    }//GEN-LAST:event_jsmallActionPerformed

    private void jbigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbigActionPerformed
        runTestEnvironment("big.csv");
    }//GEN-LAST:event_jbigActionPerformed

    private void jtupelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtupelActionPerformed
        String text = jCustomTuples.getText();
        if (text.length()!=0){
            text = text.replace(" ", ",");
            try {
                PrintWriter out = new PrintWriter("tests/custom.csv");
                out.println(text);
                out.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TestEnvironment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        runTestEnvironment("custom.csv");
    }//GEN-LAST:event_jtupelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestEnvironment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestEnvironment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestEnvironment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestEnvironment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestEnvironment().setVisible(true);
            }
        });
    }
    
    private void runTestEnvironment(String filename){
        try {
            reader = new TupleReader("tests/"+filename);
            int max_expirement = Misc.createExperimentFolder("protocols");
            while(reader.hasNextTuple()){
                Misc.createTupleFolder("protocols/"+max_expirement);
                int[] tuple = reader.getTuple();
                for(int i=0; i < tuple[2] ;i++){
                    //DEBUG, normally would have created in Algorithm
                    Protocoller protocol = new Protocoller(i,"DINIC");
                    Graph graph = new Graph(tuple[0],tuple[1]);
                    for(int j=0; j < 10; j++){
                        protocol.saveIteration(graph.getEdges());
                    }
                    //Run algorithms for TestEnvironment here
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestEnvironment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea jCustomTuples;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbig;
    private javax.swing.JButton jsmall;
    private javax.swing.JButton jtupel;
    // End of variables declaration//GEN-END:variables
}

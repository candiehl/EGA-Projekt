/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ega.projekt.gui;

import ega.projekt.graph.Properties;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Mike Demele
 */
public class PropertiesWindow extends javax.swing.JFrame {
    Properties props;
    GraphWindow gwin = new GraphWindow();

    /**
     * Creates new form PropertiesWindow
     */
    public PropertiesWindow() {
        try{
            formatter=new MaskFormatter("###########################################");
        }catch(Exception e){
            formatter=null;
            System.out.println("Error setting up formatter");
            return;
        }
        initComponents();
    }
    
    public PropertiesWindow(Properties graph_prop) {
        try{
            formatter=new MaskFormatter("###########################################");
        }catch(Exception e){
            formatter=null;
            System.out.println("Error setting up formatter");
            return;
        }
        props = graph_prop;
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

        jLabel1 = new javax.swing.JLabel();
        jnodes = new javax.swing.JFormattedTextField(formatter);
        jLabel2 = new javax.swing.JLabel();
        jcapacity = new javax.swing.JFormattedTextField(formatter);
        jLabel3 = new javax.swing.JLabel();
        jinstances = new javax.swing.JFormattedTextField(formatter);
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jalgorithm = new javax.swing.JComboBox<>();
        jTestEnv = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Graph Properties");
        setAlwaysOnTop(true);

        jLabel1.setText("# Nodes");

        jnodes.setText("100");
        jnodes.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        jLabel2.setText("Maximum Capacity");

        jcapacity.setText("100");
        jcapacity.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);
        jcapacity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcapacityActionPerformed(evt);
            }
        });

        jLabel3.setText("# Instances (used only for Test Environment)");

        jinstances.setText("1");
        jinstances.setFocusLostBehavior(javax.swing.JFormattedTextField.COMMIT);

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Algorithm");

        jalgorithm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ford-Fulkerson", "Edmonds-Karp", "Dinic", "Goldberg-Tarjan" }));
        jalgorithm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jalgorithmActionPerformed(evt);
            }
        });

        jTestEnv.setText("Test Environment");
        jTestEnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTestEnvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTestEnv))
                        .addComponent(jinstances, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jnodes)
                        .addComponent(jcapacity)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jalgorithm, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jnodes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcapacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jinstances, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jalgorithm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jTestEnv))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int nodes = Integer.parseInt(jnodes.getText().trim());
        int capacity = Integer.parseInt(jcapacity.getText().trim());
        int instances = (Integer.parseInt(jinstances.getText().trim()));
        String algorithm = jalgorithm.getSelectedItem().toString();
        
        Properties.setNodes(nodes);
        Properties.setMax_capacity(capacity);
        Properties.setInstances(instances);
        Properties.setAlgorithm(algorithm);
        
        gwin.dispose();
        gwin = new GraphWindow();
        gwin.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jalgorithmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jalgorithmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jalgorithmActionPerformed

    private void jTestEnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTestEnvActionPerformed
        // TODO add your handling code here:
        TestEnvironment testenv = new TestEnvironment();
        testenv.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jTestEnvActionPerformed

    private void jcapacityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcapacityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcapacityActionPerformed

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
            java.util.logging.Logger.getLogger(PropertiesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PropertiesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PropertiesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PropertiesWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PropertiesWindow().setVisible(true);
            }
        });
    }

    MaskFormatter formatter;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton jTestEnv;
    private javax.swing.JComboBox<String> jalgorithm;
    private javax.swing.JFormattedTextField jcapacity;
    private javax.swing.JFormattedTextField jinstances;
    private javax.swing.JFormattedTextField jnodes;
    // End of variables declaration//GEN-END:variables
}

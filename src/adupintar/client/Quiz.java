/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adupintar.client;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author fendy
 */
public class Quiz extends javax.swing.JFrame {

    /**
     * Creates new form Quiz
     */
    public Quiz() {
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

        jToggleButton1 = new javax.swing.JToggleButton();
        lblQuestionNumber = new javax.swing.JLabel();
        lblQuestion = new javax.swing.JLabel();
        radioBtnA = new javax.swing.JRadioButton();
        radioBtnB = new javax.swing.JRadioButton();
        radioButtonC = new javax.swing.JRadioButton();
        radioBtnD = new javax.swing.JRadioButton();
        btnSubmit = new javax.swing.JButton();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblQuestionNumber.setText("#i Question");

        lblQuestion.setText("Question goes here");

        radioBtnA.setText("Option 1");

        radioBtnB.setText("Option 2");

        radioButtonC.setText("Option 3");

        radioBtnD.setText("Option 4");

        btnSubmit.setText("Submit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuestion)
                    .addComponent(lblQuestionNumber)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(radioBtnD)
                        .addComponent(radioButtonC)
                        .addComponent(radioBtnB)
                        .addComponent(radioBtnA))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnSubmit)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblQuestionNumber)
                .addGap(18, 18, 18)
                .addComponent(lblQuestion)
                .addGap(18, 18, 18)
                .addComponent(radioBtnA)
                .addGap(18, 18, 18)
                .addComponent(radioBtnB)
                .addGap(18, 18, 18)
                .addComponent(radioButtonC)
                .addGap(18, 18, 18)
                .addComponent(radioBtnD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(btnSubmit)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public void showForm(String args[]) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSubmit;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel lblQuestion;
    private javax.swing.JLabel lblQuestionNumber;
    private javax.swing.JRadioButton radioBtnA;
    private javax.swing.JRadioButton radioBtnB;
    private javax.swing.JRadioButton radioBtnD;
    private javax.swing.JRadioButton radioButtonC;
    // End of variables declaration//GEN-END:variables
}

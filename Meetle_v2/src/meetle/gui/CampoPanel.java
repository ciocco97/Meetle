/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetle.gui;

/**
 *
 * @author aless
 */
public class CampoPanel extends javax.swing.JPanel {

    /**
     * Creates new form CampoPanel
     */
    public CampoPanel(String nome, String valore, boolean godmode) {
        initComponents();
        nomeLabel.setText(nome);
        valoreField.setText(valore);
        valoreField.setEnabled(godmode);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomeLabel = new javax.swing.JLabel();
        valoreField = new javax.swing.JTextField();

        setOpaque(false);
        setLayout(new java.awt.GridLayout(1, 0));

        nomeLabel.setBackground(new java.awt.Color(0, 150, 155));
        nomeLabel.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        nomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        nomeLabel.setText("jLabel1");
        nomeLabel.setOpaque(true);
        add(nomeLabel);

        valoreField.setFont(new java.awt.Font("Segoe UI Emoji", 0, 21)); // NOI18N
        valoreField.setForeground(new java.awt.Color(30, 98, 255));
        valoreField.setText("jTextField1");
        valoreField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        add(valoreField);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel nomeLabel;
    private javax.swing.JTextField valoreField;
    // End of variables declaration//GEN-END:variables
}

package meetle.gui;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import meetle.Meetle;


public class SelezioneUtentiFrame extends javax.swing.JFrame {
    private int eID;
    
    private final Meetle meetle;

    public SelezioneUtentiFrame(Meetle meetle, int eID) {
        this.meetle = meetle;
        this.eID = eID;
        initComponents();
        
        getContentPane().setBackground(new Color(0,120,155));
        
        List<String> utentiInvitabili = this.meetle.utentiInvitabili(eID);
        if(utentiInvitabili.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nessun utente da invitare");
            dispose();
            return;
        }
            
        for(String s : utentiInvitabili) {
            JCheckBox cb = new JCheckBox(s);
            cb.setOpaque(false);
            cb.setForeground(Color.white);
            cb.setFont(jPanelUtentiInvitabili.getFont());
            jPanelUtentiInvitabili.add(cb);
        }
        
        pack();
        setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonInvita = new javax.swing.JButton();
        jPanelUtentiInvitabili = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButtonInvita.setText("Invia gli inviti");
        jButtonInvita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInvitaActionPerformed(evt);
            }
        });

        jPanelUtentiInvitabili.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanelUtentiInvitabili.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jPanelUtentiInvitabili.setOpaque(false);
        jPanelUtentiInvitabili.setLayout(new javax.swing.BoxLayout(jPanelUtentiInvitabili, javax.swing.BoxLayout.Y_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(295, Short.MAX_VALUE)
                .addComponent(jButtonInvita)
                .addContainerGap())
            .addComponent(jPanelUtentiInvitabili, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanelUtentiInvitabili, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonInvita)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonInvitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInvitaActionPerformed
        for(Component c : jPanelUtentiInvitabili.getComponents()) {
            JCheckBox cb = (JCheckBox) c;
            if(cb.isSelected())
                this.meetle.mandaInvito(eID, cb.getText());
        }
        dispose();
    }//GEN-LAST:event_jButtonInvitaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonInvita;
    private javax.swing.JPanel jPanelUtentiInvitabili;
    // End of variables declaration//GEN-END:variables
}

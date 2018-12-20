package meetle.GUI;

import javax.swing.UIManager;

public class FinestraPrincipale extends javax.swing.JFrame {

    public FinestraPrincipale() {
        initComponents();
        setLocationRelativeTo(null);
    }

    private void aggiornaBacheca(int tab) {
        jPanelBacheca.updateUI();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelNotifiche = new javax.swing.JPanel();
        jPanelCategorie = new javax.swing.JPanel();
        jButtonPartitaCalcio = new javax.swing.JButton();
        jScrollPaneBacheca = new javax.swing.JScrollPane();
        jPanelBacheca = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanelNotificheLayout = new javax.swing.GroupLayout(jPanelNotifiche);
        jPanelNotifiche.setLayout(jPanelNotificheLayout);
        jPanelNotificheLayout.setHorizontalGroup(
            jPanelNotificheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanelNotificheLayout.setVerticalGroup(
            jPanelNotificheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelNotifiche, java.awt.BorderLayout.LINE_END);

        jButtonPartitaCalcio.setText("Partita di Calcio");
        jButtonPartitaCalcio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPartitaCalcioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCategorieLayout = new javax.swing.GroupLayout(jPanelCategorie);
        jPanelCategorie.setLayout(jPanelCategorieLayout);
        jPanelCategorieLayout.setHorizontalGroup(
            jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategorieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonPartitaCalcio)
                .addContainerGap(639, Short.MAX_VALUE))
        );
        jPanelCategorieLayout.setVerticalGroup(
            jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCategorieLayout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(jButtonPartitaCalcio)
                .addContainerGap())
        );

        getContentPane().add(jPanelCategorie, java.awt.BorderLayout.PAGE_START);

        jScrollPaneBacheca.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanelBacheca.setLayout(new javax.swing.BoxLayout(jPanelBacheca, javax.swing.BoxLayout.Y_AXIS));
        jScrollPaneBacheca.setViewportView(jPanelBacheca);

        getContentPane().add(jScrollPaneBacheca, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPartitaCalcioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPartitaCalcioActionPerformed
        aggiornaBacheca(7);
    }//GEN-LAST:event_jButtonPartitaCalcioActionPerformed

    public static void main(String args[]) {
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinestraPrincipale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FinestraPrincipale().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPartitaCalcio;
    private javax.swing.JPanel jPanelBacheca;
    private javax.swing.JPanel jPanelCategorie;
    private javax.swing.JPanel jPanelNotifiche;
    private javax.swing.JScrollPane jScrollPaneBacheca;
    // End of variables declaration//GEN-END:variables
}

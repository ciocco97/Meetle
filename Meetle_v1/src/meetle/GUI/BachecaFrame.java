package meetle.GUI;

import meetle.Bacheca;
import meetle.Evento;

public class BachecaFrame extends javax.swing.JFrame {

    Bacheca b;
    
    public BachecaFrame() {
        initComponents();
        setLocationRelativeTo(null);
        b = new Bacheca();
    }

    private void aggiornaBacheca(int tab) {
        for(Evento e: b.getEventi())
            jPanelBacheca.add(new EventoPanel(e));   
        
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

        javax.swing.GroupLayout jPanelCategorieLayout = new javax.swing.GroupLayout(jPanelCategorie);
        jPanelCategorie.setLayout(jPanelCategorieLayout);
        jPanelCategorieLayout.setHorizontalGroup(
            jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCategorieLayout.createSequentialGroup()
                .addComponent(jButtonPartitaCalcio)
                .addGap(0, 649, Short.MAX_VALUE))
        );
        jPanelCategorieLayout.setVerticalGroup(
            jPanelCategorieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCategorieLayout.createSequentialGroup()
                .addGap(0, 77, Short.MAX_VALUE)
                .addComponent(jButtonPartitaCalcio))
        );

        getContentPane().add(jPanelCategorie, java.awt.BorderLayout.PAGE_START);

        jScrollPaneBacheca.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanelBacheca.setLayout(new javax.swing.BoxLayout(jPanelBacheca, javax.swing.BoxLayout.Y_AXIS));
        jScrollPaneBacheca.setViewportView(jPanelBacheca);

        getContentPane().add(jScrollPaneBacheca, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Setting the Windows look and feel (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BachecaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new BachecaFrame().setVisible(true);
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
